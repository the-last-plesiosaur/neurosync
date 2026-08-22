package dev.plesiosaur.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShardList implements ShardObserver {

    private static final Logger log = LoggerFactory.getLogger(ShardList.class);

    private final List<ShardListObserver> observers = new ArrayList<>();
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
        for(Shard s : this.shards) {
            s.removeShardObserver(this);
        }
        this.shards.clear();

        for(Shard s : shards) {
            s.addShardObserver(this);
        }
        this.shards.addAll(shards);

        notifyObservers();
    }

    public void addShard(Shard shard) {
        shards.add(shard);
        shard.addShardObserver(this);
        notifyObservers();

    }

    public void removeShard(Shard shard) {
        shards.remove(shard);
        shard.removeShardObserver(this);
        notifyObservers();
    }

    public void addShardListObserver(ShardListObserver observer) {
        observers.add(observer);
    }

    public void removeShardListObserver(ShardListObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for(ShardListObserver observer : observers) {
            observer.shardListChanged(this);
        }
    }

    @Override
    public void shardChanged(Shard shard) {
        // Bubble changes from the shards up to our listeners
        notifyObservers();

    }
}
