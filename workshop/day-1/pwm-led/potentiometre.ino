int sensorPin = A0;
int led = 9;

void setup() {
  Serial.begin(9600);
}

void loop() {
  int sensorValue, y;
  sensorValue = analogRead(sensorPin);    
  y = sensorValue;
  Serial.println(y);
  analogWrite(led, y);
  delay(500);  
}

