package com.example.test_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MemoryHistory extends AppCompatActivity {


    Button buttonBackToCalculator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_history);

        buttonBackToCalculator = (Button)findViewById(R.id.btnReturnToCalculator);

        buttonBackToCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReturnToCalculator();
            }
        });



    }
    public void ReturnToCalculator(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}