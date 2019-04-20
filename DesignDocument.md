# Design Document Template

Authors: Daniele Palumbo, Magnani Simone, Marchi Riccardo, Postolov Enrico

Date: 18/04/2019

Version: 0.1

# Contents

- [Package diagram](#package-diagram)
- [Class diagram](#class-diagram)
- [Verification traceability matrix](#verification-traceability-matrix)
- [Verification sequence diagrams](#verification-sequence-diagrams)

# Package diagram

``` plantuml
@startuml
scale 500 width

skinparam PackageBackgroundColor AntiqueWhite

package "Gui" as g{
  class UserInterface
}

package "LaTazzaSystem"  as lts{
  class LaTazza

  package "Storage" as s <<Database>> {

  }
}

package "Exceptions" as e {
}


g <-- lts
LaTazza --> s
lts --> e
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

	+Colleague(name: String, surname: String)
	+getId(): Integer
	+getName(): String
	+getSurname(): String
	+getBalance(): Integer
}

class CapsuleType {
	-id: Integer
	-quantity: Integer
	-capsulesPerBox: Integer
	-boxPrice: Integer
	-name: String

	+CapsuleType(name: String, boxPrice: Integer,
	\t capsulesPerBox: Integer)
	+getPrice(): Integer
	+getId(): Integer
	+getQuantity(): Integer
	+getBoxPrice(): Integer
	+getName(): String

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
