package dev.plesiosaur.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ShardListTest {

    ShardList shardList;
    Shard s1, s2, s3, s4;

    @BeforeEach
    void setUp() {
        shardList = new ShardList();

        s1 = new Shard();
        s1.setKey("Dog");

        s2 = new Shard();
        s2.setKey("Cat");

        s3 = new Shard();
        s3.setKey("Dog");

        s4 = new Shard(); // key is null

        shardList.addShard(s1);
        shardList.addShard(s2);
        shardList.addShard(s3);
        shardList.addShard(s4);
    }

    @Test
    void getUniqueKeys() {
        List<String> keys = shardList.getUniqueKeys();
        assertNotNull(keys);
        assertEquals(2, keys.size());
        assertEquals("Cat", keys.getFirst());
        assertEquals("Dog", keys.getLast());
    }

    @Test
    void withKey() {
        List<Shard> matchingShards = shardList.withKey("Dog");

        assertNotNull(matchingShards);
        assertEquals(2, matchingShards.size());
        assertTrue(matchingShards.contains(s1));
        assertTrue(matchingShards.contains(s3));
    }

    @Test
    void withKeysNoMatch() {
        List<Shard> matchingShards = shardList.withKey("Turtle");

        assertNotNull(matchingShards);
        assertEquals(0, matchingShards.size());
    }
}
