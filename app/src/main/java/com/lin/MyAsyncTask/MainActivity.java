package com.lin.MyAsyncTask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mbutton= (Button) findViewById(R.id.bt_download);
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ImageText.class);
                startActivity(intent);
            }
        });
        /*
        MyAsyncTask task=new MyAsyncTask();
        task.execute();//开启 相当与
        */
    }


    public void progress(View view){
        startActivity(new Intent(this,ProgressBartext.class));
    }


}
