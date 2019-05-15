package it.polito.latazza.data;

import it.polito.latazza.entities.CapsuleType;
import it.polito.latazza.entities.CapsuleTypeImpl;
import it.polito.latazza.exceptions.BeverageException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestCapsuleType {

    @Test
    void testCapsuleType() {

        CapsuleType ct = null;
        try {
            ct = new CapsuleTypeImpl(0, "Coffee", 50, 75);
        } catch (BeverageException e) {
            fail();
        }
        assertEquals(ct.getId(), Integer.valueOf(0));
        assertEquals(ct.getName(), "Coffee");
        assertEquals(ct.getCapsulesPerBox(), Integer.valueOf(50));
        assertEquals(ct.getBoxPrice(), Integer.valueOf(75));
        assertEquals(ct.getQuantity(), Integer.valueOf(0));

        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("name", "Tea");
        map.put("capsulesPerBox", 100);
        map.put("boxPrice", 200);
        map.put("quantity", 99);
        CapsuleType ct2 = null;
        try {
            ct2 = new CapsuleTypeImpl(map);
        } catch (BeverageException e) {
            fail();
        }
        assertEquals(ct2.getId(), Integer.valueOf(1));
        assertEquals(ct2.getName(), "Tea");
        assertEquals(ct2.getCapsulesPerBox(), Integer.valueOf(100));
        assertEquals(ct2.getBoxPrice(), Integer.valueOf(200));
        assertEquals(ct2.getQuantity(), Integer.valueOf(99));

        try {
            ct.update("Ginseng", 25, 50);
        } catch (BeverageException e) {
            fail();
        }
        assertEquals(ct.getId(), Integer.valueOf(0));
        assertEquals(ct.getName(), "Ginseng");
        assertEquals(ct.getCapsulesPerBox(), Integer.valueOf(25));
        assertEquals(ct.getBoxPrice(), Integer.valueOf(50));
        assertEquals(ct.getQuantity(), Integer.valueOf(0));

        try {
            ct2.updateQuantity(1);
        } catch (BeverageException e) {
            fail();
        }
        assertEquals(ct2.getId(), Integer.valueOf(1));
        assertEquals(ct2.getName(), "Tea");
        assertEquals(ct2.getCapsulesPerBox(), Integer.valueOf(100));
        assertEquals(ct2.getBoxPrice(), Integer.valueOf(200));
        assertEquals(ct2.getQuantity(), Integer.valueOf(100));
    }
}
