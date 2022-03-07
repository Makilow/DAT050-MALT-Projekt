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
        sounds[0] = "src/sounds/bmusic1.wav";
        sounds[1] = "src/sounds/bmusic2.wav";
        sounds[2] = "src/sounds/chips.wav";
        sounds[3] = "src/sounds/dealcard.wav";
        sounds[4] = "src/sounds/shuffle.wav";
        sounds[5] = "src/sounds/win.wav";
        sounds[6] = "src/sounds/win1.wav";
    }

    public void setFile(int i){
        file = new File(sounds[i]);
        try{
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
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
