# weathercorp

weathercorp is a weather station project, containing a LoPy sensornode, to capture every minute: temperature, ambient light and/or barometric pressure. The data is then transmitted wirelessly, using LoRa, through a gateway, to The Things Network. Using NodeRED we retrieve the data and store it in a SQL database. Upon request the end-user can display all weather data graphically in a user interface, being an Android mobile app and a website.
This repository is used to store all of the works of this project, composing the code for LoPy, NodeRED, the website and the mobile app.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Node.js and your preferred Python 3 compiler must be installed. The connection could be set by logining to the The Things Network, filling in the necessary credentials and in the “Console” tab, pick “Application”. Press the “add application” button.
Create your own Application by filling up with correct contents.

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.


## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.


## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
