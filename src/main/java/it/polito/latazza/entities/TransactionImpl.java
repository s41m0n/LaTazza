package it.polito.latazza.entities;

import java.util.Date;

public class TransactionImpl implements Transaction {

    private Date date;
    private Integer amount;
    private Type type;

    public TransactionImpl(Date date, Integer amount, Type type) {
        this.date = date;
        this.amount = amount;
        this.type = type;
    }

    @Override
    public Date getDate() {
        return this.date;
    }

    @Override
    public Integer getAmount() {
        return this.amount;
    }

    @Override
    public Type getType() {
        return this.type;
    }

}
