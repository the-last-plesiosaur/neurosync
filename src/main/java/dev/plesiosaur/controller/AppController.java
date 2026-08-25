package dev.plesiosaur.controller;

import dev.plesiosaur.model.NeurosyncDocument;
import dev.plesiosaur.model.Shard;

import java.io.File;
import java.util.List;

public class AppController {

    private final NeurosyncDocument model;

    public AppController(NeurosyncDocument model) {
        this.model = model;
    }

    public void newVault() {
        model.newVault();
    }

    public void closeVault() {
        model.closeVault();
    }

    public void openVault(String fileName) {

    }

    public void saveAsVault(File file) {
        model.saveAsVault(file);
    }

    public void newShard() {
        CmdNewShard cmdNewShard = new CmdNewShard(model.getVault());
        model.getCommandStack().execute(cmdNewShard);
    }

    public void markShard(Shard s, boolean marked) {
        // Needs dirty tracking
        CmdMarkShard cmdMarkShard = new CmdMarkShard(s, marked);
        model.getCommandStack().execute(cmdMarkShard);
    }

    public void freezeShard(Shard s, boolean coldStorage) {
        // Needs dirty tracking
        CmdFreezeShard cmdFreezeShard = new CmdFreezeShard(s, coldStorage);
        model.getCommandStack().execute(cmdFreezeShard);
    }

    public void rekeyShard(Shard s, String newKey) {
        // Needs dirty tracking
        CmdRekeyShard cmdRekeyShard = new CmdRekeyShard(s, newKey);
        model.getCommandStack().execute(cmdRekeyShard);
    }

    public void rekeyShardsByKey(String oldKey, String newKey) {
        // Needs dirty tracking
        List<Shard> shardsToRekey = model.getVault().getShardList().getShards().stream()
                .filter(s -> s.hasKey(oldKey))
                .toList();

        CmdRekeyShard cmdRekeyShard = new CmdRekeyShard(shardsToRekey, newKey);
        model.getCommandStack().execute(cmdRekeyShard);
    }

    public void purgeShardsByKey(String key) {
        // Needs dirty tracking
        List<Shard> shardsToPurge = model.getVault().getShardList()
                                        .getShards()
                                        .stream()
                                        .filter(s -> s.getKey() != null)
                                        .filter(s -> s.getKey().equals(key))
                                        .toList();

        CmdPurgeShard cmdPurgeShard = new CmdPurgeShard(model.getVault().getShardList(), shardsToPurge);
        model.getCommandStack().execute(cmdPurgeShard);
    }

    public void purgeShard(Shard shard) {
        // Needs dirty tracking
        CmdPurgeShard cmdPurgeShard = new CmdPurgeShard(model.getVault().getShardList(), shard);
        model.getCommandStack().execute(cmdPurgeShard);
    }

}
