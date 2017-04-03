// length of the morse code 'dot'
int dotLen = 100;

// length of the morse code 'dash'
int dashLen = dotLen * 3;

// length of the pause between elements of a character
int elemPause = dotLen;

// length of the spaces between characters
int spaces = dotLen * 3;

// length of the pause between words
int wordPause = dotLen * 7;

int led10 = 10;
int led9 = 9;

void setup() {
  Serial.begin(9600);
  pinMode(led10, OUTPUT); 
  pinMode(led9, OUTPUT); 
}

char incomingByte = 0;

void loop() {
  if (Serial.available() > 0) {
      incomingByte = Serial.read();

      // Set the case to lower case
      incomingByte = toLowerCase(incomingByte);

      // Call the subroutine to get the morse code equivalent for this character
      GetChar(incomingByte);
  }
}

// DOT
void MorseDot()
{
  digitalWrite(led10, HIGH);
  digitalWrite(led9, HIGH); 
  delay(dotLen);
}

// DASH
void MorseDash()
{
  digitalWrite(led10, HIGH);
  digitalWrite(led9, HIGH);
  delay(dashLen);
}

// Turn Off
void LightsOff(int delayTime)
{
  digitalWrite(led10, LOW);
  digitalWrite(led9, LOW);
  delay(delayTime);             
}

void GetChar(char tmpChar)
{
  Serial.print(tmpChar);
  
  switch (tmpChar) {
    case 'a': 
      MorseDot();
      LightsOff(elemPause);
      MorseDash();
      LightsOff(elemPause);
    break;
    
    case 'b':
      MorseDash();
      LightsOff(elemPause);
      MorseDot();
      LightsOff(elemPause);
      MorseDot();
      LightsOff(elemPause);
      MorseDot();
      LightsOff(elemPause);
    break;
    
    case 'c':
      MorseDash();
      LightsOff(elemPause);
      MorseDot();
      LightsOff(elemPause);
      MorseDash();
      LightsOff(elemPause);
      MorseDot();
      LightsOff(elemPause);
    break;
    
    case 'd':
      MorseDash();
      LightsOff(elemPause);
      MorseDash();
      LightsOff(elemPause);
      MorseDot();
      LightsOff(elemPause);
    break;
    
    case 'e':
      MorseDot();
      LightsOff(elemPause);
    break;
    
    case 'f':
      MorseDot();
      LightsOff(elemPause);
      MorseDot();
      LightsOff(elemPause);
      MorseDash();
      LightsOff(elemPause);
      MorseDot();
      LightsOff(elemPause);
    break;
    
    case 'g':
      MorseDash();
      LightsOff(elemPause);
      MorseDash();
      LightsOff(elemPause);
      MorseDot();
      LightsOff(elemPause);
    break;
    
    case 'h':
      MorseDot();
      LightsOff(elemPause);
      MorseDot();
      LightsOff(elemPause);
      MorseDot();
      LightsOff(elemPause);
      MorseDot();
      LightsOff(elemPause);
    break;
    
    case 'i':
      MorseDot();
      LightsOff(elemPause);
      MorseDot();
      LightsOff(elemPause);
    break;
    
    case 'j':
      MorseDot();
      LightsOff(elemPause);
      MorseDash();
      LightsOff(elemPause);
      MorseDash();
      LightsOff(elemPause);
      MorseDash();
      LightsOff(elemPause);
    break;
    
    case 'k':
      MorseDash();
      LightsOff(elemPause);
      MorseDot();
      LightsOff(elemPause);
      MorseDash();
      LightsOff(elemPause);
    break;
    
    case 'l':
      MorseDot();
      LightsOff(elemPause);
      MorseDash();
      LightsOff(elemPause);
      MorseDot();
      LightsOff(elemPause);
      MorseDot();
      LightsOff(elemPause);
    break;
    
    case 'm':
      MorseDash();
      LightsOff(elemPause);
      MorseDash();
      LightsOff(elemPause);
    break;
    
    case 'n':
      MorseDash();
      LightsOff(elemPause);
      MorseDot();
      LightsOff(elemPause);
    break;
    
    case 'o':
      MorseDash();
      LightsOff(elemPause);
      MorseDash();
      LightsOff(elemPause);
      MorseDash();
      LightsOff(elemPause);
    break;
    
    case 'p':
      MorseDot();
      LightsOff(elemPause);
      MorseDash();
      LightsOff(elemPause);
      MorseDash();
      LightsOff(elemPause);
      MorseDot();
      LightsOff(elemPause);
    break;
    
    case 'q':
      MorseDash();
      LightsOff(elemPause);
      MorseDash();
      LightsOff(elemPause);
      MorseDot();
      LightsOff(elemPause);
      MorseDash();
      LightsOff(elemPause);
    break;
    
    case 'r':
      MorseDot();
      LightsOff(elemPause);
      MorseDash();
      LightsOff(elemPause);
      MorseDot();
      LightsOff(elemPause);
    break;
    
    case 's':
      MorseDot();
      LightsOff(elemPause);
      MorseDot();
      LightsOff(elemPause);
      MorseDot();
      LightsOff(elemPause);
    break;
    
    case 't':
      MorseDash();
      LightsOff(elemPause);
    break;
    
    case 'u':
      MorseDot();
      LightsOff(elemPause);
      MorseDot();
      LightsOff(elemPause);
      MorseDash();
      LightsOff(elemPause);
    break;
    
    case 'v':
      MorseDot();
      LightsOff(elemPause);
      MorseDot();
      LightsOff(elemPause);
      MorseDot();
      LightsOff(elemPause);
      MorseDash();
      LightsOff(elemPause);
    break;
    
    case 'w':
      MorseDot();
      LightsOff(elemPause);
      MorseDash();
      LightsOff(elemPause);
      MorseDash();
      LightsOff(elemPause);
    break;
    
    case 'x':
      MorseDash();
      LightsOff(elemPause);
      MorseDot();
      LightsOff(elemPause);
      MorseDot();
      LightsOff(elemPause);
      MorseDash();
      LightsOff(elemPause);
    break;
    
    case 'y':
      MorseDash();
      LightsOff(elemPause);
      MorseDot();
      LightsOff(elemPause);
      MorseDash();
      LightsOff(elemPause);
      MorseDash();
      LightsOff(elemPause);
    break;
    
    case 'z':
      MorseDash();
      LightsOff(elemPause);
      MorseDash();
      LightsOff(elemPause);
      MorseDot();
      LightsOff(elemPause);
      MorseDot();
      LightsOff(elemPause);
    break;

    // If a matching character was not found it will default to a blank space
    default:
      LightsOff(spaces);
    break;
  }
}


