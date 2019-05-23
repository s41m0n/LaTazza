package it.polito.latazza.data;

import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.EmployeeException;
import it.polito.latazza.exceptions.NotEnoughBalance;
import it.polito.latazza.exceptions.NotEnoughCapsules;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class SystemTesting {

    /** Test scenario 1 given by professor
     *
     *  Description: Colleague uses one capsule of type T
     *  Pre condition: account of C has enough money to buy capsule T
     *  Post condition: account of C updated, count of T updated
     *
     */
    @Test
    void testScenario1() {
        long start = System.currentTimeMillis(), end;
        DataInterface dt = new DataImpl();
        dt.reset();

        try {

            // PRECONDITION
            dt.createEmployee("Test", "User");
            dt.createBeverage("Test Beverage", 10, 1000);
            int ee_id = dt.getEmployeesId().get(0);
            int ct_id = dt.getBeveragesId().get(0);
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

    /**
     * Test scenario 2 given by professor
     *
     * Description: Colleague uses one capsule of type T, account negative
     * Pre condition: account of C has not enough money to buy capsule T
     * Post condition: account of C updated, count of T updated
     *
     */
    @Test
    void testScenario2() {
        long start = System.currentTimeMillis(), end;
        DataInterface dt = new DataImpl();
        dt.reset();

        try {

            // PRECONDTION
            dt.createEmployee("Test", "User");
            dt.createEmployee("System", "Balance");
            dt.createBeverage("Test Beverage", 10, 1000);

            int ee_id = dt.getEmployeesId().get(0);
            int sb_id = dt.getEmployeesId().get(1);
            int ct_id = dt.getBeveragesId().get(0);

            dt.rechargeAccount(sb_id, 1000);
            dt.buyBoxes(ct_id, 1);

            assertEquals(dt.getEmployeeBalance(ee_id).intValue(), 0);
            assertEquals(dt.getBeverageCapsules(ct_id).intValue(), 10);

            // SCENARIO ACTION
            dt.sellCapsules(ee_id, ct_id, 1, true);

            // POSTCONDITION
            assertEquals(dt.getEmployeeBalance(ee_id).intValue(),  -100);
            assertEquals(dt.getBeverageCapsules(ct_id).intValue(), 9);

        } catch (Exception e) {
            fail();
        }

        end = System.currentTimeMillis();
        assertTrue(end - start <= 500);

    }

    /**
     * Test scenario 3 invented by us
     *
     * Description: Manager buys a capsules box
     * Pre condition: System account greater than the cost of the capsule box
     * Post condition: system account updated, count of T updated
     *
     */
    @Test
    void testScenario3() {
        long start = System.currentTimeMillis(), end;
        DataImpl dt = new DataImpl();
        dt.reset();

        try {
            // PRECONDTION
            dt.createEmployee("Manager", "System");
            dt.createBeverage("Test Beverage", 10, 1000);

            int mm_id = dt.getEmployeesId().get(0);
            int ct_id = dt.getBeveragesId().get(0);

            dt.rechargeAccount(mm_id, 1000);
            assertEquals(dt.getBalance().intValue(), 1000);
            assertEquals(dt.getBeverageCapsules(ct_id).intValue(), 0);

            // SCENARIO ACTION
            dt.buyBoxes(ct_id, 1);  // manager buys 1 box

            // POSTCONDITION
            assertEquals(dt.getBeverageCapsules(ct_id).intValue(), 10);
            assertEquals(dt.getBalance().intValue(), 0);

        } catch (Exception e) {
            fail();
        }

        end = System.currentTimeMillis();
        assertTrue(end - start <= 500);

    }

    /**
     * Test scenario 4 invented by us
     *
     * Description: Employee recharges his account
     * Pre condition: Employee exists
     * Post condition: Employee account updated, system balance updated
     *
     */
    @Test
    void testScenario4() {
        long start = System.currentTimeMillis(), end;
        DataImpl dt = new DataImpl();
        dt.reset();

        try {

            // PRECONDTION
            dt.createEmployee("Test", "User");
            int ee_id = dt.getEmployeesId().get(0);

            assertTrue(ee_id >= 0);
            assertEquals(dt.getEmployeeBalance(ee_id).intValue(), 0);

            // SCENARIO ACTION
            dt.rechargeAccount(ee_id, 1000);

            // POSTCONDITION
            assertEquals(dt.getEmployeeBalance(ee_id).intValue(), 1000);

        } catch (Exception e) {
            fail();
        }

        end = System.currentTimeMillis();
        assertTrue(end - start <= 500);

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
