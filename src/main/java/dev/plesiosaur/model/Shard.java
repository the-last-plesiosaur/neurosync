package dev.plesiosaur.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Shard {
    private final UUID id;
    private final ZonedDateTime created;
    private String challengeText;
    private String responseText;
    private String key;
    private ZonedDateTime nextJack;
    private boolean marked;
    private boolean coldStorage;

    private final List<ShardObserver> observers = new ArrayList<>();

    public Shard() {
        id = UUID.randomUUID();
        created = ZonedDateTime.now();
        marked = false;
        coldStorage = false;
    }

    public Shard(UUID id, ZonedDateTime created) {
        this.id = id;
        this.created = created;
    }

    public void addShardObserver(ShardObserver observer) {
        observers.add(observer);
    }

    public void removeShardObserver(ShardObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (ShardObserver observer : observers) {
            observer.shardChanged(this);
        }
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
        if(!Objects.equals(this.key, key)) {
            this.key = key;
            notifyObservers();
        }
    }

    public boolean hasKey(String key) {
        return Objects.equals(this.key, key);
    }

    public boolean isColdStorage() {
        return coldStorage;
    }

    public void setColdStorage(boolean coldStorage) {
        if(this.coldStorage != coldStorage) {
            this.coldStorage = coldStorage;
            notifyObservers();
        }
    }

    public ZonedDateTime getNextJack() {
        return nextJack;
    }

    public void setNextJack(ZonedDateTime nextJack) {
        if(!Objects.equals(this.nextJack, nextJack)) {
            this.nextJack = nextJack;
            notifyObservers();
        }
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        if(marked != this.marked) {
            this.marked = marked;
            notifyObservers();
        }
    }

    public String getChallengeText() {
        return challengeText;
    }

    public void setChallengeText(String challengeText) {
        this.challengeText = challengeText;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }
}
