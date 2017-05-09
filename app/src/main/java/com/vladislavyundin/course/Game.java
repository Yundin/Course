package com.vladislavyundin.course;

import android.widget.Toast;

/**
 * Created by vladislavyundin on 01.03.15.
 */

public class Game {

    private Player[] players;

    private Square[][] field;

    private Current current;

    private Player activePlayer;

    private MainActivity main;

    public Game(){
        field = new Square[8][8];
        // заполнение поля
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                field[i][j] = new Square();
            }
        }
        players = new Player[2];
        activePlayer = null;
        current = new Current();
    }

    public void start(String n1, String n2) {
        reset(n1, n2);
    }

    private void resetPlayers(String n1, String n2) {
        players[0] = new Player(n1);
        players[1] = new Player(n2);
        setCurrentActivePlayer(players[0]);
    }

    public Square[][] getField() {
        return field;
    }

    public Player checkWinner() {
        Square[][] field = getField();
        Player winner = field[0][7].getPlayer();

        return winner;
    }

    private void setCurrentActivePlayer(Player player) {
        activePlayer = player;
    }

    public int getX(){
        return current.getX();
    }

    public int getY(){
        return current.getY();
    }

    public boolean isPossible(int x, int y){
        int xc = current.getX();
        int yc = current.getY();
        if (x == xc-1&&y == yc-1) return true;
        if (x == xc-1&&y == yc) return true;
        if (x == xc-1&&y == yc+1) return true;
        if (x == xc&&y == yc+1) return true;
        if (x == xc+1&&y == yc+1) return true;
        return false;
    }

    public void makeTurn(int x, int y) {
        field[x][y].fill(getCurrentActivePlayer());
        current.setX(x);
        current.setY(y);
        switchPlayers();
    }

    public Player getCurrentActivePlayer() {
        return activePlayer;
    }

    public int getNumberActivePlayer() {
        if(activePlayer == players[0])
            return 0;
        else
            return 1;
    }

    private void switchPlayers() {
        if(activePlayer == players[0]){
            activePlayer = players[1];
        }
        else{
            activePlayer = players[0];
        }
    }

    public void reset(String n1, String n2){
        resetField();
        resetPlayers(n1, n2);
    }

    private void resetField() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                field[i][j].fill(null);
            }
        }
        current.setX(7);
        current.setY(0);
    }
}
