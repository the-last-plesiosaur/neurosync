package dev.plesiosaur.controller;

import dev.plesiosaur.model.Shard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CmdRekeyShard implements Command {

    private final List<Shard> shardsToRekey;
    private final String newKey;
    private final String oldKey;

    public CmdRekeyShard(Shard s, String newKey) {
        shardsToRekey = new ArrayList<>();
        shardsToRekey.add(s);
        this.newKey = newKey;
        this.oldKey = s.getKey();
    }

    public CmdRekeyShard(List<Shard> shards, String newKey) {
        shardsToRekey = shards;
        this.newKey = newKey;
        this.oldKey = shardsToRekey.getFirst().getKey();
    }

    public String getNewKey() {
        return newKey;
    }

    public String getOldKey() {
        return oldKey;
    }

    public List<Shard> getShardsToRekey() {
        return Collections.unmodifiableList(shardsToRekey);
    }

    @Override
    public void execute() {
        for(Shard s : shardsToRekey) {
            s.setKey(newKey);
        }
    }

    @Override
    public void undo() {
        for(Shard s : shardsToRekey) {
            s.setKey(oldKey);
        }
    }

    @Override
    public void redo() {
        for(Shard s : shardsToRekey) {
            s.setKey(newKey);
        }
    }

}
