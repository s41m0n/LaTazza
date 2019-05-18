
# Unit Testing Documentation template

Authors: Magnani Simone, Marchi Riccardo, Palumbo Daniele, Postolov Enrico

Date: May 18th 2019

Version: 0.1

# Contents

- [Black Box Unit Tests](#black-box-unit-tests)




- [White Box Unit Tests](#white-box-unit-tests)


# Black Box Unit Tests

    <Define here criteria, predicates and the combination of predicates for each function of each class.
    Define test cases to cover all equivalence classes and boundary conditions.
    In the table, report the description of the black box test case and the correspondence with the JUnit black box test case name/number>

 ### **Class CapsuleTypeImpl - method CapsuleTypeImpl**



**Criteria for method CapsuleTypeImpl:**
	

 - Sign of ID
 - Sign of box price
 - Sign of capsules per box
 - Quantity of capsules




**Predicates for method CapsuleTypeImpl:**

| Criteria | Predicate |
| -------- | --------- |
| Sign of ID | < 0 |
|| >= 0 |
| Sign of box price | <= 0 |
|| > 0 |
| Sign of capsules per box | <= 0 |
|| > 0 |
| Quantity of capsules | < 0 |
|| >= 0 |





**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
| Sign | Minint, 0, maxint |
| Quantity | Minint, 0, maxint |



**Combination of predicates**:


| Sign of ID | Sign of box price | Sign of capsules per box | Quantity of capsules | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|------|
| < 0  | <= 0 | <= 0 | < 0  | I | map.put("id", -2);<br />map.put("name", "Tea");<br />map.put("capsulesPerBox", -100);<br />map.put("boxPrice", -200);<br />map.put("quantity", -99);<br />new CapsuleTypeImpl(map); | it.polito.latazza.data.TestCapsuleTypeImpl#testCapsuleTypeImpl |
|      |      |      | >= 0 | I | map.put("id", -2);<br />map.put("name", "Tea");<br />map.put("capsulesPerBox", -100);<br />map.put("boxPrice", -200);<br />map.put("quantity", 10);<br />new CapsuleTypeImpl(map); |  |
|  	   |      | > 0  | < 0  | I | map.put("id", -2);<br />map.put("name", "Tea");<br />map.put("capsulesPerBox", 10);<br />map.put("boxPrice", -200);<br />map.put("quantity", -99);<br />new CapsuleTypeImpl(map); |  |
|      |      |      | >= 0 | I | map.put("id", -2);<br />map.put("name", "Tea");<br />map.put("capsulesPerBox", 10);<br />map.put("boxPrice", -200);<br />map.put("quantity", 10);<br />new CapsuleTypeImpl(map); |  |
|      | > 0  | <= 0 | < 0  | I | map.put("id", -2);<br />map.put("name", "Tea");<br />map.put("capsulesPerBox", -100);<br />map.put("boxPrice", 20);<br />map.put("quantity", -99);<br />new CapsuleTypeImpl(map); |  |
|      |      |      | >= 0 | I | map.put("id", -2);<br />map.put("name", "Tea");<br />map.put("capsulesPerBox", -10);<br />map.put("boxPrice", 20);<br />map.put("quantity", 10);<br />new CapsuleTypeImpl(map); |  |
|      |      | > 0  | < 0  | I | map.put("id", -2);<br />map.put("name", "Tea");<br />map.put("capsulesPerBox", 10);<br />map.put("boxPrice", 20);<br />map.put("quantity", -99);<br />new CapsuleTypeImpl(map); |  |
|      |      |      | >= 0 | I | map.put("id", -2);<br />map.put("name", "Tea");<br />map.put("capsulesPerBox", 10);<br />map.put("boxPrice", 20);<br />map.put("quantity", 10);<br />new CapsuleTypeImpl(map); |  |
| >= 0 | <= 0 | <= 0 | < 0  | I | map.put("id", 1);<br />map.put("name", "Tea");<br />map.put("capsulesPerBox", -100);<br />map.put("boxPrice", -200);<br />map.put("quantity", -99);<br />new CapsuleTypeImpl(map); |  |
|      |      |      | >= 0 | I | map.put("id", 1);<br />map.put("name", "Tea");<br />map.put("capsulesPerBox", -100);<br />map.put("boxPrice", -200);<br />map.put("quantity", 10);<br />new CapsuleTypeImpl(map); |  |
|      |      | > 0  | < 0  | I | map.put("id", 1);<br />map.put("name", "Tea");<br />map.put("capsulesPerBox", 10);<br />map.put("boxPrice", -200);<br />map.put("quantity", -99);<br />new CapsuleTypeImpl(map); |  |
|      |      |      | >= 0 | I | map.put("id", 1);<br />map.put("name", "Tea");<br />map.put("capsulesPerBox", 10);<br />map.put("boxPrice", -200);<br />map.put("quantity", 10);<br />new CapsuleTypeImpl(map); |  |
|      | > 0  | <= 0 | < 0  | I | map.put("id", 1);<br />map.put("name", "Tea");<br />map.put("capsulesPerBox", -100);<br />map.put("boxPrice", 20);<br />map.put("quantity", -99);<br />new CapsuleTypeImpl(map); |  |
|      |      |      | >= 0 | I | map.put("id", 1);<br />map.put("name", "Tea");<br />map.put("capsulesPerBox", -10);<br />map.put("boxPrice", 20);<br />map.put("quantity", 10);<br />new CapsuleTypeImpl(map); |  |
|      |      | > 0  | < 0  | I | map.put("id", 1);<br />map.put("name", "Tea");<br />map.put("capsulesPerBox", 10);<br />map.put("boxPrice", 20);<br />map.put("quantity", -99);<br />new CapsuleTypeImpl(map); |  |
|      |      |      | >= 0 | V | new CapsuleTypeImpl(0, "Coffee", 50, 75); |  |


### **Class CapsuleTypeImpl - method update**

**Criteria for method update:**
	

 - Sign of box price
 - Sign of capsules per box




**Predicates for method update:**

| Criteria | Predicate |
| -------- | --------- |
| Sign of box price | <= 0 |
|| > 0 |
| Sign of capsules per box | <= 0 |
|| > 0 |





**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
| Sign | Minint, 0, maxint |



**Combination of predicates**:


| Sign of box price | Sign of capsules per box | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|
| <= 0  | <= 0  |   I   | ct.update("Ginseng", -10, -5); | it.polito.latazza.data.TestCapsuleTypeImpl#update |
|       | > 0   |   I   | ct.update("Light Coffee", 10, -5); |       |
|  > 0  | <= 0  |   I   | ct.update("Ginseng", -10, 50); |       |
|       | > 0   |   V   | ct.update("Ginseng", 25, 50); |       |


### **Class CapsuleTypeImpl - method updateQuantity**

**Criteria for method updateQuantity:**
	

 - Overflow in the sum
 - Sign of the updated quantity




**Predicates for method updateQuantity:**

| Criteria | Predicate |
| -------- | --------- |
| Overflow | <= Integer.MAX_VALUE |
|| > Integer.MAX_VALUE |
| Sign of the updated quantity | < 0 |
|| >= 0 |





**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
| Overflow | Integer.MIN_VALUE, Integer.MAX_VALUE |
| Sign | Minint, 0, maxint |



**Combination of predicates**:


| Overflow in the sum | Sign of the updated quantity | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|
| <= Integer.MAX_VALUE  | < 0 | I | ct2.updateQuantity(-200); | it.polito.latazza.data.TestCapsuleTypeImpl#updateQuantity |
|       | >=  0   | V | ct2.updateQuantity(1); ||
| > Integer.MAX_VALUE | < 0  | I | / ||
|       | >= 0 | I | ct.updateQuantity(Integer.MAX_VALUE + 1); ||



### **Class ColleagueImpl - method ColleagueImpl**

**Criteria for method ColleagueImpl:**
	

 - Sign of ID




**Predicates for method ColleagueImpl:**

| Criteria | Predicate |
| -------- | --------- |
| Sign of ID | < 0 |
|| >= 0 |


**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
| Sign | Minint, 0, maxint |



**Combination of predicates**:


| Overflow | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
| < 0  | I | ci = new ColleagueImpl(0, "Ted", "Mosby"); | it.polito.latazza.data.TestColleagueImpl#testColleagueImpl |
| >= 0 | V | ci = new ColleagueImpl(-1, "Herbert", "Bean"); | |


### **Class ColleagueImpl - method updateBalance**

**Criteria for method updateBalance:**
	

 - Overflow of the sum


**Predicates for method updateBalance:**

| Criteria | Predicate |
| -------- | --------- |
| Overflow | > Integer.MAX_VALUE |
|| Integer.MIN_VALUE <= sum <= Integer.MAX_VALUE |
|| < Integer.MIN_VALUE |


**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
| Overflow | Integer.MIN_VALUE, Integer.MAX_VALUE |



**Combination of predicates**:


| Overflow | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
| > Integer.MAX_VALUE  | I | ci.updateBalance(Integer.MAX_VALUE + 1); | it.polito.latazza.data.TestColleagueImpl#updateBalance |
| Integer.MIN_VALUE <= x <= Integer.MAX_VALUE | V | ci.updateBalance(57); | |
| < Integer.MIN_VALUE  | I | ci.updateBalance(Integer.MIN_VALUE -1); | |


### **Class TransactionImpl - method TransactionImpl**

**Criteria for method TransactionImpl:**
	

 - Date after actual one


**Predicates for method TransactionImpl:**

| Criteria | Predicate |
| -------- | --------- |
| Date | <= today |
|| > today |


**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
| Date | day - month - year |



**Combination of predicates**:


| Overflow | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
| <= today  | V | t = new TransactionImpl(new Date(), 45, Transaction.Type.RECHARGE, 0); | it.polito.latazza.data.TestTransactionImpl#TransactionImpl |
| > today | I | d = new Date();<br />Calendar c = Calendar.getInstance();<br />c.setTime(d);<br />c.add(Calendar.DATE, 1);<br />d2 = c.getTime();<br />t = new TransactionImpl(d2, 45, Transaction.Type.RECHARGE, 0); | |

# White Box Unit Tests

### Test cases definition

    <Report here all the created JUnit test cases, and the units/classes they test >


| Unit name | JUnit test case |
|--|--|
|||
|||
||||

### Code coverage report

    <Add here the screenshot report of the code and branch coverage obtained using
    the Jacoco tool. >


### Loop coverage analysis

    <Identify significant loops in the units and reports the test cases
    developed to cover zero, one or multiple iterations >

|Unit name | Loop rows | Number of iterations | JUnit test case |
|---|---|---|---|
|||||
|||||
||||||



