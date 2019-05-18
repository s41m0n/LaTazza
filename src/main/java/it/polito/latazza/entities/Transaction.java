package it.polito.latazza.entities;

import java.util.Date;

public interface Transaction {

    /**
     *  Possible transaction types
     */
    enum Type {CONSUMPTION_CASH, CONSUMPTION_BALANCE, BOX_PURCHASE, RECHARGE}

    /**
     * Return the transaction date
     *
     * @return the transaction date
     */
    Date getDate();

    /**
     * Return the transaction amount
     *
     * @return the transaction amount
     */
    Integer getAmount();

    /**
     * Return the transaction type
     *
     * @return the transaction type
     */
    Type getType();

    /**
     * Return the transaction direct object (complemento oggetto) if present
     *
     * @return the transaction direct object
     */
    Integer getDirectObject();

    /**
     * Return the transaction object (Soggetto) if present
     *
     * @return the transaction object
     */
    Integer getObject();
}
