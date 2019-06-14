const int numberOfInData = 8;
String InData[numberOfInData];
// Should look something like "123,456,789,123,456,789,0"
String input = "";
int counter = 0;
int lastIndex = 0;

void setup(){
  Serial.begin(9600);  
}

void loop() {
    if (Serial.available() > 0) {
    char ch = Serial.read();
    if (ch == '\n') {

      for (int i = 0; i < input.length(); i++) {
        if (input.substring(i, i+1) == ",") {
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
  }
