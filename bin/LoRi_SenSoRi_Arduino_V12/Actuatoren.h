#ifndef Actuatoren_h
#define Actuatoren_h

#include <arduino.h>
#include "Sensoren.h"


// ---------------------------------------- Class actuatoren ----------------------------------------//



  
class Actuatoren
{
  private:
  const int SLOTBESTURING = 4;    // outputpin voor de leds die aangeven of het slot open of dicht is
  const int SECONDENOPEN = 0;     // seconden dat het slot open moet, is niet instelbaar
  const int SECONDENDICHT = 10;   // seconden dat het slot dicht moet, is niet instelbaar
  
  //int uurOpen = 00;               // uur dat het slot open moet, waarde is in te stellen en komt uit de downlink
 // int minutenOpen = 00;           // minuten dat het slot open moet, waarde is in te stellen en komt uit de downlink
 // int uurDicht = 00;              // uur dat het slot dicht moet, waarde is in te stellen en komt uit de downlink
 // int minutenDicht = 00;          // minuten dat het slot dicht moet, waarde is in te stellen en komt uit de downlink


    
  public:
  Actuatoren();
  void SETUP();
  byte openSlot();
  byte sluitSlot();
  void setOpeningstijd(int uur, int minuut); // openingstijd waarde komt direct uit downlink
  void setSluitingstijd(int uur, int minuut);// sluitingstijd waarde komt direct uit downlink
  void setWeekendOpen(int weekend);  //alleen zaterdag weekend = 1 zaterdag en zondag weekend = 2 alleen zondag weekend = 3 zaterdag en zondag dicht weekend = 0
  
};

#endif //Actuatoren.h
