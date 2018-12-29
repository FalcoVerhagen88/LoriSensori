// ---------------------------------------- Incudes ----------------------------------------//
#include <lmic.h>
#include <hal/hal.h>
//#include <SPI.h>

#include "Sensoren.h"
#include "Actuatoren.h"
#include "Uplink.h"
#include "Downlink.h"

#define TANKENALARMTRIGGER 1
#define ACCUALARMTRIGGER 1
#define DIEFSTALALARMTRIGGER 1
#define SLOTSTANDALARMTRIGGER 1
#define CHECKBERICHTTRIGGER 3			// elke 10 x CHECKBERICHTTRIGGER  seconden wordt dit bericht gestuurd, dus bij 6 is het eke 60 seconden
#define ALIVEBERICHTTRIGGER 6			// elke 10 x ALIVEBERICHTTRIGGER  seconden wordt dit bericht gestuurd, dus bij 6 is het eke 60 seconden, standaard 2 uur 7200 sec
#define TANKENALARMALGEGEVEN 1
#define ACCUALARMALGEGEVEN 1
#define DIEFSTALALARMALGEGEVEN 1
#define SLOTSTANDALARMALGEGEVEN 1




Actuatoren A;
Downlink D;
Sensoren S;
Uplink U(&S);

// ---------------------------------------- Credentials voor ABP bij The Things Network ----------------------------------------//
static const u1_t NWKSKEY[16] = { 0x04, 0xE6, 0x24, 0xC6, 0x5C, 0x05, 0x95, 0x33, 0xCC, 0xBB, 0xE8, 0xC5, 0xFE, 0x14, 0x22, 0xDB };
static const u1_t APPSKEY[16] = { 0xE1, 0x12, 0xDF, 0x98, 0x25, 0xA5, 0x36, 0xF8, 0x4D, 0x9E, 0xF7, 0x4C, 0xD3, 0x6F, 0x9A, 0xB0 };
static const u4_t DEVADDR = 0x26011C34;


static osjob_t bericht;

int counterbericht = 0;
int alarmBerichtAccu =  0;
int alarmBerichtDiesel = 0;
int alarmBerichtDiefstal = 0;
int alarmBerichtSlotstand = 0;

// ---------------------------------------- Plan TX elke 180 seconden ---------------------------------------- //
const unsigned BERICHT_INTERVAL = 60;

   
// ---------------------------------------- Pin mapping Dragino LORA GPS Shield ----------------------------------------//
const lmic_pinmap lmic_pins = {
    .nss = 10,
    .rxtx = LMIC_UNUSED_PIN,
    .rst = 9,
    .dio = {2, 6, 7},
};


// ---------------------------------------- onEvent ----------------------------------------// 
void onEvent (ev_t ev) 
{
     Alarm.delay(1000); // jee nu werkt timealarms welAlarm.delay(1000); // jee nu werkt timealarms wel
     S.GPSmeting();       // moet deze hier op deze manier?
      D.ontvangDownlink(&S, &A, &U);

      //Serial.println("hoe vaak gebeurt dit?");


}

// ---------------------------------------- Stuur bericht een keer in de 3 minuten en alive een keer in de 3 uur---------------------------------------- //
void berichtfunctie (osjob_t* j)

{
  counterbericht++;
  
  if (LMIC.opmode & OP_TXRXPEND)                                                                                     // Check if there is not a current TX/RX job running
    {                                             
      Serial.println(F("Er wordt al een ander bericht verstuurd = 4 uur"));
    } 
    
    if (S.dieselalarmNiveau() == TANKENALARMTRIGGER && alarmBerichtDiesel != TANKENALARMALGEGEVEN && counterbericht != ALIVEBERICHTTRIGGER) //check diesel niveau
    {
		LMIC_setTxData2(1,(uint8_t*)&U.getDieselniveauAlarm(&S), U.getDieselniveauAlarm(&S).berichtLengte, 0);
    	alarmBerichtDiesel = 1;
    	Serial.println("verstuurt tanken alarm");
       os_setTimedCallback(j, os_getTime()+sec2osticks(BERICHT_INTERVAL), berichtfunctie);       //set tijdsinterval uitvoering bericht
     return ;
    }
    
    
    if (S.accuAlarm(S.accuniveaumeting()) == ACCUALARMTRIGGER && alarmBerichtAccu != ACCUALARMALGEGEVEN && counterbericht != ALIVEBERICHTTRIGGER) //check accu niveau
    {
		LMIC_setTxData2(1,(uint8_t*)&U.getAccuniveauAlarm(&S), U.getAccuniveauAlarm(&S).berichtLengte, 0);
     	alarmBerichtAccu = 1;
     	Serial.println("verstuurt accu alarm");
        os_setTimedCallback(j, os_getTime()+sec2osticks(BERICHT_INTERVAL), berichtfunctie);        //set tijdsinterval uitvoering bericht
        return;
    }
    
    
    if (S.diefstalAlarm() == DIEFSTALALARMTRIGGER && alarmBerichtDiefstal != DIEFSTALALARMALGEGEVEN && counterbericht != ALIVEBERICHTTRIGGER) //check diefstal
    {
    	LMIC_setTxData2(1,(uint8_t*)&U.getDiefstalAlarm(&S), U.getDiefstalAlarm(&S).berichtLengte, 0);
     	alarmBerichtDiefstal = 1;
     	Serial.println("verstuurt diefstal alarm");
        os_setTimedCallback(j, os_getTime()+sec2osticks(BERICHT_INTERVAL), berichtfunctie);        //set tijdsinterval uitvoering bericht
        return;
    }
    
    if (S.slotstandAlarm() == SLOTSTANDALARMTRIGGER && alarmBerichtSlotstand != SLOTSTANDALARMALGEGEVEN && counterbericht != ALIVEBERICHTTRIGGER) //check slotstand
    {
    	LMIC_setTxData2(1,(uint8_t*)&U.getASlotstandW(&S), U.getASlotstandW(&S).berichtLengte, 0);
     	alarmBerichtSlotstand = 1;
     	Serial.println("verstuurt slotstand alarm");
         os_setTimedCallback(j, os_getTime()+sec2osticks(BERICHT_INTERVAL), berichtfunctie);       //set tijdsinterval uitvoering bericht
         return;
    }
    
    if(counterbericht == ALIVEBERICHTTRIGGER)
    {       
      LMIC_setTxData2(1,(uint8_t*)&U.getAliveBericht(&S), U.getAliveBericht(&S).berichtLengte, 0);                                                         // (port 1, bericht, grootte van bericht, unconfirmed)
      Serial.println("Verstuurt alive bericht");
      //alarmBerichtDiesel = 0;
      //alarmBerichtAccu = 0;
      //alarmBerichtDiefstal = 0;
      //alarmBerichtSlotstand = 0;
      counterbericht = 0;                                                                                            // reset counter
    }
    
    if (counterbericht % CHECKBERICHTTRIGGER == 0 && counterbericht != 0 ) // modulo berekening
    {
      LMIC_setTxData2(1,(uint8_t*)&U.getCheckbericht(), U.getCheckbericht().berichtLengte, 0);       // (port 1, 2 bytes, unconfirmed) // bepalen hoe groot hetgeen is waar de pointer(uplink.kiesBericht() naar wijst
      Serial.println("verstuurt checkbericht");
      //alarmBerichtDiesel = 0;
      //alarmBerichtAccu = 0;
     // alarmBerichtDiefstal = 0;
     // alarmBerichtSlotstand = 0;
    }

      //Serial.print("alarmberichtdiesel : ");
      //Serial.println(alarmBerichtDiesel);
      //Serial.print("alarmBerichtAccu : ");
      //Serial.println(alarmBerichtAccu);
      //Serial.print("alarmBerichtDiefstal : ");
      //Serial.println(alarmBerichtDiefstal);
      //Serial.print("alarmBerichtSlotstand : ");
      //Serial.println(alarmBerichtSlotstand);

     os_setTimedCallback(j, os_getTime()+sec2osticks(BERICHT_INTERVAL), berichtfunctie);        //set tijdsinterval uitvoering bericht

   // Next TX is scheduled after TX_COMPLETE event.
}

// ---------------------------------------- Setup ----------------------------------------//
void setup() 
{
  Serial.begin(9600);                                   // initialisatie van de seriële poort
  S.SETUP();
  A.SETUP();
  os_init();                                            // initilisatie van LMIC
  LMIC_reset();                                         // Resetten van de MAC staat. Sessie en lopende data transfers worden verworpen 
  LMIC_setClockError(MAX_CLOCK_ERROR * 1 / 100);        // Let LMIC compensate for +/- 1% clock error
  LMIC_setSession (0x1, DEVADDR, NWKSKEY, APPSKEY);     // Set parameters voor sessie (ABP)
  LMIC_setLinkCheckMode(0);                             // Disable link check validation
  LMIC.dn2Dr = DR_SF9;                                  // TTN uses SF9 for its RX2 window.
  LMIC_setDrTxpow(DR_SF7,14);                           // Set data rate and transmit power for uplink (note: txpow seems to be ignored by the library) --> SF7!
  berichtfunctie(&bericht);
}


// ---------------------------------------- Main ----------------------------------------//
void loop()
{

  os_runloop_once();

}
