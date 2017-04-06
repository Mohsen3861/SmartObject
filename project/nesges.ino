// String SSID = "ANNEXE220";
// String PASS = "Reseau-GES";

// Board connections settings
int LED = 13;
boolean LEDst = false;
int CH_PD_8266 = 53;

boolean isReady = false;


void setup()
{
  // LED setup
  pinMode(LED, OUTPUT);
  digitalWrite(LED, LEDst);
  pinMode(CH_PD_8266, OUTPUT);
  digitalWrite(CH_PD_8266, HIGH);

  // Serial to USB
  Serial.begin(115200);

  // Serial to ESP
  Serial3.begin(115200);

  while(!Serial);
  while(!Serial3);
}

void loop() {
  /* if (Serial3.available() && isReady) {
    Serial3.print("AT+CIPSTART=4,\"TCP\",\"192.168.30.42\",\"6789\"\r\n");
    delay(1000);
    
    Serial3.print("AT+CIPSEND=4, 10\r\n");
    delay(500);
    
    // Serial3.print(request);
    // delay(3000);
    
    // on ferme la connexion
    Serial3.write("AT+CIPCLOSE=4");
  } */

  while (Serial.available() > 0) {
    char a = Serial.read();
    Serial3.write(a);
  }
}

// Is triggered whenever the ESP sends back something
void serialEvent3() {
  /* if (Serial3.available() > 0) {
      if (!isReady && Serial3.find("ready")) {
        isReady = true; 
      }
  } */
  while (Serial3.available() > 0) {
    char a = Serial3.read();
    Serial.write(a);
  }
}
