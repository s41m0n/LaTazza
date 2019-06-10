package it.polito.latazza.entities;

import it.polito.latazza.exceptions.BeverageException;

public interface CapsuleType {

    /**
     * Return the beverage name
     *
     * @return the beverage name
     */
    String getName();

    /**
     * Return the beverage id
     *
     * @return the beverage id
     */
    Integer getId();

    /**
     * Return the beverage price of each capsule
     *
     * @return the beverage price of each capsule
     */
    Integer getPrice();

    /**
     * Return the beverage available quantity
     *
     * @return the beverage available quantity
     */
    Integer getQuantity();

    /**
     * Return the beverage price of each box
     *
     * @return the beverage price of each box
     */
    Integer getBoxPrice();

    /**
     * Return the beverage number of capsules per each box
     *
     * @return the beverage number of capsules per each box
     */
    Integer getCapsulesPerBox();

    /**
     * Return the beverage quantity before last update
     *
     * @return the beverage quantity before last update
     */
    Integer getOldQuantity();

    /**
     * Return the beverage price before last update
     *
     * @return the beverage price before last update
     */
    Integer getOldPrice();

    /**
     * Update the beverage name, capsulesPerBox and boxPrice
     *
     * @param name           the new name of the beverage
     * @param capsulesPerBox the new number of capsules in each box
     * @param boxPrice       the new price of each box
     *
     * @throws BeverageException if capsulesPerBox or boxPrice less than zero
     */
    void update(String name, Integer capsulesPerBox, Integer boxPrice) throws BeverageException;

    /**
     * Update the beverage quantity
     *
     * @param toAdd       the quantity to add/subtract
     *
     * @throws BeverageException if quantity overflow or less than zero
     */
    void updateQuantity(Integer toAdd) throws BeverageException;
}