package it.polito.latazza.data;

import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.EmployeeException;
import it.polito.latazza.exceptions.NotEnoughCapsules;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataImplTest {

    static DataImpl dt = new DataImpl();
    static int ee_id = 0;
    static int ct_id = 0;

    @BeforeAll
    public static void init(){
        System.out.println("Before All init() method called");

        try {
            dt.createEmployee("Test", "User");
            ee_id = dt.getEmployeesId().get(0);
        } catch (EmployeeException e) {
            fail();
        }

        try {
            dt.createBeverage("TestBeverage", 50 , 75);
            ct_id = dt.getBeveragesId().get(0);
        } catch (BeverageException e) {
            fail();
        }
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
    void sellCapsulesToVisitor() {
    }

    @Test
    void rechargeAccount() {
    }

    @Test
    void buyBoxes() {
    }

    @Test
    void getEmployeeReport() {
    }

    @Test
    void getReport() {
    }

    @Test
    void createBeverage() {
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
