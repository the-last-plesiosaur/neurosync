package dev.plesiosaur.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShardList implements PropertyChangeListener {

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private final List<Shard> shards;

    public ShardList() {
        shards = new ArrayList<>();
    }

    public List<Shard> getShards() {
        return Collections.unmodifiableList(shards);
    }

    public void addShard(Shard shard) {
        List<Shard> old = getShards();
        shards.add(shard);
        shard.addPropertyChangeListener(this);
        pcs.firePropertyChange("shards", old, shards);
    }

    public void removeShard(Shard shard) {
        List<Shard> old = getShards();
        shards.remove(shard);
        shard.removePropertyChangeListener(this);
        pcs.firePropertyChange("shards", old, shards);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        pcs.firePropertyChange(evt);
    }
}
