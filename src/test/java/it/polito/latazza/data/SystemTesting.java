package it.polito.latazza.data;

import it.polito.latazza.entities.Colleague;
import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.EmployeeException;
import it.polito.latazza.exceptions.NotEnoughBalance;
import it.polito.latazza.exceptions.NotEnoughCapsules;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class SystemTesting {

    @Test
    void testScenario1() {
        
    }

    @Test
    void testScenario2() throws EmployeeException, BeverageException, NotEnoughCapsules, NotEnoughBalance {
        DataImpl dt = new DataImpl();

        // PRECONDTION
        dt.createEmployee("Test", "User");
        int ee_id = dt.getEmployeesId().get(dt.getEmployeesId().size() - 1);
        dt.createEmployee("System", "Balance");
        int sb_id = dt.getEmployeesId().get(dt.getEmployeesId().size() - 1);
        dt.createBeverage("Test Beverage", 10, 10);
        int ct_id = dt.getBeveragesId().get(dt.getBeveragesId().size() - 1);
        dt.rechargeAccount(sb_id, 10);
        dt.buyBoxes(ct_id, 1);

        assertEquals((int) dt.getEmployeeBalance(ee_id), 0);  // Employee has balance == 0
        assertEquals((int) dt.getBeverageCapsules(ct_id), 10);  // Employee has balance == 0

        // ========================= //

        dt.sellCapsules(ee_id, ct_id, 1, true);

        // POSTCONDITION
        assertEquals((int) dt.getEmployeeBalance(ee_id), -1);  // Employee has balance == 0
        assertEquals((int) dt.getBeverageCapsules(ct_id), 9);  // Employee has balance == 0
    }
}
