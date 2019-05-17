package it.polito.latazza.data;

import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.DateException;
import it.polito.latazza.exceptions.EmployeeException;
import it.polito.latazza.exceptions.NotEnoughBalance;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataImplTest {

    static DataImpl dt = new DataImpl();
    static int ee_id   = 0;
    static int ct_id   = 0;
    static int initial_balance  = 0;
    static int initial_capsules = 0;

    @BeforeAll
    public static void init() throws EmployeeException, BeverageException, NotEnoughBalance {
        System.out.println("Before All init() method called");

        if (dt.getEmployeesId().size() == 0) {
            try {
                dt.createEmployee("Test", "User");
            } catch (EmployeeException e) {
                fail();
            }
        }
        ee_id = dt.getEmployeesId().get(0);
        if (dt.getEmployeeBalance(ee_id) <= 0) {
            dt.rechargeAccount(ee_id, 1000);
        }
        initial_balance = dt.getEmployeeBalance(ee_id);

        if (dt.getBeveragesId().size() == 0) {
            try {
                dt.createBeverage("TestBeverage", 50, 75);
            } catch (BeverageException e) {
                fail();
            }
        }
        ct_id = dt.getBeveragesId().get(0);
        if (dt.getBeverageCapsules(ct_id) <= 0) {
            dt.buyBoxes(ct_id, 1);
        }
        initial_capsules = dt.getBeverageCapsules(ct_id);
    }

    @Test
    void failedSellCapsules() {
        try {
            dt.sellCapsules(-1, ct_id, 10, true);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        try {
            dt.sellCapsules(ee_id, -1, 10, true);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        try {
            dt.sellCapsules(ee_id, ct_id, -10, true);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        try {
            dt.sellCapsules(ee_id, ct_id, 1000, true);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    void sellCapsules() {
        try {
            dt.sellCapsules(ee_id, ct_id, 1, true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void failSellCapsulesToVisitor() {
        try {
            dt.sellCapsulesToVisitor(-1, 1);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        try {
            dt.sellCapsulesToVisitor(ct_id, -1);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        try {
            dt.sellCapsulesToVisitor(ct_id, 1000);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    void sellCapsulesToVisitor() {
        try {
            initial_capsules = dt.getBeverageCapsules(ct_id);
            dt.sellCapsulesToVisitor(ct_id, 10);
            assertEquals(initial_capsules - dt.getBeverageCapsules(ct_id), 10);
            initial_capsules = initial_capsules - 10;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void failRechargeAccount() {
        try {
            dt.rechargeAccount(-1, 100);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        try {
            dt.rechargeAccount(ee_id, -100);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    void rechargeAccount() {
        try {
            initial_balance = dt.getEmployeeBalance(ee_id);
            dt.rechargeAccount(ee_id, 100);
            assertEquals(initial_balance + 100, (int) dt.getEmployeeBalance(ee_id));
        } catch (EmployeeException e) {
            fail();
        }
    }

    @Test
    void FailBuyBoxes() {
        try {
            dt.buyBoxes(-1, 1);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        try {
            dt.buyBoxes(ct_id, -1);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    void buyBoxes() {
        try {
            dt.buyBoxes(ct_id, 1);
            int final_capsules = initial_capsules + dt.getBeverageCapsulesPerBox(ct_id);
            assertEquals(final_capsules, (int) dt.getBeverageCapsules(ct_id));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void failGetEmployeeReport() {
        try {
            dt.getEmployeeReport(-1, new Date(System.currentTimeMillis()-24*60*60*1000), new Date());
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        try {
            dt.getEmployeeReport(ee_id, new Date(System.currentTimeMillis()+24*60*60*1000), new Date());
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    void getEmployeeReport() {
        try {
            List<String> report = dt.getEmployeeReport(ee_id, new Date(System.currentTimeMillis()-24*60*60*1000), new Date());
            assertTrue(report.size() >= 0);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void failGetReport() {
        try {
            dt.getReport(new Date(System.currentTimeMillis()+24*60*60*1000), new Date());
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    void getReport() {
        try {
            List<String> report = dt.getReport(new Date(System.currentTimeMillis()-24*60*60*1000), new Date());
            assertTrue(report.size() >= 0);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void createBeverage() {
        try {
            int size = dt.getBeveragesId().size();
            dt.createBeverage("Test Beverage", 50, 100);
            assertEquals(size + 1, dt.getBeveragesId().size());
        } catch (BeverageException e) {
            fail();
        }
    }

    @Test
    void updateBeverage() {
    }

    @Test
    void getBeverageName() {
    }

    @Test
    void getBeverageCapsulesPerBox() {
    }

    @Test
    void getBeverageBoxPrice() {
    }

    @Test
    void getBeveragesId() {
    }

    @Test
    void getBeverages() {
    }

    @Test
    void getBeverageCapsules() {
    }

    @Test
    void createEmployee() {
    }

    @Test
    void updateEmployee() {
    }

    @Test
    void getEmployeeName() {
    }

    @Test
    void getEmployeeSurname() {
    }

    @Test
    void getEmployeeBalance() {
    }

    @Test
    void getEmployeesId() {
    }

    @Test
    void getEmployees() {
    }

    @Test
    void getBalance() {
    }

    @Test
    void reset() {
    }

    @Test
    void toString1() {
    }

}
