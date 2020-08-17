package com.tylerroyer.enginetesting;

import com.tylerroyer.molasses.*;
import com.tylerroyer.molasses.events.*;

import java.awt.Color;
import java.awt.Font;

public class AudioTestScreen extends Screen {
    Button playCrashButton, playMulanButton, playSEaButton, playSEbButton, pauseMusicButton, resumeMusicButton, mainScreenButton;

    @Override
    public void onFocus() {
        // Load audio
        Game.getAudioHandler().loadMusic("Crash.wav");
        Game.getAudioHandler().loadMusic("Mulan.wav");
        Game.getAudioHandler().loadSoundEffect("SEa.wav");
        Game.getAudioHandler().loadSoundEffect("SEb.wav");

        // Create events
        Event playCrashEvent = new PlayMusicEvent("Crash.wav");
        Event playMulanEvent = new PlayMusicEvent("Mulan.wav");
        Event playSEaEvent = new PlaySoundEffectEvent("SEa.wav");
        Event playSEbEvent = new PlaySoundEffectEvent("SEb.wav");
        Event pauseMusicEvent = new PauseMusicEvent();
        Event resumeMusicEvent = new ResumeMusicEvent();

        // Create buttons
        Font font = Config.gameFont.deriveFont(12.0f);
        playCrashButton = new Button("Play Crash Music", font, new Color(128, 0, 0), Color.WHITE, 250, 50, 10, 10, playCrashEvent);
        playMulanButton = new Button("Play Mulan Music", font, new Color(128, 0, 0), Color.WHITE, 250, 50, 270, 10, playMulanEvent);
        playSEaButton = new Button("Play Sound Effect A", font, new Color(128, 0, 0), Color.WHITE, 250, 50, 10, 70, playSEaEvent);
        playSEbButton = new Button("Play Sound Effect B", font, new Color(128, 0, 0), Color.WHITE, 250, 50, 270, 70, playSEbEvent);
        pauseMusicButton = new Button("Pause Music", font, new Color(128, 0, 0), Color.WHITE, 250, 50, 10, 130, pauseMusicEvent);
        resumeMusicButton = new Button("Resume Music", font, new Color(128, 0, 0), Color.WHITE, 250, 50, 270, 130, resumeMusicEvent);
        mainScreenButton = new Button("Return to main screen", font, new Color(128, 0, 0), Color.WHITE, 150, 50, 800, 20, new ChangeScreenEvent(new MainScreen()));
    }

    @Override
    public void update() {
        playCrashButton.update();
        playMulanButton.update();
        playSEaButton.update();
        playSEbButton.update();
        pauseMusicButton.update();
        resumeMusicButton.update();
        mainScreenButton.update();
    }

    @Override
    public void render(GameGraphics g) {
        playCrashButton.render(g);
        playMulanButton.render(g);
        playSEaButton.render(g);
        playSEbButton.render(g);
        pauseMusicButton.render(g);
        resumeMusicButton.render(g);
        mainScreenButton.render(g);
    }
}
