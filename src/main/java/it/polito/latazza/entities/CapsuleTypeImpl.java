package it.polito.latazza.entities;

import it.polito.latazza.exceptions.BeverageException;

import java.util.Map;

public class CapsuleTypeImpl implements CapsuleType {
	
	// ATTRIBUTES
    private Integer id;
    private Integer quantity;
    private Integer capsulesPerBox;
    private Integer boxPrice;
    private Integer oldQuantity;
    private Integer oldPrice;
    private String name;

    public CapsuleTypeImpl(Integer id, String name, Integer capsulesPerBox, Integer boxPrice) throws BeverageException { //Basic contructor
        if (capsulesPerBox <= 0 || boxPrice <= 0 || id < 0)
            throw new BeverageException(); //Custom exception for beverages
        this.id = id;
        this.name = name;
        this.capsulesPerBox = capsulesPerBox;
        this.boxPrice = boxPrice;
        this.quantity = 0;
        this.oldQuantity = 0;
        this.oldPrice = 0;
    }

    public CapsuleTypeImpl(Map m) throws BeverageException { //Constructor with Map data structure passed as argument
        if (!m.containsKey("id") || !m.containsKey("name") || !m.containsKey("capsulesPerBox") ||
                !m.containsKey("boxPrice") || !m.containsKey("quantity") || !m.containsKey("oldQuantity") ||
                !m.containsKey("oldPrice"))
            throw new BeverageException(); //Custom exception for beverages
        this.id = (Integer) m.get("id");
        this.name = (String) m.get("name");
        this.capsulesPerBox = (Integer) m.get("capsulesPerBox");
        this.boxPrice = (Integer) m.get("boxPrice");
        this.quantity = (Integer) m.get("quantity");
        this.oldQuantity = (Integer) m.get("oldQuantity");
        this.oldPrice = (Integer) m.get("oldPrice");

        if(capsulesPerBox <= 0 || boxPrice <= 0 || id < 0 || quantity < 0)
            throw new BeverageException();
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
    public Integer getOldQuantity() {
        return this.oldQuantity;
    }

    @Override
    public Integer getOldPrice() {
        return this.oldPrice;
    }

    @Override
    public void update(String name, Integer capsulesPerBox, Integer boxPrice) throws BeverageException { //Update all fields except quantity
        if ((capsulesPerBox <= 0 || boxPrice <= 0) || (!this.getPrice().equals(boxPrice/capsulesPerBox) && this.oldQuantity > 0))
            throw new BeverageException();
        this.name = name;
        if (!this.getPrice().equals(boxPrice/capsulesPerBox)) {
            this.oldQuantity = this.quantity;
            this.quantity = 0;
            this.oldPrice = this.getPrice();
        }
        this.capsulesPerBox = capsulesPerBox;
        this.boxPrice = boxPrice;
    }

    @Override
    public void updateQuantity(Integer toAdd) throws BeverageException { //Update quantity
        if (this.quantity.longValue() + toAdd.longValue() > Integer.MAX_VALUE
                || ((this.quantity + this.oldQuantity + toAdd) < 0))
            throw new BeverageException();
        if (toAdd < 0 && this.oldQuantity > 0)
            if (this.oldQuantity + toAdd < 0) {
                toAdd += this.oldQuantity;
                this.oldQuantity = 0;
                this.quantity += toAdd;
            } else
                this.oldQuantity += toAdd;
        else
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
