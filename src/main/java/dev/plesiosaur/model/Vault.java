package dev.plesiosaur.model;

import java.time.ZonedDateTime;
import java.util.UUID;

public class Vault {

    private final UUID id;
    private final ZonedDateTime created;

    private boolean dirty;
    private String fileName;

    public Vault() {
        id = UUID.randomUUID();
        created = ZonedDateTime.now();
        dirty = false;
        fileName = null;
    }

    public UUID getId() {
        return id;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public boolean isDirty() {
        return dirty;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
