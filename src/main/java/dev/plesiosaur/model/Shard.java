package dev.plesiosaur.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.ZonedDateTime;
import java.util.UUID;

public class Shard {

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private final UUID id;
    private final ZonedDateTime created;
    private String key;
    private ZonedDateTime nextJack;
    private boolean marked;

    public Shard() {
        id = UUID.randomUUID();
        created = ZonedDateTime.now();
        marked = false;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    public UUID getId() {
        return id;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        String old = this.key;
        this.key = key;
        pcs.firePropertyChange("key", old, key);
    }

    public ZonedDateTime getNextJack() {
        return nextJack;
    }

    public void setNextJack(ZonedDateTime nextJack) {
        ZonedDateTime old = this.nextJack;
        this.nextJack = nextJack;
        pcs.firePropertyChange("nextJack", old, nextJack);
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        boolean old = this.marked;
        this.marked = marked;
        pcs.firePropertyChange("marked", old, marked);
    }
}
