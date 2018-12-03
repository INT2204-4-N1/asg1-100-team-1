package uet.oop.bomberman;

import uet.oop.bomberman.gui.Frame;
import java.io.File;

public class BombermanGame {

    public static void main(String[] args) {

        String path = new File("").getAbsolutePath() + "\\res\\sound\\backgr.wav";
        PlayMusic soundBase = new PlayMusic(path);
        soundBase.play(-1);
        new Frame();
    }
}
