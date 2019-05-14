package it.polito.latazza.entities;

import it.polito.latazza.exceptions.EmployeeException;

public interface Colleague {

    Integer getBalance();

    Integer getId();

    String getName();

    String getSurname();

    void update(String name, String surname);

    void updateBalance(Integer amount) throws EmployeeException;

}
