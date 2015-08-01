Taxi Management System
================
## About
The system supports taxi management through android devices located on taxicabs.In addition, this system facilitates communication between operator and taxi drivers using voice IP system supported SIP protocol. Client's taxi request is optimizes.
![GUI](https://github.com/hunglkuit/Taxi_Management_System/blob/master/res/drawable-xhdpi/taxi_system_overview.png)

## Features
- Mamage real-time  taxi's location on Google Map view.
- Communication via sip protocol.
- Calling taxi via customer application.
- Optimize taxi request procedure. 
- Optimize route path for taxi driver to take client.
- Optimize delivery process.

## General Idea
Reconizing significant increasing of mobile device, we propose an innovation idea for taxi system. Each taxi cabs will be assigned with a mobile device which have reponsible collecting taxi information such as location, speed, taxi state... All of this information will be sent to server. Base on it, operator has an overview about whole taxi system.
![GUI](https://github.com/hunglkuit/Taxi_Management_System/blob/master/res/drawable-xhdpi/taxi_system__server_overview.png)
![GUI](https://github.com/hunglkuit/Taxi_Management_System/blob/master/res/drawable-xhdpi/taxi_system__client_overview.png)
## Serving customer's request process:
1. Customer opens customer application, login by their account and  press the button. Application will automatically get customer's location and send to serveer.
2. After receiving customer request, server will send this request to the nearest and empty taxi. 
3. Notification about customer reqest will be shown on driver application. If driver accept, the route to reach customer will be shown. Otherwise, request is transfer to second satisfied taxi.

