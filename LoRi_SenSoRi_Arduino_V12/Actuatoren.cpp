#include "Actuatoren.h"


  #define SLOTBESTURING  4    // outputpin voor de leds die aangeven of het slot open of dicht is
  #define SECONDENOPEN 0     // seconden dat het slot open moet, is niet instelbaar
  #define SECONDENDICHT 10   // seconden dat het slot dicht moet, is niet instelbaar
  
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
    weekendOpen = 0;
    
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

  if(weekendOpen == 1 || weekendOpen == 2)// 0 zaterdag en zondag dicht, 1 zaterdag open zondag dicht, 2 zaterdag open en zondag open, 3 zaterdag dicht en zondag open
  {
      Alarm.alarmRepeat(dowSaturday,uur, minuut, SECONDENOPEN, openSlot());
  }

  if(weekendOpen == 2 || weekendOpen == 3)// 0 zaterdag en zondag dicht, 1 zaterdag open zondag dicht, 2 zaterdag open en zondag open, 3 zaterdag dicht en zondag open
  {
      Alarm.alarmRepeat(dowSunday,uur, minuut, SECONDENOPEN, openSlot());
  }
  
  //Serial.println("");
  //Serial.print("openingstijd is : ");
  //Serial.print(uur);
  //Serial.print(":");
  //Serial.print(minuut);
}
  
  
void Actuatoren::setSluitingstijd(int uur, int minuut)
{
  Alarm.alarmRepeat(dowMonday, uur, minuut, SECONDENDICHT, sluitSlot());
  Alarm.alarmRepeat(dowTuesday, uur, minuut, SECONDENDICHT, sluitSlot());
  Alarm.alarmRepeat(dowWednesday, uur, minuut, SECONDENDICHT, sluitSlot());
  Alarm.alarmRepeat(dowThursday, uur, minuut, SECONDENDICHT, sluitSlot());
  Alarm.alarmRepeat(dowFriday, uur, minuut, SECONDENDICHT, sluitSlot());

  if(weekendOpen == 1 || weekendOpen == 2)// 0 zaterdag en zondag dicht, 1 zaterdag open zondag dicht, 2 zaterdag open en zondag open, 3 zaterdag dicht en zondag open
  {
      Alarm.alarmRepeat(dowSaturday,uur, minuut, SECONDENOPEN, openSlot());
  }

  if(weekendOpen == 2 || weekendOpen == 3)// 0 zaterdag en zondag dicht, 1 zaterdag open zondag dicht, 2 zaterdag open en zondag open, 3 zaterdag dicht en zondag open
  {
      Alarm.alarmRepeat(dowSunday,uur, minuut, SECONDENOPEN, openSlot());
  }
 
  //Serial.print("sluitingstijd is : ");
  //Serial.print(uur);
  //Serial.print(":");
  //Serial.print(minuut);
}

void Actuatoren::setWeekendOpen(int weekendsetting)
{
    weekendOpen = weekendsetting;
}
