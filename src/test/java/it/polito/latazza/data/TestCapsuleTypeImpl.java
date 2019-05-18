package it.polito.latazza.data;

import it.polito.latazza.entities.CapsuleType;
import it.polito.latazza.entities.CapsuleTypeImpl;
import it.polito.latazza.exceptions.BeverageException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestCapsuleTypeImpl {

    static CapsuleType ct = null;
    static CapsuleType ct2 = null;
    static CapsuleType ct3 = null;
    static CapsuleType ct4 = null;

    @BeforeAll
    static void testCapsuleTypeImpl() {

        try {
            ct = new CapsuleTypeImpl(0, "Coffee", 50, 75);
        } catch (BeverageException e) {
            fail();
        }

        try {
            ct4 = new CapsuleTypeImpl(0, "Coffee", 50, 75);
        } catch (BeverageException e ) {
            fail();
        }

        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("name", "Tea");
        map.put("capsulesPerBox", 100);
        map.put("boxPrice", 200);
        map.put("quantity", 99);
        try {
            ct2 = new CapsuleTypeImpl(map);
        } catch (BeverageException e) {
            fail();
        }

        try {
            ct3 = new CapsuleTypeImpl(-1, "Coffee", 50, 75);
            fail();
        } catch (BeverageException e) {
            assertTrue(true);
        }

        Map<String, Object> map2 = new HashMap<>();
        map2.put("id", -2);
        map2.put("name", "Tea");
        map2.put("capsulesPerBox", 100);
        map2.put("boxPrice", 200);
        map2.put("quantity", 99);
        try {
            ct3 = new CapsuleTypeImpl(map2);
            fail();
        } catch (BeverageException e) {
            assertTrue(true);
        }
    }

    @Test
    void getPrice() {
        assertEquals((int) ct.getPrice(), ct.getBoxPrice()/ct.getCapsulesPerBox());
    }

    @Test
    void getQuantity() {
        assertEquals(ct.getQuantity(), Integer.valueOf(0));
    }

    @Test
    void getBoxPrice() {
        assertEquals(ct.getBoxPrice(), Integer.valueOf(75));
    }

    @Test
    void getCapsulesPerBox() {
        assertEquals(ct.getCapsulesPerBox(), Integer.valueOf(50));
    }

    @Test
    void update() {
        try {
            ct4.update("Ginseng", 25, 50);
        } catch (BeverageException e) {
            fail();
        }
        assertEquals(ct4.getId(), Integer.valueOf(0));
        assertEquals(ct4.getName(), "Ginseng");
        assertEquals(ct4.getCapsulesPerBox(), Integer.valueOf(25));
        assertEquals(ct4.getBoxPrice(), Integer.valueOf(50));
        assertEquals(ct4.getQuantity(), Integer.valueOf(0));

        try {
            ct4.update("Light Coffee", 10, -5);
            fail();
        } catch (BeverageException e) {
            assertTrue(true);
        }
    }

    @Test
    void updateQuantity() {
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

        try {
            ct2.updateQuantity(Integer.MAX_VALUE);
            fail();
        } catch (BeverageException e) {
            assertTrue(true);
        }
    }

    @Test
    void getName() {
        assertEquals(ct.getName(), "Coffee");
    }

    @Test
    void getId() {
        assertEquals(ct.getId(), Integer.valueOf(0));
    }

    @Test
    void testToString() {
        assertNotNull(ct.toString());
    }
}
