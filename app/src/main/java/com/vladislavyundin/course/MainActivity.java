package com.vladislavyundin.course;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.*;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    private Game game;
    private ImageButton[][] buttons = new ImageButton[8][8];
    private TableLayout table;
    public TextView text;
    Random rnd = new Random(System.currentTimeMillis());
    int mode;
    String name1, name2;
    int turns[] = {2, 7, 0, 5, 2, 5, 2, 6, 1, 5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mode = (int) getIntent().getSerializableExtra("mode");
        if (mode == 0) {
            name1 = (String) getIntent().getSerializableExtra("name1");
            name2 = (String) getIntent().getSerializableExtra("name2");
        } else {
            name1 = "Игрок";
            name2 = "Компьютер";
        }
        if (name1 == "") name1 = "Игрок 1";
        if (name2 == "") name2 = "Игрок 2";
        table = (TableLayout) findViewById(R.id.main_l);
        text = (TextView) findViewById(R.id.text);
        game = new Game();
        game.start(name1, name2);
        buildField();
    }

    private void buildField() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
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
                    button.setImageResource(R.drawable.red);
                    button.setBackgroundResource(R.drawable.back);
                } else {
                    button.setImageResource(R.drawable.black);
                    button.setBackgroundResource(R.drawable.back);
                }
            }
            table.addView(row, tl);
        }
        buttons[7][0].setImageResource(R.drawable.blackx);
        if (mode == 0)
            text.setText(name1 + " ходит");
    }

    public class Listener implements View.OnClickListener {
        private int x = 0;
        private int y = 0;

        public Listener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void onClick(View view) {
            if (game.isPossible(x, y)) {
                makeTurn(x, y);
            }
        }
    }

    private void makeTurn(int x, int y) {
        if ((game.getX() + game.getY()) % 2 == 0) {
            buttons[game.getX()][game.getY()].setImageResource(R.drawable.red);
        } else {
            buttons[game.getX()][game.getY()].setImageResource(R.drawable.black);
        }
        game.makeTurn(x, y);
        if ((x + y) % 2 == 0) {
            buttons[x][y].setImageResource(R.drawable.redx);
        } else {
            buttons[x][y].setImageResource(R.drawable.blackx);
        }
        changeplayers(mode);
        Player winner = game.checkWinner();
        if (winner != null) {
            gameOver(winner);
        }
    }

    private void gameOver(Player player) {
        CharSequence text = player.getName() + " выиграл!";
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        game.reset(name1, name2);
        refresh();
    }

    private void refresh() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    buttons[i][j].setImageResource(R.drawable.red);
                } else {
                    buttons[i][j].setImageResource(R.drawable.black);
                }
            }
        }
        buttons[7][0].setImageResource(R.drawable.blackx);
        if (mode == 0)
            text.setText(name1 + " ходит");
    }

    private void RandomTurn() {
        boolean с = false;
        while (!с) {
            int num = rnd.nextInt(5);
            int x, y;
            switch (num) {
                case 0:
                    x = game.getX() - 1;
                    y = game.getY() - 1;
                    break;
                case 1:
                    x = game.getX() - 1;
                    y = game.getY();
                    break;
                case 2:
                    x = game.getX() - 1;
                    y = game.getY() + 1;
                    break;
                case 3:
                    x = game.getX();
                    y = game.getY() + 1;
                    break;
                default:
                    x = game.getX() + 1;
                    y = game.getY() + 1;
                    break;
            }
            if (game.isPossible(x, y)) {
                makeTurn(x, y);
                с = true;
            }
        }
    }

    private void IITurn(int mode) {
        if (mode == 1) RandomTurn();
        else {
            if (game.isPossible(0, 7)) makeTurn(0, 7);
            else
                for (int i = 0; i < 5; i++) {
                    if (game.isPossible(turns[i * 2], turns[i * 2 + 1])) {
                        makeTurn(turns[i * 2], turns[i * 2 + 1]);
                        break;
                    }
                }
            if (game.getNumberActivePlayer() == 1) RandomTurn();
        }
    }

    private void changeplayers(int mode) {
        if (mode == 0) {
            if (game.getNumberActivePlayer() == 0)
                text.setText(name1 + " ходит");
            else
                text.setText(name2 + " ходит");
        } else if (game.getNumberActivePlayer() == 1) {
            Timer mTimer = new Timer();
            MyTimerTask mMyTimerTask = new MyTimerTask();

            // секундная задержка
            mTimer.schedule(mMyTimerTask, 1000);
        }
    }

    class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    IITurn(mode);
                }
            });
        }
    }
}
