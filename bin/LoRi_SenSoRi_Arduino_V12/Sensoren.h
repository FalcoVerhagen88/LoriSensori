#ifndef Sensoren_h
#define Sensoren_h

#include <arduino.h>
#include <NMEAGPS.h>
#include <GPSport.h>
#include <DMS.h>
#include <Time.h>
#include <TimeAlarms.h>


// ---------------------------------------- Class sensoren ----------------------------------------//
class Sensoren
{
  private:
  NMEAGPS gps;                        // Klasse die de NMEA gps zinnen verwerkt
  gps_fix fix;                        // Struct die de laatste gps waarde vasthoud
  
  const int ACCUNIVEAUPIN = A0;       // inputpin om de spanning van de batterij te meten
  const int DIESELNIVEAUPIN = A1;     // inputpin voor de potmeter die het dieselniveau simuleert
  const int VOLTZONNEPANEELPIN = A2;  // inputpin om de spanning van het zonnepaneel te meten
  const int AMPZONNEPANEELPIN = A3;   // inputpin om de stroom die geleverd wordt door het zonnepaneel te meten
  const int SLOTOPENPIN = 3;          // simulatie eindstandmelder slot open, kijkt of de groene led brandt
  const int SLOTDICHTPIN = 5;         // simulatie eindstandmelder slot dicht, kijkt of de rode led brandt
  const int DIESELNIVEAUMAX = 1024;   // initialiseert de maximale waarde van de potmeter/dieselniveau
  const int DIESELNIVEAUMIN = 0;      // initialiseert de minimale waarde van de potmeter/dieselniveau
  const int ACCUNIVEAUMAX = 920;      // initialiseert de maximale waarde van de accuniveaumapping 100%
  const int ACCUNIVEAUMIN = 765;      // initialiseert de minimale waarde van de accuniveaumapping 0%
  const int ACCUALARMNIVEAU = 25;      // initialiseert de minimale waarde van het accu alarm niveau 25%

  int dieselniveau;                   // variabele om de inkomende waarde van het dieselniveau op te slaan 
  int vorigeDieselNiveau = 999;              // variabele om het vorige dieselniveau op te slaan                             
  int accuniveau;                     // variabele om de inkomende waarde van het accuniveau op te slaan
  int vermogenZonnepaneel;            // Byte om de de uitkomst van de som VOLTZONNEPANEEL*AMPZONNEPANEEL op te slaan
  int alarmniveauDiesel;              // Byte om het door de gebruiker in te stellen minimum dieselniveau op te slaan

  void setTimeMetGps(NeoGPS::time_t & dt);
  
  public:
  Sensoren();
  void SETUP();
  byte accuniveaumeting();
  byte accuAlarm(byte accuniveau);
  byte dieselniveaumeting();
  byte alarmNiveauInstellen(byte downlinkDieselniveau);
  byte dieselalarmNiveau();
  byte diefstalAlarm();
  byte vermogenZonnepaneelmeting();
  byte slotstandmeting();
  void GPSmeting();
  int getAlarmniveauDiesel();
  gps_fix getGpsFix();
  
};

#endif //Sensoren.h

/*
 * set time met gps signaal en gebruik timezone om juiste tijd te kiezen JChristensen/Timezone op github
 */
