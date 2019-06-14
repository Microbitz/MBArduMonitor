#include <Wire.h>
#include "RTClib.h"

RTC_DS1307 rtc;

void setup () {
  Serial.begin(9600);
  rtc.begin(); //Inicializamos el RTC
  Serial.println("Estableciendo Hora y fecha...");
  rtc.adjust(DateTime(F(__DATE__), F(__TIME__)));
  Serial.println("DS1307 actualizado con la hora y fecha que se compilo este programa:");
  Serial.print("Fecha = ");
  Serial.print(__DATE__);
  Serial.print("  Hora = ");
  Serial.println(__TIME__);
}

void loop () {
}
