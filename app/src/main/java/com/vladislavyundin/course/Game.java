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

    public Game(){
        field = new Square[6][6];
        // заполнение поля
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                field[i][j] = new Square();
            }
        }
        players = new Player[2];
        activePlayer = null;
        current = new Current();
    }

    public void start() {
        reset();
    }

    private void resetPlayers() {
        players[0] = new Player("1");
        players[1] = new Player("2");
        setCurrentActivePlayer(players[0]);
    }

    public Square[][] getField() {
        return field;
    }

    public Player checkWinner() {
        Square[][] field = getField();
        Player winner = field[0][5].getPlayer();

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

    private void switchPlayers() {
        activePlayer = (activePlayer == players[0]) ? players[1] : players[0];
    }

    public void reset(){
        resetField();
        resetPlayers();
    }

    private void resetField() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                field[i][j].fill(null);
            }
        }
        current.setX(5);
        current.setY(0);
    }
}
