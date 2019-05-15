package it.polito.latazza.entities;

import it.polito.latazza.exceptions.BeverageException;

import java.util.Map;

public class CapsuleTypeImpl implements CapsuleType {

    private Integer id;
    private Integer quantity;
    private Integer capsulesPerBox;
    private Integer boxPrice;
    private String name;

    public CapsuleTypeImpl(Integer id, String name, Integer capsulesPerBox, Integer boxPrice) throws BeverageException {
        if (capsulesPerBox <= 0 || boxPrice <= 0 || id < 0)
            throw new BeverageException();
        this.id = id;
        this.name = name;
        this.capsulesPerBox = capsulesPerBox;
        this.boxPrice = boxPrice;
        this.quantity = 0;
    }

    public CapsuleTypeImpl(Map m) throws BeverageException {
        if ((Integer) m.get("id") < 0 || (Integer) m.get("quantity") < 0
                || (Integer) m.get("capsulesPerBox") <= 0 || (Integer) m.get("boxPrice") <= 0)
            throw  new BeverageException();
        this.id = (Integer) m.get("id");
        this.quantity = (Integer) m.get("quantity");
        this.capsulesPerBox = (Integer) m.get("capsulesPerBox");
        this.boxPrice = (Integer) m.get("boxPrice");
        this.name = (String) m.get("name");
    }

    @Override
    public Integer getPrice() {
        return this.boxPrice / this.capsulesPerBox;
    }

    @Override
    public Integer getQuantity() {
        return this.quantity;
    }

    @Override
    public Integer getBoxPrice() {
        return this.boxPrice;
    }

    @Override
    public Integer getCapsulesPerBox() {
        return this.capsulesPerBox;
    }

    @Override
    public void update(String name, Integer capsulesPerBox, Integer boxPrice) throws BeverageException {
        if (capsulesPerBox <= 0 || boxPrice <= 0)
            throw new BeverageException();
        this.name = name;
        this.capsulesPerBox = capsulesPerBox;
        this.boxPrice = boxPrice;
    }

    @Override
    public void updateQuantity(Integer toAdd) throws BeverageException {
        if (this.quantity.longValue() + toAdd.longValue() > Integer.MAX_VALUE
                || ((this.quantity + toAdd) < 0))
            throw new BeverageException();
        this.quantity += toAdd;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":'" + this.id + '\'' +
                ", \"quantity\":" + this.quantity +
                ", \"capsulesPerBox\":" + this.capsulesPerBox +
                ", \"boxPrize\":" + this.boxPrice +
                ", \"name\":\"" + this.name + "\"" +
                '}';
    }
}
