package com.vladislavyundin.course;

import android.app.Activity;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class MainActivity extends Activity {

    private Game game;
    private ImageButton[][] buttons = new ImageButton[8][8];
    private TableLayout table;

    public MainActivity(){
        game = new Game();
        game.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        table = (TableLayout) findViewById(R.id.main_l);
        buildField();
    }

    private void buildField(){
        for(int i = 0; i < 8;i++) {
            TableRow row = new TableRow(this);
            for (int j = 0; j < 8; j++){
                ImageButton button = new ImageButton(this);
                buttons[i][j] = button;
                button.setOnClickListener(new Listener(i,j));
                row.addView(button, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT)); // добавление кнопки в строку таблицы
                if((i+j)%2 == 0){
                    button.setImageResource(R.drawable.red);
                    button.setBackgroundResource(R.drawable.back);
                }
                else{
                    button.setImageResource(R.drawable.black);
                    button.setBackgroundResource(R.drawable.back);
                }
            }
            table.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }
        buttons[7][0].setImageResource(R.drawable.blackx);
    }

    public class Listener implements View.OnClickListener {
        private int x = 0;
        private int y = 0;

        public Listener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void onClick(View view) {
            ImageButton button = (ImageButton) view;
            if (game.isPossible(x, y)){
                if((game.getX()+game.getY())%2 == 0) {
                    buttons[game.getX()][game.getY()].setImageResource(R.drawable.red);
                }
                else{
                    buttons[game.getX()][game.getY()].setImageResource(R.drawable.black);
                }
                makeTurn(x, y);
                if((x+y)%2 == 0) {
                    button.setImageResource(R.drawable.redx);
                }
                else{
                    button.setImageResource(R.drawable.blackx);
                }
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
        for(int i = 0; i<8;i++) {
            for (int j = 0; j < 8; j++) {
                if((i+j)%2 == 0) {
                    buttons[i][j].setImageResource(R.drawable.red);
                }
                else{
                    buttons[i][j].setImageResource(R.drawable.black);
                }
            }
        }
        buttons[7][0].setImageResource(R.drawable.blackx);
    }
}
