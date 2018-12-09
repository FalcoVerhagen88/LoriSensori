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
void Downlink::ontvangDownlink(Sensoren *S, Actuatoren *A, Uplink *U)
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
        Serial.print(downlink[i]);
      }  


    switch (downlink[0])                                                              // byte eerste downlink is de id van het downlinkbericht. Vanuit hier wordt besloten wat er gedaan moet worden.                                                                                                                                                                           
    {
    case dlIdSlotstandDl:
      if(downlink[1] == 00 && (S->slotstandmeting()== 01 || S->slotstandmeting()== 02 ))
      {
        A->sluitSlot();

        LMIC_setTxData2(1,(uint8_t*)&U->getAckSlotstandW(S), U->getAckSlotstandW(S).berichtLengte, 0);
        Serial.println("wijzig slotstand via downlink en stuur ack");
      }
      else if (downlink[1] == 01 && (S->slotstandmeting()== 00 || S->slotstandmeting()== 02 ))
      {
        A->openSlot();
         LMIC_setTxData2(1,(uint8_t*)&U->getAckSlotstandW(S), U->getAckSlotstandW(S).berichtLengte, 0);
         Serial.println("wijzig slotstand via downlink en stuur ack");
      }
    break;
    case dlIdDieselniveauDl:
          S->alarmNiveauInstellen(downlink[1]); // stuur een ack dat het niveau is gewijzigd
          LMIC_setTxData2(1,(uint8_t*)&U->getAckDieselAlarmniveauW(), U->getAckDieselAlarmniveauW().berichtLengte, 0);
          Serial.println("wijzig dieselalarmniveau via downlink en stuur ack");
    break;
    case dlIdOpeningstijdDl:
          A->setOpeningstijd((uint8_t)downlink[1], (uint8_t)downlink[2]); // stuur een ack dat de openingstijd is gewijzigd
        //  LMIC_setTxData2(1,(uint8_t*)&U->getAckOpeningstijdW(), U->getAckOpeningstijdW().berichtLengte, 0);
          Serial.println("wijzig openingstijd via downlink en stuur ack");
    break;
    case dlIdSluitingstijdDl:
          A->setSluitingstijd((uint8_t)downlink[1], (uint8_t)downlink[2]); // stuur een ack dat de sluitingstijd is gewijzigd
         // LMIC_setTxData2(1,(uint8_t*)U->getAckSluitingstijdW(), U->getAckSluitingstijdW().berichtLengte, 0);
          Serial.println("wijzig sluitingstijd via downlink en stuur ack");
    break;

    }
  }
}
