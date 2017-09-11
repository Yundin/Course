package com.vladislavyundin.course.Classes.Pieces;

import com.vladislavyundin.course.Classes.Square;

public class Pawn extends Piece{

    public Pawn(int x, int y, int player){
        super(x,y, player);
    }

    public boolean isPossible(int x, int y, Square[][] field){

        if(field[x][y].getPiece() == null || field[x][y].getPiece().getPlayer() != super.getPlayer()) {
            if (super.getPlayer() == 0) {
                if ((x == super.getX() - 1 && (y == super.getY() + 1 || y == super.getY() - 1)) && field[x][y].getPlayer() != null && field[x][y].getPiece().getPlayer() == 1)
                    return true;
                else if (super.getX() == 6 && field[x][y].getPiece() == null) {
                    if (y == super.getY() && (x == super.getX() - 1 || x == super.getX() - 2))
                        return true;
                } else if (field[x][y].getPiece() == null && y == super.getY() && x == super.getX() - 1)
                    return true;
            } else {
                if ((x == super.getX() + 1 && (y == super.getY() + 1 || y == super.getY() - 1)) && field[x][y].getPlayer() != null && field[x][y].getPiece().getPlayer() == 0)
                    return true;
                else if (super.getX() == 1 && field[x][y].getPiece() == null) {
                    if (y == super.getY() && (x == super.getX() + 1 || x == super.getX() + 2))
                        return true;
                } else if (field[x][y].getPiece() == null && y == super.getY() && x == super.getX() + 1)
                    return true;
            }
        }
        return false;
    }
}