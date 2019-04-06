# ASSUMPTIONS

1.  LaTazza is a desktop application with two different interfaces:
	 - interface for the manager;
	 - interface for the employees.
	Moreover, in order to buy a capsule a visitor can only refer to the manager.
2.	Both the interfaces communicate with the server:
	 - the server stores data concerning accounts, orders, available capsules and payments.
3.	When an employee downloads the application he can create his own account.
4.	It is possible to buy many capsules (100) at a time using the application (Employee).
5.	The desktop application interface for the **employee** allows to:
	 - create an account;
	 - login/logout with own account;
	 - buy capsules (max 100, even different types);
	 - add a payment method to the account;
	 - add cash to the account to buy credits;
	 - pay off debts (using an associated payment method);
	 - schedule future purchases (if there are no available capsules).
6.	The desktop application interface for the **manager** allows to:
	 - login/logout with own account;
	 - manage options and privileges (including the possibility to promote an employee as new manager);
	 - manage employees' account (delete, pay off debts using cash, assign credits, manage pending purchases);
	 - manage inventory;
	 - order capsules from vendors
	 - pay capsules order.
7.	LaTazza can sell capsules from different vendors:
	 - each vendor who wants to support the application shall expose specific API.
8.	The manager orders capsules with his own credentials, including the payment method:
	 - LaTazza shall use the API exposed by the banks.
