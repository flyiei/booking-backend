# Hotel Reservation App

## BACKEND Requirement 
* The Booking API would be a hotel booking service that allows you to list, create, update and delete a reservation.
* Basic error handing, logging.
* Create a README detailing instructions on how to run and call the API4.
* Write appropriate unit and Integration tests
* An agency might have several hundred bookings how would solve this issue through API
* Create a Spingboot application running within a docker container on port 8080 using Java17 and Jersey using an in-memory H2 database

## FRONTEND Requirement
* Create a frontend serving the above API. Do the best you can considering the time constraints.
* Create a React interface using the MaterialUI library.

# Run Project

## Start booking backend app: booking-backend 

https://github.com/flyiei/booking-backend

```zsh
# Make sure your docker is running !

# give exe permission to create_docker_image.sh 
chmod +x create_docker_image.sh

# create_docker_image.sh will create docker image
./create_docker_image.sh

# build and run the containers use 'docker-compose up -d' 
# if the app need to run at background
docker-compose up

# Stop and remove the containers:
docker-compose down
```

## Access to H2-console
http://localhost:8080/h2-console
username: sa 
no password
jdbc url(app run from ide): jdbc:h2:mem:testdb
jdbc url(app run from docker): jdbc:h2:mem:testdb

## Start booking ui app: booking-ui 
https://github.com/flyiei/booking-ui
Check README.md in booking-ui project 

# Booking Backend APIS
## GET /api/v1/reservations
* Response
```json
[
    {
        "id": 1,
        "agency": "agency1",
        "hotel": "hotel1",
        "reserveDate": null,
        "consumer": "consumer1"
    }
]
```
## GET /api/v1/reservations/{id}
* Response
```json
{
    "id": 1,
    "agency": "agency1",
    "hotel": "hotel1",
    "reserveDate": "reserveDate1",
    "consumer": "consumer1"
}
```

## UPDATE /api/v1/reservations/{id}
* Request
```json
{
    "id" : "1",
    "agency": "agency1",
    "consumer": "consumer1",
    "hotel": "hotel1"
}
```
* Response
```json
{
  "id" : "1",
  "agency": "agency1",
  "consumer": "consumer1",
  "hotel": "hotel1"
}
```

## POST /api/v1/reservation
```json
{
  "agency": "agency1",
  "consumer": "consumer1",
  "hotel": "hotel1"
}
```
* Response
```json
{
  "id" : "1",
  "agency": "agency1",
  "consumer": "consumer1",
  "hotel": "hotel1"
}
```