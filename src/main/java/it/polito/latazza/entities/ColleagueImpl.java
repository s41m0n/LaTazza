package it.polito.latazza.entities;

import it.polito.latazza.exceptions.EmployeeException;

import java.util.Map;
//Implementation of Colleague class
public class ColleagueImpl implements Colleague {

	//ATTRIBUTES
    private Integer id;
    private Integer balance;
    private String name;
    private String surname;

    public ColleagueImpl(Integer id, String name, String surname) throws EmployeeException { //Basic constructor
        if (id < 0)
            throw  new EmployeeException(); //Custom exception for employees
        this.id = id;
        this.balance = 0;
        this.name = name;
        this.surname = surname;
    }

    public ColleagueImpl(Map m) throws EmployeeException { //Constructor with Map data structure passed as argument
        Integer id = (Integer) m.get("id"), balance = (Integer) m.get("balance");
        String name = (String) m.get("name"), surname = (String) m.get("surname");
        if (!m.containsKey("id") || !m.containsKey("balance") ||
                !m.containsKey("surname") || !m.containsKey("name"))
            throw new EmployeeException();
        this.id = id;
        this.balance = balance;
        this.name = name;
        this.surname = surname;
        if(id < 0)
            throw new EmployeeException();
    }

    @Override
    public Integer getBalance() {
        return this.balance;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getSurname() {
        return this.surname;
    }

    @Override
    public void update(String name, String surname) { //Method for updating the personal info about the employee
        this.name = name;
        this.surname = surname;
    }

    @Override
    public void updateBalance(Integer amount) throws EmployeeException { //Method for updating the balance of the employee
        if (this.balance.longValue() + amount.longValue() < Integer.MIN_VALUE
                || this.balance.longValue() + amount.longValue() > Integer.MAX_VALUE) {
            throw new EmployeeException();
        }
        this.balance += amount;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + this.id +
                ", \"balance\":" + this.balance +
                ", \"name\":\"" + this.name + "\"" +
                ", \"surname\":\"" + this.surname + "\"" +
                "]}";
    }
}
