package dev.plesiosaur.gui;

import dev.plesiosaur.model.ShardList;

import javax.swing.table.AbstractTableModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.format.DateTimeFormatter;

public class ShardTableModel extends AbstractTableModel implements PropertyChangeListener {

    private static final Logger log = LoggerFactory.getLogger(ShardTableModel.class);

    private static final String[] columns = {
            "Id",
            "Key",
            "Created",
            "Marked"
    };

    private static final Class<?>[] columnClasses = {
            String.class,
            String.class,
            String.class,
            Boolean.class
    };

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm a");

    private final ShardList shardList;

    public ShardTableModel(ShardList shardList) {
        this.shardList = shardList;
        this.shardList.addPropertyChangeListener(this);
    }

    @Override
    public int getRowCount() {
        return shardList.getShards().size();
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(rowIndex >= shardList.getShards().size()) {
            throw new IllegalArgumentException("Row index out of bounds");
        }

        return switch (columnIndex) {
            case 0 -> shardList.getShards().get(rowIndex).getId();
            case 1 -> shardList.getShards().get(rowIndex).getKey();
            case 2 -> shardList.getShards().get(rowIndex).getCreated().format(formatter);
            case 3 -> shardList.getShards().get(rowIndex).isMarked();
            default -> throw new IllegalArgumentException("Column " + columnIndex + " not found");
        };

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
         log.info("Changed property received");
         fireTableDataChanged();
    }
}
