package it.polito.latazza.data;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import it.polito.latazza.entities.*;
import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.DateException;
import it.polito.latazza.exceptions.EmployeeException;
import it.polito.latazza.exceptions.NotEnoughBalance;
import it.polito.latazza.exceptions.NotEnoughCapsules;
import it.polito.latazza.persistency.DataManagerImpl;
import org.apache.commons.lang3.mutable.MutableInt;

public class DataImpl implements DataInterface {

	//ATTRIBUTES
	private MutableInt sysBalance;				/* LaTazzaAccount balance */
	private List<CapsuleType> capsuleTypes;		/* List of all capsules */
	private List<Colleague> colleagues;			/* List of all colleagues */
	private List<Transaction> transactions;		/* List of all transactions*/

	public DataImpl() { //Constructor
		this.sysBalance = new MutableInt(0);
		this.capsuleTypes = new ArrayList<>();
		this.colleagues = new ArrayList<>();
		this.transactions = new ArrayList<>();
		DataManagerImpl.getDataManager().load(this.sysBalance, this.capsuleTypes, this.colleagues, this.transactions); //Call the DataManager to fill the data structures from the JSON file
	}

	//Method for selling a given number of capsules of a given type to a given employee
	@Override
	public Integer sellCapsules(Integer employeeId, Integer beverageId, Integer numberOfCapsules, Boolean fromAccount)
			throws EmployeeException, BeverageException, NotEnoughCapsules {
		//Search for the employee corresponding to the passed ID
		if (employeeId == null || employeeId < 0)
			throw new EmployeeException();
		else if (beverageId == null || beverageId < 0)
			throw new BeverageException();
		Optional<Colleague> c = this.colleagues.stream()
				.filter(x -> x.getId().equals(employeeId))
				.findFirst();
		if(!c.isPresent())
			throw new EmployeeException();
		if(numberOfCapsules == null || numberOfCapsules < 0)
			throw new NotEnoughCapsules();
		if (numberOfCapsules == 0)
			return c.get().getBalance();
		//Search for the capsule type corresponding to the passed ID
		Optional<CapsuleType> ct = this.capsuleTypes.stream()
				.filter(x -> x.getId().equals(beverageId))
				.findFirst();
		if(!ct.isPresent())
			throw new BeverageException();
		//If requested number is less then one the operation should not be executed-> exception
		if(numberOfCapsules < 1)
			throw new BeverageException();
		//If there are less capsules than the requested number we can't complete the operation -> exception
		if(ct.get().getQuantity() < numberOfCapsules)
			throw new NotEnoughCapsules();
		//In the opposite case we can try to create the transaction (checking also if it can be done through the balance, in opposite case use cash)
		try {
			this.transactions.add(new TransactionImpl(new Date(), numberOfCapsules,
					fromAccount? Transaction.Type.CONSUMPTION_BALANCE : Transaction.Type.CONSUMPTION_CASH, c.get().getId(), ct.get().getId()));
		} catch (DateException e) {
			e.printStackTrace();
		}
		ct.get().updateQuantity(-numberOfCapsules); //Decrease the quantity of the capsule type
		if(fromAccount) {
			c.get().updateBalance(-numberOfCapsules * ct.get().getPrice()); //Update the balance of the account if the transaction was made through the account
		} else {
			this.sysBalance.add(numberOfCapsules * ct.get().getPrice());
		}
		DataManagerImpl.getDataManager().store(this.sysBalance, this.capsuleTypes, this.colleagues, this.transactions); //Update the JSON file
		return c.get().getBalance(); //Return new balance of the employee
	}

	//Method for selling capsules to a visitor
	@Override
	public void sellCapsulesToVisitor(Integer beverageId, Integer numberOfCapsules)
			throws BeverageException, NotEnoughCapsules {
		//Retrieve the capsule type (beverage)
		if(numberOfCapsules == null || numberOfCapsules < 0)
			throw new NotEnoughCapsules();
		if (numberOfCapsules == 0)
			return;
		Optional<CapsuleType> ct = this.capsuleTypes.stream()
				.filter(x -> x.getId().equals(beverageId))
				.findFirst();
		if(!ct.isPresent())
			throw new BeverageException();
		//Check if there are enough capsules for the beverage
		if(ct.get().getQuantity() < numberOfCapsules)
			throw new NotEnoughCapsules();
		try {
			//Create the transaction -> necessarily by cash for visitors
			this.transactions.add(new TransactionImpl(new Date(), numberOfCapsules,
					Transaction.Type.CONSUMPTION_CASH, ct.get().getId()));
		} catch (DateException e) {
			e.printStackTrace();
		}
		ct.get().updateQuantity(-numberOfCapsules); //Update quantity of capsules for the beverage
		this.sysBalance.add(numberOfCapsules * ct.get().getPrice()); //Update the system balance
		DataManagerImpl.getDataManager().store(this.sysBalance, this.capsuleTypes, this.colleagues, this.transactions); //Store in the JSON file
	}

	//Method for recharging an account
	@Override
	public Integer rechargeAccount(Integer id, Integer amountInCents) throws EmployeeException {
		//Search for the employee
		Optional<Colleague> c = this.colleagues.stream()
				.filter(x -> x.getId().equals(id))
				.findFirst();
		if(!c.isPresent())
			throw new EmployeeException();
		try {
			//Create the recharge transaction
			this.transactions.add(new TransactionImpl(new Date(), amountInCents, Transaction.Type.RECHARGE, c.get().getId()));
		} catch (DateException e) {
			e.printStackTrace();
		}
		c.get().updateBalance(amountInCents); //Update the balance of the employee
		this.sysBalance.add(amountInCents);
		DataManagerImpl.getDataManager().store(this.sysBalance, this.capsuleTypes, this.colleagues, this.transactions); //Store in the JSON file
		return c.get().getBalance(); //Return the new balance of the employee
	}

	//Method for buying a given amount of boxes of a given beverage
	@Override
	public void buyBoxes(Integer beverageId, Integer boxQuantity) throws BeverageException, NotEnoughBalance {
		//Search for the beverage
		if (boxQuantity == null || boxQuantity < 0)
			throw new BeverageException();
		if (boxQuantity == 0)
			return;
		Optional<CapsuleType> ct = this.capsuleTypes.stream()
				.filter(x -> x.getId().equals(beverageId))
				.findFirst();
		if(!ct.isPresent())
			throw new BeverageException();
		//Check if the system balance is enough for buying the amount of boxes of that beverage
		if(this.sysBalance.getValue() < ct.get().getBoxPrice()*boxQuantity)
			throw new NotEnoughBalance();
		ct.get().updateQuantity(boxQuantity*ct.get().getCapsulesPerBox()); //Update the quantity of capsules of the beverage
		this.sysBalance.add(-boxQuantity*ct.get().getBoxPrice()); //Decrease the system balance
		try {
			this.transactions.add(new TransactionImpl(new Date(), boxQuantity, Transaction.Type.BOX_PURCHASE, ct.get().getId()));
		} catch (DateException e) {
			e.printStackTrace();
		}
		DataManagerImpl.getDataManager().store(this.sysBalance, this.capsuleTypes, this.colleagues, this.transactions); //Store in the JSON file
	}

	//Method for getting the report of a given employee in a given date range
	@Override
	public List<String> getEmployeeReport(Integer employeeId, Date startDate, Date endDate)
			throws EmployeeException, DateException {
		if (startDate == null || endDate == null)
			throw new DateException();
		if (employeeId == null)
			throw new EmployeeException();
		//Throw exception if dates are not coherent
		if(endDate.before(startDate) || startDate.after(new Date()))
			throw new DateException();
		Calendar cal = Calendar.getInstance();
		cal.setTime(endDate);
		cal.add(Calendar.DATE, 1);
		Date endDayDate = cal.getTime();
		//Search for the employee
		Optional<Colleague> c = this.colleagues.stream()
				.filter(x -> x.getId().equals(employeeId))
				.findFirst();
		if(!c.isPresent())
			throw new EmployeeException();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//From the list of transactions get the ones in the date range and related to the given employee
		return this.transactions.stream()
				.filter(x -> x.getObject() != null && x.getObject().equals(employeeId)
						&& x.getDate().after(startDate) && x.getDate().before(endDayDate))
				.map(x -> {
					String employee = this.colleagues.stream().filter(y -> y.getId().equals(employeeId)).map(y -> y.getName() + " " +y.getSurname()).collect(Collectors.joining()); //For each transaction retrieve a string with employee's name and surame
					String capsuleType = this.capsuleTypes.stream().filter(y -> y.getId().equals(x.getDirectObject())).map(CapsuleType::getName).collect(Collectors.joining()); //For each transaction retrieve the name of the beverage
					switch(x.getType()) { //Depending on the transaction type print differently
						case RECHARGE:
							return "" + dateFormat.format(x.getDate()) + " RECHARGE " + employee +
									" " + String.format("%.2f \u20ac",0.01*x.getAmount());
						case CONSUMPTION_BALANCE:
							return "" + dateFormat.format(x.getDate()) + " BALANCE " + employee +
									" " + capsuleType + " " + x.getAmount();
						case CONSUMPTION_CASH:
							return "" + dateFormat.format(x.getDate()) + " CASH " + employee +
									" " + capsuleType + " " + x.getAmount();
						default: return "Error";
					}
				}).collect(Collectors.toList());
	}

	//Method for getting the transaction report in a given date range
	@Override
	public List<String> getReport(Date startDate, Date endDate) throws DateException {
		if (startDate == null || endDate == null)
			throw new DateException();
		//Check dates coherence
		if(endDate.before(startDate) || startDate.after(new Date()))
			throw new DateException();
		Calendar cal = Calendar.getInstance();
		cal.setTime(endDate);
		cal.add(Calendar.DATE, 1);
		Date endDayDate = cal.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//From the list of transactions get the ones in the date range
		return this.transactions.stream()
				.filter(x -> x.getDate().after(startDate) && x.getDate().before(endDayDate))
				.map(x -> {
					String employee = this.colleagues.stream().filter(y -> y.getId().equals(x.getObject())).map(y -> y.getName() + " " +y.getSurname()).collect(Collectors.joining()); //String with employee's name and surname
					String capsuleType = this.capsuleTypes.stream().filter(y -> y.getId().equals(x.getDirectObject())).map(CapsuleType::getName).collect(Collectors.joining()); //String with the beverage name
					switch(x.getType()) { //Depending on the transaction type print differently
						case CONSUMPTION_CASH:
							if(x.getObject() == null)  return "" + dateFormat.format(x.getDate()) + " VISITOR " +
									capsuleType + " " + x.getAmount();
							else return "" + dateFormat.format(x.getDate()) + " CASH " +
									employee + " " + capsuleType + " " + x.getAmount();
						case CONSUMPTION_BALANCE:
							return "" + dateFormat.format(x.getDate()) + " BALANCE " +
									employee + " " + capsuleType + " " + x.getAmount();
						case RECHARGE:
							return "" + dateFormat.format(x.getDate()) + " RECHARGE " + employee +
									" " + String.format("%.2f \u20ac",0.01*x.getAmount());
						case BOX_PURCHASE:
							return "" + dateFormat.format(x.getDate()) + " BUY " +
									capsuleType + " " + x.getAmount();
						default: return "Error";
					}
				}).collect(Collectors.toList());
	}

	//Method for creating a new beverage
	@Override
	public Integer createBeverage(String name, Integer capsulesPerBox, Integer boxPrice) throws BeverageException {
		if (name == null || name.isEmpty() || capsulesPerBox == null || boxPrice == null)
			throw new BeverageException();
		Integer newId = this.capsuleTypes.stream()
				.map(x -> x.getId() + 1)
				.reduce(Integer::max)
				.orElse(0);
		this.capsuleTypes.add(new CapsuleTypeImpl(newId, name, capsulesPerBox, boxPrice));
		DataManagerImpl.getDataManager().store(this.sysBalance, this.capsuleTypes, this.colleagues, this.transactions);
		return newId;
	}

	//Method for updating the attributes of a given bevarage
	@Override
	public void updateBeverage(Integer id, String name, Integer capsulesPerBox, Integer boxPrice)
			throws BeverageException {
		if (name == null || name.isEmpty() || capsulesPerBox == null || boxPrice == null)
			throw new BeverageException();
		Optional<CapsuleType> c = this.capsuleTypes.stream()
				.filter(x -> x.getId().equals(id))
				.findFirst();
		if(!c.isPresent())
			throw new BeverageException();
		c.get().update(name, capsulesPerBox, boxPrice);
		DataManagerImpl.getDataManager().store(this.sysBalance, this.capsuleTypes, this.colleagues, this.transactions);
	}

	@Override
	public String getBeverageName(Integer id) throws BeverageException {
		Optional<CapsuleType> c = this.capsuleTypes.stream()
				.filter(x -> x.getId().equals(id))
				.findFirst();
		if(!c.isPresent())
			throw new BeverageException();
		return c.get().getName();
	}

	@Override
	public Integer getBeverageCapsulesPerBox(Integer id) throws BeverageException {
		Optional<CapsuleType> c = this.capsuleTypes.stream()
				.filter(x -> x.getId().equals(id))
				.findFirst();
		if(!c.isPresent())
			throw new BeverageException();
		return c.get().getCapsulesPerBox();
	}

	@Override
	public Integer getBeverageBoxPrice(Integer id) throws BeverageException {
		Optional<CapsuleType> c = this.capsuleTypes.stream()
				.filter(x -> x.getId().equals(id))
				.findFirst();
		if(!c.isPresent())
			throw new BeverageException();
		return c.get().getBoxPrice();
	}

	//Method for getting a List of IDs of all the beverages
	@Override
	public List<Integer> getBeveragesId() {
		return this.capsuleTypes.stream()
				.map(CapsuleType::getId)
				.collect(Collectors.toList());
	}

	//Method for getting a Map of IDs and related names of all the beverages
	@Override
	public Map<Integer, String> getBeverages() {
		return this.capsuleTypes.stream()
				.collect(Collectors.toMap(CapsuleType::getId, CapsuleType::getName));
	}

	//Method for getting the quantity of capsules of a given beverage
	@Override
	public Integer getBeverageCapsules(Integer id) throws BeverageException {
		//Get first beverage corresponding to the passed ID
		Optional<CapsuleType> c = this.capsuleTypes.stream()
				.filter(x -> x.getId().equals(id))
				.findFirst();
		if(!c.isPresent())
			throw new BeverageException();
		return c.get().getQuantity();
	}

	@Override
	public Integer createEmployee(String name, String surname) throws EmployeeException {
		if (name == null || name.isEmpty() || surname == null || surname.isEmpty())
			throw new EmployeeException();
		Integer maxId = this.colleagues.stream()
				.map(Colleague::getId)
				.reduce(Integer::max)
				.orElse(0);
		if (maxId.longValue() + 1 > Integer.MAX_VALUE)
			throw new EmployeeException();
		this.colleagues.add(new ColleagueImpl(maxId + 1, name, surname));
		DataManagerImpl.getDataManager().store(this.sysBalance, this.capsuleTypes, this.colleagues, this.transactions);
		return maxId + 1;
	}

	@Override
	public void updateEmployee(Integer id, String name, String surname) throws EmployeeException {
		if (name == null || name.isEmpty() || surname == null || surname.isEmpty())
			throw new EmployeeException();
		Optional<Colleague> c = this.colleagues.stream()
				.filter(x -> x.getId().equals(id))
				.findFirst();
		if(!c.isPresent())
			throw new EmployeeException();
		c.get().update(name ,surname);
		DataManagerImpl.getDataManager().store(this.sysBalance, this.capsuleTypes, this.colleagues, this.transactions);
	}

	@Override
	public String getEmployeeName(Integer id) throws EmployeeException {
		Optional<Colleague> c = this.colleagues.stream()
				.filter(x -> x.getId().equals(id))
				.findFirst();
		if(!c.isPresent())
			throw new EmployeeException();
		return c.get().getName();
	}

	@Override
	public String getEmployeeSurname(Integer id) throws EmployeeException {
		Optional<Colleague> c = this.colleagues.stream()
				.filter(x -> x.getId().equals(id))
				.findFirst();
		if(!c.isPresent())
			throw new EmployeeException();
		return c.get().getSurname();
	}

	@Override
	public Integer getEmployeeBalance(Integer id) throws EmployeeException {
		Optional<Colleague> c = this.colleagues.stream()
				.filter(x -> x.getId().equals(id))
				.findFirst();
		if(!c.isPresent())
			throw new EmployeeException();
		return c.get().getBalance();
	}

	@Override
	public List<Integer> getEmployeesId() {
		return this.colleagues.stream().map(Colleague::getId).collect(Collectors.toList());
	}

	@Override
	public Map<Integer, String> getEmployees() {
		return this.colleagues.stream().collect(Collectors.toMap(Colleague::getId, x -> "" + x.getName() + " " + x.getSurname()));
	}

	@Override
	public Integer getBalance() {
		return this.sysBalance.getValue();
	}

	@Override
	public void reset() {
		this.sysBalance = new MutableInt(0);
		this.capsuleTypes = new ArrayList<>();
		this.colleagues = new ArrayList<>();
		this.transactions = new ArrayList<>();
		DataManagerImpl.getDataManager().store(this.sysBalance, this.capsuleTypes, this.colleagues, this.transactions);
	}

	@Override
	public String toString() {
		return "DataImpl{" +
				"\n\tsysBalance:" + this.sysBalance +
				"\n\tcapsuleTypes:" + this.capsuleTypes.toString() +
				"\n\tcolleagues:" + this.colleagues.toString() +
				"\n\ttransactions:" + this.transactions.toString() +
				"\n}";
	}

}
