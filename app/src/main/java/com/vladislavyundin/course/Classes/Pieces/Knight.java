package com.vladislavyundin.course.Classes.Pieces;

import com.vladislavyundin.course.Classes.Square;

public class Knight extends Piece{

    public Knight(int x, int y, int player){
        super(x,y, player);
    }

    public boolean isPossible(int x, int y, Square[][] field){

        if(field[x][y].getPiece() == null || field[x][y].getPiece().getPlayer() != super.getPlayer()) {
            if((Math.abs(x - super.getX()) == 2 && Math.abs(y - super.getY()) == 1) || (Math.abs(x - super.getX()) == 1 && Math.abs(y - super.getY()) == 2))
                return true;
        }
        return false;
    }
}
