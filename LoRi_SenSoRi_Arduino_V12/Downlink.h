#ifndef Downlink_h
#define Downlink_h

#include <lmic.h>
#include <hal/hal.h>
#include <arduino.h>
#include "Actuatoren.h"
#include "Sensoren.h"

  

// ---------------------------------------- Id's definiëren ----------------------------------------//



// ---------------------------------------- Class downlink ----------------------------------------//
class Downlink
{
  private:


  
  public:
  Downlink();
  void ontvangDownlink(Sensoren *S, Actuatoren *A);

};


#endif //Downlink.h
