package it.polito.latazza.entities;

import java.util.ArrayList;
import java.util.List;

public class ColleagueImpl implements Colleague {

    private Integer id;
    private Integer balance;
    private String name;
    private String surname;
    private ArrayList<Transaction> transactions;

    public ColleagueImpl(Integer id, String name, String surname) {
        this.id = id;
        this.balance = 0;
        this.name = name;
        this.surname = surname;
        this.transactions = new ArrayList<>();
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
    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    @Override
    public void update(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public void recordTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        if(transaction.getType() == Transaction.Type.RECHARGE)
            this.balance += transaction.getAmount();
    }


}
