package com.vladislavyundin.course.Classes;

import com.vladislavyundin.course.Classes.Pieces.Piece;

/**
 * Created by vladislavyundin on 01.03.17.
 */
public class Square {

    private Player player = null;
    private Piece piece = null;

    public void fill(Player player, Piece piece) {
        this.player = player;
        this.piece = piece;
    }

    public Player getPlayer() {
        return player;
    }

    public Piece getPiece(){
        return piece;
    }
}