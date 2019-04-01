# Requirements Document Template

Authors:
- Daniele Palumbo
- Enrico Postolov
- Riccardo
- Simone

Date: 28/03/2019

Version: 0.1

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
	- **Banking System**, which permits the payments to be performed.

The system itself has references to the inventory for the capsules, so the latter is not needed in the representation.	

![alt text] [image]
[image]: ContextDiagram.png


