package dev.plesiosaur.persistence;

import dev.plesiosaur.model.Shard;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.ZonedDateTime;
import java.util.UUID;

@XmlType(propOrder = {"id", "created", "key", "challengeText", "responseText", "nextJack", "marked", "coldStorage"})
public class ShardRecord {

    private UUID id;
    private ZonedDateTime created;
    private String challengeText;
    private String responseText;
    private String key;
    private ZonedDateTime nextJack;
    private boolean marked;
    private boolean coldStorage;

    public ShardRecord() {}

    public ShardRecord(Shard shard) {
        this.id = shard.getId();
        this.created = shard.getCreated();
        this.challengeText = shard.getChallengeText();
        this.responseText = shard.getResponseText();
        this.key = shard.getKey();
        this.nextJack = shard.getNextJack();
        this.marked = shard.isMarked();
        this.coldStorage = shard.isColdStorage();
    }

    public Shard toShard() {
        Shard shard = new Shard(this.id, this.created);
        shard.setChallengeText(this.challengeText);
        shard.setResponseText(this.responseText);
        shard.setKey(this.key);
        shard.setNextJack(this.nextJack);
        shard.setMarked(this.marked);
        shard.setColdStorage(this.coldStorage);

        return shard;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @XmlJavaTypeAdapter(ZonedDateTimeAdapter.class)
    public ZonedDateTime getCreated() {
        return created;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @XmlJavaTypeAdapter(ZonedDateTimeAdapter.class)
    public ZonedDateTime getNextJack() {
        return nextJack;
    }

    public void setNextJack(ZonedDateTime nextJack) {
        this.nextJack = nextJack;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public boolean isColdStorage() {
        return coldStorage;
    }

    public void setColdStorage(boolean coldStorage) {
        this.coldStorage = coldStorage;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public String getChallengeText() {
        return challengeText;
    }

    public void setChallengeText(String challengeText) {
        this.challengeText = challengeText;
    }
}
