package dev.plesiosaur.controller;

import dev.plesiosaur.model.AppModel;
import dev.plesiosaur.model.Shard;

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

}
