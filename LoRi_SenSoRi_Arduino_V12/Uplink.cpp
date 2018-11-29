#include "Uplink.h"

// ---------------------------------------- Constructor Uplink -----------------------------------------//
Uplink::Uplink(void)
{
  // Constructor body Uplink, void dus niets nodig
}

void Uplink::setVorigeSlotstand(Sensoren *S)
{
  VorigeSlotstand = S->slotstandmeting();
}

void Uplink::setVorigeDieselAlarmniveau(Sensoren *S)
{
  vorigeDieselAlarmniveau = S->getAlarmniveauDiesel();
}
// ---------------------------------------- Opstellen tankbericht ----------------------------------------//
void Uplink::berichtTankUl(TankUl *p, Sensoren *s) 
{
p->ulId = ulIdTankUl;
p->dieselniveau = s->dieselniveaumeting();
p->slotstand = s->slotstandmeting();
p->accuspanning = s->accuniveaumeting();
p->zonnepaneel = s->vermogenZonnepaneelmeting();
p->latGraden = s->getGpsFix().latitudeDMS.degrees;
p->latMinuten = s->getGpsFix().latitudeDMS.minutes;
p->latSeconden = s->getGpsFix().latitudeDMS.seconds_whole;
p->latTiendeSeconden = highByte(s->getGpsFix().latitudeDMS.seconds_frac);
p->lonGraden = s->getGpsFix().longitudeDMS.degrees;
p->lonMinuten = s->getGpsFix().longitudeDMS.minutes;
p->lonSeconden = s->getGpsFix().longitudeDMS.seconds_whole;
p->lonTiendeSeconden = highByte(s->getGpsFix().longitudeDMS.seconds_frac);
p->berichtLengte = 13;
}
// ---------------------------------------- Opstellen alarm bij te laag dieselniveau ----------------------------------------//
void Uplink::berichtATankUl(ATankUl *p, Sensoren *s) 
{
p->ulId = ulIdATankenUl;
p->dieselniveau = s->dieselniveaumeting();
p->berichtLengte = 2;
}


// ---------------------------------------- Opstellen alarm bij dieselniveauverlaging buiten openingstijd ----------------------------------------//
void Uplink::berichtADieselniveauUl(ADieselniveauUl *p, Sensoren *s) 
{
p->ulId = ulIdADieselniveauUl;
p->dieselniveau = s->dieselniveaumeting();
p->slotstand = s->slotstandmeting();
p->latGraden = s->getGpsFix().latitudeDMS.degrees;
p->latMinuten = s->getGpsFix().latitudeDMS.minutes;
p->latSeconden = s->getGpsFix().latitudeDMS.seconds_whole;
p->latTiendeSeconden = highByte(s->getGpsFix().latitudeDMS.seconds_frac);
p->lonGraden = s->getGpsFix().longitudeDMS.degrees;
p->lonMinuten = s->getGpsFix().longitudeDMS.minutes;
p->lonSeconden = s->getGpsFix().longitudeDMS.seconds_whole;
p->lonTiendeSeconden = highByte(s->getGpsFix().longitudeDMS.seconds_frac);
p->berichtLengte = 11;
}


// ---------------------------------------- Opstellen alarm bij een te laag accuniveau ----------------------------------------//
void Uplink::berichtAAccuniveauUl(AAccuniveauUl *p, Sensoren *s) 
{
p->ulId = ulIdAAccuniveauUl;
p->accuspanning = s->accuniveaumeting();
p->berichtLengte = 2;
}

// ---------------------------------------- Opstellen alarm bij een wijziging van de slotstand ----------------------------------------//
void Uplink::berichtWSlotstandUl(WSlotstandUl *p, Sensoren *s) 
{
p->ulId = ulIdWSlotstandUl;
p->slotstand = s->slotstandmeting();
p->berichtLengte = 2;
}


// ---------------------------------------- Opstellen melding bij wijziging van de melding dieselalarmniveau ----------------------------------------//
void Uplink::berichtWDieselniveauUl(WDieselniveauUl *p, Sensoren *s) 
{
p->ulId = ulIdWDieselniveauUl;
p->dieselniveau = s->getAlarmniveauDiesel();
p->berichtLengte = 2;
}


// ---------------------------------------- Opstellen melding Checkbericht ----------------------------------------//
void Uplink::BerichtCheck(CheckBericht *p)
{
    p->ulId = ulIdCheck;                  // uid checkbericht
    p->berichtLengte = 1;                 // lengte van het bericht
}


// ---------------------------------------- Kies bericht n.a.v. tankmetingen ----------------------------------------//
BerichtPointerLengte Uplink::maakBericht(Sensoren *s)   //bekijkt welk bericht/status tank
{
    if (s->accuAlarm(s->accuniveaumeting()) == 01) // als klasse meegeven, parameter
  {
    berichtAAccuniveauUl(&accuniveauAlarm, s);
    berichtPointerLengte.berichtLengte = accuniveauAlarm.berichtLengte;
    berichtPointerLengte.berichtPointer = (uint8_t*)&accuniveauAlarm;
    return berichtPointerLengte;
  }

    else if(s->diefstalAlarm() == 01) // als dieselniveau vorig < dieselniveau huidig en het slot is dicht dan is er iets aan de hand
  {
    berichtADieselniveauUl(&verlagingDieselniveauAlarm, s);
    berichtPointerLengte.berichtLengte = verlagingDieselniveauAlarm.berichtLengte;
    berichtPointerLengte.berichtPointer = (uint8_t*)&verlagingDieselniveauAlarm;
    return berichtPointerLengte;
  }
  
  else if (s->dieselalarmNiveau() == 01) // als dieselniveau vorig < alarmniveau diesel dan stuur dit bericht zodat de tankwagen ingepland kan worden
  {
    berichtATankUl(&tankenAlarm, s);
    berichtPointerLengte.berichtLengte = tankenAlarm.berichtLengte;
    berichtPointerLengte.berichtPointer = (uint8_t*)&tankenAlarm;
    return berichtPointerLengte;
  }

  else if (s->slotstandmeting() != VorigeSlotstand)                     // als de slotstand wordt gewijzigd stuurt dan dit bericht
  {
    berichtWSlotstandUl(&slotstandWijziging, s);
    berichtPointerLengte.berichtLengte = slotstandWijziging.berichtLengte;
    berichtPointerLengte.berichtPointer = (uint8_t*)&slotstandWijziging;
    return berichtPointerLengte;
  }

    else if (s->getAlarmniveauDiesel() != vorigeDieselAlarmniveau)                 // Als het dieselalarmniveau wordt gewijzigd stuurt dan dit bericht
  {
    berichtWDieselniveauUl(&alarmniveauDieselWijziging, s);
    berichtPointerLengte.berichtLengte = alarmniveauDieselWijziging.berichtLengte;
    berichtPointerLengte.berichtPointer = (uint8_t*)&alarmniveauDieselWijziging;
    return berichtPointerLengte;
  }

    else 
  {
    BerichtCheck(&checkBericht);                                    //Alles is ok stuurt dan dit bericht
    berichtPointerLengte.berichtLengte = checkBericht.berichtLengte;
    berichtPointerLengte.berichtPointer = (uint8_t*)&checkBericht;
    return berichtPointerLengte;
  }
}

/*
 * 1 accuiveau
 * 2 diefstal
 * 3 tanken
 * 4 slotstand
 * 5 dieselniveau gewijzigd
 */

// ---------------------------------------- Kies bericht n.a.v. tankmetingen ----------------------------------------//
TankUl Uplink::maakAliveBericht(Sensoren *s)   //bekijkt welk bericht/status tank
 {
  berichtTankUl(&alive, s); 
  return alive;
 }
