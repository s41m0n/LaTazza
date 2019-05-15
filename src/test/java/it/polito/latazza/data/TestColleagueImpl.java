package it.polito.latazza.data;

import it.polito.latazza.entities.Colleague;
import it.polito.latazza.entities.ColleagueImpl;
import it.polito.latazza.exceptions.EmployeeException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestColleagueImpl {

    @Test
    void testColleagueImpl() {

        Colleague ci = null;
        try {
            ci = new ColleagueImpl(0, "Ted", "Mosby");
        } catch (EmployeeException e) {
            fail();
        }
        assertEquals(ci.getBalance(), Integer.valueOf(0));
        assertEquals(ci.getId(), Integer.valueOf(0));
        assertEquals(ci.getName(), "Ted");
        assertEquals(ci.getSurname(), "Mosby");

        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("balance", 4243);
        map.put("name", "Marshall");
        map.put("surname", "Ericksen");
        Colleague ci2 = null;
        try {
            ci2 = new ColleagueImpl(map);
        } catch (EmployeeException e) {
            fail();
        }
        assertEquals(ci2.getBalance(), Integer.valueOf(4243));
        assertEquals(ci2.getId(), Integer.valueOf(1));
        assertEquals(ci2.getName(), "Marshall");
        assertEquals(ci2.getSurname(), "Ericksen");

        ci.update("Barney", "Stinson");
        assertEquals(ci.getName(), "Barney");
        assertEquals(ci.getSurname(), "Stinson");

        try {
            ci2.updateBalance(57);
            assertEquals(ci2.getBalance(), Integer.valueOf(4300));
        } catch (EmployeeException e) {
            fail();
        }
    }

    @Test
    void testColleagueImplException() {

        try {
            Colleague cf = new ColleagueImpl(-1, "Herbert", "Bean");
        } catch (EmployeeException e) {
            assertTrue(true);
        }

        Colleague ci = null;
        try {
            ci = new ColleagueImpl(0, "Ted", "Mosby");
        } catch (EmployeeException e) {
            fail();
        }

        try {
            ci.updateBalance(1);
            ci.updateBalance(Integer.MAX_VALUE);
            fail();
        } catch (EmployeeException e) {
            assertTrue(true);
        }

        try {
            ci.updateBalance(-2);
            ci.updateBalance(Integer.MIN_VALUE);
            fail();
        } catch (EmployeeException e) {
            assertTrue(true);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("id", -2);
        map.put("balance", 4243);
        map.put("name", "Marshall");
        map.put("surname", "Ericksen");
        Colleague ci2 = null;
        try {
            ci2 = new ColleagueImpl(map);
            fail();
        } catch (EmployeeException e) {
            assertTrue(true);
        }
    }
}
