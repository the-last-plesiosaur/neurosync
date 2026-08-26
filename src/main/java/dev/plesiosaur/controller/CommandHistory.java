package dev.plesiosaur.controller;

import java.util.ArrayList;
import java.util.List;

public class CommandHistory {

    private record HistoryEntry(Command command, int beforeRevision, int afterRevision) {}

    private final List<HistoryEntry> history;
    private final ArrayList<CommandHistoryObserver> observers;

    // Index of the next command that would be redone.
    // Everything before this index is currently applied.
    private int position = 0;

    private int nextRevision = 1;
    private int currentRevision = 0;
    private int savedRevision = 0;

    public CommandHistory() {
        history = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public void clear() {
        history.clear();
        position = 0;
        nextRevision = 1;
        currentRevision = 0;
        savedRevision = 0;

        notifyObservers();
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
        return position > 0;
    }

    public boolean canRedo() {
        return position < history.size();
    }

    public void execute(Command command) {
        // If we've undone commands and now execute something new,
        // throw away the redo branch
        if(position < history.size()) {
            history.subList(position, history.size()).clear();
        }

        int beforeRevision = currentRevision;
        int afterRevision = nextRevision++;

        command.execute();

        HistoryEntry entry = new HistoryEntry(command, beforeRevision, afterRevision);

        history.add(entry);
        position++;

        currentRevision = afterRevision;

        notifyObservers();
    }

    public void undo() {
        if(!canUndo()) throw new IllegalStateException("No commands are available to undo");

        HistoryEntry entry = history.get(position - 1);
        entry.command.undo();
        position--;
        currentRevision = entry.beforeRevision;

        notifyObservers();
    }

    public void redo() {
        if(!canRedo()) throw new IllegalStateException("No commands are available to redo");

        HistoryEntry entry = history.get(position);
        entry.command.redo();
        position++;
        currentRevision = entry.afterRevision;

        notifyObservers();
    }

    public void markSaved() {
        savedRevision = currentRevision;
        notifyObservers();
    }

    public boolean isDirty() {
        return currentRevision != savedRevision;
    }

}
