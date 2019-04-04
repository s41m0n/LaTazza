# Requirements Document Template

Authors:
- Daniele Palumbo
- Enrico Postolov
- Riccardo Marchi
- Simone Magnani

Date: 28/03/2019

Version: 0.2

# Contents

- [(Daniele) Stakeholders](#stakeholders)
- [(Enrico) Context Diagram and interfaces](#context-diagram-and-interfaces)
	+ [Context Diagram](#context-diagram)
	+ [Interfaces](#interfaces) 
	
- [(Riccardo) Stories and personas](#stories-and-personas)
- [(Simone) Functional and non functional requirements](#functional-and-non-functional-requirements)
	+ [Functional Requirements](#functional-requirements)
	+ [Non functional requirements](#non-functional-requirements)
- [(Daniele, Enrico) Use case diagram and use cases](#use-case-diagram-and-use-cases)
	+ [Use case diagram](#use-case-diagram)
	+ [Use cases](#use-cases)
	+ [Relevant scenarios](#relevant-scenarios)
- [(Riccardo) Glossary](#glossary)
- [(Simone, Riccardo) System design](#system-design)

# Stakeholders


| Stakeholder name  | Description | 
| ----------------- |:-----------:|
|Manager           | Super user of the system, sells capsules, manages the inventory, manages the credits and the debts of employees | 
|Owner         	  |  Provides online system and supplies it to the manager           | 
|Employee         | Drinks coffee / tea and can use its local account | 
|Visitor             |  Drinks coffee / tea |
|System Admin |   Manages web system          | 
|Inventory Admin |Manages LaTazza warehouse|

# Context Diagram and Interfaces
## Context Diagram

The context diagram can be derived.

- The principal actors in the system are the following:

	- **Manager**
	- **Employee**
	- **Visitor**

The system itself has references to the inventory for the capsules, so the latter is not needed in the representation.	

```plantuml
left to right direction
skinparam packageStyle rectangle
actor Employee as e
actor Visitor as v
actor Manager as m

rectangle System {
  (LaTazza) as lt
  e--lt: "Service usage"
  v--lt: "Service usage"
  lt--m: "Service guarantee"
}
```

## Interfaces
The following interfaces are needed for the realization of the system.

| Actor | Logical Interface | Physical Interface  |
| ------------- |:-------------:| -----:|
| Manager     | Access to system | GUI/Web page |
| Employee     | Access to system | GUI/Web page |
| Visitor     | Access to system | GUI/Web page |
| Banking System     | Perform payments | Web Service using APIs |

Each actor among Manager, Employee and Visitor will exploit a different type of account, more in particular:

	1. General/Admin for Managers
	2. Local for Employees
	3. Visitor for Visitors
Of course, each account will manage the users in different ways with different permissions.

# Stories and Personas


Rachel Green (Manager)

46, secretary at "Senses & Feelings Corporation", mother of two children

As the secretary of the company, Rachel has been picked to manage the LaTazza system, to maintain the order in the usage of the coffee maker and to do the required actions to keep the capsules supply always available. She also has the job to sell capsules to those visitors who want make use of it.

It is the end of the week and Rachel notices from the inventory of the application that the supply of the Arabic cofee capsules is almost finished and she has to immediately put an order of some boxes of them to avoid any lack the following week.
Once logged in, she selects the type of capsules and the quantity to order, raising up the load compared to the previous time, due to the fast consumption. Then she buys them with her own money.
As soon as the payment has been done, she is able to keep filling the papers her boss told her to before the end of her working week.

---

Ted Mosby (Employee)

25, architect at "Building Stuff Association", single and living alone

Being a young man at his first experience in living alone, Ted is not used to have breakfast at home and his being late tendency does not let him have it too. So it is essential for him to have a coffee maker at work to charge the batteries in view of a long working day.

It is monday morning.
After a rough weekend out of town with his friends, Ted needs to gain some concentration with a coffee, but he has neither a capsule nor any cash in his account. After logging in, he recharges its balance and then he buys 15 capsules, hoping that they will be enough for the entire week. Later he drinks his daily morning coffee and chitchats with his colleague before the start of the shift.
After the energy refill he heads to his office and he is ready to start to work on the new project commissioned.

---

Meredith Grey (Visitor)

69, retired, widow

Meredith is not a patient woman, so when she has a fixed appointment for her checkups, she does not want to wait further than the expected. The only way to make her waiting better is a hot cup of tea, her favourite beverage.

It is Wednesday afternoon.
As any other time, Meredith is on time for her monthly checkup at the private clinic, but the doctors are not ready yet. Therefore she needs a cup of tea to spend the time while waiting to be visited.
Meredith asks for it to the front-desk girl and she sells her a Lemon-tea capsule. Once payed for it, she gets her tea and enjoys the delay in a relaxed way.
The time lost was not so terrible with that beverage and now she is even more ready and calm to be supervised by the medical crew.


# Functional and non functional requirements
## Functional requirements

| ID        | Description  |
| ------------- |:-------------:| 
|  FR1    	 |  Create an account |
|  FR2    	 |  Login |
|  FR3    	 |  Logout |
|  FR4       |  Sell/Buy capsules | 
|  FR5       |  Order capsules from vendor | 
|  FR6       |  Manage account |  
|  FR6.1     |  Add/Remove payment method |  
|  FR6.2     |  Buy credits |
|  FR6.3     |  Pay off debts |
|  FR7       |  Manage inventory |
|  FR8       |  Manage cash account |
|  FR9     	 |  Manage personal account |  
|  FR10    	 |  Log each transaction  |

## Non Functional Requirements

| ID        | Type (efficiency, reliability, ...)           | Description  | Refers to |
| ------------- |:-------------:| :-----:| -----:|
|  NF1     	|  Domain 	   | Accepted currency shall be only €				 							| FR4 |
|  NF2     	|  Domain 	   | Accepted cash shall be > 0.5€					 							| FR4 |
|  NF3     	|  Domain 	   | The maximum capsules an employee can buy shall be less than 100 			| FR4 	| 
|  NF4     	|  Domain 	   | The maximum capsules a visitor can buy shall be 1				 			| FR4 	| 
|  NF5     	|  Reliability | Cash machine shall fail less than 2% of all the times 						| FR4 | 
|  NF6     	|  Reliability | Log failures shall be less than 1% of all the transactions					| FR10 |
|  NF7      |  Performance | Application startup and changing menu shall require less than 3 seconds 	| FR[1-10] | 
|  NF8      |  Performance | Server response during transaction shall be less than 1 seconds		 	| FR[1-10] | 
|  NF9    	|  Portability | Modules to be changed shall be less than 50% 								| FR[1-10] |
|  NF10     |  Usability   | The software shall require less than 15 minutes to be learnt 				| FR[1-10] | 
|  NF11     |  Security    | To break into the system a high-skilled hacker shall take more than 1 week | FR[1-10] |
|  NF12     |  Operating   | System resources required shall be less than 1GB 							| FR[1-10] | 
|  NF13    	|  Reliability | System downtime shall be less than 1 hour per day 							| FR[1-10] | 
|  NF14     |  Privacy     | Private data shall be preserved 											| FR1, FR2, FR3, FR4. FR5, FR6 |
|  NF15     |  Legislation | Transactions shall be stored for 5 years 									| FR10 |


# Glossary


```plantuml
class Person {
  fiscalCode
  name
  surname
}

class Manager {
}

class Employee {
  ID
}

class Visitor {
}

class Capsule {
  ID
  type
}

class Box {
  ID
}

class Vendor {
  ID
  companyName
}

Employee <|-- Manager
Person <|-- Employee
Person <|-- Visitor
Vendor "1" -- "*" Box : owns
Box o-- Capsule : contains
Manager "1" -- "*" Capsule : sells
Manager "1" -- "*" Box : orders
Person "1" -- "*" Capsule : buys
```

# Use case diagram and use cases

## Use case diagram

``` plantuml
left to right direction

actor Employee as e
actor Visitor as v
actor Manager as m

(Buy capsules) as bc
(Sell capsules) as sc
(Order capsules from vendor) as oc
(Manage inventory) as mi
(Add/Remove payment method) as pm
(Buy credits) as b
(Pay off debts) as p
(Manage cash account) as mca
(Manage personal account) as mpa
(Update inventory) as bi
(Log each transaction) as lt

e -- bc
bc -- v
e -- b
e -- p
e -- mpa
sc -- m
mpa -- m
mca -- m
mi -- m
bc .> sc : include
b .> pm : include
p .> pm : include
bc .> bi : include
mi .> bi : include
mi .> oc : include
bc .> lt : include
```

## Use cases



## Relevant scenarios

### 1. Succesful capsules purchase from Employee

<u>PRECONDITION:</u> The Employee should have a local account. If this is verified, it is also necessary that the user has a sufficient credit on its account or that he has sufficient cash with him to pay the capsules.

<u>POSTCONDITION:</u> The caspule bought by the Employee should be removed from the Inventory and the latter should update its availability in real time.

| SCENARIO ID: Sc1        | Corresponds to UC: Buy capsules  |
| :-------------: |:-------------:| 
|  Step#    	 |  Description |
|  1    	 |  Login on local account|
|  2    	 |  Select capsule type |
|  3       |  Select amount of capsules |
|  4       |	 Pay with account credit/cash |
|  5       |  System adds the purchase if payment succesful and decreases user credits if necessary|
|  6       |  System updates the inventory |

---

### 2. Succesful inventory management

<u>PRECONDITION:</u> There must be a valid Manager account.

<u>POSTCONDITION:</u> The amount of boxes ordered by the Manager should be added to the inventory and thus increase the capsule availability in the system.

| SCENARIO ID: Sc2        | Corresponds to UC: Manage inventory  |
| :-------------: |:-------------:| 
|  Step#    	 |  Description |
|  1    	 |  System realizes that only one box of a capsule type is present in the inventory|
|  2    	 |  System notifies Manager about the missing|
|  3       |  Manager orders a number of boxes from the vendor |
|  4       |	 Pay with account credit/cash for the order |
|  5       |  Wait for order completion |
|  6       |  Set of boxes arrives |
|  7       |  System records the purchase |
|  8		 |  Boxes placed in the inventory |
|  9		 |  System updates capsule availability for future purchases|

---

### 3. Credits purchase by Employee

<u>PRECONDITION:</u> The Employee must have a local account on the system.

<u>POSTCONDITION:</u> The amount of credits requested by the Employee should be added to its account, in order to be used for future purchases.

| SCENARIO ID: Sc3        | Corresponds to UC: Buy credits  |
| :-------------: |:-------------:| 
|  Step#    	 |  Description |
|  1    	 |  Login on local account|
|  2    	 |  Add a payment method for credits if not yet present on system |
|  3       |  System saves the selected payment method |
|  4       |  Select amount of credits to buy |
|  5       |	 Pay with the provided payment method |
|  6       |  System adds the credits to the local account |


