# Railway system database project

## Assumptions:
The project models 4 things:
- Stations
- Tracks, connecting stations
- Trains, with ID and name
- Train schedules

It stores the following information:
- Time in
- Time out

Create API where consumer can:
- check schedule on train station
- check the schedule of particular train(it should contain info about times on specific stations)

## Key points:
- Java 17
- Spring 5
- Gradle
- Lombok
- JUnit
- Mockito
- ConcurrentHashMap database for units test
- H2 database for integrations test
- Postgres database for production

## Run
To build project, please use:
```
./gradlew clean build

```

To run program after build:
```
java -jar build/libs/RailwaySystemDatabase-1.0-SNAPSHOT.jar
```
Or run them from the IDE.

## Test
To execute tests:
```
gradle test
```
Or run them from the IDE.

After above execution, service will start at port 8080.

## Sample requests:

Example request:
```
curl -X GET \
  http://localhost:8080/api/all-tracks
```
Example response:

```
200 OK

{
    "content": [
        {
            "trackId": {
                "value": "55a4d2f5-5874-4b9c-957d-7ef72bc46004"
            },
            "trainId": {
                "value": "2237bf5c-d4e4-4af2-b36e-4f6357ee5ead"
            },
            "sourceStationId": {
                "value": "b155c018-618b-4869-9be5-56c17f9b6b3f"
            },
            "destinationStationId": {
                "value": "eceafca2-182e-493c-b4fe-500a1b2cfeb5"
            },
            "timeIn": "08:39:00",
            "timeOut": "12:39:00"
        },
        {
            "trackId": {
                "value": "442b29a2-1cd3-4060-8b29-ba0d95f04783"
            },
            "trainId": {
                "value": "8ce6881f-091e-4ce7-9cc0-b1e6c03b40c0"
            },
            "sourceStationId": {
                "value": "eceafca2-182e-493c-b4fe-500a1b2cfeb5"
            },
            "destinationStationId": {
                "value": "48e2f7c7-89a2-44f4-84a7-722d902638f9"
            },
            "timeIn": "11:39:00",
            "timeOut": "13:39:00"
        },
        {
            "trackId": {
                "value": "9482b616-35c1-41c8-94b1-fb8328099722"
            },
            "trainId": {
                "value": "e55ce215-e72f-4bd2-87a0-a1579f58a61b"
            },
            "sourceStationId": {
                "value": "48e2f7c7-89a2-44f4-84a7-722d902638f9"
            },
            "destinationStationId": {
                "value": "1487501a-bb04-47d6-aeda-f85a935eac02"
            },
            "timeIn": "12:39:00",
            "timeOut": "14:39:00"
        },
        {
            "trackId": {
                "value": "adf2a0f7-1620-4404-88db-511403bce718"
            },
            "trainId": {
                "value": "1fa06545-6ada-49f8-ab5c-d7c9f3657769"
            },
            "sourceStationId": {
                "value": "1487501a-bb04-47d6-aeda-f85a935eac02"
            },
            "destinationStationId": {
                "value": "c3d02edd-4cb9-4290-a43e-7e647ca1fcb9"
            },
            "timeIn": "13:39:00",
            "timeOut": "15:39:00"
        },
        {
            "trackId": {
                "value": "80b70b21-3133-49f0-a830-17fa0b0645a0"
            },
            "trainId": {
                "value": "071f3796-b95a-480c-9532-54968ff294a6"
            },
            "sourceStationId": {
                "value": "c3d02edd-4cb9-4290-a43e-7e647ca1fcb9"
            },
            "destinationStationId": {
                "value": "eb9d8bc1-b811-4fd8-9e43-9b2b743ae6f3"
            },
            "timeIn": "14:39:00",
            "timeOut": "16:39:00"
        },
        {
            "trackId": {
                "value": "74ec79e8-b855-4cd8-a8b4-f5bd99f5639e"
            },
            "trainId": {
                "value": "d0d4e597-089f-45ec-844c-7ce6b10217e0"
            },
            "sourceStationId": {
                "value": "eb9d8bc1-b811-4fd8-9e43-9b2b743ae6f3"
            },
            "destinationStationId": {
                "value": "3e5c92ce-ddc5-4bc8-a877-72b3b651069b"
            },
            "timeIn": "15:39:00",
            "timeOut": "17:39:00"
        },
        {
            "trackId": {
                "value": "372d054a-c554-4a72-b6c5-4c8efdb3b20d"
            },
            "trainId": {
                "value": "0eed651d-9fe3-4e5e-9b4e-f57015290cb6"
            },
            "sourceStationId": {
                "value": "3e5c92ce-ddc5-4bc8-a877-72b3b651069b"
            },
            "destinationStationId": {
                "value": "26b5aeb7-3363-435a-b2b2-bc2b01b30df0"
            },
            "timeIn": "16:39:00",
            "timeOut": "18:39:00"
        },
        {
            "trackId": {
                "value": "ccc022aa-d555-4fab-9aa5-af2063d867d7"
            },
            "trainId": {
                "value": "a5af8c71-07f7-40eb-837c-be751c0aa945"
            },
            "sourceStationId": {
                "value": "26b5aeb7-3363-435a-b2b2-bc2b01b30df0"
            },
            "destinationStationId": {
                "value": "9f94a4b4-ced3-4d2c-a75b-ea6cb30cbaa3"
            },
            "timeIn": "17:39:00",
            "timeOut": "19:39:00"
        },
        {
            "trackId": {
                "value": "a0a87575-b0d5-4306-a24c-4081124694e8"
            },
            "trainId": {
                "value": "9ab25f7d-ae40-4af6-9cd2-50f3fd1777d5"
            },
            "sourceStationId": {
                "value": "9f94a4b4-ced3-4d2c-a75b-ea6cb30cbaa3"
            },
            "destinationStationId": {
                "value": "b155c018-618b-4869-9be5-56c17f9b6b3f"
            },
            "timeIn": "18:39:00",
            "timeOut": "20:39:00"
        }
    ],
    "pageable": {
        "sort": {
            "empty": true,
            "unsorted": true,
            "sorted": false
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 20,
        "paged": true,
        "unpaged": false
    },
    "totalPages": 1,
    "totalElements": 9,
    "last": true,
    "size": 20,
    "number": 0,
    "sort": {
        "empty": true,
        "unsorted": true,
        "sorted": false
    },
    "numberOfElements": 9,
    "first": true,
    "empty": false
}
```
## Database H2 access

H2 console available at '/console'
Example access to console:
```
http://localhost:8080/console
```
```
JDBC URL: 'jdbc:h2:mem:testdb'
User Name: sa
Password: sa
```