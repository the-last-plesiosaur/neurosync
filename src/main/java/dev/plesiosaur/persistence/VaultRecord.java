package dev.plesiosaur.persistence;

import dev.plesiosaur.model.Shard;
import dev.plesiosaur.model.Vault;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@XmlRootElement(name = "vault")
@XmlType(propOrder = {"id", "created", "shards"})
public class VaultRecord {

    private UUID id;
    private ZonedDateTime created;
    private List<ShardRecord> shards;

    public VaultRecord() {}

    public VaultRecord(Vault vault) {
        this.id = vault.getId();
        this.created = vault.getCreated();

        this.shards = new ArrayList<>();
        for(Shard s : vault.getShardList().getShards()) {
            this.shards.add(new ShardRecord(s));
        }
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

    @XmlElementWrapper(name = "shards")
    @XmlElement(name = "shard")
    public List<ShardRecord> getShards() {
        return shards;
    }

    public void setShards(List<ShardRecord> shards) {
        this.shards = shards;
    }
}
