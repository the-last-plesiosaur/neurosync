package dev.plesiosaur.controller;

import java.util.ArrayList;

public class CommandHistory {

    private final ArrayList<Command> undoCommands;
    private final ArrayList<Command> redoCommands;
    private final ArrayList<CommandHistoryObserver> observers;

    public CommandHistory() {
        undoCommands = new ArrayList<>();
        redoCommands = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public void addObserver(CommandHistoryObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(CommandHistoryObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (CommandHistoryObserver observer : observers) {
            observer.commandStackChanged(this);
        }
    }

    public boolean canUndo() {
        return !undoCommands.isEmpty();
    }

    public boolean canRedo() {
        return !redoCommands.isEmpty();
    }

    public void execute(Command command) {
        command.execute();
        if(command.isUndoable()) {
            undoCommands.add(command);
            notifyObservers();
        }
    }

    public void undo() {
        if(!canUndo()) throw new IllegalStateException("No commands are available to undo");
        Command c = undoCommands.removeLast();
        c.undo();
        redoCommands.add(c);
        notifyObservers();
    }

    public void redo() {
        if(!canRedo()) throw new IllegalStateException("No commands are available to redo");
        Command c = redoCommands.removeLast();
        c.redo();
        undoCommands.add(c);
        notifyObservers();
    }
}
