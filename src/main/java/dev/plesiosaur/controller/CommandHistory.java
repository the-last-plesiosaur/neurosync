package dev.plesiosaur.controller;

import java.util.ArrayList;
import java.util.List;

public class CommandHistory {

    private final List<Command> commands;
    private final ArrayList<CommandHistoryObserver> observers;

    private int position = 0;
    private int savedPosition = 0;


    public CommandHistory() {
        commands = new ArrayList<>();
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
        return position != 0;
    }

    public boolean canRedo() {
        return position < commands.size();
    }

    public void execute(Command command) {

        if(position < commands.size()) {
            // We've undone commands, executing something new creates a new history branch
            // Important: if the saved state is in the discarded redo history, it is no longer
            // reachable
            if (savedPosition > position) {
                savedPosition = -1;
            }

        }

        command.execute();
        commands.add(command);
        position++;

        notifyObservers();
    }

    public void undo() {
        if(!canUndo()) throw new IllegalStateException("No commands are available to undo");
        commands.get(position - 1).undo();
        position--;
        notifyObservers();
    }

    public void redo() {
        if(!canRedo()) throw new IllegalStateException("No commands are available to redo");
        commands.get(position).redo();
        position++;
        notifyObservers();
    }

    public boolean markSaved() {
        return savedPosition == position;
    }

    public boolean isDirty() {
        return savedPosition != position;
    }

}
