package it.polito.latazza.entities;

import java.util.List;

public interface Colleague {

    public Integer getBalance();
    public Integer getId();
    public String getName();
    public String getSurname();
    public List<Transaction> getTransactions();
    public void update(String name, String surname);
    public void recordTransaction(Transaction transaction);

}
