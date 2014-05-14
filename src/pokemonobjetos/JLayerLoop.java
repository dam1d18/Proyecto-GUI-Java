/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonobjetos;
//Audio/Blink-182 - First Date.mp3

import javazoom.jl.player.advanced.*;

class JLayerLoop extends PlaybackListener implements Runnable {

    private String filePath;
    private boolean loop;
    private AdvancedPlayer player;
    private Thread playerThread;
    public static boolean reproducir = true;

    public JLayerLoop(String filePath, boolean loop) {
        this.filePath = filePath;
        this.loop = loop;
    }

    public void play() {/*
        try {
            String urlAsString = "file:///" + new java.io.File(".").getCanonicalPath() + "/Recursos/Audio/" + this.filePath;

            this.player = new AdvancedPlayer(
                    new java.net.URL(urlAsString).openStream(),
                    javazoom.jl.player.FactoryRegistry.systemRegistry().createAudioDevice()
            );

            this.player.setPlayBackListener(this);

            this.playerThread = new Thread(this, "AudioPlayerThread");

            this.playerThread.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
    }

    public void stop() {/*
        this.playerThread.stop();*/
    }

    // PlaybackListener members
    public void playbackStarted(PlaybackEvent playbackEvent) {
        System.out.println("playbackStarted()");
    }

    public void playbackFinished(PlaybackEvent playbackEvent) {
        if (loop) {
            this.play();
        }
    }

    // Runnable members
    public void run() {
        try {
            this.player.play();
        } catch (javazoom.jl.decoder.JavaLayerException ex) {
            ex.printStackTrace();
        }
    }

}
