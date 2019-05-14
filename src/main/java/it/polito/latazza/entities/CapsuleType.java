package it.polito.latazza.entities;

import it.polito.latazza.exceptions.BeverageException;

public interface CapsuleType {

    String getName();

    Integer getId();

    Integer getPrice();

    Integer getQuantity();

    Integer getBoxPrice();

    Integer getCapsulesPerBox();

    void update(String name, Integer capsulesPerBox, Integer boxPrice);

    void updateQuantity(Integer toAdd) throws BeverageException;
}