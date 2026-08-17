package dev.plesiosaur.controller;

import dev.plesiosaur.model.Shard;
import dev.plesiosaur.model.ShardList;

import java.util.Collections;
import java.util.List;

public class CmdPurgeShard implements Command {

    private final List<Shard> shards;
    private final ShardList shardList;

    public CmdPurgeShard(ShardList shardList, List<Shard> shards) {
        this.shardList = shardList;
        this.shards = List.copyOf(shards);
    }

    @Override
    public void execute() {
        for(Shard shard : shards) {
            shardList.removeShard(shard);
        }
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }

    @Override
    public boolean isUndoable() {
        return false;
    }
}
