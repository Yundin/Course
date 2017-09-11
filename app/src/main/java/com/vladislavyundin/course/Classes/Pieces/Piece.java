package com.vladislavyundin.course.Classes.Pieces;

import com.vladislavyundin.course.Classes.Square;

public abstract class Piece {

    private int x, y;
    private int player;
    private boolean alive;

    protected Piece(int x, int y, int player){
        this.x = x;
        this.y = y;
        this.player = player;
        alive = true;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getPlayer() {
        return player;
    }

    public boolean isAlive(){
        return alive;
    }

    public void die(){
        alive = false;
    }

    public abstract boolean isPossible(int x, int y, Square[][] field);

}
