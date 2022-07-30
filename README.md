# SmartAccess
Android application to communicate with Firebase via Wi-Fi and an ATmega328P
via Bluetooth. This was part of my team's work on designing a 'Smart Village'
in EC338 (mini-project in microprocessor and embedded system) at NITK Surathkal
in 2017.

## My Team
* Barun Kumar Acharya
* Tanvi Girinath
* Vishal Pankaj Chandratreya

## Application Features

### Main menu
Asks for the user's credentials to log in. The credentials are compared against
those stored in a Firebase database (which is no longer active, though).

### User's Details
Displays the user's personal details.

### Sensor Information
Raw sensor readings (of temperature and moisture) are retrieved by an
ATmega328P and transmitted to this application via Bluetooth. A motor (supposed
to represent sprinklers) is automatically activated by the Atmega328P if the
temperature rises or moisture drops. (I have lost the microcontroller
code.:sweat_smile:)

### Home Automation
Turns lights in the user's 'house' on or off.

### Banking
Shows the user's current bank balance. Transfers funds (not real money) from
the user's account to another. Each user's bank balance is simply a number
stored in the aforementioned database.

### Medicines
'Purchases' medicines and updates the database with a purchase event. A Python
program (supposed to be running on a Raspberry Pi 'server') monitoring the
database would then display the details on the screen. (I seem to have lost
that script as well!:sweat_smile:)

### Emergency Services
Sends a report to the server in the event of an emergency.
