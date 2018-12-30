#ifndef Downlink_h
#define Downlink_h

#include <lmic.h>
#include <hal/hal.h>
#include <arduino.h>
#include "Actuatoren.h"
#include "Sensoren.h"
#include "Uplink.h"

  

// ---------------------------------------- Id's definiëren ----------------------------------------//



// ---------------------------------------- Class downlink ----------------------------------------//
class Downlink
{
  private:


  
  public:
  Downlink();
  void ontvangDownlink(Sensoren *S, Actuatoren *A, Uplink *U);

};


#endif //Downlink.h
