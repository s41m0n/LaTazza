package it.polito.latazza.entities;

public interface Colleague {

    public Integer getBalance();
    public Integer getId();
    public String getName();
    public String getSurname();
    public void update(String name, String surname);

}
