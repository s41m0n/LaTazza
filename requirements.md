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