package it.polito.latazza.entities;

import it.polito.latazza.exceptions.EmployeeException;

public interface Colleague {

    /**
     * Return the employee balance
     *
     * @return the employee balance
     */
    Integer getBalance();

    /**
     * Return the employee id
     *
     * @return the employee id
     */
    Integer getId();

    /**
     * Return the employee name
     *
     * @return the employee name
     */
    String getName();

    /**
     * Return the employee surname
     *
     * @return the employee surname
     */
    String getSurname();

    /**
     * Method to update the employee info
     *
     * @param name      the employee name
     * @param surname   the employee surname
     *
     */
    void update(String name, String surname);

    /**
     * Method to update the employee balance
     *
     * @throws EmployeeException if the new balance overflow
     */
    void updateBalance(Integer amount) throws EmployeeException;

}
