/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonobjetos;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Port;
import javax.sound.sampled.Port.Info;

/**
 *
 * @author Victor
 */
public class Ecualizador {

    public void setVolume(double volume) {
        volume = volume / 1000;
        Info sourcespeaker = Port.Info.SPEAKER;

        if (AudioSystem.isLineSupported(sourcespeaker)) {
            try {
                Port outline = (Port) AudioSystem.getLine(sourcespeaker);
                outline.open();
                FloatControl volumeControl = (FloatControl) outline.getControl(FloatControl.Type.VOLUME);
                System.out.println("       volume: " + volumeControl.getValue());
                float v = (float) volume;
                volumeControl.setValue(v);
                System.out.println("newest volume: " + volumeControl.getValue());
            } catch (LineUnavailableException ex) {
                System.err.println("source not supported");
                ex.printStackTrace();
            }
        }
        Info sourceheadphones = Port.Info.HEADPHONE;

        if (AudioSystem.isLineSupported(sourceheadphones)) {
            try {
                Port outline = (Port) AudioSystem.getLine(sourceheadphones);
                outline.open();
                FloatControl volumeControl = (FloatControl) outline.getControl(FloatControl.Type.VOLUME);
                System.out.println("       volume: " + volumeControl.getValue());
                System.out.println("CASCOS");
                float v = (float) volume;
                volumeControl.setValue(v);
                System.out.println("newest volume: " + volumeControl.getValue());
            } catch (LineUnavailableException ex) {
                System.err.println("source not supported");
                ex.printStackTrace();
            }
        }
    }
}
