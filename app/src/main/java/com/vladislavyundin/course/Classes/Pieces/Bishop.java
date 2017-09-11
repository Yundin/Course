package com.vladislavyundin.course.Classes.Pieces;

import com.vladislavyundin.course.Classes.Square;

public class Bishop extends Piece{

    public Bishop(int x, int y, int player){
        super(x,y, player);
    }

    public boolean isPossible(int x, int y, Square[][] field){

        if(field[x][y].getPiece() == null || field[x][y].getPiece().getPlayer() != super.getPlayer()) {
            if(Math.abs(x - super.getX()) == Math.abs(y - super.getY()))
                return true;
        }
        return false;


    }
}
