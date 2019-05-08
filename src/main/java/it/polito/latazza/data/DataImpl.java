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
import it.polito.latazza.persistency.DataManager;
import org.apache.commons.lang3.mutable.MutableInt;

public class DataImpl implements DataInterface {

	private MutableInt sysBalance;				/* LaTazzaAccount balance */
	private List<CapsuleType> capsuleTypes;		/* List of all capsules */
	private List<Colleague> colleagues;			/* List of all colleagues */
	private List<Transaction> transactions;		/* List of all transactions*/

	public DataImpl() {
		this.sysBalance = new MutableInt(0);
		this.capsuleTypes = new ArrayList<>();
		this.colleagues = new ArrayList<>();
		this.transactions = new ArrayList<>();
		DataManager.getDataManager().load(this.sysBalance, this.capsuleTypes, this.colleagues, this.transactions);
	}

	@Override
	public Integer sellCapsules(Integer employeeId, Integer beverageId, Integer numberOfCapsules, Boolean fromAccount)
			throws EmployeeException, BeverageException, NotEnoughCapsules {
		Optional<Colleague> c = this.colleagues.stream()
				.filter(x -> x.getId().equals(employeeId))
				.findFirst();
		if(!c.isPresent())
			throw new EmployeeException();
		Optional<CapsuleType> ct = this.capsuleTypes.stream()
				.filter(x -> x.getId().equals(beverageId))
				.findFirst();
		if(!ct.isPresent())
			throw new BeverageException();
		if(ct.get().getQuantity() < numberOfCapsules)
			throw new NotEnoughCapsules();
		this.transactions.add(new TransactionImpl(new Date(), numberOfCapsules,
				fromAccount? Transaction.Type.CONSUMPTION_BALANCE : Transaction.Type.CONSUMPTION_CASH, c.get().getId(), ct.get().getId()));
		ct.get().updateQuantity(-numberOfCapsules);
		if(fromAccount)
			c.get().updateBalance(-numberOfCapsules * ct.get().getPrice());
		this.sysBalance.add(numberOfCapsules * ct.get().getPrice());
		DataManager.getDataManager().store(this.sysBalance, this.capsuleTypes, this.colleagues, this.transactions);
		return c.get().getBalance();
	}

	@Override
	public void sellCapsulesToVisitor(Integer beverageId, Integer numberOfCapsules)
			throws BeverageException, NotEnoughCapsules {
		Optional<CapsuleType> ct = this.capsuleTypes.stream()
				.filter(x -> x.getId().equals(beverageId))
				.findFirst();
		if(!ct.isPresent())
			throw new BeverageException();
		if(ct.get().getQuantity() < numberOfCapsules)
			throw new NotEnoughCapsules();
		this.transactions.add(new TransactionImpl(new Date(), numberOfCapsules,
				Transaction.Type.CONSUMPTION_CASH, ct.get().getId()));
		ct.get().updateQuantity(-numberOfCapsules);
		this.sysBalance.add(numberOfCapsules * ct.get().getPrice());
		DataManager.getDataManager().store(this.sysBalance, this.capsuleTypes, this.colleagues, this.transactions);
	}

	@Override
	public Integer rechargeAccount(Integer id, Integer amountInCents) throws EmployeeException {
		Optional<Colleague> c = this.colleagues.stream()
				.filter(x -> x.getId().equals(id))
				.findFirst();
		if(!c.isPresent())
			throw new EmployeeException();
		this.transactions.add(new TransactionImpl(new Date(), amountInCents, Transaction.Type.RECHARGE, c.get().getId()));
		c.get().updateBalance(amountInCents);
		this.sysBalance.add(amountInCents);
		DataManager.getDataManager().store(this.sysBalance, this.capsuleTypes, this.colleagues, this.transactions);
		return c.get().getBalance();
	}

	@Override
	public void buyBoxes(Integer beverageId, Integer boxQuantity) throws BeverageException, NotEnoughBalance {
		Optional<CapsuleType> ct = this.capsuleTypes.stream()
				.filter(x -> x.getId().equals(beverageId))
				.findFirst();
		if(!ct.isPresent())
			throw new BeverageException();
		if(this.sysBalance.getValue() < ct.get().getBoxPrice()*boxQuantity)
			throw new NotEnoughBalance();
		ct.get().updateQuantity(boxQuantity*ct.get().getCapsulesPerBox());
		this.sysBalance.add(-boxQuantity*ct.get().getBoxPrice());
		this.transactions.add(new TransactionImpl(new Date(), boxQuantity, Transaction.Type.BOX_PURCHASE, ct.get().getId()));
		DataManager.getDataManager().store(this.sysBalance, this.capsuleTypes, this.colleagues, this.transactions);
	}

	@Override
	public List<String> getEmployeeReport(Integer employeeId, Date startDate, Date endDate)
			throws EmployeeException, DateException {
		if(endDate.before(startDate))
			throw new DateException();
		Optional<Colleague> c = this.colleagues.stream()
				.filter(x -> x.getId().equals(employeeId))
				.findFirst();
		if(!c.isPresent())
			throw new EmployeeException();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return this.transactions.stream()
				.filter(x -> x.getObject() != null && x.getObject().equals(employeeId)
						&& x.getDate().after(startDate) && x.getDate().before(endDate))
				.map(x -> {
					String employee = this.colleagues.stream().filter(y -> y.getId().equals(employeeId)).map(y -> y.getName() + " " +y.getSurname()).collect(Collectors.joining());
					String capsuleType = this.capsuleTypes.stream().filter(y -> y.getId().equals(x.getDirectObject())).map(CapsuleType::getName).collect(Collectors.joining());
					switch(x.getType()) {
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

	@Override
	public List<String> getReport(Date startDate, Date endDate) throws DateException {
		if(endDate.before(startDate))
			throw new DateException();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return this.transactions.stream()
				.filter(x -> x.getDate().after(startDate) && x.getDate().before(endDate))
				.map(x -> {
					String employee = this.colleagues.stream().filter(y -> y.getId().equals(x.getObject())).map(y -> y.getName() + " " +y.getSurname()).collect(Collectors.joining());
					String capsuleType = this.capsuleTypes.stream().filter(y -> y.getId().equals(x.getDirectObject())).map(CapsuleType::getName).collect(Collectors.joining());
					switch(x.getType()) {
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

	@Override
	public Integer createBeverage(String name, Integer capsulesPerBox, Integer boxPrice) throws BeverageException {
		Integer newId = this.capsuleTypes.stream()
				.map(x -> x.getId() + 1)
				.reduce(Integer::max)
				.orElse(0);
		if(!this.capsuleTypes.add(new CapsuleTypeImpl(newId, name, capsulesPerBox, boxPrice)))
			throw new BeverageException();
		DataManager.getDataManager().store(this.sysBalance, this.capsuleTypes, this.colleagues, this.transactions);
		return newId;
	}

	@Override
	public void updateBeverage(Integer id, String name, Integer capsulesPerBox, Integer boxPrice)
			throws BeverageException {
		Optional<CapsuleType> c = this.capsuleTypes.stream()
				.filter(x -> x.getId().equals(id))
				.findFirst();
		if(!c.isPresent())
			throw new BeverageException();
		c.get().update(name, capsulesPerBox, boxPrice);
		DataManager.getDataManager().store(this.sysBalance, this.capsuleTypes, this.colleagues, this.transactions);
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

	@Override
	public List<Integer> getBeveragesId() {
		return this.capsuleTypes.stream()
				.map(CapsuleType::getId)
				.collect(Collectors.toList());
	}

	@Override
	public Map<Integer, String> getBeverages() {
		return this.capsuleTypes.stream()
				.collect(Collectors.toMap(CapsuleType::getId, CapsuleType::getName));
	}

	@Override
	public Integer getBeverageCapsules(Integer id) throws BeverageException {
		Optional<CapsuleType> c = this.capsuleTypes.stream()
				.filter(x -> x.getId().equals(id))
				.findFirst();
		if(!c.isPresent())
			throw new BeverageException();
		return c.get().getQuantity();
	}

	@Override
	public Integer createEmployee(String name, String surname) throws EmployeeException {
		Integer newId = this.colleagues.stream()
				.map(x -> x.getId() + 1)
				.reduce(Integer::max)
				.orElse(0);
		if(!this.colleagues.add(new ColleagueImpl(newId, name, surname)))
			throw new EmployeeException();
		DataManager.getDataManager().store(this.sysBalance, this.capsuleTypes, this.colleagues, this.transactions);
		return newId;
	}

	@Override
	public void updateEmployee(Integer id, String name, String surname) throws EmployeeException {
		Optional<Colleague> c = this.colleagues.stream()
				.filter(x -> x.getId().equals(id))
				.findFirst();
		if(!c.isPresent())
			throw new EmployeeException();
		c.get().update(name ,surname);
		DataManager.getDataManager().store(this.sysBalance, this.capsuleTypes, this.colleagues, this.transactions);
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
		DataManager.getDataManager().store(this.sysBalance, this.capsuleTypes, this.colleagues, this.transactions);
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
