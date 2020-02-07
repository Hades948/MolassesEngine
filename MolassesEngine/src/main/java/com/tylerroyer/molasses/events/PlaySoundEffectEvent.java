package com.tylerroyer.molasses.events;

import com.tylerroyer.molasses.Game;

public class PlaySoundEffectEvent extends Event {
    private String name;

    public PlaySoundEffectEvent(String name) {
        this.name = name;
    }

    @Override
    protected void performAction() {
        Game.getAudioHandler().playSoundEffect(name);
    }
}
