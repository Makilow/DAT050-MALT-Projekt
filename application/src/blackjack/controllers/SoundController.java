package blackjack.controllers;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.net.URL;

public class SoundController {

    Clip clip;
    URL[] soundURL = new URL [30];

    public SoundController() {
        soundURL[1] = getClass().getResource("/sounds/bmusic1.wav");
        soundURL[2] = getClass().getResource("/sounds/bmusic2.wav");
        soundURL[3] = getClass().getResource("/sounds/chips.wav");
        soundURL[4] = getClass().getResource("/sounds/dealcard.wav");
        soundURL[5] = getClass().getResource("/sounds/shuffle.wav");
        soundURL[6] = getClass().getResource("/sounds/win.wav");
        soundURL[7] = getClass().getResource("/sounds/win1.wav");
    }


    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}
