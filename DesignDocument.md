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

# Verification traceability matrix

\<for each functional requirement from the requirement document, list which classes concur to implement it>


|  | Class x | Class y  | .. |
| ------------- |:-------------:| -----:| -----:|
| Functional requirement x  |  |  | |
| Functional requirement y  |  |  | |
| .. |  |  | |

# Verification sequence diagrams 
\<select key scenarios from the requirement document. For each of them define a sequence diagram showing that the scenario can be implemented by the classes and methods in the design>

```plantuml
": Class X" -> ": Class Y": "1: message1()"
": Class X" -> ": Class Y": "2: message2()"
```
