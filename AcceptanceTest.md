# Acceptance Testing

Authors: Magnani Simone, Marchi Riccardo, Palumbo Daniele, Postolov Enrico

Date: 23/05/2019

Version: 1.0

# Contents

- [Scenarios](#scenarios)

- [Coverage of scenarios](#scenario-coverage)
- [Coverage of non-functional requirements](#nfr-coverage)


# Scenarios

| Scenario ID: SC1 | Corresponds to UC1                             |
| ---------------- | ---------------------------------------------- |
| Description      | Colleague uses one capsule of type 1           |
| Precondition     | account of C has enough money to buy capsule T |
| Postcondition    | account of C updated, count of T updated       |
| Step#            | Step description                               |
| 1                | Administrator selects capsule type T           |
| 2                | Administrator selects colleague C              |
| 3                | Deduce one for quantity of capsule T           |
| 4                | Deduce price of T from account of C            |

| Scenario ID: SC2 | Corresponds to UC2                                     |
| ---------------- | ------------------------------------------------------ |
| Description      | Colleague uses one capsule of type T, account negative |
| Precondition     | account of C has not enough money to buy capsule T     |
| Postcondition    | account of C updated, count of T updated               |
| Step#            | Step description                                       |
| 1                | Administrator selects capsule type T                   |
| 2                | Administrator selects colleague C                      |
| 3                | Deduce one for quantity of capsule T                   |
| 4                | Deduce price of T from account of C                    |
| 5                | Account of C is negative, issue warning                |

| Scenario ID: SC3 | Corresponds to UC3 |
| ---------------- | ------------------ |
| Description      | Manager buys one box capsules of type T, account positive |
| Precondition     | System balance is enough to buy a box                     |
| Postcondition    | System balance updated, count of T updated                |
| Step#            | Step description                                          |
| 1                | Administrator selects capsule type T                      |
| 2                | Administrator buys one box of capsule type T              |
| 3                | Increase capsule type T of a box quantity                 |
| 4                | Deduce price of box of T from system balance              |

| Scenario ID: SC4 | Corresponds to UC4 |
| ---------------- | ------------------ |
| Description      | An employee recharges it's account buying credit          |
| Precondition     | The Employee must have a local account on the system.     |
| Postcondition    | Requested credits added to employee account               |
| Step#            | Step description                                          |
| 1                | Employee selects it's local account                       |
| 2                | Employee selects amount of credits to buy                 |
| 3                | Pay with the provided payment method                      |
| 4                | System adds the credits to the local account              |

| Scenario ID: SC5 | Corresponds to UC5 |
| ---------------- | ------------------ |
| Description      | Administrator sells 1 capsule of type T to a visitor      |
| Precondition     | System balance is enough to buy one capsule               |
| Postcondition    | System balance updated, count of T updated                |
| Step#            | Step description                                          |
| 1                | Visitor chooses which capsule to buy                      |
| 2                | Administrator selects capsule type T                      |
| 3                | Administrator sell one capsule of type T                  |
| 4                | Deduce one for quantity of capsule T                      |
| 5                | Increase system balance of one capsule cost               |

| Scenario ID: SC6 | Corresponds to UC3 |
| ---------------- | ------------------ |
| Description      | Administrator buys a capsule box T, account negative      |
| Precondition     | system account not enough					               |
| Postcondition    | system account unchanged, capsule T unchanged             |
| Step#            | Step description                                          |
| 1                | Administrator selects capsule type T                      |
| 2                | Administrator fails to buy 				               |

| Scenario ID: SC7 | Corresponds to UC1                             		|
| ---------------- | ------------------------------------------------------ |
| Description      | Colleague uses 1 capsule T paying with cash    		|
| Precondition     | Colleague exists 			 				    		|
| Postcondition    | Colleague balance unchanged, system account updated    |
| Step#            | Step description                               		|
| 1                | Administrator selects capsule type T           		|
| 2                | Administrator selects colleague C              		|
| 3                | Deduce one for quantity of capsule T           		|
| 4                | Deduce price of T from cash of C               		|

| Scenario ID: SC8 | Corresponds to UC5                             								 	   |
| ---------------- | ------------------------------------------------------------------------------------- |
| Description      | Administrator wants to see reports of Employee E which has performed just 1 action    |
| Precondition     | Employee E exists, 1 action performed, right dates 							 	   |
| Postcondition    | /     																			 	   |
| Step#            | Step description                               								 	   |
| 1                | Administrator selects colleague ID             								 	   |
| 2                | Administrator selects dates              										 	   |
| 3                | Administrator reads the report              									 	   |

| Scenario ID: SC9  | Corresponds to UC6              		|
| ----------------- | ------------------------------------- |
| Description       | Administrator wants to see reports    |
| Precondition      | endDate < startDate 			  		|
| Postcondition     | /     						  		|
| Step#             | Step description                		|
| 1                 | Administrator selects dates     		|
| 2                 | Administrator receives error    		|

| Scenario ID: SC10 | Corresponds to UC6              		|
| ----------------- | ------------------------------------- |
| Description       | Administrator wants to see reports 	|
| Precondition      | startDate > currentDate    	  		|
| Postcondition     | /     						  		|
| Step#             | Step description                		|
| 1                 | Administrator selects dates     		|
| 2                 | Administrator receives error    		|

| Scenario ID: SC11 | Corresponds to FR7	                             						 	 |
| ----------------- | ------------------------------------------------------------------------------ |
| Description       | Administrator changes capsule info           					 				 |
| Precondition      | capsule exists											 					 |
| Postcondition     | capsule info changed  													     |
| Step#             | Step description                               								 |
| 1                 | Administrator selects capsule type T 				           					 |
| 2                 | Administrator updates info of capsule T              							 |

| Scenario ID: SC12 | Corresponds to FR8                             							 	 |
| ----------------- | ------------------------------------------------------------------------------ |
| Description       | Administrator changes employee info           								 |
| Precondition      | employee exists							 									 |
| Postcondition     | employee info changed  								 					     |
| Step#             | Step description                               								 |
| 3                 | Administrator selects colleague C		           								 |
| 4                 | Administrator updates info of colleague T              						 |


# Coverage of Scenarios

| Scenario ID | Functional Requirements covered | API Test(s) | GUI Test(s) |
| ----------- | ------------------------------- | ----------- | ----------- |
| 1           | FR1                             | SystemTesting(testScenario1)   | sc1.txt     |
| 2           | FR1                             | SystemTesting(testScenario2)   | sc2.txt     |
| 3	          | FR4                             | SystemTesting(testScenario3)   | sc3.txt     |
| 4           | FR3                             | SystemTesting(testScenario4)   | sc4.txt     |
| 5           | FR2                             | SystemTesting(testScenario5)   | sc5.txt     |
| 6           | FR3                             | SystemTesting(testScenario6)   | sc6.txt     |
| 7           | FR1                             | SystemTesting(testScenario7)   | sc7.txt     |
| 8           | FR5                             | SystemTesting(testScenario8)   | sc8.txt     |
| 9           | FR6                             | SystemTesting(testScenario9)   | sc9.txt     |
| 10          | FR6                             | SystemTesting(testScenario10)  | sc10.txt    |
| 11          | FR7		                        | SystemTesting(testScenario11)  | sc11.txt    |
| 12          | FR8                       		| SystemTesting(testScenario12)  | sc12.txt    |


# Coverage of Non Functional Requirements

| Non Functional Requirement | Test name |
| -------------------------- | --------- |
|   2                        |   PerformanceTesting(testNFR[1-8])    |

