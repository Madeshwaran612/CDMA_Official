package com.madesh.scorer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button cJ=findViewById(R.id.chiefJudge);
        Button J1=findViewById(R.id.Judge1);
        Button J2=findViewById(R.id.Judge2);
        Button J3=findViewById(R.id.Judge3);
        Button J4=findViewById(R.id.Judge4);
        Button t=findViewById(R.id.timer);

    }

    public void intend(View v){
        switch (v.getId()){
            case R.id.chiefJudge:
                startActivity(new Intent(this,ChiefJudge.class));
                break;

            case R.id.Judge1:
                startActivity(new Intent(this,Judge1.class));
                break;

            case R.id.Judge2:
                startActivity(new Intent(this,Judge2.class));
                break;

            case R.id.Judge3:
                startActivity(new Intent(this,Judge3.class));
                break;
            case R.id.Judge4:
                startActivity(new Intent(this,Judge4.class));
                break;
            case R.id.timer:
                startActivity(new Intent(this,Timer.class));
                break;

        }
    }

}