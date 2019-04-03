# Requirements Document Template

Authors:
- Daniele Palumbo
- Enrico Postolov
- Riccardo
- Simone

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
- [(Simone, Riccardo) (System design](#system-design)

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
|  NF1     |  Domain | Accepted cash shall be only euro | FR4 |
|  NF2     |  Legislation | Transactions shall be stored for 5 years | FR10 |
|  NF3     |  Privacy | Private data shall be preserved | FR1, FR2, FR3, FR6 |

## Inverse User Requirements

Following situations should be avoided.

| ID        | Description  |
| ------------- |:-------------:| 
|  IF1     |  Payment shall not be performed in the case the required n° of capsules is not available |
|  IF2     |  When the server is unreachable, the application should not allow users to perform any operation | 
