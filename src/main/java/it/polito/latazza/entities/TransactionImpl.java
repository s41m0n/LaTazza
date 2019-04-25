package it.polito.latazza.entities;

import java.util.Date;
import java.util.Optional;

public class TransactionImpl implements Transaction {

    private Date date;
    private Integer amount;
    private Type type;
    private String directObject;

    public TransactionImpl(Date date, Integer amount, Type type) {
        this.date = date;
        this.amount = amount;
        this.type = type;
    }

    public TransactionImpl(Date date, Integer amount, Type type, String directObject){
        this(date, amount, type);
        this.directObject = directObject;
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

    @Override
    public Optional<String> getDirectObject() {
        return Optional.ofNullable(this.directObject);
    }


}
