package dev.plesiosaur;

import dev.plesiosaur.model.AppModel;

public class AppController {

    private final AppModel model;

    public AppController(AppModel model) {
        this.model = model;
    }


    public void newVault() {
        model.setVaultOpen(true);

    }

    public void closeVault() {
        model.setVaultOpen(false);
    }

    public void openVault(String fileName) {
        model.setVaultOpen(true);

    }


}
