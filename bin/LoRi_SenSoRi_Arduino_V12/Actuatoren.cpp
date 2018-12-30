#include "Actuatoren.h"

//----------------------------------------- Constructor Actatoren -----------------------------------------//
Actuatoren::Actuatoren(void)
{
// Constructor body van actuatoren, void dus niets nodig  
}

//----------------------------------------- SETUP Actatoren -----------------------------------------//
void Actuatoren::SETUP()
{
    pinMode(SLOTBESTURING, OUTPUT);
    setTime(22,23,50,7,11,18);  // tijd moet worden opgehaald uit het NMEA gps signaal
}


byte Actuatoren::openSlot()
{
    digitalWrite (SLOTBESTURING, HIGH);
    Serial.println(F("Alarm: - slot open"));
    return 01;
}


byte Actuatoren::sluitSlot()
{
    digitalWrite (SLOTBESTURING, LOW);
    Serial.println(F("Alarm: - slot dicht"));
    return 01;
}


void Actuatoren::setOpeningstijd(int uur, int minuut) // set openingstijd en stel alarm.alarmrepeat in zodat het slot open en dicht gaat
{
  Alarm.alarmRepeat(dowMonday,uur, minuut, SECONDENOPEN, openSlot());
  Alarm.alarmRepeat(dowTuesday,uur, minuut, SECONDENOPEN, openSlot());
  Alarm.alarmRepeat(dowWednesday,uur, minuut, SECONDENOPEN, openSlot());
  Alarm.alarmRepeat(dowThursday,uur, minuut, SECONDENOPEN, openSlot());
  Alarm.alarmRepeat(dowFriday,uur, minuut, SECONDENOPEN, openSlot());
  Serial.println("");
  Serial.print("openingstijd is : ");
  Serial.print(uur);
  Serial.print(":");
  Serial.print(minuut);
}
  
  
void Actuatoren::setSluitingstijd(int uur, int minuut)
{
  Alarm.alarmRepeat(dowMonday, uur, minuut, SECONDENDICHT, sluitSlot());
  Alarm.alarmRepeat(dowTuesday, uur, minuut, SECONDENDICHT, sluitSlot());
  Alarm.alarmRepeat(dowWednesday, uur, minuut, SECONDENDICHT, sluitSlot());
  Alarm.alarmRepeat(dowThursday, uur, minuut, SECONDENDICHT, sluitSlot());
  Alarm.alarmRepeat(dowFriday, uur, minuut, SECONDENDICHT, sluitSlot());
  Alarm.alarmRepeat(dowSaturday, 00, 00, SECONDENDICHT, sluitSlot());
  Serial.print("sluitingstijd is : ");
  Serial.print(uur);
  Serial.print(":");
  Serial.print(minuut);
}


void Actuatoren::setWeekendOpen(int weekend)  //alleen zaterdag weekend = 1 zaterdag en zondag weekend = 2 alleen zondag weekend = 3 zaterdag en zondag dicht weekend = 0
{
  
}
