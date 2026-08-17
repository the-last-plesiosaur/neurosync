package dev.plesiosaur.controller;

import dev.plesiosaur.model.Shard;

public class CmdRekeyShard implements Command {

    private final Shard s;
    private final String newKey;
    private String oldKey;

    public CmdRekeyShard(Shard s, String newKey) {
        this.s = s;
        this.newKey = newKey;
    }

    @Override
    public void execute() {
        oldKey = s.getKey();
        s.setKey(newKey);
    }

    @Override
    public void undo() {
        s.setKey(oldKey);
    }

    @Override
    public void redo() {
        s.setKey(newKey);
    }

    @Override
    public boolean isUndoable() {
        return true;
    }
}
