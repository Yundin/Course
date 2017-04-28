package com.vladislavyundin.course;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by vladislavyundin on 10.04.17.
 */
public class Menu extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button play = (Button) findViewById(R.id.play);
        Button rules = (Button) findViewById(R.id.rules);
        //pl.droidsonroids.gif.GifTextView gif = new (pl.droidsonroids.gif.GifTextView) findViewById(R.id.bgif);

        play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder ad = new AlertDialog.Builder(Menu.this);
                ad.setMessage("Выберите режим игры");
                ad.setPositiveButton("С игроком", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int i) {
                        //Ввод имен
                        Intent intent = new Intent(Menu.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                ad.setNegativeButton("С компьютером", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        //Запуск с компом
                        Intent intent = new Intent(Menu.this, Rules.class);
                        startActivity(intent);
                    }
                });
                ad.setCancelable(true);
                AlertDialog alert = ad.create();
                alert.show();
            }
        });

        rules.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Menu.this, Rules.class);
                startActivity(intent);
            }
        });
    }
}
