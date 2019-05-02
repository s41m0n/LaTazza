package it.polito.latazza.entities;

import java.util.Date;

public interface Transaction {

    enum Type {CONSUMPTION_CASH, CONSUMPTION_BALANCE, BOX_PURCHASE, RECHARGE}

    Date getDate();

    Integer getAmount();

    Type getType();

    Integer getDirectObject();

    Integer getObject();
}
