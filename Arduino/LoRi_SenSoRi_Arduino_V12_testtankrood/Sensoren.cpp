// ---------------------------------------- Sensoren ----------------------------------------//
#include "Sensoren.h"


Sensoren::Sensoren(void)
{
  //Constructor body sensoren, void dus niets nodig
}

// ---------------------------------------- SETUP ----------------------------------------//
void Sensoren::SETUP()
{
  // set pinmodes
  pinMode(ACCUNIVEAUPIN, INPUT);
  pinMode(DIESELNIVEAUPIN, INPUT);
  pinMode(VOLTZONNEPANEELPIN, INPUT);
  pinMode(AMPZONNEPANEELPIN, INPUT);
  pinMode(SLOTOPENPIN, INPUT);
  pinMode(SLOTDICHTPIN, INPUT);

  //set tijd bij opstsarten
  setTime(10,10,10,10,10,2018);                       

  // initialisatie van de GPS poort (Serial1, gedefinieerd in library)
  gpsPort.begin(9600);    
  //set niveau en slotstand met eerste meting bij opstarten                              
  vorigeDieselNiveau = dieselniveaumeting();
  vorigeSlotstand = slotstandmeting();
}


// ---------------------------------------- Accuniveau doorsturen ----------------------------------------//
byte Sensoren::accuniveaumeting()
{
  accuniveau = analogRead(ACCUNIVEAUPIN);
  accuniveau = map(accuniveau, ACCUNIVEAUMIN, ACCUNIVEAUMAX, 0, 100); // waarde omzetten naar een getal tussen 0 en 100%
  return accuniveau;                                                 // geeft het accuniveau terug
}


// ---------------------------------------- Accuniveau te laag doorsturen ----------------------------------------//
byte Sensoren::accuAlarm(byte accuniveau)
{
  if (accuniveau < ACCUALARMNIVEAU)
    {
      return 01;                                         // geeft 01 als het accuniveau minder dan 25% is        
    }
      return 00;                                             // geeft 00 als het accuniveau meer dan 25% is
}

// ---------------------------------------- Dieselniveau doorsturen ----------------------------------------//
byte Sensoren::dieselniveaumeting()
{
  dieselniveau = analogRead(DIESELNIVEAUPIN);
  dieselniveau = map(dieselniveau, DIESELNIVEAUMIN, DIESELNIVEAUMAX, 0, 101);   // waarde omzetten naar een getal tussen 0 en 100%
  return dieselniveau;                                                          // geeft het dieselniveau terug
}


// ---------------------------------------- Minimum dieselniveau instellen ----------------------------------------//
byte Sensoren::alarmNiveauInstellen(byte downlinkDieselniveau)                 // waarde uit downlink om het niveau aan te passen 0..100
{
    alarmniveauDiesel = downlinkDieselniveau;
    return alarmniveauDiesel;                                                  //alarmniveauDiesel is gelijk aan het door de gebruiker ingestelde niveau
}

// ---------------------------------------- Dieselniveau alarm doorgeven ----------------------------------------//
byte Sensoren::dieselalarmNiveau()
{
 if (dieselniveaumeting() <= alarmniveauDiesel)
    {
      return 01;                                                              // geeft 01 als het dieselniveau <= aan het ingestelde alarmniveau
    }
    else
    {
      return 00;                                                                  // geeft 00 als het dieselniveau > aan het ingestelde alarmniveau
    }
}

// ---------------------------------------- Vermogen zonnepaneel doorsturen ----------------------------------------//
byte Sensoren::vermogenZonnepaneelmeting()
{
  int voltZonnepaneel = analogRead(VOLTZONNEPANEELPIN); // lees analoog in voor volt zonnepaneel 1V voor elke 5V input
  int ampZonnepaneel = analogRead(AMPZONNEPANEELPIN);   // lees analoog in voor amp 1V voor elke amp input
  float volt = voltZonnepaneel * (5.0 / 1024.0) * 5.0;
  float amp = ampZonnepaneel * (5.0 / 1024.0);
  int watt = volt * amp * 100;                          // watt is vermogen * 100 om 2 decimalen precisie te houden na cast to byte
  vermogenZonnepaneel = lowByte(watt);                  // vermogen zonnepaneel is byte waarde van watt. zonnepanelen geven max 2W dus byte 200
  return vermogenZonnepaneel;
}


// ---------------------------------------- Slot open/dicht lezen ----------------------------------------//
byte Sensoren::slotstandmeting()
{
  byte slotOpen = digitalRead(SLOTOPENPIN);
  byte slotDicht = digitalRead(SLOTDICHTPIN);
  
  if (slotOpen == HIGH && slotDicht == LOW)                                                         // als het slot open is wordt er 01 gestuurd
  {
    return 01;
  }
  else if (slotOpen == LOW && slotDicht == HIGH)                                                    // als het slot dicht is wordt er 00 gestuurd
  {
    return 00;
  }
  else if (slotOpen == HIGH && slotDicht == HIGH || slotOpen == LOW && slotDicht == LOW)            // slotopenpin en slotdichtpin kunnen niet dezelfde waarde hebben, er wordt een foutmelding 02 gestuurd 
 {                                                                                  
    return 02;
  }
}

// ---------------------------------------- GPS waarden lezen ----------------------------------------//
void Sensoren::GPSmeting()
{
  if (gps.available( gpsPort )) {
    fix = gps.read(); 
    
        if (fix.valid.location && fix.valid.time && fix.valid.date) // Alleen als er een complete nmea zin klaar is de tijd ervan ophalen
        {
        setTimeMetGps( fix.dateTime );
        Serial.print("uren : ");
        Serial.println(fix.dateTime.hours);
        Serial.print("minuten : ");
        Serial.println(fix.dateTime.minutes);
        Serial.print("seconden : ");
        Serial.println(fix.dateTime.seconds);
        Serial.print("dag : ");
        Serial.println(fix.dateTime.date);
        Serial.print("maand : ");
        Serial.println(fix.dateTime.month);
        Serial.print("jaar : ");
        Serial.println(fix.dateTime.year);
        }
  }   
} // GPSloop

// ---------------------------------------- GPS waarden lezen ----------------------------------------//
gps_fix Sensoren::getGpsFix()
{
 return fix;  //return GPS waarden
}

//---------------------------------------- Set Time met GPS signaal ----------------------------------------//

void Sensoren::setTimeMetGps(NeoGPS::time_t & dt)
{

    setTime(fix.dateTime.hours,fix.dateTime.minutes,fix.dateTime.seconds,fix.dateTime.date,fix.dateTime.month,fix.dateTime.year);
            Serial.print("set time from gps data ");
}

byte Sensoren::diefstalAlarm() // kijk of er een diefstal alarm moet worden gegeven
{
  
  if(slotstandmeting() == 00 && vorigeDieselNiveau != 999)
  {
    if(dieselniveaumeting() < vorigeDieselNiveau )
    {
     vorigeDieselNiveau = dieselniveaumeting();
     return 01 ;
    }
    
  } 
    vorigeDieselNiveau = dieselniveaumeting();
    return 00 ;
}


int Sensoren::getAlarmniveauDiesel() 
{
  return alarmniveauDiesel;
}


byte Sensoren::slotstandAlarm() // kijkt of de slotstand is veranderd zonder downlink of ingeplande opdracht uit Time.Alarms
{
	if( vorigeSlotstand != slotstandmeting())
	{
		return 01;
	}

	vorigeSlotstand = slotstandmeting();
	return 00;

}

//-------------------------get vorige slotstand-------------------------------//
void Sensoren::setVorigeSlotstand(int slotstand)
{
  vorigeSlotstand = slotstand;
}
