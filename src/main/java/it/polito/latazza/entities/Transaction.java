package it.polito.latazza.entities;

import java.util.Date;

public interface Transaction {

    public enum Type{ CONSUMPTION_CASH, CONSUMPTION_BALANCE, BOXPURCHASE, RECHARGE;}
    public Date getDate();
    public Integer getAmount();
    public Type getType();
}
