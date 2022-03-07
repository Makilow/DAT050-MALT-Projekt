package blackjack.models;

import blackjack.models.MainModel;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sounds {
    private Clip clip = null;
    private File file = null;
    private String[] sounds = new String[30];

    public Sounds() {
        sounds[0] = "sounds/bmusic1.wav";
        sounds[1] = "sounds/bmusic2.wav";
        sounds[2] = "sounds/chips.wav";
        sounds[3] = "sounds/dealcard.wav";
        sounds[4] = "sounds/shuffle.wav";
        sounds[5] = "sounds/win.wav";
        sounds[6] = "sounds/win1.wav";
    }

    public void setFile(int i){
        try{
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResource(sounds[i]));
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        }catch (Exception e) {
            System.out.println("Sound file missing");
            e.printStackTrace();
        }
    }

    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {clip.close();}

}
