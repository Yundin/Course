package com.vladislavyundin.course.Classes.Pieces;

import com.vladislavyundin.course.Classes.Square;

public class Rook extends Piece{

    public Rook(int x, int y, int player){
        super(x,y, player);
    }

    public boolean isPossible(int x, int y, Square[][] field){

        if(field[x][y].getPiece() == null || field[x][y].getPiece().getPlayer() != super.getPlayer()) {
            if(y == super.getY() || x == super.getX())
                return true;
        }
        return false;
    }
}
