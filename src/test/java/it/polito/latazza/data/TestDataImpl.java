package it.polito.latazza.data;

import it.polito.latazza.entities.Transaction;
import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.EmployeeException;
import it.polito.latazza.exceptions.NotEnoughBalance;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestDataImpl {

    static DataImpl dt = new DataImpl();
    static int ee_id   = 0;
    static int ct_id   = 0;
    static int initial_balance  = 0;
    static int initial_capsules = 0;
    static Transaction t1 = null;
    static Transaction t2 = null;
    static Transaction t3 = null;
    static Transaction t4 = null;
    static Transaction t5 = null;

    @BeforeAll
    public static void init() throws EmployeeException, BeverageException, NotEnoughBalance {
        
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
    void sellCapsules() {
        try {
            dt.sellCapsules(ee_id, ct_id, 1, true);
        } catch (Exception e) {
            fail();
        }
        
        //fromAccount = false
        try {
        	dt.sellCapsules(ee_id, ct_id, 1, false); 
        } catch(Exception e) {
        	fail();
        }
        
        //SHould fail
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
    void sellCapsulesToVisitor() {
        try {
            initial_capsules = dt.getBeverageCapsules(ct_id);
            dt.sellCapsulesToVisitor(ct_id, 10);
            assertEquals(initial_capsules - dt.getBeverageCapsules(ct_id), 10);
            initial_capsules = initial_capsules - 10;
        } catch (Exception e) {
            fail();
        }
        
        //Should fail
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
    void rechargeAccount() {
        try {
            initial_balance = dt.getEmployeeBalance(ee_id);
            dt.rechargeAccount(ee_id, 100);
            assertEquals(initial_balance + 100, (int) dt.getEmployeeBalance(ee_id));
        } catch (EmployeeException e) {
            fail();
        }
        
        //Should fail
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
    void buyBoxes() {
        try {
            dt.buyBoxes(ct_id, 1);
            int final_capsules = initial_capsules + dt.getBeverageCapsulesPerBox(ct_id);
            assertEquals(final_capsules, (int) dt.getBeverageCapsules(ct_id));
        } catch (Exception e) {
            fail();
        }
       
        //Should fail
        try {
        	dt.buyBoxes(ct_id, 100000);
        	fail();
        } catch(Exception e) {
        	assertTrue(true);
        }
        
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
        
        try {
        	dt.buyBoxes(-1,-1);
        	fail();
        } catch(Exception e) {
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
        
        //Should fail
        //Id < 0
        try {
            dt.getEmployeeReport(-1, new Date(System.currentTimeMillis()-24*60*60*1000), new Date());
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
        
        //startDate after new Date
        try {
            dt.getEmployeeReport(ee_id, new Date(System.currentTimeMillis()+24*60*60*1000), new Date());
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
        
        //endDate before startDate
        try {
            dt.getEmployeeReport(ee_id, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()-24*60*60*1000));
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
        
        //Should fail
        //startDate after new Date
        try {
            dt.getReport(new Date(System.currentTimeMillis()+24*60*60*1000), new Date());
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
        
        //endDate before startDate
        try {
        	dt.getReport(new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()-24*60*60*1000));
        	fail();
        }catch(Exception e) {
        	assertTrue(true);
        }
    }

    @Test
    void createBeverage() {
        try {
            int size = dt.getBeveragesId().size();
            dt.createBeverage("Test Beverage", 50, 75);
            assertEquals(size + 1, dt.getBeveragesId().size());
        } catch (BeverageException e) {
            fail();
        }
    }

    @Test
    void updateBeverage() {
        try {
            dt.updateBeverage(ct_id, "Update beverage", 50, 75);
            assertEquals(dt.getBeverageName(ct_id), "Update beverage");
        } catch (BeverageException e) {
            fail();
        }
        
        //Should fail
        try {
            dt.updateBeverage(-1, "Update beverage", 50, 75);
            fail();
        } catch (BeverageException e) {
            assertTrue(true);
        }
    }

    @Test
    void getBeverageName() {
        try {
            dt.updateBeverage(ct_id, "Test beverage", 50, 75);
            assertEquals(dt.getBeverageName(ct_id), "Test beverage");
        } catch (BeverageException e) {
           fail();
        }
        
        //Should fail
        try {
            dt.getBeverageName(-1);
            fail();
        } catch (BeverageException e) {
            assertTrue(true);
        }
    }

    @Test
    void getBeverageCapsulesPerBox() {
        try {
            assertEquals((int) dt.getBeverageCapsulesPerBox(ct_id), 50);
        } catch (BeverageException e) {
            fail();
        }
        
        //Should fail
        try {
            dt.getBeverageCapsulesPerBox(-1);
            fail();
        } catch (BeverageException e) {
            assertTrue(true);
        }
    }

    @Test
    void getBeverageBoxPrice() {
        try {
            assertEquals((int) dt.getBeverageBoxPrice(ct_id), 75);
        } catch (BeverageException e) {
            fail();
        }
        
        try {
            dt.getBeverageBoxPrice(-1);
            fail();
        } catch (BeverageException e) {
            assertTrue(true);
        }
    }

    @Test
    void getBeveragesId() {
        assertTrue(dt.getBeveragesId().size() > 0);
    }

    @Test
    void getBeverages() {
        assertTrue(dt.getBeverages().size() > 0);
    }

    @Test
    void getBeverageCapsules() {
        try {
            dt.buyBoxes(ct_id, 1);
            assertTrue(dt.getBeverageCapsules(ct_id) > 50);
        } catch (Exception e) {
            fail();
        }
        
        //Should fail
        try {
            dt.getBeverageCapsules(-1);
            fail();
        } catch (BeverageException e) {
            assertTrue(true);
        }
    }

    @Test
    void createEmployee() {
        try {
            int size = dt.getEmployeesId().size();
            dt.createEmployee("Test", "User");
            assertEquals(size + 1, dt.getEmployeesId().size());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void updateEmployee() {
        try {
            dt.updateEmployee(ee_id, "Test", "User");
            assertEquals(dt.getEmployeeName(ee_id), "Test");
            assertEquals(dt.getEmployeeSurname(ee_id), "User");
        } catch (EmployeeException e) {
            fail();
        }
        
        //Should fail
        try {
            dt.updateEmployee(-1, "Test", "User");
            fail();
        } catch (EmployeeException e) {
            assertTrue(true);
        }
    }

    @Test
    void getEmployeeName() {
        try {
            assertEquals(dt.getEmployeeName(ee_id), "Test");
        } catch (EmployeeException e) {
            fail();
        }
        
        //Should fail
        try {
            dt.getEmployeeName(-1);
            fail();
        } catch (EmployeeException e) {
            assertTrue(true);
        }
    }

    @Test
    void getEmployeeSurname() {
        try {
            assertEquals(dt.getEmployeeSurname(ee_id), "User");
        } catch (EmployeeException e) {
            fail();
        }
        
        //Should fail
        try {
            dt.getEmployeeSurname(-1);
            fail();
        } catch (EmployeeException e) {
            assertTrue(true);
        }
    }
    
    @Test
    void getEmployeeBalance() {
        try {
            dt.rechargeAccount(ee_id, 1000);
            assertTrue(dt.getEmployeeBalance(ee_id) >= 1000);
        } catch (EmployeeException e) {
            fail();
        }
        
        //Should fail
        try {
            dt.getEmployeeBalance(-1);
            fail();
        } catch (EmployeeException e) {
            assertTrue(true);
        }
    }

    @Test
    void getEmployeesId() {
        assertTrue(dt.getEmployeesId().size() > 0);
    }

    @Test
    void getEmployees() {
        assertTrue(dt.getEmployees().size() > 0);
    }

    @Test
    void getBalance() {
        assertTrue(dt.getBalance() > 0);
    }

    @Test
    void toString1() {
        assertNotNull(dt.toString());
    }

}
