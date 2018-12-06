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


// --------------- Bericht slotstand gewijzigd ----------------------//
typedef struct wSlotstandUl
{
    byte ulId;                // Identificatienummer bericht = 0x04
    byte slotstand;           // 0 = dicht, 1 = open
    uint8_t berichtLengte;        // lengte van bericht in bytes
}WSlotstandUl; 


// -------------- Bericht dieselniveau gewijzigd -------------------//
typedef struct wDieselniveauUl
{
    byte ulId;                // Identificatienummer bericht = 0x005
    byte dieselniveau;        // 0…1023 == 0%...100%
    uint8_t berichtLengte;        // lengte van bericht in bytes
}WDieselniveauUl;


// ------------- Bericht dieselniveau gewijzigd ----------------//
typedef struct checkBericht
{
    byte ulId;                // Identificatienummer bericht = 0x006
    uint8_t berichtLengte;        // lengte van bericht in bytes
}CheckBericht;


// --------------- lengte en pointer in struct ---------------//
typedef struct berichtPointerLengte
{
    uint8_t *berichtPointer;                // pointer naar bericht
    uint8_t berichtLengte;                 // lengte van het bericht
}BerichtPointerLengte;


    // ---------------------------------------- class uplink ----------------------------------------//
class Uplink
{
  private:
  // ------------------ private const. int Id's ---------------//
  #define ulIdTankUl 0x00
  #define ulIdATankenUl 0x01
  #define ulIdADieselniveauUl 0x02
  #define ulIdAAccuniveauUl 0x03
  #define ulIdWSlotstandUl 0x04
  #define ulIdWDieselniveauUl 0x05
  #define ulIdCheck 0x06

    // ------------- private const. berichtlengte ---------------//
  #define ALIVELENGTE 13 // lengte van het alive bericht
  #define ALARMTANKENLENGTE 2 // lengte van het laag niveau alarm bericht
  #define ALARMDIEFSTALLENGTE 11 // lengte van het bericht bij een dieselniveauverlaging na sluitingstijd
  #define ALARMACCUSPANNING 2 // lengte van het bericht bij een te lage accuspanning
  #define WIJZIGINGSLOTSTANDLENGTE 2 // lengte van het bericht bij een wijziging van de slotstand
  #define WIJZIGINGALARMNIVEAUDIESEL 2 //lengte van beericht na wijziging minimum diesel niveau in tank
  #define CHECKBERICHTLENGTE 1 // lengte van het bericht (checkbericht) dat elke x minuten wordt verstuurd om te luisteren naar de gateway

  int VorigeSlotstand;
  int vorigeDieselAlarmniveau;
  
    // -------- instanstieren private structs -----------//  
  TankUl alive;
  ATankUl tankenAlarm;
  ADieselniveauUl verlagingDieselniveauAlarm;
  AAccuniveauUl accuniveauAlarm;
  WSlotstandUl slotstandWijziging;
  WDieselniveauUl alarmniveauDieselWijziging;
  BerichtPointerLengte berichtPointerLengte;
  CheckBericht checkBericht;

    // ------------ Class Uplink prive methodes -------------//
  void berichtTankUl(TankUl *p, Sensoren *s);
  void berichtATankUl(ATankUl *p, Sensoren *s);
  void berichtADieselniveauUl(ADieselniveauUl *p, Sensoren *s);
  void berichtAAccuniveauUl(AAccuniveauUl *p, Sensoren *s);
  void berichtWSlotstandUl(WSlotstandUl *p, Sensoren *s);
  void berichtWDieselniveauUl(WDieselniveauUl *p,Sensoren *s);
  void BerichtCheck(CheckBericht *p);

  
  public:
  // ------------ Class Uplink constructor --------------//
  Uplink();
  // ------------ Class Uplink publieke methodes -------//
  BerichtPointerLengte maakBericht(Sensoren *s);
  TankUl maakAliveBericht(Sensoren *s);
  WSlotstandUl getSlotstandWijziging(Sensoren *s);
  WDieselniveauUl getAlarmNiveauDieselWijziging(Sensoren *s);
  void setVorigeSlotstand(Sensoren *S);
  void setVorigeDieselAlarmniveau(Sensoren *S);
};
// *************************************** einde Class uplink definitie ***************************************//
#endif //Uplink.h
