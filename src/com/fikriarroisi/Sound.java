package com.fikriarroisi;

import jaco.mp3.player.MP3Player;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 * @author fikriarroisi
 */
public class Sound {

    private MP3Player playerChange;
    private MP3Player playerDone;
    private MP3Player playerAlmost;

    private boolean changeEx;
    private boolean doneEx;
    private boolean almostEx;

    public Sound() {
        File jar;
        try {
            jar = new File(AList.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile();

            File change = new File(jar + "/Sounds/alertchange.mp3");
            File done = new File(jar + "/Sounds/alertdone.mp3");
            File almost = new File(jar + "/Sounds/alertalmost.mp3");
            if (change.exists()) {
                playerChange = new MP3Player(change.getCanonicalFile());
                changeEx = true;
            }
            if (done.exists()) {
                playerDone = new MP3Player(done.getCanonicalFile());
                doneEx = true;
            }
            if (almost.exists()) {
                playerAlmost = new MP3Player(almost.getCanonicalFile());
                almostEx = true;
            }
        } catch (URISyntaxException ex) {
            System.out.println("Error occured.");
        } catch (IOException ex) {
            System.out.println("Error occured.");
        }
    }

    public void changePlay() {
        if (this.changeEx) {
            this.playerChange.play();
        }
    }

    public void changeStop() {
        if (this.changeEx) {
            this.playerChange.stop();
        }
    }

    public void donePlay() {
        if (this.doneEx) {
            this.playerDone.play();
        }
    }

    public void doneStop() {
        if (this.doneEx) {
            this.playerDone.stop();
        }
    }

    public void almostPlay() {
        if (this.almostEx) {
            this.playerAlmost.play();
        }
    }

    public void almostStop() {
        if (this.almostEx) {
            this.playerAlmost.stop();
        }
    }
}
