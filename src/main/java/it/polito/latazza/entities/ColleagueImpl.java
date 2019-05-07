package it.polito.latazza.entities;

import java.util.Map;

public class ColleagueImpl implements Colleague {

    private Integer id;
    private Integer balance;
    private String name;
    private String surname;

    public ColleagueImpl(Integer id, String name, String surname) {
        this.id = id;
        this.balance = 0;
        this.name = name;
        this.surname = surname;
    }

    public ColleagueImpl(Map m) {
        this.id = (Integer) m.get("id");
        this.balance = (Integer) m.get("balance");
        this.name = (String) m.get("name");
        this.surname = (String) m.get("surname");
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
    public void updateBalance(Integer amount) {
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
