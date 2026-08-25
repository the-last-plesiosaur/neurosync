package dev.plesiosaur.controller;

import dev.plesiosaur.model.Shard;
import dev.plesiosaur.model.ShardList;
import dev.plesiosaur.model.Vault;

public class CmdNewShard implements Command {

    private final Vault vault;
    private Shard newShard;

    public CmdNewShard(Vault v) {
        vault = v;
    }

    @Override
    public void execute() {
        newShard = new Shard();
        vault.getShardList().addShard(newShard);
    }

    @Override
    public void undo() {
        vault.getShardList().removeShard(newShard);
    }

    @Override
    public void redo() {
        vault.getShardList().addShard(newShard);
    }

}
