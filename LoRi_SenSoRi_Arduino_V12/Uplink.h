#ifndef Uplink_h
#define Uplink_h

#include <lmic.h>
#include <hal/hal.h>
#include <arduino.h>
#include "Sensoren.h"



// ------------------------- Tankbericht --------------------------//
 typedef struct tankUl
 {
    byte ulId;                // Identificatienummer bericht = 0x00
    byte dieselniveau;        // 0…1023 == 0%...100%
    byte slotstand;           // 0 = dicht, 1 = open
    byte accuspanning;        // 765…920 == 0%...100%
    byte zonnepaneel;         // watt/hour
    byte latGraden;           // latitude graden
    byte latMinuten;          // latitude minuten
    byte latSeconden;         // latitude seconden
    byte latTiendeSeconden;   // latitude 10e boogseconde
    byte lonGraden;           // longitude graden
    byte lonMinuten;          // longitude minuten
    byte lonSeconden;         // longitude seconden
    byte lonTiendeSeconden;   // longitude 10e boogseconde
    uint8_t berichtLengte;        // lengte van bericht in bytes
}TankUl;


// -------- Alarm dieselniveau te laag -----------------------------//
typedef struct aTankUl
{
    byte ulId;                // Identificatienummer bericht = 0x01
    byte dieselniveau;        // 0…1023 == 0%...100%
    uint8_t berichtLengte;        // lengte van bericht in bytes
}ATankUl; 


// --------- Alarm dieselniveauverlaging tijdens sluitingstijd --------------//
typedef struct aDieselniveauUl
{
    byte ulId;                // Identificatienummer bericht = 0x02
    byte dieselniveau;        // 0…1023 == 0%...100%
    byte slotstand;           // 0 = dicht, 1 = open
    byte latGraden;           // latitude graden
    byte latMinuten;          // latitude minuten
    byte latSeconden;         // latitude seconden
    byte latTiendeSeconden;   // latitude 10e boogseconde
    byte lonGraden;           // longitude graden
    byte lonMinuten;          // longitude minuten
    byte lonSeconden;         // longitude seconden
    byte lonTiendeSeconden;   // longitude 10e boogseconde
    uint8_t berichtLengte;        // lengte van bericht in bytes
}ADieselniveauUl; 


// ---------------- Alarm accuniveau te laag ---------------------------//
typedef struct aAccuniveauUl
{
    byte ulId;                // Identificatienummer bericht = 0x03
    byte accuspanning;        // 765…920 == 0%...100%
    uint8_t berichtLengte;        // lengte van bericht in bytes
}AAccuniveauUl;


// --------------- Bericht ack slotstand gewijzigd ----------------------//
typedef struct ASlotstandWijziging
{
    byte ulId;                     // Identificatienummer bericht = 0x04
    byte slotStand;					// Slotstand 00 = dicht, 1 = open, 2 = geen feedback
    uint8_t berichtLengte;         // lengte van bericht in bytes
}ASlotstandW;


// -------------- Bericht ack dieselniveau gewijzigd -------------------//
typedef struct ackDieselniveauWijziging
{
    byte ulId;                    // Identificatienummer bericht = 0x05
    uint8_t berichtLengte;        // lengte van bericht in bytes
}AckDieselniveauW;

// -------------- Bericht ack Sluitingstijd gewijzigd -------------------//
typedef struct ackSluitingstijdWijziging
{
    byte ulId;                // Identificatienummer bericht = 0x06
    uint8_t berichtLengte;        // lengte van bericht in bytes
}AckSluitingstijdW;

// -------------- Bericht ack Openingstijd gewijzigd -------------------//
typedef struct ackOpeningstijdWijziging
{
    byte ulId;                // Identificatienummer bericht = 0x07
    uint8_t berichtLengte;        // lengte van bericht in bytes
}AckOpeningstijdW;


// -------------- Bericht ack Openingstijd gewijzigd -------------------//
typedef struct ackSlotstandWijziging
{
    byte ulId;                // Identificatienummer bericht = 0x08
    byte slotStand;				// Slotstand 00 = dicht, 1 = open, 2 = geen feedback
    uint8_t berichtLengte;        // lengte van bericht in bytes
}AckSlotstandW;



// ------------- Checkbericht gewijzigd ----------------//
typedef struct checkBericht
{
    byte ulId;                // Identificatienummer bericht = 0x09
    uint8_t berichtLengte;        // lengte van bericht in bytes
}CheckBericht;

    // ---------------------------------------- class uplink ----------------------------------------//
class Uplink
{
  private:
  // ------------------ private const. int Id's ---------------//
  #define ulIdTankUl 0x00
  #define ulIdATankenUl 0x01
  #define ulIdADieselniveauUl 0x02
  #define ulIdAAccuniveauUl 0x03
  #define ulIdSlotstandWijziging 0x04 // ack slotstand wijziging
  #define ackIdDieselAlarmniveauWijziging 0x05 // ack dieselalarmniveau wijziging
  #define ackIdOpeningstijdWijziging 0x06 
  #define ackIdSluitingstijdWijziging 0x07
  #define ackIdSlotstandwijziging 0x08
  #define ulIdCheck 0x09

  // slotstandwijziging is alarm dat slot niet wordt opeggebroken

    // ------------- private const. berichtlengte ---------------//
  #define ALIVELENGTE 13 // lengte van het alive bericht
  #define ALARMTANKENLENGTE 2 // lengte van het laag niveau alarm bericht
  #define ALARMDIEFSTALLENGTE 11 // lengte van het bericht bij een dieselniveauverlaging na sluitingstijd
  #define ALARMACCUSPANNINGLENGTE 2 // lengte van het bericht bij een te lage accuspanning
  #define WIJZIGINGSLOTSTANDLENGTE 1 // lengte van het bericht bij een wijziging van de slotstand
  #define ACKWIJZIGINGALARMNIVEAUDIESEL 1 //lengte van beericht na wijziging minimum diesel niveau in tank
  #define ACKWIJZIGINGSLUITINGSTIJD 1 // lengte van het bericht na wijziging sluitingstijd
  #define ACKWIJZIGINGOPENINGSTIJD 1 // lengte van het bericht na wijziging sluitingstijd
  #define ACKSLOTSTANDWIJZIGING 2 // lengte van het bericht na wijziging sluitingstijd
  #define CHECKBERICHTLENGTE 1 // lengte van het bericht (checkbericht) dat elke x minuten wordt verstuurd om te luisteren naar de gateway

  int VorigeSlotstand;
  int vorigeDieselAlarmniveau;
  
    // -------- instanstieren private structs -----------//  
  TankUl alive;
  ATankUl tankenAlarm;
  ADieselniveauUl verlagingDieselniveauAlarm;
  AAccuniveauUl accuniveauAlarm;
  ASlotstandW slotstandWijziging;
  AckDieselniveauW alarmniveauDieselWijziging;
  AckSluitingstijdW ackWSluitingstijd;
  AckOpeningstijdW ackWOpeningstijd;
  AckSlotstandW ackSlotstandWijziging;
  CheckBericht checkBericht;

    // ------------ Class Uplink prive methodes -------------//
  void berichtTankUl(TankUl *p, Sensoren *s);
  void berichtATankUl(ATankUl *p, Sensoren *s);
  void berichtADieselniveauUl(ADieselniveauUl *p, Sensoren *s);
  void berichtAAccuniveauUl(AAccuniveauUl *p, Sensoren *s);
  void berichtAslotstandW(ASlotstandW *p, Sensoren *s);
  void ackDieselAlarmniveauW(AckDieselniveauW *p);
  void ackSluitingstijdWijziging(AckSluitingstijdW *p);
  void ackOpeningstijdWijziging(AckOpeningstijdW *p);
  void ackSlotstandW(AckSlotstandW *p, Sensoren *s);
  void BerichtCheck(CheckBericht *p);

  
  public:
  // ------------ Class Uplink constructor --------------//
  Uplink(Sensoren *s);
  // ------------ Class Uplink publieke methodes -------//
  TankUl getAliveBericht(Sensoren *s);
  ATankUl getDieselniveauAlarm(Sensoren *s);
  ADieselniveauUl getDiefstalAlarm(Sensoren *s);
  AAccuniveauUl getAccuniveauAlarm(Sensoren *s);
  ASlotstandW getASlotstandW(Sensoren *s);
  AckDieselniveauW getAckDieselAlarmniveauW();
  AckSluitingstijdW getAckSluitingstijdW();
  AckOpeningstijdW getAckOpeningstijdW();
  AckSlotstandW getAckSlotstandW(Sensoren *s);
  CheckBericht getCheckbericht();
  void setVorigeSlotstand(Sensoren *s);
  void setVorigeDieselAlarmniveau(Sensoren *s);

};
// *************************************** einde Class uplink definitie ***************************************//
#endif //Uplink.h
