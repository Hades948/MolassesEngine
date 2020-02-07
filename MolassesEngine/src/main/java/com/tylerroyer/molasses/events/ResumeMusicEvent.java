package com.tylerroyer.molasses.events;

import com.tylerroyer.molasses.Game;

public class ResumeMusicEvent extends Event {
    @Override
    protected void performAction() {
        Game.getAudioHandler().resumeMusic();
    }
}
