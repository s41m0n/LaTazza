package it.polito.latazza.persistency;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.polito.latazza.entities.*;
import org.apache.commons.lang3.mutable.MutableInt;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class DataManagerImpl implements DataManager{
    private static DataManagerImpl dm = null;

    private static String filename = ".dataset";
    private static Integer maxFileNumber = 0;
    private static String extension= ".json";

    private DataManagerImpl() {
        maxFileNumber = 0;
        try (Stream<Path> files = Files.walk(Paths.get("."))){

            final Pattern pattern = Pattern.compile(filename + "(\\d)+" + extension);
            files.forEach(x -> {
                Matcher m = pattern.matcher(x.getFileName().toString());
                int maxTmp;
                if(m.matches() && (maxTmp = Integer.parseInt(m.group(1))) > maxFileNumber)
                    maxFileNumber= maxTmp;
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DataManagerImpl getDataManager() {
        if(dm == null)
            dm = new DataManagerImpl();
        return dm;
    }

    //Since we want to take advantage of a ClassCastException (and abort the load operation), this warning is useless
    @SuppressWarnings("unchecked")
    public void load(MutableInt sysBalance, List<CapsuleType> capsuleTypes, List<Colleague> colleagues, List<Transaction> transactions) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            Map dataset = objectMapper.readValue(new File(filename+maxFileNumber+extension), Map.class);

            sysBalance.setValue((Integer) dataset.get("sysBalance"));
            if(sysBalance.getValue() < 0)
                throw new IllegalArgumentException("Negative System Balance");

            for (Map colleague: (List<Map>)dataset.get("colleagues"))
                if(colleagues.stream().anyMatch(x -> x.getId().equals(colleague.get("id"))))
                    throw new IllegalArgumentException("Duplicate colleague id");
                else
                    colleagues.add(new ColleagueImpl(colleague));

            for (Map capsuleType : (List<Map>)dataset.get("capsuleTypes"))
                if(capsuleTypes.stream().anyMatch(x -> x.getId().equals(capsuleType.get("id"))))
                    throw new IllegalArgumentException("Duplicate capsule id");
                else
                    capsuleTypes.add(new CapsuleTypeImpl(capsuleType));
                
            for (Map transaction: (List<Map>)dataset.get("transactions"))
                transactions.add(new TransactionImpl(transaction));

            System.out.println("File `" + filename + maxFileNumber + extension + "` found, restored dataset");

        } catch (FileNotFoundException e) {

            //Creating new file with the same name since it does not exist yet
            System.out.println("File `" + filename + maxFileNumber + extension + "`, not found, creating new dataset");
            this.store(sysBalance, capsuleTypes, colleagues, transactions);

        } catch (Exception e) {
            //Creating new file with name += 1 since we don't want to wipe present data
            System.out.println("Found problem in the file `" + filename + maxFileNumber + extension + "`, creating new dataset" +
                    "\n|->\tException: " + e.getClass().getName() +
                    "\n|->\tMessage: " + e.getMessage());
            maxFileNumber++;
            if(!(e instanceof IOException)) {
                sysBalance = new MutableInt(0);
                capsuleTypes = new ArrayList<>();
                colleagues = new ArrayList<>();
                transactions = new ArrayList<>();
            }
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

                mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filename+maxFileNumber+extension), dataset);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).run();

    }
}
