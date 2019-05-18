package it.polito.latazza.persistency;

import it.polito.latazza.entities.CapsuleType;
import it.polito.latazza.entities.Colleague;
import it.polito.latazza.entities.Transaction;
import org.apache.commons.lang3.mutable.MutableInt;

import java.util.List;

public interface DataManager {

    /**
     * Method to read from the file the dataset
     *
     * @param sysBalance        the sysBalance to be loaded
     * @param capsuleTypes      the capsuleType to be loaded
     * @param colleagues        the colleagues to be loaded
     * @param transactions      the transactions to be loaded
     *
     */
    void load(MutableInt sysBalance, List<CapsuleType> capsuleTypes, List<Colleague> colleagues, List<Transaction> transactions);

    /**
     * Method to store into the file the dataset
     *
     * @param sysBalance        the sysBalance to be stored
     * @param capsuleTypes      the capsuleType to be stored
     * @param colleagues        the colleagues to be stored
     * @param transactions      the transactions to be stored
     *
     */
    void store(MutableInt sysBalance, List<CapsuleType> capsuleTypes, List<Colleague> colleagues, List<Transaction> transactions);
}
