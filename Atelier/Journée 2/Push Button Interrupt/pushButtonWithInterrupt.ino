const int ledPin =  13;
const int buttonPin = 2;

volatile int state = LOW;


void setup() {
  pinMode(ledPin, OUTPUT);
  attachInterrupt(buttonPin, blink, CHANGE);
}

void loop() {
  digitalWrite(ledPin, state);
}

void blink() {
  state = !state;
}

