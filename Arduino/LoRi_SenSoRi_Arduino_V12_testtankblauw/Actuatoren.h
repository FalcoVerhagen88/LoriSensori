#ifndef Actuatoren_h
#define Actuatoren_h

#include <arduino.h>
#include "Sensoren.h"


// ---------------------------------------- Class actuatoren ----------------------------------------//

class Actuatoren
{
  private:

  int weekendOpen;                   // 0 zaterdag en zondag dicht, 1 zaterdag open zondag dicht, 2 zaterdag open en zondag open, 3 zaterdag dicht en zondag open
 
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
