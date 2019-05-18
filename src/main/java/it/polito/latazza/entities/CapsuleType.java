package it.polito.latazza.entities;

import it.polito.latazza.exceptions.BeverageException;

public interface CapsuleType {

    /**
     * Return the name of this beverage
     *
     * @return the name of this beverage
     */
    String getName();

    /**
     * Return the id of this beverage
     *
     * @return the id of this beverage
     */
    Integer getId();

    /**
     * Return the price of each capsule of this beverage
     *
     * @return the price of each capsule of this beverage
     */
    Integer getPrice();

    /**
     * Return the available quantity of this beverage
     *
     * @return the available quantity of this beverage
     */
    Integer getQuantity();

    /**
     * Return the price of each box of this beverage
     *
     * @return the price of each box of this beverage
     */
    Integer getBoxPrice();

    /**
     * Return the number of capsules per each box of this beverage
     *
     * @return the number of capsules per each box of this beverage
     */
    Integer getCapsulesPerBox();

    /**
     * Update the name, the capsulesPerBox and the boxPrice of this beverage
     *
     * @param name           the new name of the beverage
     * @param capsulesPerBox the new number of capsules in each box
     * @param boxPrice       the new price of each box
     *
     * @throws BeverageException if capsulesPerBox or boxPrice less than zero
     */
    void update(String name, Integer capsulesPerBox, Integer boxPrice) throws BeverageException;

    /**
     * Update the available quantity of this beverage
     *
     * @param toAdd       the quantity to add/subtract
     *
     * @throws BeverageException if quantity would overflow or less than zero
     */
    void updateQuantity(Integer toAdd) throws BeverageException;
}