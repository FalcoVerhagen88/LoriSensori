#include "Downlink.h"

  #define dlIdSlotstandDl 0x00
  #define dlIdDieselniveauDl 0x01
  #define dlIdOpeningstijdDl 0x02
  #define dlIdSluitingstijdDl 0x03

//----------------------------------------- Constructor Downlink ------------------------------//
Downlink::Downlink(void)
{
  //Constructor Downlink, void dus niets nodig
}        

//---------------------------------------Ontvang downlink---------------------------------------//
void Downlink::ontvangDownlink(Sensoren *S, Actuatoren *A, Uplink U)
{
    Serial.println(F("EV_TXCOMPLETE (includes waiting for RX windows)"));
   
    if (LMIC.txrxFlags & TXRX_ACK)
    {
      Serial.println(F("Acknowledge ontvangen"));
    }
    if (LMIC.dataLen) 
    {
       Serial.print("als ik een downlink stuur kom ik hier uit");
      uint8_t downlink[LMIC.dataLen];
      for (int i = 0; i < LMIC.dataLen; i++)
      {
        memcpy(&downlink[i],&(LMIC.frame+LMIC.dataBeg)[i],LMIC.dataLen);
      }  

    Serial.print("Eerste byte: ");
    Serial.println(downlink[0]);                               
    Serial.print("Tweede byte: "); 
    Serial.println(downlink[1]);
    Serial.print("Derde byte: "); 
    Serial.println(downlink[2]);
 

    switch (downlink[0])                                                              // byte eerste downlink is de id van het downlinkbericht. Vanuit hier wordt besloten wat er gedaan moet worden.                                                                                                                                                                           
    {
    case dlIdSlotstandDl:
      if(downlink[1] == 00 && (S->slotstandmeting()== 01 || S->slotstandmeting()== 02 ))
      {
        A->sluitSlot();
        Serial.println("sluit slot in de klasse actuatoren");
      }
      else if (downlink[1] == 01 && (S->slotstandmeting()== 00 || S->slotstandmeting()== 02 ))
      {
        A->openSlot();
         Serial.println("open slot in de klasse actuatoren");
      }
    break;
    case dlIdDieselniveauDl:
          S->alarmNiveauInstellen(downlink[1]);
    break;
    case dlIdOpeningstijdDl:
          A->setOpeningstijd((uint8_t)downlink[1], (uint8_t)downlink[2]);
    break;
    case dlIdSluitingstijdDl:
          A->setSluitingstijd((uint8_t)downlink[1], (uint8_t)downlink[2]);
    break;
    default:
    //
    break;
    }
  }
}
