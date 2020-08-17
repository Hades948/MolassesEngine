package com.tylerroyer.molasses.events;

import com.tylerroyer.molasses.Game;

public class PlayMusicEvent extends Event {
    private String name;

    public PlayMusicEvent(String name) {
        this.name = name;
    }

    @Override
    protected void performAction() {
        Game.getAudioHandler().playMusic(name);
    }
}
