package com.example.test_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class MemoryHistory extends AppCompatActivity {


    Button buttonBackToCalculator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_history);

        List<TextView> memoryOperations = new ArrayList<TextView>();

        Bundle historicoResultados = getIntent().getExtras();
        //float test = historicoResultados.getFloat("historicoResultados");


        for(int i = 0; i< historicoResultados.size(); i++){
            //intent.putExtra("historicoResultados-"+i, historicoResultados.get(i));
            float test = historicoResultados.getFloat("historicoResultados"+String.valueOf(i));
            Log.println(Log.INFO, "GetFloatArray", String.valueOf(test));
        }

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