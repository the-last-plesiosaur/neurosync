package dev.plesiosaur.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShardTest {

    @Test
    void hasKey() {
        Shard s = new  Shard();
        s.setKey("Cat");

        assertTrue(s.hasKey("Cat"));
        assertFalse(s.hasKey("Dog"));
    }

    @Test
    void hasKeyNoErrorWithNullKey() {
        Shard s = new Shard();
        assertFalse(s.hasKey("Tree"));
    }
}
