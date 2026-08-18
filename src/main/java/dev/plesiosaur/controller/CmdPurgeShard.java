package dev.plesiosaur.controller;

import dev.plesiosaur.model.Shard;
import dev.plesiosaur.model.ShardList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CmdPurgeShard implements Command {

    private final List<Shard> shardsToRemove;
    private final ShardList shardList;
    private List<Shard> shardsBeforeRemoval;

    public CmdPurgeShard(ShardList shardList, List<Shard> shards) {
        this.shardList = shardList;
        this.shardsToRemove = List.copyOf(shards);
    }

    @Override
    public void execute() {
        shardsBeforeRemoval = List.copyOf(shardList.getShards());
        for(Shard shard : shardsToRemove) {
            shardList.removeShard(shard);
        }
    }

    @Override
    public void undo() {
        shardList.clearAndReplace(shardsBeforeRemoval);
    }

    @Override
    public void redo() {
        for(Shard shard : shardsToRemove) {
            shardList.removeShard(shard);
        }
    }

    @Override
    public boolean isUndoable() {
        return true;
    }
}
