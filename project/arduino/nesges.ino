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
  // À faire lors de la 1ère connexion à un réseau étranger :
  // AT+CWJAP="ESGI","Reseau-GES"  

  // Nb de connexions simultanées :
  // AT+CIPMUX=1

  // Lancement de la connexion TCP :
  // AT+CIPSTART=1,"TCP","192.168.30.42",6789
  
  // Envoi de 10 bytes sur la connexion avec l'id 1 :
  // AT+CIPSEND=1,10

  // Ferme la connexion avec l'id 1
  // AT+CIPCLOSE=1

  // Ping Google :
  // AT+PING="www.google.fr"
  
  if (Serial3.available() && isReady) {
    Serial3.print("AT+CIPMUX=1\r\n");
    delay(50);
    
    Serial3.print("AT+CIPSTART=1,\"TCP\",\"192.168.30.42\",6789\r\n");
    delay(100);
    
    Serial3.print("AT+CIPSEND=1, 6\r\n");
    delay(50);
    
    Serial3.print("ping\r\n");
    delay(50);
    
    Serial3.print("AT+CIPCLOSE=1\r\n");
    delay(50);
  }

  /* while (Serial.available() > 0) {
    char a = Serial.read();
    Serial3.write(a);
  } */
}

// Is triggered whenever the ESP sends back something
void serialEvent3() {
  if (Serial3.available() > 0) {
      if (!isReady && Serial3.find("ready")) {
        isReady = true; 
      } else {
        char a = Serial3.read();
        Serial.write(a);        
      }
  }
  /* while (Serial3.available() > 0) {
    char a = Serial3.read();
    Serial.write(a);
  } */
}
