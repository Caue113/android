package com.example.test_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    int operacaoAtual = 1; //1-soma 2-subtração 3-multiplicação 4-divisão
    float resultado = 0;
    List<Float> memoria;
    float memory = 0; //mudar imediatamente

    //Inputs
    EditText input1;
    EditText input2;
    TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        input1 = (EditText) findViewById(R.id.inputValor1);
        input2 = (EditText) findViewById(R.id.inputValor2);

        //Resultado
        TextView textResultado = (TextView) findViewById(R.id.textResultado);

        //Coisas de MR MC M+ e M-
        Button buttonMemoriaResult = findViewById(R.id.btnMemoriaResult);
        Button buttonMemoriaClear = (Button)findViewById(R.id.btnMemoriaClear);
        Button buttonMemoriaSoma = (Button)findViewById(R.id.btnMemoriaSoma);
        Button buttonMemoriaSub = (Button)findViewById(R.id.btnMemoriaSub);
        buttonMemoriaResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textResultado.setText(String.valueOf(memory));
            }
        });

        buttonMemoriaClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearMemory();
            }
        });

        buttonMemoriaSoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddToMemory(resultado);
            }
        });

        buttonMemoriaSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddToMemory(-resultado);
            }
        });

        //Botões de operação
        ImageButton buttonSum = (ImageButton)findViewById(R.id.buttonSum);
        ImageButton buttonSubtraction = (ImageButton)findViewById(R.id.buttonSubtraction);
        ImageButton buttonMultiply = (ImageButton)findViewById(R.id.buttonMultiply);
        ImageButton buttonDivision = (ImageButton)findViewById(R.id.buttonDivision);


        buttonSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacaoAtual = 1;

            }
        });

        buttonSubtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacaoAtual = 2;
            }
        });

        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacaoAtual = 3;
            }
        });

        buttonDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacaoAtual = 4;
            }
        });

        Button buttonCalcular = (Button) findViewById(R.id.buttonCalcular);
        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calcular();
            }
        });
    }

    /*
    public void SetOperacaoAtual(String operacao){
        operacaoAtual = operacao;
    }
    */

    //consertar dps
    public void AddNumberToMemory(float number){
        memoria.add(number);
    }

    //-------------------------------------
    // MR MC M+ M-
    public void ClearMemory(){
        memory = 0;
    }

    public void AddToMemory(float number){
        memory += number;
    }
    //-------------------------------------

    public float GetCurrentMemoryNumber(){
        return memoria.get(memoria.lastIndexOf(memoria));
    }

    public void Calcular(){
        float num1;
        float num2;

        if(TextUtils.isEmpty(input1.getText().toString()) || TextUtils.isEmpty(input1.getText().toString())) {
            textResultado.setText("Erro, ambos os campos tem que ser preenchidos");
            return;
        }
        else
        {
            num1 = Float.parseFloat(input1.getText().toString());
            num2 = Float.parseFloat(input2.getText().toString());
        }


        switch (operacaoAtual){
            case 1:
                resultado = num1 + num2;
                break;

            case 2:
                resultado = num1 - num2;
                break;

            case 3:
                resultado = num1 * num2;
                break;

            case 4:
                if(num2 != 0)
                {
                    resultado = num1 / num2;
                }
                else
                {
                    return;
                }
                break;
        }

        //AddNumberToMemory(resultado);

        //textResultado.setText(String.valueOf(resultado)); PORQUE NAO FUNCIONA:?????????????????????????????????????????????????????????????????????????????????
        Toast.makeText(getApplicationContext(), String.valueOf(resultado), Toast.LENGTH_LONG).show(); //funciona :)
    }

}