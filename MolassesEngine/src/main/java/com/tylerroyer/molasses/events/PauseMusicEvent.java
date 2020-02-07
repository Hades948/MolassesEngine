package com.tylerroyer.molasses.events;

import com.tylerroyer.molasses.Game;

public class PauseMusicEvent extends Event {
    @Override
    protected void performAction() {
        Game.getAudioHandler().pauseMusic();
    }
}
