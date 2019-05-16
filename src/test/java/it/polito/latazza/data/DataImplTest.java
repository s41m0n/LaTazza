package it.polito.latazza.data;

import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.EmployeeException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataImplTest {

    DataImpl dt = new DataImpl();

    @BeforeAll
    public void init(){
        System.out.println("Before All init() method called");

        try {
            dt.createEmployee("Test", "User");
        } catch (EmployeeException e) {
            fail();
        }

        try {
            dt.createBeverage("TestBeverage", 50 , 75);
        } catch (BeverageException e) {
            fail();
        }
    }

    @Test
    void sellCapsules() {
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