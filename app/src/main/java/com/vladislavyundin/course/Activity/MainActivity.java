package com.vladislavyundin.course.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.vladislavyundin.course.Classes.Game;
import com.vladislavyundin.course.Classes.Pieces.*;
import com.vladislavyundin.course.Classes.Player;
import com.vladislavyundin.course.Classes.Square;
import com.vladislavyundin.course.R;

public class MainActivity extends Activity {

    private Game game;
    private ImageButton[][] buttons = new ImageButton[8][8];
    private TableLayout table;
    private TextView text;
    private String name1, name2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new Game();
        name1 = (String) getIntent().getSerializableExtra("name1");
        name2 = (String) getIntent().getSerializableExtra("name2");
        if (name1.isEmpty() || name1.equals(" ")) name1 = "Игрок 1";
        if (name2.isEmpty() || name2.equals(" ")) name2 = "Игрок 2";
        table = (TableLayout) findViewById(R.id.main_l);
        text = (TextView) findViewById(R.id.text);
        game.reset(name1, name2);
        buildField();
    }

    private void buildField() {
        TableRow.LayoutParams tr = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        tr.setMargins(0, 0, 0, -30);
        TableLayout.LayoutParams tl = new TableLayout.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT);
        tl.setMargins(0, 0, 0, -30);
        for (int i = 0; i < 8; i++) {
            TableRow row = new TableRow(this);
            row.setLayoutParams(tr);
            for (int j = 0; j < 8; j++) {
                ImageButton button = new ImageButton(this);
                buttons[i][j] = button;
                button.setOnClickListener(new Listener(i, j));
                row.addView(button, tr); // добавление кнопки в строку таблицы

                if ((i + j) % 2 == 0) {
                    button.setBackgroundResource(R.drawable.red);
                } else {
                    button.setBackgroundResource(R.drawable.black);
                }
            }
            table.addView(row, tl);
        }
        refresh();
    }

    public class Listener implements View.OnClickListener {
        private int x = 0;
        private int y = 0;

        private Listener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void onClick(View view) {
            if(game.isSelected()) {
                if (game.isSame(x, y)) {
                    game.unselect();
                    if ((x + y) % 2 == 0) {
                        buttons[x][y].setBackgroundResource(R.drawable.red);
                    } else {
                        buttons[x][y].setBackgroundResource(R.drawable.black);
                    }
                }
                else
                    if (game.isPossible(x, y)) {
                    makeTurn(x, y);
                }
            } else
                if(game.isSquareFilled(x, y)) {
                    if (game.isActivePlayer(x, y)) {
                        game.select(x, y);
                        buttons[x][y].setBackgroundResource(R.drawable.green);
                    }
                }
        }
    }

    private void makeTurn(int x, int y) {
        buttons[game.getX()][game.getY()].setImageResource(R.drawable.back);
        if ((game.getX() + game.getY()) % 2 == 0) {
            buttons[game.getX()][game.getY()].setBackgroundResource(R.drawable.red);
        } else {
            buttons[game.getX()][game.getY()].setBackgroundResource(R.drawable.black);
        }
        game.makeTurn(x, y);
        setPieceImg(x, y, game.getField());
        Player winner = game.checkWinner();
        if (winner != null) {
            gameOver(winner);
        }
        else {
            changeplayers();
        }
    }

    private void gameOver(Player player) {
        CharSequence text = player.getName() + " выиграл!";
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        String c = name1;
        name1 = name2;
        name2 = c;
        game.reset(name1, name2);
        refresh();
    }

    private void refresh() {
        Square[][] field = game.getField();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                setPieceImg(i, j, field);
            }
        }
        text.setText(name1 + " ходит");
    }

    private void setPieceImg(int i, int j, Square[][] field){
        if (field[i][j].getPiece() instanceof Pawn) {
            if (field[i][j].getPiece().getPlayer() == 0)
                buttons[i][j].setImageResource(R.drawable.pawn_white);
            else
                buttons[i][j].setImageResource(R.drawable.pawn_black);
        } else
        if (field[i][j].getPiece() instanceof Rook) {
            if (field[i][j].getPiece().getPlayer() == 0)
                buttons[i][j].setImageResource(R.drawable.rook_white);
            else
                buttons[i][j].setImageResource(R.drawable.rook_black);
        } else
        if (field[i][j].getPiece() instanceof Knight) {
            if (field[i][j].getPiece().getPlayer() == 0)
                buttons[i][j].setImageResource(R.drawable.knight_white);
            else
                buttons[i][j].setImageResource(R.drawable.knight_black);
        } else
        if (field[i][j].getPiece() instanceof Bishop) {
            if (field[i][j].getPiece().getPlayer() == 0)
                buttons[i][j].setImageResource(R.drawable.bishop_white);
            else
                buttons[i][j].setImageResource(R.drawable.bishop_black);
        } else
        if (field[i][j].getPiece() instanceof King) {
            if (field[i][j].getPiece().getPlayer() == 0)
                buttons[i][j].setImageResource(R.drawable.king_white);
            else
                buttons[i][j].setImageResource(R.drawable.king_black);
        } else
        if (field[i][j].getPiece() instanceof Queen) {
            if (field[i][j].getPiece().getPlayer() == 0)
                buttons[i][j].setImageResource(R.drawable.queen_white);
            else
                buttons[i][j].setImageResource(R.drawable.queen_black);
        } else
            buttons[i][j].setImageResource(R.drawable.back);
    }

    private void changeplayers() {
        if (game.getNumberActivePlayer() == 0)
                text.setText(name1 + " ходит");
        else
            text.setText(name2 + " ходит");
    }
}
