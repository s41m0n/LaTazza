package it.polito.latazza.entities;

import java.util.Date;
import java.util.Optional;

public interface Transaction {

    public enum Type{ CONSUMPTION_CASH, CONSUMPTION_BALANCE, BOX_PURCHASE, RECHARGE}
    public Date getDate();
    public Integer getAmount();
    public Type getType();
    public Optional<String> getDirectObject();
}
