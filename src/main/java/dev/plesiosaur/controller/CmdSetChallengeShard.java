package dev.plesiosaur.controller;

import dev.plesiosaur.model.Shard;

public class CmdSetChallengeShard implements Command {

    public final Shard shard;
    public final String challenge;
    public final boolean resetDecayWindow;

    public String oldChallenge;
    public boolean oldDecayWindow;

    public CmdSetChallengeShard(Shard shard, String challenge, boolean resetDecayWindow) {
        this.shard = shard;
        this.challenge = challenge;
        this.resetDecayWindow = resetDecayWindow;
    }

    @Override
    public void execute() {
        oldChallenge = shard.getChallengeText();
        shard.setChallengeText(challenge);
    }

    @Override
    public void undo() {
        shard.setChallengeText(oldChallenge);
    }

    @Override
    public void redo() {
        shard.setChallengeText(challenge);
    }
}
