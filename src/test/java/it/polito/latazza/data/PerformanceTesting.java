package it.polito.latazza.data;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import it.polito.latazza.exceptions.BeverageException;
import it.polito.latazza.exceptions.DateException;
import it.polito.latazza.exceptions.EmployeeException;
import it.polito.latazza.exceptions.NotEnoughBalance;
import it.polito.latazza.exceptions.NotEnoughCapsules;

public class PerformanceTesting {
	
	/* Test performance on method sellCapsules() */
	@Test
	void testNFR1() throws EmployeeException, BeverageException, NotEnoughCapsules, NotEnoughBalance {
		DataImpl d = new DataImpl();
		
		d.reset();
		
		d.createEmployee("First", "User");
		d.createBeverage("Beverage1", 100, 20);
		int empId = d.getEmployeesId().get(0);
		int bevId = d.getBeveragesId().get(0);
		d.rechargeAccount(empId, 1000);
        d.buyBoxes(bevId, 1);
		
		long begin, end;
		long totalTime = 0;
		for(int i = 0; i < 100; i++) {
			begin = System.currentTimeMillis();
			d.sellCapsules(empId, bevId, 1, true);
			end = System.currentTimeMillis();
			totalTime += end - begin;
		}
		double avgTime = totalTime/100.0;
		assertTrue(avgTime < 500);
	}
	
	/* Test performance on method sellCapsulesToVisitor() */
	@Test
	void testNFR2() throws BeverageException, NotEnoughBalance, NotEnoughCapsules, EmployeeException {
		DataImpl d = new DataImpl();
		
		d.reset();
		
		d.createEmployee("Manager", "System");
		d.createBeverage("Beverage2", 100, 20);
		int manId = d.getEmployeesId().get(0);
		int bevId = d.getBeveragesId().get(0);
		d.rechargeAccount(manId, 1000);
		d.buyBoxes(bevId, 1);
		
		long begin, end;
		long totalTime = 0;
		for(int i = 0; i < 100; i++) {
			begin = System.currentTimeMillis();
			d.sellCapsulesToVisitor(bevId, 1);
			end = System.currentTimeMillis();
			totalTime += end - begin;
		}
		double avgTime = totalTime/100.0;
		assertTrue(avgTime < 500);
		
	}
	
	/* Test performance on method rechargeAccount() */
	@Test
	void testNFR3() throws EmployeeException {
		DataImpl d = new DataImpl();
		
		d.reset();
		
		d.createEmployee("Employee", "Test");
		int empId = d.getEmployeesId().get(0);
		
		long begin, end;
		long totalTime = 0;
		for(int i = 0; i < 100; i++) {
			begin = System.currentTimeMillis();
			d.rechargeAccount(empId, 1000);
			end = System.currentTimeMillis();
			totalTime += end - begin;
		}
		double avgTime = totalTime/100.0;
		assertTrue(avgTime < 500);
		
	}
	
	/* Test performance on method buyBoxes() */
	@Test
	void testNFR4() throws EmployeeException, BeverageException, NotEnoughBalance {
		DataImpl d = new DataImpl();
		
		d.reset();
		
		d.createEmployee("First", "User");
		d.createBeverage("Beverage1", 100, 20);
		int empId = d.getEmployeesId().get(0);
		int bevId = d.getBeveragesId().get(0);
		d.rechargeAccount(empId, 5000);
		
		long begin, end;
		long totalTime = 0;
		for(int i = 0; i < 100; i++) {
			begin = System.currentTimeMillis();
			d.buyBoxes(bevId, 1);
			end = System.currentTimeMillis();
			totalTime += end - begin;
		}
		double avgTime = totalTime/100.0;
		assertTrue(avgTime < 500);
		
	}
	
	/* Test performance on method getEmployeeReport */
	@Test
	void testNFR5() throws EmployeeException, BeverageException, NotEnoughBalance, NotEnoughCapsules, DateException {
		DataImpl d = new DataImpl();
		
		d.reset();
		
		d.createEmployee("First", "User");
		d.createBeverage("Beverage1", 100, 20);
		int empId = d.getEmployeesId().get(0);
		int bevId = d.getBeveragesId().get(0);
		d.rechargeAccount(empId, 5000);
		d.buyBoxes(bevId, 1);
		d.sellCapsules(empId, bevId, 1, true);
		
		long begin, end;
		long totalTime = 0;
		for(int i = 0; i < 100; i++) {
			begin = System.currentTimeMillis();
			d.getEmployeeReport(empId, new Date(System.currentTimeMillis()-24*60*60*1000), new Date(System.currentTimeMillis()+24*60*60*1000));
			end = System.currentTimeMillis();
			totalTime += end - begin;
		}
		double avgTime = totalTime/100.0;
		assertTrue(avgTime < 500);
		
	}

}
