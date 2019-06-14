#include <Wire.h>
#include <VL53L0X.h>
VL53L0X sensor;
 
int n_Samples = 5; // numero de muestras para promediar
 
//#define LONG_RANGE // Aumenta sensibilidad, +rango, -precision
//#define HIGH_SPEED // Mayor velocidad, menor precision
#define HIGH_ACCURACY // Alta precision, menor velocidad
 
 
void setup() {
 
 Serial.begin(9600);
 Wire.begin();
 
 sensor.init();
 sensor.setTimeout(500);
 
//###### Parametros Medida simple ##########
#if defined LONG_RANGE
// Limite de la tasa de retorno (por defecto 0.25 MCPS)
 sensor.setSignalRateLimit(0.1);
// Periodo de pulso laser (por defecto 14 y 10 PCLKs)
 sensor.setVcselPulsePeriod(VL53L0X::VcselPeriodPreRange, 18);
 sensor.setVcselPulsePeriod(VL53L0X::VcselPeriodFinalRange, 14);
#endif
 
#if defined HIGH_SPEED
// reduce tiempo estimado a 20 ms (por defecto 33 ms)
 sensor.setMeasurementTimingBudget(33000);
#elif defined HIGH_ACCURACY
// incrementa tiempo estimado a 200 ms
 sensor.setMeasurementTimingBudget(200000);
#endif
}
 
 
void loop() {
 
 float DISTANCIA = getDISTANCIA (n_Samples);
 
 if (sensor.timeoutOccurred()) {
 //Serial.println(" TIME OUT");
 }else {
 if (DISTANCIA< 2) ;//Serial.println("Fuera de Rango (d < 2 cm)");
 else if (DISTANCIA>220) ;//Serial.println("Fuera de Rango (d > 2 m)");
 else { 
 Serial.println(DISTANCIA, 2); // distancia en cm y 1 decimal
 //Serial.println(" cm"); 
 }
 }
 delay (10);
}
 
 
float getDISTANCIA(int n) { // hacemos "n" mediciones
 
 float SUMA_n = 0;
 for (int i = 0; i < n; i++) { 
 SUMA_n += sensor.readRangeSingleMillimeters();
 }
 return( SUMA_n /n /100); // Promedio en centimetros
}
