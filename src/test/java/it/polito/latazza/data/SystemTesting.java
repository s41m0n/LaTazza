package it.polito.latazza.data;

import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.EmployeeException;
import it.polito.latazza.exceptions.NotEnoughBalance;
import it.polito.latazza.exceptions.NotEnoughCapsules;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class SystemTesting {

    @Test
    void testScenario1() {
        long start = System.currentTimeMillis(), end;
        DataInterface dt = new DataImpl();
        dt.reset();

        try {

            // PRECONDITION
            dt.createEmployee("Test", "User");
            dt.createBeverage("Test Beverage", 10, 1000);
            int ee_id = dt.getEmployeesId().get(dt.getEmployeesId().size() - 1);
            int ct_id = dt.getBeveragesId().get(dt.getBeveragesId().size() - 1);
            dt.rechargeAccount(ee_id, 1000);
            dt.buyBoxes(ct_id, 1);

            assertTrue(dt.getEmployeeBalance(ee_id) >= dt.getBeverageBoxPrice(ct_id));

            // SCENARIO ACTION
            dt.sellCapsules(ee_id, ct_id, 1, true);

            // POSTCONDITION
            assertEquals(dt.getEmployeeBalance(ee_id).intValue() ,900);
            assertEquals(dt.getBeverageCapsules(ct_id).intValue(), 9);

        }catch (Exception e) {
            fail();
        }

        end = System.currentTimeMillis();
        assertTrue(end - start <= 500);

    }

    @Test
    void testScenario2() {
        long start = System.currentTimeMillis(), end;
        DataInterface dt = new DataImpl();
        dt.reset();

        try {

            // PRECONDTION
            dt.createEmployee("Test", "User");
            int ee_id = dt.getEmployeesId().get(dt.getEmployeesId().size() - 1);
            dt.createEmployee("System", "Balance");
            int sb_id = dt.getEmployeesId().get(dt.getEmployeesId().size() - 1);
            dt.createBeverage("Test Beverage", 10, 10);
            int ct_id = dt.getBeveragesId().get(dt.getBeveragesId().size() - 1);
            dt.rechargeAccount(sb_id, 10);
            dt.buyBoxes(ct_id, 1);


            assertEquals((int) dt.getEmployeeBalance(ee_id), 0);
            assertEquals((int) dt.getBeverageCapsules(ct_id), 10);

            // ========================= //

            dt.sellCapsules(ee_id, ct_id, 1, true);

            // POSTCONDITION
            assertTrue(dt.getEmployeeBalance(ee_id) < 0);
            assertEquals((int) dt.getBeverageCapsules(ct_id), 9);
        }catch (Exception e) {

        }

        end = System.currentTimeMillis();
        assertTrue(end - start <= 500);

    }

    @Test
    void testScenario3() throws EmployeeException, BeverageException, NotEnoughBalance {
        DataImpl dt = new DataImpl();
        dt.reset();                     //clear DataImpl

        // PRECONDTION
        dt.createEmployee("Manager", "System");
        int mm_id = dt.getEmployeesId().get(dt.getEmployeesId().size() - 1);
        dt.createBeverage("Test Beverage", 10, 10);
        int ct_id = dt.getBeveragesId().get(dt.getBeveragesId().size() - 1);
        dt.rechargeAccount(mm_id, 100);
        assertEquals((int) dt.getBalance(), 100);               // System has balance == 100
        assertEquals((int) dt.getBeverageCapsules(ct_id), 0);   // T starts with 0 capsules

        // ========================= //

        dt.buyBoxes(ct_id, 1);  // manager buys 1 box

        // POSTCONDITION
        assertEquals((int) dt.getBeverageCapsules(ct_id), 10);  // T ends with 10 capsules
        assertEquals((int) dt.getBalance(), 90);                // System has balance == 90
    }

    @Test
    void testScenario4() throws EmployeeException, BeverageException, NotEnoughBalance {
        DataImpl dt = new DataImpl();
        dt.reset();                     //clear DataImpl

        // PRECONDTION
        dt.createEmployee("Test", "User");
        int ee_id = dt.getEmployeesId().get(dt.getEmployeesId().size() - 1);
        assertEquals((int) dt.getEmployeeBalance(ee_id), 0);   // Employee has balance == 0
        // ========================= //

        dt.rechargeAccount(ee_id, 100);  // recharge employee account with 100 credits

        // POSTCONDITION
        assertEquals((int) dt.getEmployeeBalance(ee_id), 100);  // Employee has balance == 100
    }

    @Test
    void testScenario5() throws BeverageException, NotEnoughBalance, NotEnoughCapsules, EmployeeException {
        DataImpl dt = new DataImpl();
        dt.reset();                     //clear DataImpl

        // PRECONDTION
        dt.createEmployee("Manager", "System");
        int mm_id = dt.getEmployeesId().get(dt.getEmployeesId().size() - 1);
        dt.createBeverage("Test Beverage", 10, 10);
        int ct_id = dt.getBeveragesId().get(dt.getBeveragesId().size() - 1);
        dt.rechargeAccount(mm_id, 110);
        dt.buyBoxes(ct_id, 1);

        assertEquals((int) dt.getBalance(), 100);               // System has balance == 100
        assertEquals((int) dt.getBeverageCapsules(ct_id), 10);  // T starts with 10 capsules
        // ========================= //

        dt.sellCapsulesToVisitor(ct_id, 1);

        // POSTCONDITION
        assertEquals((int) dt.getBalance(), 101);               // System balance increased by 1
        assertEquals((int) dt.getBeverageCapsules(ct_id), 9);  // Capsules quantity deduced by 1
    }
}
