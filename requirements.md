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


# Stories and Personas

<Manager>
Rachel Green

46, secretary at "Senses & Feelings Corporation", mother of two children

As the secretary of the company, Rachel has been picked to manage also the LaTazza system, to maintain the order in the usage of the coffee maker and to do the required actions to keep the capsules supply always available. She also has the job to sell capsules to those visitors who want make use of it.

<Employee>
Ted Mosby

25, architect at "Building Stuff Association", single and living alone

Being a young man at his first experiences in living alone, Ted is not used to have breakfast at home and his being late tendency does not let him have it too. So it is essential for him to have a coffee maker at work to charge the batteries in view of a long working day.

<Visitor>
Meredith Grey

69, retired, widow

Meredith is not a patient woman, so when she has a fixed appointment for her checkups, she does not want to wait further than the expected. The only way to make her waiting better is a hot cup of tea, her favourite beverage.