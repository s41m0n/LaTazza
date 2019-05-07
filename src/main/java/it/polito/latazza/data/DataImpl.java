package it.polito.latazza.data;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import it.polito.latazza.entities.*;
import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.DateException;
import it.polito.latazza.exceptions.EmployeeException;
import it.polito.latazza.exceptions.NotEnoughBalance;
import it.polito.latazza.exceptions.NotEnoughCapsules;

public class DataImpl implements DataInterface {

	private Integer sysBalance;								/* LaTazzaAccount balance */
	private ArrayList<CapsuleType> capsuleTypes;			/* List of all capsules */
	private ArrayList<Colleague> colleagues;				/* List of all colleagues */
	private ArrayList<Transaction> transactions;

	public DataImpl() {
		/* Actually empty, still to decide how to store data */
		this.sysBalance = 0;
		this.capsuleTypes = new ArrayList<>();
		this.colleagues = new ArrayList<>();
		this.transactions = new ArrayList<>();
	}

	@Override
	public Integer sellCapsules(Integer employeeId, Integer beverageId, Integer numberOfCapsules, Boolean fromAccount)
			throws EmployeeException, BeverageException, NotEnoughCapsules {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void sellCapsulesToVisitor(Integer beverageId, Integer numberOfCapsules)
			throws BeverageException, NotEnoughCapsules {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer rechargeAccount(Integer id, Integer amountInCents) throws EmployeeException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void buyBoxes(Integer beverageId, Integer boxQuantity) throws BeverageException, NotEnoughBalance {
		// TODO Auto-generated method stub
		
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
		return c.get().getTransactions().stream()
				.filter(x -> x.getDate().after(startDate) && x.getDate().before(endDate))
				.map(x -> {
					switch(x.getType()) {
						case CONSUMPTION_BALANCE: {

						}
						case CONSUMPTION_CASH: {

						}
						case BOXPURCHASE: {

						}
						case RECHARGE: {

						}
						default: return "Error";
					}
				})
				.collect(Collectors.toList());
	}

	@Override
	public List<String> getReport(Date startDate, Date endDate) throws DateException {
		// TODO Auto-generated method stub
		return new ArrayList<String>();
	}

	@Override
	public Integer createBeverage(String name, Integer capsulesPerBox, Integer boxPrice) throws BeverageException {
		Integer newId = this.capsuleTypes.stream()
				.map(x -> x.getId() + 1)
				.reduce(Integer::max)
				.orElse(0);
		if(!this.capsuleTypes.add(new CapsuleTypeImpl(newId, name, capsulesPerBox, boxPrice)))
			throw new BeverageException();
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
		return this.sysBalance;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
