package dev.plesiosaur.controller;

import dev.plesiosaur.model.Shard;

public class CmdMarkShard implements Command {

    private final Shard shard;
    private final Boolean b;
    private Boolean oldValue;

    public CmdMarkShard(Shard shard, Boolean b) {
        this.shard = shard;
        this.b = b;
    }

    @Override
    public void execute() {
        oldValue = shard.isMarked();
        shard.setMarked(b);
    }

    @Override
    public void undo() {
        shard.setMarked(oldValue);
    }

    @Override
    public void redo() {
        shard.setMarked(b);
    }

    @Override
    public boolean isUndoable() {
        return true;
    }
}
