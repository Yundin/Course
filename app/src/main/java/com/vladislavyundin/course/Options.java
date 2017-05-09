package com.vladislavyundin.course;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by vladislavyundin on 02.05.17.
 */
public class Options extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        Button ok = (Button) findViewById(R.id.button);

        ok.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText name1 = (EditText) findViewById(R.id.editText);
                EditText name2 = (EditText) findViewById(R.id.editText2);
                String n1 = name1.getText().toString();
                String n2 = name2.getText().toString();
                Intent intent = new Intent(Options.this, MainActivity.class);
                intent.putExtra("name1", n1);
                intent.putExtra("name2", n2);
                intent.putExtra("mode", 0);
                startActivity(intent);
            }
        });
    }
}