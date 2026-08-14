package dev.plesiosaur;

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
        Shard s = new Shard();
        model.getShardList().addShard(s);
    }

}
