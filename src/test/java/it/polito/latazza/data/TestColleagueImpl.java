package it.polito.latazza.data;

import it.polito.latazza.entities.Colleague;
import it.polito.latazza.entities.ColleagueImpl;
import it.polito.latazza.exceptions.EmployeeException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestColleagueImpl {

    static Colleague ci = null;
    static Colleague ci2 = null;
    static Colleague cf = null;
    static Colleague cf2 = null;

    @BeforeAll
    static void testColleagueImpl() {

        try {
            ci = new ColleagueImpl(0, "Ted", "Mosby");
        } catch (EmployeeException e) {
            fail();
        }

        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("balance", 4243);
        map.put("name", "Marshall");
        map.put("surname", "Ericksen");
        try {
            ci2 = new ColleagueImpl(map);
        } catch (EmployeeException e) {
            fail();
        }

        try {
            cf = new ColleagueImpl(-1, "Herbert", "Bean");
            fail();
        } catch (EmployeeException e) {
            assertTrue(true);
        }

        Map<String, Object> map2 = new HashMap<>();
        map2.put("id", -2);
        map2.put("balance", 4243);
        map2.put("name", "Marshall");
        map2.put("surname", "Ericksen");
        try {
            cf2 = new ColleagueImpl(map2);
            fail();
        } catch (EmployeeException e) {
            assertTrue(true);
        }
    }

    @Test
    void getBalance() {
        assertEquals(ci.getBalance(), Integer.valueOf(0));
    }

    @Test
    void getId() {
        assertEquals(ci.getId(), Integer.valueOf(0));
    }

    @Test
    void getName() {
        assertEquals(ci.getName(), "Ted");
    }

    @Test
    void getSurname() {
        assertEquals(ci.getSurname(), "Mosby");
    }

    @Test
    void update() {
        ci2.update("Barney", "Stinson");
        assertEquals(ci2.getName(), "Barney");
        assertEquals(ci2.getSurname(), "Stinson");
    }

    @Test
    void updateBalance() {
        try {
            ci2.updateBalance(57);
            assertEquals(ci2.getBalance(), Integer.valueOf(4300));
        } catch (EmployeeException e) {
            fail();
        }
        
        // To meet second condition
        try {
            ci2.updateBalance(Integer.MAX_VALUE);
            fail();
        } catch (EmployeeException e) {
            assertTrue(true);
        }
        
        //To meet first condition
        try {
        	ci2.updateBalance(Integer.MIN_VALUE - ci2.getBalance());
        	fail();
        } catch (EmployeeException e) {
        	assertTrue(true);
        }
    }

    @Test
    void testToString() {
        assertNotNull(ci.toString());
    }
}
