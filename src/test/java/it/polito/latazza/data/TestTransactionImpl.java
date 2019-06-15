package it.polito.latazza.data;

import it.polito.latazza.entities.Transaction;

import it.polito.latazza.entities.TransactionImpl;
import it.polito.latazza.exceptions.DateException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestTransactionImpl {

    static Transaction t = null;
    static Transaction t2 = null;
    static Transaction t3 = null;
    static Transaction t4 = null;
    static Date d = null;
    static Date d2 = null;
    static Map<String, Object> map = null;
    static Map<String, Object> map2 = null;

    @BeforeAll
    static void testTransactionImpl() {

        d = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.DATE, 1);
        d2 = c.getTime();
        map = new HashMap<>();
        map2 = new HashMap<>();
        map.put("date", d.getTime());
        map.put("amount", 10);
        map.put("type", Transaction.Type.BOX_PURCHASE);
        map.put("object" , null);
        map.put("directObject", 15);
        map2.put("date", d2.getTime());
        map2.put("amount", 10);
        map2.put("type", Transaction.Type.BOX_PURCHASE);
        map2.put("object" , null);
        map2.put("directObject", 15);
        
        try {
            t = new TransactionImpl(d, 45, Transaction.Type.RECHARGE, 0);
        } catch (DateException e) {
            fail();
        }
        
        try {
            t2 = new TransactionImpl(map);
        } catch (DateException e) {
            fail();
        }

        try {
            t3 = new TransactionImpl(d2, 45, Transaction.Type.RECHARGE, 0);
            fail();
        } catch (DateException e) {
            assertTrue(true);
        }

        try {
            t3 = new TransactionImpl(map2);
            fail();
        } catch (DateException e) {
            assertTrue(true);
        }

        try {
            map2.remove("date");
            t4 = new TransactionImpl(map2);
            fail();
        } catch (DateException e) {
            assertNull(t4);
        }
        
    }

    @Test
    void getDate() {
        assertEquals(t.getDate(), d);
    }

    @Test
    void getAmount() {
        assertEquals(45, (int) t.getAmount());
    }

    @Test
    void getType() {
        assertEquals(t.getType(), Transaction.Type.RECHARGE);
    }

    @Test
    void getDirectObject() {
        assertNull(t.getDirectObject());
    }

    @Test
    void getObject() {
        assertEquals((int) t.getObject(), 0);
    }

    @Test
    void testToString() {
        assertNotNull(t.toString());
    }
}
