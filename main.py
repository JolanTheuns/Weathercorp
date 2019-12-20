# Jolan Theuns
# 467794@student.saxion.nl 12/2019
# Aplication for sending sensor data to the TTN (The Things Network)
from network import WLAN                              # For operation of WiFi network
import time                                           # Allows use of time.sleep() for delays
import pycom                                          # Base library for Pycom devices
import ubinascii                                      # Needed to run any MicroPython code
import machine                                        # Interfaces with hardware components
import micropython
from pysense import Pysense                           #Expansion board
from SI7006A20 import SI7006A20                       #temperature
from LTR329ALS01 import LTR329ALS01                   #light sensor
from MPL3115A2 import MPL3115A2,ALTITUDE,PRESSURE     #pressure sensor
from network import LoRa                              #Lora implementation
import struct 
import binascii 
import socket 

# setup chips and variables
py = Pysense()
si = SI7006A20(py)
mpp = MPL3115A2(py,mode=PRESSURE)
lt = LTR329ALS01(py)
temp = si.temperature

# Function to setup the conection to the TTN
def loraSetup():
   freq = 902300000

   # Initialize LoRa in LORAWAN mode. 
   lora = LoRa(mode=LoRa.LORAWAN)

   #Setup the single channel for connection to the gateway 
   for channel in range(0, 8): 
      lora.remove_channel(channel) 
   for chan in range(0,3): 
      lora.add_channel(chan,  frequency=freq,  dr_min=0,  dr_max=3) 

   #Device Address 
   deviceAdress = struct.unpack(">l", binascii.unhexlify('26011AB0'))[0] 
   #Network Session Key 
   networkSesionKey = binascii.unhexlify('A40CF392721BD0ECF32D3A69544344A9') 
   #App Session Key 
   appSesionKey = binascii.unhexlify('DAD491B4F931A87D757F00BFB06BB45F')

   lora.join(activation=LoRa.ABP, auth=(deviceAdress, networkSesionKey, appSesionKey)) 

   # create a LoRa socket 
   s = socket.socket(socket.AF_LORA, socket.SOCK_RAW) 

   # set the LoRaWAN data rate 
   s.setsockopt(socket.SOL_LORA, socket.SO_DR, 3) 

   # make the socket non-blocking 
   s.setblocking(False)
   return s


socketTTN = loraSetup() # Function call to setup the Lora


# Main loop
while True:
   # printing the values for debugging
    print("humidity: "+ str(si.humidity()))
    print("temperture: "+ str(si.temperature()))
    print("Pressure: " + str(mpp.pressure()))
    print("v2.0")

    # encodinng the data to send over the network
    blueLux,redLux = lt.light()
    tranpres = (int(mpp.pressure())/1000)
    dataList = [int(si.temperature()),int(si.humidity()),int(tranpres),int(blueLux)]
    data = bytes(dataList)

    # sending data to TTN and sleep the device
    socketTTN.send(data)
    time.sleep(60)