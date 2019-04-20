package it.polito.latazza.entities;

public class CapsuleTypeImpl implements CapsuleType {

    private Integer id;
    private Integer quantity;
    private Integer capsulesPerBox;
    private Integer boxPrice;
    private String name;

    public CapsuleTypeImpl(Integer id, String name, Integer capsulesPerBox, Integer boxPrice) {
        this.id = id;
        this.name = name;
        this.capsulesPerBox = capsulesPerBox;
        this.boxPrice = boxPrice;
    }

    @Override
    public Integer getPrice() {
        return this.boxPrice / this.capsulesPerBox;
    }

    @Override
    public Integer getBeverageId() {
        return this.id;
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
    public void update(String name, Integer capsulesPerBox, Integer boxPrice) {
        this.name = name;
        this.capsulesPerBox = capsulesPerBox;
        this.boxPrice = boxPrice;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Integer getId() {
        return this.id;
    }
}
