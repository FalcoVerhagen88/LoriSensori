#ifndef Actuatoren_h
#define Actuatoren_h

#include <arduino.h>
#include "Sensoren.h"


// ---------------------------------------- Class actuatoren ----------------------------------------//



  
class Actuatoren
{
  private:

  int weekendOpen;                   // 0 zaterdag en zondag dicht, 1 zaterdag open zondag dicht, 2 zaterdag open en zondag open, 3 zaterdag dicht en zondag open
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
  void setWeekendOpen(int weekendsetting);
  
};

#endif //Actuatoren.h
