package com.vladislavyundin.course.Classes;

import com.vladislavyundin.course.Classes.Pieces.*;

/**
 * Created by vladislavyundin on 01.03.15.
 */

public class Game {

    private Player[] players;
    private Square[][] field;
    private Player activePlayer;
    private King[] kings;
    private Queen[] queens;
    private Bishop[] bishops;
    private Knight[] knights;
    private Rook[] rooks;
    private Pawn[] pawns;
    private Piece selected;


    public Game(){
        field = new Square[8][8];
        // создание поля
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                field[i][j] = new Square();
            }
        }
        players = new Player[4];
        activePlayer = null;
        pawns = new Pawn[16];
        kings = new King[2];
        queens = new Queen[2];
        bishops = new Bishop[4];
        knights = new Knight[4];
        rooks = new Rook[4];
        selected = null;
    }

    public Square[][] getField() {
        return field;
    }

    public void select(int x, int y){
        selected = field[x][y].getPiece();
    }

    public void unselect(){
        selected = null;
    }

    public boolean isSelected(){
        if (selected != null)
            return true;
        return false;
    }

    public boolean isSquareFilled(int x, int y){
        if (field[x][y].getPiece() != null)
            return true;
        return false;
    }

    public boolean isSame(int x, int y){
        if(field[x][y].getPiece() == selected)
            return true;
        return false;
    }

    public boolean isActivePlayer(int x, int y){
        if(getNumberActivePlayer() == field[x][y].getPiece().getPlayer())
            return true;
        return false;
    }

    private void resetPieces(){
        for (int i = 0; i < 8; i++) {
            pawns[i] = new Pawn(1, i, 1);
            pawns[i + 8] = new Pawn(6, i, 0);
        }
        bishops[0] = new Bishop(0, 2, 1);
        bishops[1] = new Bishop(0, 5, 1);
        bishops[2] = new Bishop(7, 2, 0);
        bishops[3] = new Bishop(7, 5, 0);
        knights[0] = new Knight(0, 1, 1);
        knights[1] = new Knight(0, 6, 1);
        knights[2] = new Knight(7, 1, 0);
        knights[3] = new Knight(7, 6, 0);
        rooks[0] = new Rook(0, 0, 1);
        rooks[1] = new Rook(0, 7, 1);
        rooks[2] = new Rook(7, 0, 0);
        rooks[3] = new Rook(7, 7, 0);
        queens[0] = new Queen(0, 3, 1);
        queens[1] = new Queen(7, 3, 0);
        kings[0] = new King(0, 4, 1);
        kings[1] = new King(7, 4, 0);
    }

    private void resetPlayers(String n1, String n2) {
        players[0] = new Player(n1);
        players[1] = new Player(n2);
        setCurrentActivePlayer(players[0]);
    }

    public Player checkWinner() {
        if(!kings[0].isAlive())
            return players[0];
        else if (!kings[1].isAlive())
            return players[1];
        return null;
    }

    private void setCurrentActivePlayer(Player player) {
        activePlayer = player;
    }

    public int getX(){
        return selected.getX();
    }

    public int getY(){
        return selected.getY();
    }


    public boolean isPossible(int x, int y){
        return selected.isPossible(x, y, field);
    }

    public void makeTurn(int x, int y) {
        if(field[x][y].getPiece() != null)
            field[x][y].getPiece().die();
        field[selected.getX()][selected.getY()].fill(null, null);
        field[x][y].fill(getCurrentActivePlayer(), selected);
        selected.setXY(x, y);
        unselect();
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
        resetPieces();
        resetField();
        resetPlayers(n1, n2);
    }

    private void resetField() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 1)
                    field[i][j].fill(players[1], pawns[j]);
                else if (i == 6)
                    field[i][j].fill(players[0], pawns[j + 8]);
                else if (i == 0 && (j == 2 || j == 5))
                    field[i][j].fill(players[1], bishops[j==2?0:1]);
                else if (i == 7 && (j == 2 || j == 5))
                    field[i][j].fill(players[0], bishops[j==2?2:3]);
                else if (i == 0 && (j == 1 || j == 6))
                    field[i][j].fill(players[1], knights[j==1?0:1]);
                else if (i == 7 && (j == 1 || j == 6))
                    field[i][j].fill(players[0], knights[j==1?2:3]);
                else if (i == 0 && (j == 0 || j == 7))
                    field[i][j].fill(players[1], rooks[j==0?0:1]);
                else if (i == 7 && (j == 0 || j == 7))
                    field[i][j].fill(players[0], rooks[j==0?2:3]);
                else if (i == 0 && j == 3)
                    field[i][j].fill(players[1], queens[0]);
                else if (i == 7 && j == 3)
                    field[i][j].fill(players[0], queens[1]);
                else if (i == 0 && j == 4)
                    field[i][j].fill(players[1], kings[0]);
                else if (i == 7 && j == 4)
                    field[i][j].fill(players[0], kings[1]);
                else
                    field[i][j].fill(null, null);
            }
        }
    }
}
