# Design Document Template

Authors: Palumbo Daniele, Magnani Simone, Marchi Riccardo, Postolov Enrico

Date: 18/04/2019

Version: 1.0

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
  class DataManagerImpl << (S,#FF7700) Singleton >>
  
  package "Storage" <<Database>> {
  }
}

package "Exceptions" {}

package "Entities" {
  class CapsuleTypeImpl
  class ColleagueImpl
  class TransactionImpl
}

package "Data" {
  class DataImpl
}

Gui <-- LaTazzaSystem
Data <-- LaTazzaSystem
Exceptions <- LaTazzaSystem
LaTazzaSystem --> Entities
Persistency <-- LaTazzaSystem
DataManagerImpl --> Storage
@enduml
```

_**Note:**_

The class DataManagerImpl responsible of storing and loading information into a database (or a file) will be realized as a static Singleton, since just one connection to the storage system is needed.

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
	+update(name: String, surname: String): void
	+updateBalance(amount: Integer): void
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
		\tboxPrice: Integer): void
	+updateQuantity(toAdd: Integer): void
}

class Transaction {
	-date: Date
	-amount: Integer

	+Transaction(date: Date, amount: Integer,
		\tType type, x: Integer)
	+Transaction(date: Date, amount: Integer,
		\tType type, object: Integer, directObject: Integer)
	+getDate(): Date
	+getType(): Type
	+getObject(): Integer
	+getDirectObject(): Integer
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

_**Note:**_

We decided not to insert the class DataManagerImpl in this table since it is assumed a priori that in order to perform every operation there should be an update/store of the whole dataset in the database (or file).

# Verification Sequence Diagrams 

## Scenario 1

```plantuml
@startuml
"DataImpl" -> "CapsuleType": "1: getBeverage()"
"CapsuleType" -> "DataImpl": "capsuleId"
"DataImpl" -> "Colleague": "2: getColleague()"
"Colleague" -> "DataImpl": "colleagueId"
"DataImpl" -> "Transaction": "3: sellCapsules(colleagueId, capsuleId, quantity)"
"DataImpl" -> "CapsuleType": "4: updatedCapsuleQuantity(capsuleId)"
"DataImpl" -> "Colleague": "5: updateEmployeeAccount(colleagueId)"
@enduml
```
## Scenario 2

```plantuml
@startuml
"DataImpl" -> "CapsuleType": "1: getBeverage()"
"CapsuleType" -> "DataImpl": "capsuleId"
"DataImpl" -> "Colleague": "2: getColleague()"
"Colleague" -> "DataImpl": "colleagueId"
"DataImpl" -> "Transaction": "3: sellCapsules(colleagueId, capsuleId, quantity)"
"DataImpl" -> "CapsuleType": "4: updatedCapsuleQuantity(capsuleId)"
"DataImpl" -> "Colleague": "5: updateEmployeeAccount(colleagueId)"
"Colleague" -> "DataImpl" : "6: warningNegativeBalance()"
@enduml
```

_**Note:**_

It is assumed that after every operation the whole dataset is updated/stored thanks to the DataManagerImpl class.