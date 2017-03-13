package com.vladislavyundin.course;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import android.content.pm.ActivityInfo;

public class MainActivity extends Activity {

    private Game game;
    private Button[][] buttons = new Button[6][6];
    private TableLayout table;

    public MainActivity(){
        game = new Game();
        game.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
        table = (TableLayout) findViewById(R.id.main_l);
        buildField();
    }

    private void buildField(){
        for(int i = 0; i < 6;i++) {
            TableRow row = new TableRow(this);
            for (int j = 0; j < 6; j++){
                Button button = new Button(this);
                buttons[i][j] = button;
                button.setOnClickListener(new Listener(i,j));
                row.addView(button, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT)); // добавление кнопки в строку таблицы
                button.setWidth(0);
                button.setHeight(0);
            }
            table.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }
        buttons[5][0].setText("x");
    }

    public class Listener implements View.OnClickListener {
        private int x = 0;
        private int y = 0;

        public Listener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void onClick(View view) {
            Button button = (Button) view;
            if (game.isPossible(x, y)){
                buttons[game.getX()][game.getY()].setText("");
                makeTurn(x, y);
                button.setText("x");
            }
            Player winner = game.checkWinner();
            if (winner != null) {
                gameOver(winner);
            }
        }
    }

    private void makeTurn(int x, int y) {
        game.makeTurn(x, y);
    }

    private void gameOver(Player player){
        CharSequence text = "Player \"" + player.getName() + "\" won!";
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        game.reset();
        refresh();
    }

    private  void refresh(){
        for(int i = 0; i<6;i++) {
            for (int j = 0; j < 6; j++) {
                buttons[i][j].setText("");
            }
        }
        buttons[5][0].setText("x");
    }
}
