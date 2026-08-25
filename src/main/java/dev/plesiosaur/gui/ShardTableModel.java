package dev.plesiosaur.gui;

import dev.plesiosaur.controller.AppController;
import dev.plesiosaur.model.*;

import javax.swing.table.AbstractTableModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.format.DateTimeFormatter;

public class ShardTableModel extends AbstractTableModel implements NeurosyncDocumentObserver, VaultObserver, ShardListObserver {

    private static final Logger log = LoggerFactory.getLogger(ShardTableModel.class);

    private static final String[] columns = {
            "Id",
            "Key",
            "Created",
            "Marked",
            "Cold Storage"
    };

    private static final Class<?>[] columnClasses = {
            String.class,
            String.class,
            String.class,
            Boolean.class,
            Boolean.class
    };

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm a");

    private final NeurosyncDocument model;
    private final AppController appController;

    public ShardTableModel(NeurosyncDocument model, AppController appController) {
        this.model = model;
        this.appController = appController;

        this.model.addObserver(this);
        if(this.model.hasVault()) {
            this.model.getVault().addVaultObserver(this);
            this.model.getVault().getShardList().addShardListObserver(this);
        }
    }

    public Shard getShardAt(int idx) {
        if(!model.hasVault()) {
            throw new IllegalArgumentException("No open vault");
        }
        return model.getVault().getShardList().getShards().get(idx);
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Class<?> getColumnClass(int column) {
        return columnClasses[column];
    }

    @Override
    public int findColumn(String columnName) {
        for (int i = 0; i < columns.length; i++) {
            if (columns[i].equals(columnName)) {
                return i;
            }
        }

        throw new IllegalArgumentException("Column " + columnName + " not found");
    }

    @Override
    public int getRowCount() {
        if(model.hasVault()) {
            return model.getVault().getShardList().getShards().size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(!model.hasVault()) {
            throw new IllegalArgumentException("No open vault");
        }

        ShardList shardList = model.getVault().getShardList();

        if(rowIndex >= shardList.getShards().size()) {
            throw new IllegalArgumentException("Row index out of bounds");
        }

        return switch (columnIndex) {
            case 0 -> shardList.getShards().get(rowIndex).getId();
            case 1 -> shardList.getShards().get(rowIndex).getKey();
            case 2 -> shardList.getShards().get(rowIndex).getCreated().format(formatter);
            case 3 -> shardList.getShards().get(rowIndex).isMarked();
            case 4 -> shardList.getShards().get(rowIndex).isColdStorage();
            default -> throw new IllegalArgumentException("Column " + columnIndex + " not found");
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(!model.hasVault()) {
            throw new IllegalArgumentException("No open vault");
        }

        ShardList shardList = model.getVault().getShardList();

        if(rowIndex >= shardList.getShards().size()) {
            throw new IllegalArgumentException("Row index out of bounds");
        }

        if(columnIndex == 3 || columnIndex == 1 || columnIndex == 4) {
            return true;
        }

        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(!model.hasVault()) {
            throw new IllegalArgumentException("No open vault");
        }

        ShardList shardList = model.getVault().getShardList();

        if(rowIndex >= shardList.getShards().size()) {
            throw new IllegalArgumentException("Row index out of bounds");
        }

        if(columnIndex == 3) {
            Boolean b =  (Boolean) aValue;
            appController.markShard(shardList.getShards().get(rowIndex), b);
        } else if(columnIndex == 1) {
            String s = (String) aValue;
            appController.rekeyShard(shardList.getShards().get(rowIndex), s);
        } else if(columnIndex == 4) {
            Boolean b = (Boolean) aValue;
            appController.freezeShard(shardList.getShards().get(rowIndex), b);
        }
        else {
            throw new IllegalArgumentException("Column " + columnIndex + " not editable");
        };
    }

    @Override
    public void vaultOpened(NeurosyncDocument neurosyncDocument, Vault vault) {
        vault.addVaultObserver(this);
        vault.getShardList().addShardListObserver(this);
        fireTableDataChanged();
    }

    @Override
    public void vaultClosed(NeurosyncDocument neurosyncDocument, Vault vault) {
        vault.removeVaultObserver(this);
        vault.getShardList().removeShardListObserver(this);
        fireTableDataChanged();
    }

    @Override
    public void vaultSaved(NeurosyncDocument neurosyncDocument, Vault vault) {

    }

    @Override
    public void shardListChanged(ShardList shardList) {
        log.info("Changed property received");
        fireTableDataChanged();
    }
}
