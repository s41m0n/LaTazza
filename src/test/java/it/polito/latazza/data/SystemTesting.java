package it.polito.latazza.data;

import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.DateException;
import it.polito.latazza.exceptions.EmployeeException;
import it.polito.latazza.exceptions.NotEnoughBalance;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class SystemTesting {

    /** Test scenario 1 given by professor
     *
     *  Description: Colleague uses one capsule of type T
     *  Pre condition: account of C has enough money to buy capsule T
     *  Post condition: account of C updated, count of T updated, system balance unchanged
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
            assertEquals(dt.getBalance().intValue() ,0);

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
     * Post condition: account of C updated, count of T updated, system balance unchanged
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
            assertEquals(dt.getBalance().intValue() ,0);

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

    /**
     * Test scenario 5 invented by us
     *
     * Description: Visitor buys capsule
     * Pre condition: capsule T are enough
     * Post condition: system account updated, capsule T quantity updated
     *
     */
    @Test
    void testScenario5() {
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

            dt.buyBoxes(ct_id, 1);
            assertEquals(dt.getBalance().intValue(), 0);
            assertEquals(dt.getBeverageCapsules(ct_id).intValue(), 10);

            // SCENARIO ACTION
            dt.sellCapsulesToVisitor(ct_id, 1);

            // POSTCONDITION
            assertEquals(dt.getBalance().intValue(), 100);
            assertEquals(dt.getBeverageCapsules(ct_id).intValue(), 9);

        } catch (Exception e) {
            fail();
        }

        end = System.currentTimeMillis();
        assertTrue(end - start <= 500);

    }

    /**
     * Test scenario 6 invented by us
     *
     * Description: Manager wants to buy a capsule box
     * Pre condition: system account not enough
     * Post condition: system account unchanged, capsule T unchanged
     *
     */
    @Test
    void testScenario6() {
        long start = System.currentTimeMillis(), end;
        DataImpl dt = new DataImpl();
        dt.reset();

        try {

            // PRECONDTION
            dt.createBeverage("Test Beverage", 10, 1000);

            int ct_id = dt.getBeveragesId().get(0);

            assertEquals(dt.getBalance().intValue(), 0);

            // SCENARIO ACTION
            dt.buyBoxes(ct_id, 1);

        } catch (NotEnoughBalance e) {

            try {

                int ct_id = dt.getBeveragesId().get(0);
                assertEquals(dt.getBalance().intValue(), 0);
                assertEquals(dt.getBeverageCapsules(ct_id).intValue(), 0);

            } catch (BeverageException ee) {
                fail();
            }

        } catch (Exception e) {
            fail();
        }

        end = System.currentTimeMillis();
        assertTrue(end - start <= 500);

    }

    /**
     * Test scenario 7 invented by us
     *
     * Description: Manager recharges a not-existent employee
     * Pre condition: Employee does not exists
     * Post condition: system account unchanged
     *
     */
    @Test
    void testScenario7() {
        long start = System.currentTimeMillis(), end;
        DataImpl dt = new DataImpl();
        dt.reset();

        try {

            // PRECONDTION
            assertEquals(dt.getEmployeesId().size(), 0);

            // SCENARIO ACTION
            dt.rechargeAccount(0, 1000);

            fail();

        } catch (EmployeeException e) {

            assertEquals(dt.getBalance().intValue(), 0);

        } catch (Exception e) {
            fail();
        }

        end = System.currentTimeMillis();
        assertTrue(end - start <= 500);

    }

    /**
     * Test scenario 8 invented by us
     *
     * Description: Colleague uses 1 capsule T paying with cash
     * Pre condition: Colleague exists
     * Post condition: Colleague balance unchanged, system account updated
     *
     */
    @Test
    void testScenario8() {
        long start = System.currentTimeMillis(), end;
        DataImpl dt = new DataImpl();
        dt.reset();

        try {

            /// PRECONDITION
            dt.createEmployee("Test", "User");
            dt.createBeverage("Test Beverage", 10, 1000);
            int ee_id = dt.getEmployeesId().get(0);
            int ct_id = dt.getBeveragesId().get(0);

            dt.rechargeAccount(ee_id, 1000);
            dt.buyBoxes(ct_id, 1);

            // SCENARIO ACTION
            dt.sellCapsules(ee_id, ct_id, 1, false);

            // POSTCONDITION
            assertEquals(dt.getEmployeeBalance(ee_id).intValue() ,1000);
            assertEquals(dt.getBeverageCapsules(ct_id).intValue(), 9);
            assertEquals(dt.getBalance().intValue() ,100);

        } catch (Exception e) {
            fail();
        }

        end = System.currentTimeMillis();
        assertTrue(end - start <= 500);

    }

    /**
     * Test scenario 9 invented by us
     *
     * Description: Manager wants to see reports of Employee E which has performed just 1 action
     * Pre condition: Employee E exists, 1 action performed (RECHARGE)
     * Post condition: /
     *
     */
    @Test
    void testScenario9() {
        long start = System.currentTimeMillis(), end;
        DataImpl dt = new DataImpl();
        dt.reset();

        try {

            /// PRECONDITION
            dt.createEmployee("Test", "User");
            int ee_id = dt.getEmployeesId().get(0);

            dt.rechargeAccount(ee_id, 1000);

            // SCENARIO ACTION
            List<String> report = dt.getEmployeeReport(ee_id, new GregorianCalendar(2014, 2, 11).getTime(), new Date());

            // POSTCONDITION
            assertEquals(report.size(), 1);

        } catch (Exception e) {
            fail();
        }

        end = System.currentTimeMillis();
        assertTrue(end - start <= 500);

    }

    /**
     * Test scenario 10 invented by us
     *
     * Description: Manager wants to see reports
     * Pre condition: endDate < startDate
     * Post condition: /
     *
     */
    @Test
    void testScenario10() {
        long start = System.currentTimeMillis(), end;
        DataImpl dt = new DataImpl();
        dt.reset();

        List<String> report = new ArrayList<>();

        try {

            /// PRECONDITION
            dt.createEmployee("Test", "User");
            int ee_id = dt.getEmployeesId().get(0);

            dt.rechargeAccount(ee_id, 1000);

            Date startDate = new Date();
            Date endDate = new Date();

            Calendar c = Calendar.getInstance();
            c.setTime(startDate);
            c.add(Calendar.DATE, -1);
            startDate = c.getTime();
            c.add(Calendar.DATE, -1);
            endDate = c.getTime();

            // SCENARIO ACTION
            report = dt.getEmployeeReport(ee_id, startDate, endDate);

            fail();

        } catch (DateException e) {
            assertEquals(report.size(), 0);
        } catch (Exception e) {
            fail();
        }

        end = System.currentTimeMillis();
        assertTrue(end - start <= 500);

    }

    /**
     * Test scenario 11 invented by us
     *
     * Description: Manager wants to see reports
     * Pre condition: startDate > currentDate
     * Post condition: /
     *
     */
    @Test
    void testScenario11() {
        long start = System.currentTimeMillis(), end;
        DataImpl dt = new DataImpl();
        dt.reset();

        List<String> report = new ArrayList<>();

        try {

            /// PRECONDITION
            dt.createEmployee("Test", "User");
            int ee_id = dt.getEmployeesId().get(0);

            dt.rechargeAccount(ee_id, 1000);

            Date startDate = new Date();
            Date endDate = new Date();

            Calendar c = Calendar.getInstance();
            c.setTime(startDate);
            c.add(Calendar.DATE, +1);
            startDate = c.getTime();
            c.add(Calendar.DATE, +1);
            endDate = c.getTime();

            // SCENARIO ACTION
            report = dt.getEmployeeReport(ee_id, startDate, endDate);

            fail();
        } catch (DateException e) {
            assertEquals(report.size(), 0);
        } catch (Exception e) {
            fail();
        }

        end = System.currentTimeMillis();
        assertTrue(end - start <= 500);

    }

}
