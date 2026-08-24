package dev.plesiosaur.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CommandHistoryTest {

    @Test
    void undoCommandsAreEmptyWithNewHistory() {
        CommandHistory commandHistory = new CommandHistory();
        assertFalse(commandHistory.canUndo());
    }

    @Test
    void redoCommandsAreEmptyWithNewHistory() {
        CommandHistory commandHistory = new CommandHistory();
        assertFalse(commandHistory.canRedo());
    }

    @Test
    void commandExecuteCalled() {
        CommandHistory commandHistory = new CommandHistory();

        Command mock = mock(Command.class);

        commandHistory.execute(mock);
        verify(mock).execute();
    }

    @Test
    void undoCommandAvailableAfterExecute() {
        CommandHistory commandHistory = new CommandHistory();

        Command mock = mock(Command.class);

        commandHistory.execute(mock);
        assertTrue(commandHistory.canUndo());
    }

    @Test
    void commandUndoCalled() {
        CommandHistory commandHistory = new CommandHistory();

        Command mock = mock(Command.class);

        commandHistory.execute(mock);
        commandHistory.undo();

        verify(mock).undo();
    }

    @Test
    void redoCommandAvailableAfterUndo() {
        CommandHistory commandHistory = new CommandHistory();

        Command mock = mock(Command.class);

        commandHistory.execute(mock);
        commandHistory.undo();
        assertTrue(commandHistory.canRedo());
        assertFalse(commandHistory.canUndo());
    }

    @Test
    void commandRedoCalled() {
        CommandHistory commandHistory = new CommandHistory();
        Command mock = mock(Command.class);

        commandHistory.execute(mock);
        commandHistory.undo();
        commandHistory.redo();

        verify(mock).redo();
    }

    @Test
    void isNotDirtyWhenEmpty() {
        CommandHistory commandHistory = new CommandHistory();

        assertFalse(commandHistory.isDirty());
    }

    @Test
    void isDirtyAfterCommand() {
        CommandHistory commandHistory = new CommandHistory();
        Command mock = mock(Command.class);
        commandHistory.execute(mock);
        assertTrue(commandHistory.isDirty());
    }

    @Test
    void isNotDirtyAfterUndo() {
        CommandHistory commandHistory = new CommandHistory();
        Command mock = mock(Command.class);
        commandHistory.execute(mock);
        commandHistory.undo();
        assertFalse(commandHistory.isDirty());
    }

    @Test
    void isDirtyAfterRedo() {
        CommandHistory commandHistory = new CommandHistory();
        Command mock = mock(Command.class);
        commandHistory.execute(mock);
        commandHistory.undo();
        commandHistory.redo();
        assertTrue(commandHistory.isDirty());
    }

    @Test
    void markSavedClearsDirty() {
        CommandHistory commandHistory = new CommandHistory();
        Command mock = mock(Command.class);
        commandHistory.execute(mock);
        commandHistory.markSaved();

        assertFalse(commandHistory.isDirty());
    }

    @Test
    void undoAfterMarkSavedIsDirty() {
        CommandHistory commandHistory = new CommandHistory();
        Command mock = mock(Command.class);
        commandHistory.execute(mock);
        commandHistory.markSaved();
        commandHistory.undo();

        assertTrue(commandHistory.isDirty());
    }

}
