# weathercorp

weathercorp is a weather station project, containing a LoPy sensornode, to capture every minute: temperature, ambient light and/or barometric pressure. The data is then transmitted wirelessly, using LoRa, through a gateway, to The Things Network. Using NodeRED we retrieve the data and store it in a SQL database. Upon request the end-user can display all weather data graphically in a user interface, being an Android mobile app and a website.
This repository is used to store all of the works of this project, composing the code for LoPy, NodeRED, the website and the mobile app.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Node.js and Atom or Visual studio code must be installed with the Pymakr extension. The connection to The Things Network could be set by following this tutorial [https://www.thethingsnetwork.org/docs/devices/rak811-lora-module-breakout-board/connecting-to-ttn.html].

## Deployment

Please follow the set up file.

## Built With

* [Visual studio code](https://code.visualstudio.com/) - The IDE used
* [Python 3.8.0](https://www.python.org/) - The language used
* [IBM Cloud](https://www.ibm.com/cloud) - Used to deploy the Node-Red
* [The things network](https://www.thethingsnetwork.org/) - Used to interact with LoPy


## Authors

* **Theuns, Jolan** - *ACS students*
* **Heiden, Jeroen van der** - *ACS students* 
* **Nguyen, Tran Khanh Linh** - *ACS students* 
* **Dang, Quyen** - *ACS students* 
* **Klosinski, Antoine** - *ACS students* 


## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
