package it.polito.latazza.entities;

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
}
