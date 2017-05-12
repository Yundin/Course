package com.vladislavyundin.course;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

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
//        GifImageView gifImageView = (GifImageView) findViewById(R.id.bgif);
//        GifDrawable gifDrawable = (GifDrawable) gifImageView.getDrawable();
//        gifDrawable.setLoopCount(0);

        play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder ad = new AlertDialog.Builder(Menu.this);
                ad.setMessage("Выберите режим игры");
                ad.setPositiveButton("С игроком", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int i) {
                        //Ввод имен
                        Intent intent = new Intent(Menu.this, Options.class);
                        startActivity(intent);
                    }
                });
                ad.setNegativeButton("С компьютером", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        //Запуск с компом

                        final Intent intent = new Intent(Menu.this, MainActivity.class);

                        AlertDialog.Builder ad = new AlertDialog.Builder(Menu.this);
                        ad.setMessage("Выберите Сложность");
                        ad.setPositiveButton("Сложная", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int i) {

                                intent.putExtra("mode", 2);
                                startActivity(intent);
                            }
                        });
                        ad.setNegativeButton("Легкая", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                //Запуск с компом
                                intent.putExtra("mode", 1);
                                startActivity(intent);
                            }
                        });

                        ad.setCancelable(true);
                        AlertDialog alert = ad.create();
                        alert.show();
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
