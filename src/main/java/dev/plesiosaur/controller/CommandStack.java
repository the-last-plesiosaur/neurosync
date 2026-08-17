package dev.plesiosaur.controller;

import java.util.ArrayList;

public class CommandStack {

    private final ArrayList<Command> undoCommands;
    private final ArrayList<Command> redoCommands;
    private final ArrayList<CommandStackObserver> observers;

    public CommandStack() {
        undoCommands = new ArrayList<>();
        redoCommands = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public void addObserver(CommandStackObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(CommandStackObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (CommandStackObserver observer : observers) {
            observer.commandStackChanged(this);
        }
    }

    public boolean hasUndoCommands() {
        return !undoCommands.isEmpty();
    }

    public boolean hasRedoCommands() {
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
        if(!hasUndoCommands()) throw new IllegalStateException("No commands are available to undo");
        Command c = undoCommands.removeLast();
        c.undo();
        redoCommands.add(c);
        notifyObservers();
    }

    public void redo() {
        if(!hasRedoCommands()) throw new IllegalStateException("No commands are available to redo");
        Command c = redoCommands.removeLast();
        c.redo();
        undoCommands.add(c);
        notifyObservers();
    }
}
