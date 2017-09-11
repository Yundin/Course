package com.vladislavyundin.course.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.vladislavyundin.course.R;

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

                Intent intent = new Intent(Menu.this, Options.class);
                startActivity(intent);

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
