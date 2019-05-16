package it.polito.latazza.data;

import it.polito.latazza.entities.Transaction;
import it.polito.latazza.entities.TransactionImpl;
import it.polito.latazza.exceptions.DateException;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestTransactionImpl {

    @Test
    void testTransactionImpl() {
        Date d = new Date();
        Transaction t = null;
        try {
            t = new TransactionImpl(d, 45, Transaction.Type.RECHARGE, 0);
        } catch (DateException e) {
            fail();
        }
        assertEquals(t.getDate(), d);
        assertEquals(45, (int) t.getAmount());
        assertEquals(t.getType(), Transaction.Type.RECHARGE);
        assertEquals((int) t.getObject(), 0);
        assertNull(t.getDirectObject());

        Map<String, Object> map = new HashMap<>();
        map.put("date", d);
        map.put("amount", 10);
        map.put("type", Transaction.Type.BOX_PURCHASE);
        map.put("object" , null);
        map.put("directObject", 15);
        Transaction t2 = null;
        try {
            t2 = new TransactionImpl(map);
        } catch (DateException e) {
            fail();
        }
        assertEquals(t2.getDate().toString(), d.toString());
        assertEquals(10, (int) t2.getAmount());
        assertEquals(t2.getType(), Transaction.Type.BOX_PURCHASE);
        assertNull(t2.getObject());
        assertEquals((int) t2.getDirectObject(), 15);
    }
}
