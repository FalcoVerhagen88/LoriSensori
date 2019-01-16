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
void Downlink::ontvangDownlink(Sensoren *S, Actuatoren *A, Uplink *U) // ontvang een downlink van het lorashield
{
    Serial.println(F("EV_TXCOMPLETE (includes waiting for RX windows)"));
   
    if (LMIC.txrxFlags & TXRX_ACK)
    {
      Serial.println(F("Acknowledge ontvangen"));
    }
        
    if (LMIC.dataLen) 
    {
      uint8_t downlink[LMIC.dataLen];         // informatie uit de lora communicatie wordt omgezet naar een array in de loop
      for (int i = 0; i < LMIC.dataLen; i++)
      {
        memcpy(&downlink[i],&(LMIC.frame+LMIC.dataBeg)[i],LMIC.dataLen);
        Serial.print(downlink[i]);
        Serial.println("");
      }  

     Serial.print(downlink[0]);
    switch (downlink[0])                     // byte eerste downlink is de id van het downlinkbericht. Vanuit hier wordt besloten wat er gedaan moet worden.                                                                                                                                                                           
    {
    case dlIdSlotstandDl:
      if(downlink[1] == 00 && (S->slotstandmeting()== 01 || S->slotstandmeting()== 02 ))
      {
        A->sluitSlot();
        S->setVorigeSlotstand(00); // wijzig waarde vorige slotstand zodat er geen alarm wordt getriggert
        LMIC_setTxData2(1,(uint8_t*)&U->getAckSlotstandW(S), U->getAckSlotstandW(S).berichtLengte, 0); // stuur ack slotstand gewijzigd
        Serial.println("wijzig slotstand via downlink en stuur ack");
      }
      else if (downlink[1] == 01 && (S->slotstandmeting()== 00 || S->slotstandmeting()== 02 ))
      {
        A->openSlot();
        S->setVorigeSlotstand(01); // wijzig waarde vorige slotstand zodat er geen alarm wordt getriggert
         LMIC_setTxData2(1,(uint8_t*)&U->getAckSlotstandW(S), U->getAckSlotstandW(S).berichtLengte, 0);  // stuur ack slotstand gewijzigd
         Serial.println("wijzig slotstand via downlink en stuur ack");
      }
    break;
    case dlIdDieselniveauDl:
          S->alarmNiveauInstellen(downlink[1]); //wijzig dieselniveau alarmwaarde met naar de waarde uit de downlink
          LMIC_setTxData2(1,(uint8_t*)&U->getAckDieselAlarmniveauW(), U->getAckDieselAlarmniveauW().berichtLengte, 0); // stuur een ack dat het niveau is gewijzigd
          Serial.println("wijzig dieselalarmniveau via downlink en stuur ack");
    break;
    case dlIdOpeningstijdDl:
          A->setOpeningstijd((uint8_t)downlink[1], (uint8_t)downlink[2]);  //wijzig openingstijden van het slot deze zijn voor alle dagen gelijk
          LMIC_setTxData2(1,(uint8_t*)&U->getAckOpeningstijdW(), U->getAckOpeningstijdW().berichtLengte, 0); // stuur een ack dat de openingstijd is gewijzigd
          Serial.println("wijzig openingstijd via downlink en stuur ack");
    break;
    case dlIdSluitingstijdDl:
          A->setSluitingstijd((uint8_t)downlink[1], (uint8_t)downlink[2]); //wijzig sluitingstijden van het slot, deze zijn voor alle dagen gelijk
          LMIC_setTxData2(1,(uint8_t*)&U->getAckSluitingstijdW(), U->getAckSluitingstijdW().berichtLengte, 0); // stuur een ack dat de sluitingstijd is gewijzigd
          Serial.println("wijzig sluitingstijd via downlink en stuur ack");
    break;

    default:
    //
    break;
    }
  }
}
