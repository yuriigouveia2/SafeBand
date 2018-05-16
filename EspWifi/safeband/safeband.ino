/*
SOFTWARE PARA SISTEMAS EMBARCADOS
YURI GOUVEIA
WELLTON THYAGO
GEORGE NUNES
PROF. ALISSON BRITO

Código do esp8266, que realiza a comunicação entre o botão
externo com o aplicativo por meio firebase.
*/

#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>

// Set these to run example.
#define FIREBASE_HOST "onyx-silo-199918.firebaseio.com"
#define WIFI_SSID "Conductor_Lab"
#define WIFI_PASSWORD "Cdt@123."
int botton = 13;
int val = 0;

void setup() {
  Serial.begin(9600);
  pinMode(botton, INPUT); 
  // connect to wifi.
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("connecting");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }
  Serial.println();
  Serial.print("connected: ");
  Serial.println(WiFi.localIP());

  Firebase.begin(FIREBASE_HOST);
  Firebase.set("clientes/cliente2/flagSafe", false);
}

void loop() {
  val = digitalRead(botton); 
  if (val == LOW) {
  Firebase.set("clientes/cliente2/flagSafe", true);
  } else {
    Firebase.set("clientes/cliente2/flagSafe", false);
  }
}
