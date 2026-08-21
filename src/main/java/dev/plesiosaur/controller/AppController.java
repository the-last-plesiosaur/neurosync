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
        model.newVault();
    }

    public void closeVault() {
        model.closeVault();
    }

    public void openVault(String fileName) {
        //model.setVaultOpen(true);
        //model.setVaultDirty(false);
    }

    public void newShard() {
        CmdNewShard cmdNewShard = new CmdNewShard(model.getVault());
        model.getCommandStack().execute(cmdNewShard);
    }

    public void markShard(Shard s, boolean marked) {
        //model.setVaultDirty(true);
        CmdMarkShard cmdMarkShard = new CmdMarkShard(s, marked);
        model.getCommandStack().execute(cmdMarkShard);
    }

    public void freezeShard(Shard s, boolean coldStorage) {
        CmdFreezeShard cmdFreezeShard = new CmdFreezeShard(s, coldStorage);
        model.getCommandStack().execute(cmdFreezeShard);
    }

    public void rekeyShard(Shard s, String newKey) {
        //model.setVaultDirty(true);
        CmdRekeyShard cmdRekeyShard = new CmdRekeyShard(s, newKey);
        model.getCommandStack().execute(cmdRekeyShard);
    }

    public void rekeyShardsByKey(String oldKey, String newKey) {
        List<Shard> shardsToRekey = model.getVault().getShardList().getShards().stream()
                .filter(s -> s.hasKey(oldKey))
                .toList();

        CmdRekeyShard cmdRekeyShard = new CmdRekeyShard(shardsToRekey, newKey);
        model.getCommandStack().execute(cmdRekeyShard);
    }

    public void purgeShardsByKey(String key) {
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
        CmdPurgeShard cmdPurgeShard = new CmdPurgeShard(model.getVault().getShardList(), shard);
        model.getCommandStack().execute(cmdPurgeShard);
    }

}
