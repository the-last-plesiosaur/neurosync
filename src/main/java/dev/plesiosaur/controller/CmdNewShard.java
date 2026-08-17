package dev.plesiosaur.controller;

import dev.plesiosaur.model.Shard;
import dev.plesiosaur.model.ShardList;

public class CmdNewShard implements Command {

    private final ShardList shardList;
    private Shard newShard;

    public CmdNewShard(ShardList shardList) {
        this.shardList = shardList;
    }

    @Override
    public void execute() {
        newShard = new Shard();
        shardList.addShard(newShard);
    }

    @Override
    public void undo() {
        shardList.removeShard(newShard);
    }

    @Override
    public void redo() {
        shardList.addShard(newShard);
    }

    @Override
    public boolean isUndoable() {
        return true;
    }
}
