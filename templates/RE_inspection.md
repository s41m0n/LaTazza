# Inspection log

- [Inspection 8th April 2019](#inspection-8th-april-2019)
- [Inspection 5th April 2019](#inspection-5th-april-2019)

## Inspection 8th April 2019

| Attribute  | Value | 
| ----------------- |:-----------:|
| Date  | 8th April, 2019  |
| Place  | Politecnico di Torino  |
| Object of inspection  | Requirements document  |
| Moderator  | Daniele Palumbo  |
| Author  | Enrico Postolov  | 
| Reader  | Francesco Pavan  |
| Reader  | Giulia Milan  |
| Reader  | Riccardo Marchi |
| Scribe  | Simone Magnani |
| Start time  | 17:30  |
| End time  | 18:30  |


| Problem ID  | Location | Problem description | Status | Type | Gravity |
|:-----------:|:-----------:|:-----------:|:-----------:|:-----------:|:-----------:|
| 1 | Context Diagram | Visitor is defined as actor but has no interaction with the system itself | Closed  | Inconsistency/Contradiction | Major |
| 2 | Context Diagram | Missing APIs suppliers as actors in the context diagram | Closed  | Omission/Incompleteness | Major |
| 3 | UC 			  | Use cases format is not compliant with the given template  | Closed  | Omission/Incompleteness | Major |
| 4 | Stakeholders 	  | Missing capsules vendor in the stakeholder section | Closed  | Omission/Incompleteness | Minor |
| 5 | UCD			  | Missing actors and actions in the use case diagram | Closed  | Omission/Incompleteness | Major |
| 6 | System Design   | Software modules shall not be represented in this section (but in the software design)| Closed | Extraneous Information  |  Normal |


Domains of interest:

* Location: where in the document/code
* Status: open, assigned or closed
* Type:
	- Omission/incompleteness
	- Incorrect Fact
	- Inconsistency/contradiction
	- Ambiguity
	- Extraneous Information (overspecification/underspecification)
	- Un-reality
	- Un-verifiability
	- Un-traceability
* Gravity: minor, normal or major.

## Inspection 5th April 2019

| Attribute  | Value | 
| ----------------- |:-----------:|
| Date  | 5th April, 2019  |
| Place  | Politecnico di Torino  |
| Object of inspection  | Requirements document  |
| Moderator  | Simone Magnani  |
| Author  | Riccardo Marchi  | 
| Reader  | Nicola Amadei  |
| Reader  | Simone Peraccini  |
| Reader  | Enrico Postolov |
| Scribe  | Daniele Palumbo |
| Start time  | 14:30  |
| End time  | 16:30  |


| Problem ID  | Location | Problem description | Status | Type | Gravity |
|:-----------:|:-----------:|:-----------:|:-----------:|:-----------:|:-----------:|
| 1 | FR/NFR | There is no possibility to give manager privileges to another user | Closed  | Omission/Incompleteness | Normal |
| 2  | Glossary Diagram | The Person hierarchy leads you to understand that the system stores useless information concerning visitors  | Closed | Incorrect Fact  | Minor |
| 3  | Assumption Document | The assumption document is in italian, need to translate it | Closed | Extraneous Information | Minor  |
| 4  | System Design  | In spite he can, in the system design it is not explicitly shown that the Manager can manage the inventory | Closed | Omission/Incompleteness | Normal |
| 5  | Context Diagram  | The visitor cannot interact with the system itself, but he must refer to the manager | Closed | Inconsistency/Contradiction | Major |
| 6  | Interfaces | The specification mentioned LaTazza as a Desktop Application. Webpage is not needed. | Closed | Ambiguity | Normal |
| 7  | UCD | Use case diagram presents many inconsistency compared to the requirements | Closed | Inconsistency/Contradiction | Major |
| 8  | Requirements Document | The requirements document presents an unused section. Moreover the title shall be modified | Closed | Extraneous Information | Minor |
| 9 | FR/NFR | There is no possibility to delete an account and it is not fixed the max debt an employee could have | Closed | Omission/Incompleteness | Normal |
| 10 | UC | Missing use cases in the UC section | Closed | Omission/Incompleteness | Major |
| 11 | UC | Manager "manage inventory" use case is missing | Closed | Omission/Incompleteness | Normal |

Domains of interest:

* Location: where in the document/code
* Status: open, assigned or closed
* Type:
	- Omission/incompleteness
	- Incorrect Fact
	- Inconsistency/contradiction
	- Ambiguity
	- Extraneous Information (overspecification/underspecification)
	- Un-reality
	- Un-verifiability
	- Un-traceability
* Gravity: minor, normal or major.
