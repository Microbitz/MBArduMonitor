//Heart Beat
int Heart_beat = 0;
unsigned long previousMillis = 0;
const long interval = 200;
//Analog_IN
float A0spam = 100.0;
float A0cero = 0.0;
float A0value = 0.0;
float A1spam = 100.0;
float A1cero = 0.0;
float A1value = 0.0;
float A2spam = 100.0;
float A2cero = 0.0;
float A2value = 0.0;
float A3spam = 100.0;
float A3cero = 0.0;
float A3value = 0.0;
float A4spam = 100.0;
float A4cero = 0.0;
float A4value = 0.0;
float A5spam = 100.0;
float A5cero = 0.0;
float A5value = 0.0;
//Analog_OUT
float ref_value0;
float PWM0spam = 100.0;
float PWM0cero = 0.0;
float Aout0value = 0.0;
float ref_value1;
float PWM1spam = 100.0;
float PWM1cero = 0.0;
float Aout1value = 0.0;
float ref_value2;
float PWM2spam = 100.0;
float PWM2cero = 0.0;
float Aout2value = 0.0;
float ref_value3;
float PWM3spam = 100.0;
float PWM3cero = 0.0;
float Aout3value = 0.0;
float ref_value4;
float PWM4spam = 100.0;
float PWM4cero = 0.0;
float Aout4value = 0.0;
float ref_value5;
float PWM5spam = 100.0;
float PWM5cero = 0.0;
float Aout5value = 0.0;
int SerialData_value_AO_0;
int SerialData_value_AO_1;
//Digital_IN
int InState0 = 0;
int InState1 = 0;
int InState2 = 0;
int InState3 = 0;
int InState4 = 0;
byte InputState = 0;
//Digital_OUT
int Relay_0;
int Relay_1;
int Relay_2;
int Relay_3;
//Serial_IN
const int numberOfInData = 8;
String InData[numberOfInData];
String input = "";
int counter = 0;
int lastIndex = 0;

void setup() {
  //--------AI_Type--------------
  pinMode(A0, INPUT);
  pinMode(A1, INPUT);
  pinMode(A2, INPUT);
  pinMode(A3, INPUT);
  pinMode(A4, INPUT);
  pinMode(A5, INPUT);
  //--------AO_Type--------------
  pinMode(5, OUTPUT);
  pinMode(6, OUTPUT);
  //--------DI_Type--------------
  pinMode(3, INPUT);
  pinMode(4, INPUT);
  pinMode(7, INPUT);
  pinMode(8, INPUT);
  pinMode(9, INPUT);
  //--------DO_Type--------------
  pinMode(10, OUTPUT);
  pinMode(11, OUTPUT);
  pinMode(12, OUTPUT);
  pinMode(13, OUTPUT);
  digitalWrite(10, HIGH);
  digitalWrite(11, HIGH);
  digitalWrite(12, HIGH);
  digitalWrite(13, HIGH);
  Serial.begin(9600);
}

void loop() {
  unsigned long currentMillis = millis();
  if (currentMillis - previousMillis >= interval) {
    previousMillis = currentMillis;
    if (Heart_beat == 0) {
      Heart_beat = 1;
      //digitalWrite(LED_BUILTIN, HIGH);
    } else {
      Heart_beat = 0;
      //digitalWrite(LED_BUILTIN, LOW);
    }
  }
  //Serial.println(Heart_beat);
  //-------------------------wireless_Link_Output---------------
  /*Serial.print(A0value);
    Serial.print(",");
    Serial.print(A1value);
    Serial.print(",");
    Serial.print(A2value);
    Serial.print(",");
    Serial.print(A3value);
    Serial.print(",");
    Serial.print(A4value);
    Serial.print(",");
    Serial.print(A5value);
    Serial.print(",");
    Serial.print(A0value);*/
  // Serial.print(",");
  Serial.print(InData[0]);
  Serial.print(",");
  Serial.print(InData[1]);
  Serial.print(",");
  Serial.print(InData[2]);
  Serial.print(",");
  Serial.print(InData[3]);
  Serial.print(",");
  Serial.print(InData[4]);
  Serial.print(",");
  Serial.print(InData[5]);
  Serial.println();
  //delay(100);
  //-------------------------wireless_Link_Input---------------
  if (Serial.available() > 0) {
    char ch = Serial.read();
    if (ch == '\n') {

      for (int i = 0; i < input.length(); i++) {
        if (input.substring(i, i + 1) == ",") {
          InData[counter] = input.substring(lastIndex, i);
          lastIndex = i + 1;
          counter++;
        }
        if (i == input.length() - 1) {
          InData[counter] = input.substring(lastIndex, i);
        }
      }
      input = "";
      counter = 0;
      lastIndex = 0;
    }
    else {
      input += ch;
    }

  }
  //-------------------------Analog Inputs---------------
  //--------Update----------------
  int RAW_Value0 = analogRead(A0);
  int RAW_Value1 = analogRead(A1);
  int RAW_Value2 = analogRead(A2);
  int RAW_Value3 = analogRead(A3);
  int RAW_Value4 = analogRead(A4);
  int RAW_Value5 = analogRead(A5);
  //-------Scalling_AI---------------
  //-------Default_Scalling----------
  A0spam = 100.0;
  A0cero = 0.0;
  A1spam = 100.0;
  A1cero = 0.0;
  A2spam = 100.0;
  A2cero = 0.0;
  A3spam = 100.0;
  A3cero = 0.0;
  A4spam = 100.0;
  A4cero = 0.0;
  A5spam = 100.0;
  A5cero = 0.0;
  //-------Data_update---------------
  A0value = map(RAW_Value0, 0, 1023, A0cero, A0spam);
  A1value = map(RAW_Value1, 0, 1023, A1cero, A1spam);
  A2value = map(RAW_Value2, 0, 1023, A2cero, A2spam);
  A3value = map(RAW_Value3, 0, 1023, A3cero, A3spam);
  A4value = map(RAW_Value4, 0, 1023, A4cero, A4spam);
  A5value = map(RAW_Value5, 0, 1023, A5cero, A5spam);
  //-------Scalling_AO---------------
  SerialData_value_AO_0 = InData[4].toInt();
  SerialData_value_AO_1 = InData[5].toInt();
  ref_value0 = SerialData_value_AO_0;
  Aout0value = map(ref_value0, 0, 100, 0, 255);
  analogWrite(5, Aout0value);
  ref_value1 = SerialData_value_AO_1;
  Aout1value = map(ref_value1, 0, 100, 0, 255);
  analogWrite(6, Aout1value);
  //-------Digital_IN----------------
  InState0 = digitalRead(3);
  InState1 = digitalRead(4);
  InState2 = digitalRead(7);
  InState3 = digitalRead(8);
  InState4 = digitalRead(9);
  if (InState0 == HIGH) {
    bitSet(InputState, 0);
  }
  if (InState1 == HIGH) {
    bitSet(InputState, 1);
  }
  if (InState2 == HIGH) {
    bitSet(InputState, 2);
  }
  if (InState3 == HIGH) {
    bitSet(InputState, 3);
  }
  if (InState4 == HIGH) {
    bitSet(InputState, 4);
  }
  if (InState0 == LOW) {
    bitClear(InputState, 0);
  }
  if (InState1 == LOW) {
    bitClear(InputState, 1);
  }
  if (InState2 == LOW) {
    bitClear(InputState, 2);
  }
  if (InState3 == LOW) {
    bitClear(InputState, 3);
  }
  if (InState4 == LOW) {
    bitClear(InputState, 4);
  }
  //Serial.println(InState2);
  //-------Digital_Out---------------
  Relay_0 = InData[0].toInt();
  Relay_1 = InData[1].toInt();
  Relay_2 = InData[2].toInt();
  Relay_3 = InData[3].toInt();
  if (InData[0] == "A") {
    digitalWrite(13, LOW);
  }
  if (InData[1] == "B") {
    digitalWrite(12, LOW);
  }
  if (InData[2] == "C") {
    digitalWrite(11, LOW);
  }
  if (InData[3] == "D") {
    digitalWrite(10, LOW);
  }
  if (InData[0] == "a") {
    digitalWrite(13, HIGH);
  }
  if (InData[1] == "b") {
    digitalWrite(12, HIGH);
  }
  if (InData[2] == "c") {
    digitalWrite(11, HIGH);
  }
  if (InData[3] == "d") {
    digitalWrite(10, HIGH);
  }

}
/* 
A,b,c,d,0,0,0
A,B,c,d,0,0,0
A,B,C,d,0,0,0
A,B,C,D,0,0,0
A,b,c,D,0,0,0
a,B,C,d,0,0,0
*/
