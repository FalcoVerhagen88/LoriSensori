// ---------------------------------------- Incudes ----------------------------------------//
#include <lmic.h>
#include <hal/hal.h>
//#include <SPI.h>

#include "Sensoren.h"
#include "Actuatoren.h"
#include "Uplink.h"
#include "Downlink.h"


Actuatoren A;
Downlink D;
Sensoren S;
Uplink U;

// ---------------------------------------- Credentials voor ABP bij The Things Network ----------------------------------------//
static const u1_t NWKSKEY[16] = { 0x04, 0xE6, 0x24, 0xC6, 0x5C, 0x05, 0x95, 0x33, 0xCC, 0xBB, 0xE8, 0xC5, 0xFE, 0x14, 0x22, 0xDB };
static const u1_t APPSKEY[16] = { 0xE1, 0x12, 0xDF, 0x98, 0x25, 0xA5, 0x36, 0xF8, 0x4D, 0x9E, 0xF7, 0x4C, 0xD3, 0x6F, 0x9A, 0xB0 };
static const u4_t DEVADDR = 0x26011C34;

static osjob_t alivebericht;
static osjob_t bericht;

int counterbericht = 0;


// ---------------------------------------- Plan TX elke 180 seconden ---------------------------------------- //
const unsigned ALIVE_BERICHT_INTERVAL = 14400; // moet 14400 zijn -> 4 uur
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
   D.ontvangDownlink(&S,&A);
   S.GPSmeting();       // moet deze hier op deze manier?
   Alarm.delay(1000); // jee nu werkt timealarms wel
   os_setTimedCallback(&bericht, os_getTime()+sec2osticks(BERICHT_INTERVAL), berichtfunctie);        //Schedule next transmission --> ack?
}

// ---------------------------------------- Stuur bericht een keer in de 3 minuten en alive een keer in de 3 uur---------------------------------------- //
void berichtfunctie (osjob_t* j)

{
  counterbericht++;
  
  if (LMIC.opmode & OP_TXRXPEND)                                                                                     // Check if there is not a current TX/RX job running
    {                                             
      Serial.println(F("Er wordt al een ander bericht verstuurd = 4 uur"));
    } 
    else if(counterbericht > 2)
    {       
      LMIC_setTxData2(1,(uint8_t*)&U.maakAliveBericht(&S), U.maakAliveBericht(&S).berichtLengte, 0);                                                         // (port 1, bericht, grootte van bericht, unconfirmed)
      Serial.println(F("Verstuurt alive bericht"));
      os_setTimedCallback(j, os_getTime()+sec2osticks(BERICHT_INTERVAL), berichtfunctie);                              // tijdsinterval bericht verzenden
      counterbericht = 0;                                                                                            // reset counter
    }
    else if (counterbericht != 0)
    {
      LMIC_setTxData2(1,(uint8_t*)U.maakBericht(&S).berichtPointer, U.maakBericht(&S).berichtLengte, 0);       // (port 1, 2 bytes, unconfirmed) // bepalen hoe groot hetgeen is waar de pointer(uplink.kiesBericht() naar wijst
      Serial.println("pointer");
      Serial.println(*U.maakBericht(&S).berichtPointer);
      Serial.println("lengte");
      Serial.println(U.maakBericht(&S).berichtLengte );
      os_setTimedCallback(j, os_getTime()+sec2osticks(BERICHT_INTERVAL), berichtfunctie);                            // tijdsinterval bericht verzenden
    }
   // Next TX is scheduled after TX_COMPLETE event.
}


// ---------------------------------------- Setup ----------------------------------------//
void setup() 
{
  Serial.begin(9600);                                   // initialisatie van de seriële poort
  counterbericht = 0;
  S.SETUP();
  A.SETUP();
  U.setVorigeSlotstand(&S);
  U.setVorigeDieselAlarmniveau(&S);  
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
