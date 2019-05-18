package it.polito.latazza.persistency;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.polito.latazza.entities.*;
import org.apache.commons.lang3.mutable.MutableInt;

import java.io.*;
import java.util.*;

public class DataManagerImpl implements DataManager{
    private static DataManagerImpl dm = null;

    private static String filename = ".dataset.json";

    public static DataManagerImpl getDataManager() {
        if(dm == null)
            dm = new DataManagerImpl();
        return dm;
    }

    public void load(MutableInt sysBalance, List<CapsuleType> capsuleTypes, List<Colleague> colleagues, List<Transaction> transactions) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            Map dataset = objectMapper.readValue(new File(DataManagerImpl.filename), HashMap.class);

            sysBalance.setValue((Integer) dataset.get("sysBalance"));
            if(sysBalance.getValue() < 0)
                throw new IllegalArgumentException();

            for (Map capsuleType : (List<HashMap>)dataset.get("capsuleTypes")) capsuleTypes.add(new CapsuleTypeImpl(capsuleType));
            for (Map colleague: (List<HashMap>)dataset.get("colleagues")) colleagues.add(new ColleagueImpl(colleague));
            for (Map transaction: (List<HashMap>)dataset.get("transactions")) transactions.add(new TransactionImpl(transaction));
            System.out.println("File found, restoring dataset");

        } catch (FileNotFoundException e) {

            System.out.println("File not found, creating new dataset");
            this.store(sysBalance, capsuleTypes, colleagues, transactions);

        } catch (IOException e) {

            e.printStackTrace();

        } catch (Exception e) {

            System.out.println("Found problem in the file due to a " + e.getClass().getName() + ", creating new dataset");
            sysBalance = new MutableInt(0);
            capsuleTypes = new ArrayList<>();
            colleagues = new ArrayList<>();
            transactions = new ArrayList<>();
            this.store(sysBalance, capsuleTypes, colleagues, transactions);

        }
    }

    public void store(MutableInt sysBalance, List<CapsuleType> capsuleTypes, List<Colleague> colleagues, List<Transaction> transactions) {

        new Thread(() -> {

            ObjectMapper mapper = new ObjectMapper();

            try {

                Map<String, Object> dataset = new HashMap<>();
                dataset.put("sysBalance", sysBalance);
                dataset.put("capsuleTypes", capsuleTypes);
                dataset.put("colleagues", colleagues);
                dataset.put("transactions", transactions);

                mapper.writerWithDefaultPrettyPrinter().writeValue(new File(DataManagerImpl.filename), dataset);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).run();

    }
}
