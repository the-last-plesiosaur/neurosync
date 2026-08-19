package dev.plesiosaur.controller;

import dev.plesiosaur.model.AppModel;
import dev.plesiosaur.model.Shard;
import dev.plesiosaur.model.ShardList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AppControllerTest {

    @Mock
    private AppModel model;

    @Mock
    private CommandStack  commandStack;

    @InjectMocks
    private AppController controller;

    @Captor
    private ArgumentCaptor<CmdRekeyShard> rekeyCaptor;

    @Test
    void rekeyShardsByKey() {
        ShardList shardList = new ShardList();

        Shard s1 = new Shard();
        s1.setKey("Dog");

        Shard s2 = new Shard();
        s2.setKey("Cat");

        Shard s3 = new Shard();
        s3.setKey("Dog");

        Shard s4 = new Shard(); // key is null

        shardList.addShard(s1);
        shardList.addShard(s2);
        shardList.addShard(s3);
        shardList.addShard(s4);

        when(model.getShardList()).thenReturn(shardList);
        when(model.getCommandStack()).thenReturn(commandStack);

        controller.rekeyShardsByKey("Dog", "Wolf");

        verify(commandStack).execute(rekeyCaptor.capture());

        CmdRekeyShard cmdRekeyShard = rekeyCaptor.getValue();
        assertEquals("Dog", cmdRekeyShard.getOldKey());
        assertEquals("Wolf", cmdRekeyShard.getNewKey());

        assertNotNull(cmdRekeyShard.getShardsToRekey());
        assertEquals(2, cmdRekeyShard.getShardsToRekey().size());

    }

}
