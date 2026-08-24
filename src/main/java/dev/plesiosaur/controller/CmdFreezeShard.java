package dev.plesiosaur.controller;

import dev.plesiosaur.model.Shard;

public class CmdFreezeShard implements Command {

    private final Shard shard;
    private final Boolean b;
    private Boolean oldValue;

    public CmdFreezeShard(Shard shard, Boolean b) {
        this.shard = shard;
        this.b = b;
    }

    @Override
    public void execute() {
        oldValue = shard.isColdStorage();
        shard.setColdStorage(b);
    }

    @Override
    public void undo() {
        shard.setColdStorage(oldValue);
    }

    @Override
    public void redo() {
        shard.setColdStorage(b);
    }

}