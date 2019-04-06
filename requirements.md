# Requirements Document

Authors:
- Daniele Palumbo
- Enrico Postolov
- Riccardo Marchi
- Simone Magnani

Date: 28/03/2019

Version: 0.5

# Important information

Because of previous GIT configurations on the machines of a part of the team, some commits visible through the GitLab interface refer to usernames not associated with our student ID.
Here's the general mapping with the student numbers:

| Commit Author Name| Real Name | Student# | 
| ----------------- | ----------|:-----------:|
|CavalloCrop     | Daniele Palumbo | 257603|
|Enrico Postolov | Enrico Postolov  | 262745|
|Simone Magnani     | Simone Magnani | 265171|
|Riccardo Marchi | Riccardo Marchi  | 265140|


# Contents

- [Stakeholders](#stakeholders)
- [Context Diagram and interfaces](#context-diagram-and-interfaces)
	+ [Context Diagram](#context-diagram)
	+ [Interfaces](#interfaces) 
	
- [Stories and personas](#stories-and-personas)
- [Functional and non functional requirements](#functional-and-non-functional-requirements)
	+ [Functional Requirements](#functional-requirements)
	+ [Non functional requirements](#non-functional-requirements)
- [Use case diagram and use cases](#use-case-diagram-and-use-cases)
	+ [Use case diagram](#use-case-diagram)
	+ [Use cases](#use-cases)
	+ [Relevant scenarios](#relevant-scenarios)
- [Glossary](#glossary)
- [System design](#system-design)

# Stakeholders


| Stakeholder name  | Description | 
| ----------------- |:-----------:|
|Manager           | Super user of the system, sells capsules, manages the inventory, manages the credits and the debts of employees | 
|Owner         	  |  Provides online system and supplies it to the manager           | 
|Employee         | Drinks coffee / tea and can use his local account | 
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
  lt--m: "Service guarantee"
  v--m: "Service usage"
```

## Interfaces
The following interfaces are needed for the realization of the system.

| Actor | Logical Interface | Physical Interface  |
| ------------- |:-------------:| -----:|
| Manager     | Access to system | GUI |
| Employee     | Access to system | GUI |
| Visitor     | Capsule purchase | Manager |
| Banking System     | Perform payments | Web Service using APIs |

Each actor among Manager and Employee will exploit a different type of account, more in particular:

	1. General/Admin for Managers
	2. Local for Employees
Of course, each account will manage the users in different ways with different permissions.
For what concerns the Visitor, he doesn't own a local account on the system, thus he'll refer to the Manager anytime he wants to perform a purchase.

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
|  FR4       |  Buy capsules | 
|  FR5       |  Order capsules from vendor | 
|  FR6       |  Manage account |  
|  FR6.1     |  Add/Remove payment method |  
|  FR6.2     |  Buy credits |
|  FR6.3     |  Pay off debts |
|  FR6.4	 |	Leave Privileges |
|  FR6.5	 |	Delete employee account |
|  FR7       |  Manage inventory |
|  FR8       |  Manage cash account |
|  FR9       |  Sell capsules |  

## Non Functional Requirements

| ID        | Type (efficiency, reliability, ...)           | Description  | Refers to |
| ------------- |:-------------:| :-----:| -----:|
|  NF1     	|  Domain 	   | Accepted currency shall be only €				 							| FR4, FR9 |
|  NF2     	|  Domain 	   | The maximum capsules an employee can buy shall be 100			 			| FR4, FR9 	| 
|  NF3      |  Domain 	   | In the system there shall be only 1 manager 							 	| FR6.5 |
|  NF4      |  Domain 	   | An account to be deleted shall have 0 € of debts 							| FR6.5 |
|  NF5      |  Domain 	   | A user shall have less than 20 € of debts 									| FR4, FR9, FR6.3 |
|  NF6     	|  Reliability | Log failures shall be less than 1% of the total logged activities			| FR[1-10] |
|  NF7    	|  Reliability | System downtime shall be less than 30 minutes per day 						| FR[1-10] | 
|  NF8      |  Performance | Application startup shall require less than 5 seconds 						| FR[1-10] | 
|  NF9      |  Performance | Server response during transaction shall be less than 3 seconds		 	| FR[1-10] | 
|  NF10    	|  Portability | Modules to be changed shall be less than 50% 								| FR[1-10] |
|  NF11     |  Usability   | The software shall require less than 15 minutes to be learnt 				| FR[1-10] | 
|  NF12     |  Security    | To break into the system a high-skilled hacker shall take more than 1 week | FR[1-10] |
|  NF13     |  Operating   | System resources required shall be less than 1GB 							| FR[1-10] | 
|  NF14     |  Legislation | Transactions shall be stored for 5 years 									| FR4, FR5, FR6.2, FR8, FR9 |
|  NF15     |  Privacy     | Private data shall be preserved 											| FR1, FR2, FR3, FR4. FR5, FR6, FR9 |


# Use case diagram and use cases

## Use case diagram

``` plantuml
left to right direction

actor Employee as e
actor Visitor as v
actor Manager as m

rectangle LaTazzaSystem {
	(Buy capsules) as bc
	(Sell capsules) as sc
	(Add/Remove payment method) as pm
	(Delete account) as da
	(Leave privileges) as lp
	(Login) as l
	(Create an account) as ca
	(Buy credits) as b
	(Pay off debts) as p
	(Manage personal account) as mpa
	(Manage user account) as ma
	(Manage Inventory) as mi
	(Order capsules) as oc


	m -- bc
	m -- sc
	e -- ca
	e -- bc
	e -- mpa
	m  -- ma
	m -- mi
	v -- bc
	mpa .> l : include
	oc .> mi : extend
	lp .> ma : extend
	da .> ma : extend
	ma .> mpa : extend
	pm .> mpa : extend
	b .> mpa : extend
	p .> mpa : extend
}
```

## Use cases

| ACTOR | GOAL | BRIEF |
| --- | --- | --- |
| Visitor | Buy capsules | The visitor  wants to buy one or more capsules from the Manager. He can only pay by cash. The system logs the sell (the manager has to perform the sale using the application) and updates automatically the inventory.|
| Employee | Create an account | Before enjoying LaTazza system, the employee shall create his own account using his company ID. |
| Employee | Buy capsules | The employee wants to buy one or more capsules (max 100) from LaTazza system. He can pay them through cash, or with credits from his account or by creating a debt (max 20 €) on his account. After the purchase, the order is added to the employee's account and the system inventory is automatically updated.  |
| Employee | Account management | The Employee can modify his public and private information (validation required), he can add credits or pay debts doing a payment by cash or digital system. The system logs the payment and updates the account status. |
| Manager | Buy capsules | The manager wants to buy one or more capsules from LaTazza system. Since when he orders capsules from vendors he shall pay with his own bank account, he theoretically already earns them, but to make the system work properly he shall use his application (he will not pay for them) in order to automatically update the inventory state. |
| Manager | Sell capsules | The manager is the only one who can physically send the capsules to visitors. Since the inventory need to be updated, his desktop application is provived with a generical sell function that will be used in those cases.
| Manager | Inventory management | The Manager is able to check the inventory status and to see how many capsules remain for each beverage type. If necessary, he can order from vendors more boxes of capsules. The system saves the transaction and updates the inventory. The system can also notify the Manager when a type of capsules is finishing. He also has the ability to see all previous transactions and previous sells of capsules. |
| Manager | Account management | As all the other users, the manager can modify his own account and data. |
| Manager | User Account Management | The manager has the privileges to manage other users account. He not only can delete an account (ex. if an employee is fired), but also leave his privileges to another user (ex. if the manager wants to quit). Finally, when an employees pays off his debt using cash, the manager can update his account by zeroing the debt. |

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


# Glossary


```plantuml
class Person {
}

class Manager {
}

class Employee {
  ID
  name
  surname
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

# System Design

```plantuml
class LaTazzaSystem {
}

class Server {
}

class Software {
}

class AccountManager {
}

class TransactionManager {
}

class LoginManager {
}

Class InventoryManager {
}

LaTazzaSystem o-- "1" Server
Server -- Software
Software o-- "1" AccountManager
Software o-- "1" TransactionManager
Software o-- "1" LoginManager
Software o-- "1" InventoryManager
```

