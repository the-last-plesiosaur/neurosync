package dev.plesiosaur.controller;

public interface Command {

    void execute();
    void undo();
    void redo();
    boolean isUndoable();

}
