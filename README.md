# Car Leasing Service

REST API for car leasing. Through this you can apply for leasing by sending information about vehicle, personal data including your income and requested funding amount. Credit application will be approved automatically, if your income is not lower than 600eur in family per person, ottherwise it will be rejected. It is also possible to retrieve information about previously processed credit applications.

### How to run:

Run with Docker: `docker goes here`

Run with Maven: `maven goes here`

----
### Endpoints:

#### POST /api/carleasing/apply
* takes application form in JSON body and returns processed application with the results.

#### Application JSON Object fields (all are required):
**make**:String - car make

**model**:String - car model

**year**:Integer - car year

**fuelType**:String - fuel type. Possible inputs - "diesel", "diesel/electric", "electric", "ethanol", "gas", "gasoline", "gasoline/electric", "petrol/gas", "petrol/natural gas". Anything else will be coded as "other".

**damage**:String - car damage state. Possible inputs - "crashed", "engine", "fire", "gearbox", "hail", "water", "no damage". Anything else will be coded as "other".

**firstName**:String - person first name

**lastName**:String - person last name

**socialSecurityNumber**:String - person social security number

**dependents**:Integer - number of dependents of the person

**monthlyIncome**:Integer - person monthly income

Example request (assuming application is running locally on port 8080):

`curl -X POST -H "Content-Type: application/json" -d '{"amount" : 5000, "make" : "Ford", "model" : "Focus", "fuelType" : "diesel", "damage" : "no damages", "year" : 1999, "firstName" : "Johny", "lastName" : "Dough", "socialSecurityNumber" : "196604201234", "dependents" : 0, "monthlyIncome" : 1050}' http://localhost:8080/api/carleasing/apply`

Example response:
![carleasingsc1](https://user-images.githubusercontent.com/49102436/89534585-d8125800-d7fd-11ea-829c-2b3b78d7b095.jpg)
----
#### GET /api/carleasing/application/{id}
* finds and returns processed application by id number.

Example request (assuming application is running locally on port 8080):

`curl -X GET http://localhost:8080/api/carleasing/application/3`

Example response:
![carleasingsc1](https://user-images.githubusercontent.com/49102436/89534585-d8125800-d7fd-11ea-829c-2b3b78d7b095.jpg)
