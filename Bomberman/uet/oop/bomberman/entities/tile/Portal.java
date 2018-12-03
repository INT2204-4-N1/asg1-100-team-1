package uet.oop.bomberman.entities.tile;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.PlayMusic;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;

public class Portal extends Tile {

    protected Board _board;

    public Portal(int x, int y, Sprite sprite, Board board) {
        super(x, y, sprite);
        _board = board;
    }

    @Override
    public boolean collide(Entity e) {
        // TODO: xử lý khi Bomber đi vào
        if (e instanceof Bomber) {
            if (_board.detectNoEnemies() == false) return false;

            if (e.getXTile() == getX() && e.getYTile() == getY()) {
                if (_board.detectNoEnemies())
                    _board.nextLevel();
                String path = new File("").getAbsolutePath() + "\\res\\sound\\portal.wav";
                PlayMusic soundBase = new PlayMusic(path);
                soundBase.play(0);
            }
            return true;
        }
        return false;
    }

}
