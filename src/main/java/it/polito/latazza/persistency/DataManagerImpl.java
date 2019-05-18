package it.polito.latazza.persistency;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.polito.latazza.entities.*;
import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.DateException;
import it.polito.latazza.exceptions.EmployeeException;
import org.apache.commons.lang3.mutable.MutableInt;

import java.io.*;
import java.util.*;

public class DataManager {
    private static DataManager dm = null;

    private String filename;

    private DataManager() {
        this.filename = ".dataset.json";
    }

    public static DataManager getDataManager() {
        if(dm == null)
            dm = new DataManager();
        return dm;
    }

    public void load(MutableInt sysBalance, List<CapsuleType> capsuleTypes, List<Colleague> colleagues, List<Transaction> transactions) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map asd2 = objectMapper.readValue(new File(this.filename), HashMap.class);
            sysBalance.setValue((Integer) asd2.get("sysBalance"));
            Optional.ofNullable((ArrayList)asd2.get("capsuleTypes")).ifPresent(x -> x.forEach(y -> {
                try {
                    capsuleTypes.add(new CapsuleTypeImpl((HashMap) y));
                } catch (BeverageException e) {
                    e.printStackTrace();
                }
            }));
            Optional.ofNullable((ArrayList)asd2.get("colleagues")).ifPresent(x -> x.forEach(y -> {
                try {
                    colleagues.add(new ColleagueImpl((HashMap) y));
                } catch (EmployeeException e) {
                    e.printStackTrace();
                }
            }));
            Optional.ofNullable((ArrayList)asd2.get("transactions")).ifPresent(x -> x.forEach(y -> {
                try {
                    transactions.add(new TransactionImpl((HashMap) y));
                } catch (DateException e) {
                    e.printStackTrace();
                }
            }));
            System.out.println("File found, restoring dataset");
        } catch (FileNotFoundException e) {
            System.out.println("File not found, creating new dataset");
            this.store(sysBalance, capsuleTypes, colleagues, transactions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void store(MutableInt sysBalance, List<CapsuleType> capsuleTypes, List<Colleague> colleagues, List<Transaction> transactions) {

        new Thread(() -> {

            ObjectMapper mapper = new ObjectMapper();

            try {

                Map<String, Object> map = new HashMap<>();
                map.put("sysBalance", sysBalance);
                map.put("capsuleTypes", capsuleTypes);
                map.put("colleagues", colleagues);
                map.put("transactions", transactions);

                mapper.writerWithDefaultPrettyPrinter().writeValue(new File(this.filename), map);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).run();

    }
}
