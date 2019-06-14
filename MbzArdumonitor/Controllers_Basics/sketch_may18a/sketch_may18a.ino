const int numberOfPieces = 8;
String pieces[numberOfPieces];
// Should look something like "123,456,789,0"
String input = "";

// Keep track of current position in array
int counter = 0;

// Keep track of the last comma so we know where to start the substring
int lastIndex = 0;

void setup(){
  Serial.begin(9600);  
}

void loop() {

  // Check for data coming in from serial
  if (Serial.available() > 0) {
    
    // Read the first byte and store it as a char
    char ch = Serial.read();
    
    // Do all the processing here since this is the end of a line
    if (ch == '\n') {

      for (int i = 0; i < input.length(); i++) {
        // Loop through each character and check if it's a comma
        if (input.substring(i, i+1) == ",") {
          // Grab the piece from the last index up to the current position and store it
          pieces[counter] = input.substring(lastIndex, i);
          // Update the last position and add 1, so it starts from the next character
          lastIndex = i + 1;
          // Increase the position in the array that we store into
          counter++;
        }

        // If we're at the end of the string (no more commas to stop us)
        if (i == input.length() - 1) {
          // Grab the last part of the string from the lastIndex to the end
          pieces[counter] = input.substring(lastIndex, i);
        }
      }
      

      // Clear out string and counters to get ready for the next incoming string
      input = "";
      counter = 0;
      lastIndex = 0;
    }
    else {
      
      //if we havent reached a newline character yet, add the current character to the string
      input += ch;
    }

  }
Serial.print(pieces[0]);
Serial.print(",");
Serial.print(pieces[1]);
Serial.print(",");
Serial.print(pieces[2]);
Serial.print(",");
Serial.print(pieces[3]);
Serial.print(",");
Serial.print(pieces[4]);
Serial.print(",");
Serial.print(pieces[5]);
Serial.println();
  }
