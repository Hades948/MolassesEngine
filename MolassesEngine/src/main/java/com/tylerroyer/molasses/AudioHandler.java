package com.tylerroyer.molasses;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioHandler {
    private Clip currentMusic = null;
    private HashMap<String, Clip> music = new HashMap<>();
    private HashMap<String, Clip> soundEffects = new HashMap<>();

    public AudioHandler() {}

    public void loadMusic(String name) {
        try {
            Clip clip;
            InputStream is = AudioHandler.class.getResourceAsStream("/res/" + name);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(is);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            music.put(name, clip);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {e.printStackTrace();}
    }

    public void loadSoundEffect(String name) {
        try {
            Clip clip;
            InputStream is = AudioHandler.class.getResourceAsStream("/res/" + name);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(is);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            soundEffects.put(name, clip);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {e.printStackTrace();}
    }

    public void playMusic(String name) {
        Clip clip = music.get(name);
        if (clip == currentMusic) return;

        if (currentMusic != null) {
            currentMusic.stop();
            currentMusic.setFramePosition(0);
        }

        clip.loop(Clip.LOOP_CONTINUOUSLY);
        if (!clip.isActive()) {
            clip.setFramePosition(0);
            clip.start();
        }

        currentMusic = clip;
    }

    public void pauseMusic() {
        if (currentMusic == null) return;

        currentMusic.stop();
    }

    public void resumeMusic() {
        if (currentMusic == null) return;
        
        currentMusic.start();
    }

    public void playSoundEffect(String name) {
        Clip clip = soundEffects.get(name);
        if (!clip.isRunning()) {
            clip.setFramePosition(0);
            clip.start();
        }
    }
}