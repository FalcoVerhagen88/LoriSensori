#include "Uplink.h"

// ---------------------------------------- Constructor Uplink -----------------------------------------//
Uplink::Uplink(Sensoren *s)
{
this->VorigeSlotstand = s->slotstandmeting();
this->vorigeDieselAlarmniveau = s->dieselniveaumeting();

}

void Uplink::setVorigeSlotstand(Sensoren *s)
{
  VorigeSlotstand = s->slotstandmeting();
}

void Uplink::setVorigeDieselAlarmniveau(Sensoren *s)
{
  vorigeDieselAlarmniveau = s->getAlarmniveauDiesel();
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
p->berichtLengte = ALIVELENGTE;
}
// ---------------------------------------- Opstellen alarm bij te laag dieselniveau ----------------------------------------//
void Uplink::berichtATankUl(ATankUl *p, Sensoren *s) 
{
p->ulId = ulIdATankenUl;
p->dieselniveau = s->dieselniveaumeting();
p->berichtLengte = ALARMTANKENLENGTE;
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
p->berichtLengte = ALARMDIEFSTALLENGTE;
}


// ---------------------------------------- Opstellen alarm bij een te laag accuniveau ----------------------------------------//
void Uplink::berichtAAccuniveauUl(AAccuniveauUl *p, Sensoren *s) 
{
p->ulId = ulIdAAccuniveauUl;
p->accuspanning = s->accuniveaumeting();
p->berichtLengte = ALARMACCUSPANNINGLENGTE;
}

// ---------------------------------------- Opstellen alarm bij een wijziging van de slotstand ----------------------------------------//
void Uplink::berichtAslotstandW(ASlotstandW *p, Sensoren *s)
{
p->ulId = ulIdSlotstandWijziging;
p->slotStand = s->slotstandmeting();
p->berichtLengte = WIJZIGINGSLOTSTANDLENGTE;
}


// ---------------------------------------- Opstellen ack bij wijziging van het dieselalarmniveau ----------------------------------------//
void Uplink::ackDieselAlarmniveauW(AckDieselniveauW *p)
{
p->ulId = ackIdDieselAlarmniveauWijziging;
p->berichtLengte = ACKWIJZIGINGALARMNIVEAUDIESEL;
}


// ---------------------------------------- Opstellen melding Checkbericht ----------------------------------------//
void Uplink::BerichtCheck(CheckBericht *p)
{
    p->ulId = ulIdCheck;                  // uid checkbericht
    p->berichtLengte = 1;                 // lengte van het bericht
}

// ---------------------------------------- Opstellen acknowledge slotstandwijziging ----------------------------------------//
  void Uplink::ackSlotstandW(AckSlotstandW *p, Sensoren *s)
  {
    p-> ulId = ackIdSlotstandwijziging;
    p-> slotStand = s->slotstandmeting();
    p-> berichtLengte = ACKSLOTSTANDWIJZIGING;

  }


  // ---------------------------------------- Opstellen acknowledge dieselniveau alarmniveau wijziging ----------------------------------------//
  void ackDieselAlarmniveauW(AckDieselniveauW *p)
  {
   p-> ulId = ackIdDieselAlarmniveauWijziging;
   p-> berichtLengte = ACKWIJZIGINGALARMNIVEAUDIESEL;

  }


  // ---------------------------------------- Opstellen acknowledge sluitingstijd wijziging ----------------------------------------//
  void ackSluitingstijdWijziging(AckSluitingstijdW *p)
  {
	   p-> ulId = ackIdSluitingstijdWijziging;
	   p-> berichtLengte = ACKWIJZIGINGSLUITINGSTIJD;
  }
  

  // ---------------------------------------- Opstellen acknowledge openingstijd wijziging ----------------------------------------//
  void ackOpeningstijdWijziging(AckOpeningstijdW *p)
  {
	   p-> ulId = ackIdOpeningstijdWijziging;
	   p-> berichtLengte = ACKWIJZIGINGOPENINGSTIJD;
  }



// ---------------------------------------- Getters voor berichtstsructs ----------------------------------------//

TankUl Uplink::getAliveBericht(Sensoren *s)   //bekijkt welk bericht/status tank
 {
  berichtTankUl(&alive, s); 
  return alive;
 }


  //-------------------------------------- Get Dieselalarmniveau  --------------------------------------//
  ATankUl Uplink::getDieselniveauAlarm(Sensoren *s)
  {
    berichtATankUl(&tankenAlarm, s);
    return tankenAlarm;
  }

    //-------------------------------------- Get Diefstalalarm  --------------------------------------//
  ADieselniveauUl Uplink::getDiefstalAlarm(Sensoren *s)
  {
    berichtADieselniveauUl(&verlagingDieselniveauAlarm,s);
    return verlagingDieselniveauAlarm;
  }
  
     //-------------------------------------- Get accuniveau alarm --------------------------------------//
  AAccuniveauUl Uplink::getAccuniveauAlarm(Sensoren *s)

  {
    berichtAAccuniveauUl(&accuniveauAlarm,s);
    return accuniveauAlarm;
  }
  

  //-------------------------------------- Get accuniveau alarm --------------------------------------//
  ASlotstandW Uplink::getASlotstandW(Sensoren *s)
  {
	  berichtAslotstandW(&slotstandWijziging, s);
	  return slotstandWijziging;

  }

  //-------------------------------------- Getters voor de acknowledgements --------------------------------------//

  //-------------------------------------- Get ack wijziging diesel alarmniveau --------------------------------------/
  AckDieselniveauW Uplink::getAckDieselAlarmniveauW()
  {
	ackDieselAlarmniveauW(&alarmniveauDieselWijziging);
	return alarmniveauDieselWijziging;
  }


  //-------------------------------------- Get ack wijziging sluitingstijd --------------------------------------/
  AckSluitingstijdW Uplink::getAckSluitingstijdW()
  {
	ackSluitingstijdWijziging(&ackWSluitingstijd);
	return ackWSluitingstijd;
  }


  //-------------------------------------- Get ack wijziging openingstijd --------------------------------------/
  AckOpeningstijdW Uplink::getAckOpeningstijdW()
  {
	  ackOpeningstijdWijziging(&ackWOpeningstijd);
	  return ackWOpeningstijd;
  }


  //-------------------------------------- Get ack wijziging slotstand--------------------------------------/
  AckSlotstandW Uplink::getAckSlotstandW(Sensoren *s)
  {
	  ackSlotstandW(&ackSlotstandWijziging, s);
	  return ackSlotstandWijziging;
  }


  CheckBericht Uplink::getCheckbericht()
  {
	  BerichtCheck(&checkBericht);
	  return checkBericht;
  }
