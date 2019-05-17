package it.polito.latazza.entities;

import it.polito.latazza.exceptions.DateException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

//Implementation of the Transaction class
public class TransactionImpl implements Transaction {

	//ATTRIBUTES
    private Date date;
    private Integer amount;
    private Type type;
    private Integer object;
    private Integer directObject;

    public TransactionImpl(Date date, Integer amount, Type type, Integer x) throws DateException { //Basic constructor
        this(date, amount, type, type == Type.RECHARGE? x : null, type == Type.RECHARGE? null : x);
    }

    public TransactionImpl(Date date, Integer amount, Type type, Integer object, Integer directObject) throws DateException {
        if (date.after(new Date()))
            throw new DateException(); //Throw exception if trying to instantiate a transaction after the current date
        this.date = date;
        this.amount = amount;
        this.type = type;
        this.object = object;
        this.directObject = directObject;
    }

    public TransactionImpl(Map m) throws DateException { //Constructor with Map data structure passed as argument
        Date d = new Date((Long) m.get("date"));
        if (d.after(new Date()))
            throw new DateException();
        this.date = d;
        this.amount = (Integer) m.get("amount");
        this.type = Type.valueOf(m.get("type").toString());
        this.object = (Integer) m.get("object");
        this.directObject = (Integer) m.get("directObject");
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
    public Integer getDirectObject() {
        return this.directObject;
    }

    @Override
    public Integer getObject() {
        return this.object;
    }

    @Override
    public String toString() {
        return "{" +
                "\"date\":\"" + this.date.toString() + '\"' +
                ", \"amount\":" + this.amount+
                ", \"type\":\"" + this.type.ordinal() + "\"" +
                ", \"directObject\":\"" + this.directObject + "\"" +
                '}';
    }

}
