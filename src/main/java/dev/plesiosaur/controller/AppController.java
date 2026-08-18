package dev.plesiosaur.controller;

import dev.plesiosaur.model.AppModel;
import dev.plesiosaur.model.Shard;

import java.util.List;
import java.util.Objects;

public class AppController {

    private final AppModel model;

    public AppController(AppModel model) {
        this.model = model;
    }

    public void newVault() {
        model.setVaultOpen(true);
        model.setVaultDirty(true);
    }

    public void closeVault() {
        model.setVaultOpen(false);
        model.setVaultDirty(false);
    }

    public void openVault(String fileName) {
        model.setVaultOpen(true);
        model.setVaultDirty(false);

    }

    public void newShard() {
        //model.setVaultDirty(true);
        CmdNewShard cmdNewShard = new CmdNewShard(model.getShardList());
        model.getCommandStack().execute(cmdNewShard);
    }

    public void markShard(Shard s, boolean marked) {
        //model.setVaultDirty(true);
        CmdMarkShard cmdMarkShard = new CmdMarkShard(s, marked);
        model.getCommandStack().execute(cmdMarkShard);
    }

    public void rekeyShard(Shard s, String newKey) {
        //model.setVaultDirty(true);
        CmdRekeyShard cmdRekeyShard = new CmdRekeyShard(s, newKey);
        model.getCommandStack().execute(cmdRekeyShard);
    }

    public void rekeyShardsByKey(String oldKey, String newKey) {
        List<Shard> shardsToRekey = model.getShardList().getShards().stream()
                .filter(s -> s.getKey() != null)
                .filter(s -> s.getKey().equals(oldKey))
                .toList();

        CmdRekeyShard cmdRekeyShard = new CmdRekeyShard(shardsToRekey, newKey);
        model.getCommandStack().execute(cmdRekeyShard);
    }

    public void purgeShardsByKey(String key) {
        List<Shard> shardsToPurge = model.getShardList()
                                        .getShards()
                                        .stream()
                                        .filter(s -> s.getKey() != null)
                                        .filter(s -> s.getKey().equals(key))
                                        .toList();

        CmdPurgeShard cmdPurgeShard = new CmdPurgeShard(model.getShardList(), shardsToPurge);
        model.getCommandStack().execute(cmdPurgeShard);
    }

}
