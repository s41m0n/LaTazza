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

    public void load(MutableInt sysBalance, List<CapsuleType> capsuleTypes, List<Colleague> colleagues, List<Transaction> transactions) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            Map dataset = objectMapper.readValue(new File(filename+maxFileNumber+extension), Map.class);

            sysBalance.setValue((Integer) dataset.get("sysBalance"));
            if(sysBalance.getValue() < 0)
                throw new IllegalArgumentException();

            for (Map capsuleType : (List<Map>)dataset.get("capsuleTypes")) capsuleTypes.add(new CapsuleTypeImpl(capsuleType));
            for (Map colleague: (List<Map>)dataset.get("colleagues")) colleagues.add(new ColleagueImpl(colleague));
            for (Map transaction: (List<Map>)dataset.get("transactions")) transactions.add(new TransactionImpl(transaction));

            System.out.println("File `" + filename + maxFileNumber + extension + "` found, restoring dataset");

        } catch (FileNotFoundException e) {

            //Creating new file with the same name since it does not exist yet
            System.out.println("File `" + filename + maxFileNumber + extension + "`, not found, creating new dataset");
            this.store(sysBalance, capsuleTypes, colleagues, transactions);

        } catch (IOException e) {

            //Creating new file with name += 1 since we don't want to wipe present data
            System.out.println("Found problem in file `" + filename + maxFileNumber + extension + "` due to a " + e.getClass().getName() + ", creating new dataset");
            maxFileNumber++;
            this.store(sysBalance, capsuleTypes, colleagues, transactions);

        } catch (Exception e) {

            //Creating new file with name += 1 since we don't want to wipe present data
            System.out.println("Found problem in the file `" + filename + maxFileNumber + extension + "` due to a " + e.getClass().getName() + ", creating new dataset");
            maxFileNumber++;
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

                mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filename+maxFileNumber+extension), dataset);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).run();

    }
}
