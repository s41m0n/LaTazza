package it.polito.latazza.entities;

public interface CapsuleType {

    public String getName();
    public Integer getId();
    public Integer getPrice();
    public Integer getBeverageId();
    public Integer getQuantity();
    public Integer getBoxPrice();
    public Integer getCapsulesPerBox();
    public void update(String name, Integer capsulesPerBox, Integer boxPrice);
}
