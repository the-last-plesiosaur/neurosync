package dev.plesiosaur.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShardList implements PropertyChangeListener {

    private static final Logger log = LoggerFactory.getLogger(ShardList.class);
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private final List<Shard> shards;

    public ShardList() {
        shards = new ArrayList<>();
    }

    public List<String> getUniqueKeys() {
        return this.shards.stream()
                .map(Shard::getKey)
                .filter(Objects::nonNull)
                .filter(Predicate.not(String::isBlank))
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<Shard> getShards() {
        return Collections.unmodifiableList(shards);
    }

    public List<Shard> withKey(String key) {
        return shards.stream()
                .filter(s -> s.hasKey(key))
                .collect(Collectors.toList());
    }

    public void clearAndReplace(List<Shard> shards) {
        List<Shard> old = List.copyOf(shards);

        for(Shard s : this.shards) {
            s.removePropertyChangeListener(this);
        }
        this.shards.clear();

        for(Shard s : shards) {
            s.addPropertyChangeListener(this);
        }
        this.shards.addAll(shards);

        pcs.firePropertyChange("shards", old, shards);
    }

    public void addShard(Shard shard) {
        List<Shard> old = List.copyOf(shards);
        shards.add(shard);
        shard.addPropertyChangeListener(this);
        pcs.firePropertyChange("shards", old, shards);
    }

    public void removeShard(Shard shard) {
        List<Shard> old = List.copyOf(shards);
        shards.remove(shard);
        shard.removePropertyChangeListener(this);
        pcs.firePropertyChange("shards", old, shards);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Bubble changes to the shards up to our listeners
        pcs.firePropertyChange(evt);
    }
}
