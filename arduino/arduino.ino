#define DR 2
#define MR 3
#define DL 4
#define ML 5

#define bFL 9
#define bFR 8
#define bBL 10
#define bBR 11

#define irFront A3
#define irBack A2

#define irMR A1
#define irML A0

bool vbFL, vbFR, vbBL, vbBR;
int virFront, virBack;
int virMR, virML;

bool direction = false;
bool going = false;
bool h1 = 0, h2 = 0;
int speedLeft = 190;
int speedRight = 190;

void setup()
{
  Serial.begin(9600);
  pinMode(bFL, INPUT); 
  pinMode(bFR, INPUT); 
  pinMode(bBL, INPUT); 
  pinMode(bBR, INPUT);
  pinMode(DL,OUTPUT);
  pinMode(DR,OUTPUT);
  
  pinMode(13,OUTPUT); //Test
}

void readSensors()
{
  int sensorsValue = 0;
  
  vbFL = digitalRead(bFL);
  vbFR = digitalRead(bFR);
  vbBL = digitalRead(bBL); 
  vbBR = digitalRead(bBR);
  
  virFront = analogRead(irFront);
  virBack = analogRead(irBack);
  
/**
  | vbFL | vbFR | vbBL | vbBR | virFront | virBack |
  |   x  |   x  |   x  |   x  |    x     |    x    |   
  
  vbFL =     100000 = 32
  vbFR =     010000 = 16
  vbBL =     001000 = 8
  vbBR =     000100 = 4
  virFront = 000010 = 2
  virBack =  000001 = 1
**/

  sensorsValue = (int)vbFL*32 + (int)vbFR*16 + (int)vbBL*8 + (int)vbBR*4 + checkFront()*2 + checkBack();
  
  Serial.print(sensorsValue);
  
/*     
  virMR = analogRead(irMR);
  virML = analogRead(irML);
  Serial.print(vbFL);
  Serial.print(" : ");
  Serial.print(vbFR);
  Serial.print(" : ");
  Serial.print(vbBL);
  Serial.print(" : ");
  Serial.print(vbBR);
  Serial.print(" - ");
  
  Serial.print(virFront);
  Serial.print(" : ");
  Serial.print(virBack);
  Serial.print(" -- ");
  
  Serial.print(virMR);
  Serial.print(" : ");
  Serial.println(virML);
*/  
}

void readCom()
{
  int input = 0;
  
  if (Serial.available()) 
  {
    input = Serial.read();// - 48;
  }

  Serial.print("Input: ");
  Serial.println(input);

  switch (input) {
    case 1:
      runForward();
      break;
    case 2:
      runBack();
      break;
    case 3:
      turnRight();
      break;
    case 4:
      turnLeft();
      break;
    case 5:
      runStop();
      break;
  }
  
}

//------------------------------ checkFront -------------------------------
int checkFront()
{
  if (virFront < 920)  //40(yes) -- 1019(no)
    return 1;
   return 0;
}

//------------------------------ checkBack -------------------------------
int checkBack()
{
  if (virBack < 920)  //40(yes) -- 1019(no)
    return 1;
   return 0;
}

//------------------------------ checkMRight -------------------------------
int checkMRight()
{
  if (virMR > 650)  //900(yes) -- 400(no)
    return 1;
   return 0;
}

//------------------------------ checkMLeft -------------------------------
int checkMLeft()
{
  if (virML > 450)  //700(yes) -- 200(no)
    return 1;
   return 0;
}

//------------------------------ runStop -------------------------------
void runStop()
{
  digitalWrite(DL, 0);
  digitalWrite(DR, 0);
  analogWrite(ML, 0);
  analogWrite(MR, 0);
}

//------------------------------ runForward -------------------------------
void runForward()
{
  digitalWrite(DL, 1);
  digitalWrite(DR, 1);
  analogWrite(ML, 255 - speedLeft);
  analogWrite(MR, 255 - speedRight);
}

//------------------------------ runForwardStep -------------------------------
void runForwardStep()
{
  readSensors();
  int initLeftMot = checkMLeft();
  int initRightMot = checkMRight();
  int leftMot = initLeftMot;
  int rightMot = initRightMot;
  int spL = speedLeft*0.1;
  int spR = speedRight*0.1;
  digitalWrite(DL, 1);
  digitalWrite(DR, 1);
  analogWrite(ML, 255 - spL);
  analogWrite(MR, 255 - spR);
  do
  {
    readSensors();
    if(!(initLeftMot ^ leftMot)) leftMot = checkMLeft();
    if(!(initRightMot ^ rightMot)) rightMot = checkMRight();
    
//    Serial.print(initLeftMot);
//    Serial.print("^");
//    Serial.print(leftMot);
//    Serial.print("=");
//    Serial.print(initLeftMot ^ leftMot);
//    Serial.print(" -- ");
//    Serial.print(initRightMot);
//    Serial.print("^");
//    Serial.print(rightMot);
//    Serial.print("=");
//    Serial.println(initRightMot ^ rightMot);
    
    if(initLeftMot ^ leftMot)
    {
      digitalWrite(DL, 0);
      analogWrite(ML, 0);
    }
    else
    {
      spL += speedLeft*0.11;
      analogWrite(ML, 255 - spL);
    }
    if(initRightMot ^ rightMot)
    {
      digitalWrite(DR, 0);
      analogWrite(MR, 0);
    }
    else
    {
      spR += speedRight*0.05;
      analogWrite(MR, 255 - spR);
    }
  } while ((initLeftMot == leftMot) || (initRightMot == rightMot));
}

//------------------------------ runBack -------------------------------
void runBack()
{
  digitalWrite(DL, 0);
  digitalWrite(DR, 0);
  analogWrite(ML, speedLeft);
  analogWrite(MR, speedRight);
}

//------------------------------ turnRight -------------------------------
void turnRight()
{
  digitalWrite(DR, 0);
  analogWrite(MR, 0);
  digitalWrite(DL, 1);
  analogWrite(ML, 255 - speedLeft);
  delay(1000);
  digitalWrite(DL, 0);
  analogWrite(ML, 0);
}

//------------------------------ turnLeft -------------------------------
void turnLeft()
{
  digitalWrite(DL, 0);
  analogWrite(ML, 0);
  digitalWrite(DR, 1);
  analogWrite(MR, 255 - speedRight);
  delay(1000);
  digitalWrite(DR, 0);
  analogWrite(MR, 0);
}

void spinRight()
{
  digitalWrite(DR, 0);
  analogWrite(MR, speedRight);
  digitalWrite(DL, 1);
  analogWrite(ML, 255 - speedLeft);
  delay(1000);
  digitalWrite(DL, 0);
  analogWrite(ML, 0);
  analogWrite(MR, 0);
}

void spinLeft()
{
  digitalWrite(DL, 0);
  analogWrite(ML, speedLeft);
  digitalWrite(DR, 1);
  analogWrite(MR, 255 - speedRight);
  delay(1000);
  digitalWrite(DR, 0);
  analogWrite(MR, 0);
  analogWrite(ML, 0);
}

void loop()
{
  readSensors();
  readCom();
  delay(1000);


  /*
  runForward();
  digitalWrite(13,HIGH);
  do
  {
    readSensors();
    Serial.println(checkFront());
  }
  while(!checkFront());
  
  spinLeft();
  */
//  runBack();
//  digitalWrite(13,LOW);
//  do
//  {
//    readSensors();
//  }
//  while(!checkBack());
//  

//  readSensors();
//  if (checkMLeft())
//    digitalWrite(13,HIGH);
//  else
//     digitalWrite(13,LOW);
  /*
  runForwardStep();
  
  Serial.println("++++++++++++++");
  delay(2000);
  
  runForward();
  delay(4000);
  
  runStop();
  delay(1000);
  
  runBack();
  delay(4000);
  
  runStop();
  delay(2000);
  */
  /*
  digitalWrite(DL, 1);
    digitalWrite(DR, 1);
    analogWrite(ML, 0);
    analogWrite(MR, 0);
  delay(2000);
  
  digitalWrite(DL, 0);
    digitalWrite(DR, 0);
    analogWrite(ML, 0);
    analogWrite(MR, 0);
  delay(2000);
  
  digitalWrite(DL, 0);
    digitalWrite(DR, 0);
    analogWrite(ML, 255);
    analogWrite(MR, 255);
  delay(2000);
  
  digitalWrite(DL, 1);
    digitalWrite(DR, 1);
    analogWrite(ML, 255);
    analogWrite(MR, 255);
  delay(2000);
  */
  
  /*
  digitalWrite(DL, 1);
  digitalWrite(DR, 0);
  analogWrite(ML, 0);
  analogWrite(MR, 150);
  delay(2000);
  
  digitalWrite(DL, 0);
  digitalWrite(DR, 1);
  analogWrite(ML, 150);
  analogWrite(MR, 0);
  delay(2000);
  
  if (digitalRead(bBR))
  {
    if (!h1) 
    {
      going = going ^1;
      h1 = true;
    }
  }
  else
    h1 = false;
  
  if (digitalRead(bBL))
  {
    if (!h2) 
    {
      direction = direction ^1;
      h2 = true;
    }
  }
  else
    h2 = false;
  
  if (going) 
  {
    digitalWrite(DL, direction);
    digitalWrite(DR, direction);
    analogWrite(ML, speed);
    analogWrite(MR, speed);
  }
  else
  {
    analogWrite(ML, 0);
    analogWrite(MR, 0);
  }
  
  delay(50);
  */
}
