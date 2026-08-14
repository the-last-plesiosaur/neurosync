package dev.plesiosaur.gui;

import dev.plesiosaur.model.ShardList;

import javax.swing.table.AbstractTableModel;

public class ShardTableModel extends AbstractTableModel {

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

    private final ShardList shardList;

    public ShardTableModel(ShardList shardList) {
        this.shardList = shardList;
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
            case 2 -> shardList.getShards().get(rowIndex).getCreated().toString();
            case 3 -> shardList.getShards().get(rowIndex).isMarked();
            default -> throw new IllegalArgumentException("Column " + columnIndex + " not found");
        };

    }
}
