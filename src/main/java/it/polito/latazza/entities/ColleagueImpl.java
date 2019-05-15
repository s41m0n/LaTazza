package it.polito.latazza.entities;

import it.polito.latazza.exceptions.EmployeeException;

import java.util.Map;

public class ColleagueImpl implements Colleague {

    private Integer id;
    private Integer balance;
    private String name;
    private String surname;

    public ColleagueImpl(Integer id, String name, String surname) throws EmployeeException {
        if (id < 0)
            throw  new EmployeeException();
        this.id = id;
        this.balance = 0;
        this.name = name;
        this.surname = surname;
    }

    public ColleagueImpl(Map m) throws EmployeeException {
        this.id = (Integer) m.get("id");
        this.balance = (Integer) m.get("balance");
        this.name = (String) m.get("name");
        this.surname = (String) m.get("surname");
        if (this.id < 0)
            throw  new EmployeeException();
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
    public void update(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public void updateBalance(Integer amount) throws EmployeeException {
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
                /*", \"transactions\":[" + this.transactions +*/
                "]}";
    }
}
