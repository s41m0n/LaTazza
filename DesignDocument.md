# Design Document Template

Authors: Daniele Palumbo, Magnani Simone, Marchi Riccardo, Postolov Enrico

Date: 18/04/2019

Version: 0.2

# Contents

- [Package diagram](#package-diagram)
- [Class diagram](#class-diagram)
- [Verification traceability matrix](#verification-traceability-matrix)
- [Verification sequence diagrams](#verification-sequence-diagrams)

# Package diagram

``` plantuml
@startuml
skinparam PackageBackgroundColor AntiqueWhite

package "Gui" {
  class UserInterface
}

package "LaTazzaSystem" {
  class LaTazza
}

package "Persistency" {
  class DataManager << (S,#FF7700) Singleton >>
  
  package "Storage" <<Database>> {
  }
}

package "Exceptions" {}

package "Entities" {
  class CapsuleTypeImpl
  class ColleagueImpl
  class Transaction
}

package "Data" {
  class DataImpl
}

Gui <-- LaTazzaSystem
Data <-- LaTazzaSystem
Exceptions <- LaTazzaSystem
LaTazzaSystem --> Entities
Persistency <-- LaTazzaSystem
DataManager --> Storage
@enduml
```

# Class diagram

```plantuml
@startuml
class LaTazza

class DataImpl {
	-sysBalance: Integer

	+DataImpl()
	+reset(): void
}

class Colleague {
	-id: Integer
	-balance: Integer
	-name: String
	-surname: String

	+Colleague(id: Integer, name: String, surname: String)
	+getId(): Integer
	+getName(): String
	+getSurname(): String
	+getBalance(): Integer
	+update(name: String, surname: String)
}

class CapsuleType {
	-id: Integer
	-quantity: Integer
	-capsulesPerBox: Integer
	-boxPrice: Integer
	-name: String

	+CapsuleType(id: Integer, name: String, 
		boxPrice: Integer, capsulesPerBox: Integer)
	+getPrice(): Integer
	+getId(): Integer
	+getQuantity(): Integer
	+getBoxPrice(): Integer
	+getName(): String
	+update(name: String, capsulesPerBox: Integer, 
		\tboxPrice: Integer)
}

class Transaction {
	-date: Date
	-amount: Integer

	+Transaction(date: Date, amount: Integer)
	+getDate(): Date
	+getAmount(): Integer
}

class BoxPurchase
class Recharge
class Consumption

LaTazza -- DataImpl
DataImpl -- "*" Colleague
DataImpl - "*" Transaction
DataImpl -- "*" CapsuleType

Colleague -- "*" Consumption
Colleague -- "*" Recharge
CapsuleType -- "*" Consumption
CapsuleType -- "*" BoxPurchase

Transaction <|-- Recharge
Transaction <|-- Consumption
Transaction <|-- BoxPurchase
@enduml
```

_**Note:**_

In order to realize an exhaustive and high level class diagram, this diagram does not display not only all the low level class-specific information (internal lists, class to manage save/load, etc.), but also all the given DataImpl methods, as their behaviour are comprehensible the same.

# Verification Traceability Matrix

Each functional requirement described in the Requirement Document has a reference to one or more classes of the class diagram. They are listed here below.


|  | Class 1 | Class 2  | Class 3|  Class 4| Class 5|
| ------------- |:-------------:| :-----:| :-----:| :-----:| :-----:|
| FR1   | Colleague |Transaction | CapsuleType | DataImpl | - |
| FR2  | CapsuleType | Transaction | DataImpl | -  | - |
| FR3 | Colleague | Transaction | DataImpl| - | - |
| FR4 | CapsuleType |Transaction | DataImpl| - | - |
| FR5 | Colleague |Transaction | DataImpl| -  | - |
| FR6 |Transaction | DataImpl | - | -  | - |
| FR7 | CapsuleType | DataImpl | -| - | - |
| FR8 | Colleague| DataImpl | -| - | - |

# Verification Sequence Diagrams 
The selected key scenario is "Scenario 1" from the Requirement Document,in which a quantity of capsules is sold to an Employee (Colleague).

```plantuml
@startuml
"DataImpl" -> "CapsuleType": "1: getBeverage()"
"CapsuleType" -> "DataImpl": "capsuleId"
"DataImpl" -> "Colleague": "2: getColleague()"
"Colleague" -> "DataImpl": "colleagueId"
"DataImpl" -> "Transaction": "3: sellCapsules(colleagueId, capsuleId, quantity)"
"Transaction" -> "CapsuleType": "4: updatedCapsuleQuantity(capsuleId)"
"Transaction" -> "Colleague": "5: updateEmployeeAccount(colleagueId)"
@enduml
```
