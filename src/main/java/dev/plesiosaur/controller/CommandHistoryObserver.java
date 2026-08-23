package dev.plesiosaur.controller;

public interface CommandHistoryObserver {
    void commandStackChanged(CommandHistory cs);
}
