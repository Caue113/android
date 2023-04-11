package com.example.test_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int operacaoAtual = 1; //1-soma 2-subtração 3-multiplicação 4-divisão
    float resultado = 0;
    List<Float> historicoResultados = new ArrayList<Float>();
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
        textResultado = (TextView) findViewById(R.id.textResultado);

        //Coisas de MR MC M+ e M-
        Button buttonMemoriaResult = findViewById(R.id.btnMemoriaResult);
        Button buttonMemoriaClear = (Button)findViewById(R.id.btnMemoriaClear);
        Button buttonMemoriaSoma = (Button)findViewById(R.id.btnMemoriaSum);
        Button buttonMemoriaSub = (Button)findViewById(R.id.btnMemoriaSubtract);
        Button buttonMemoriaSave = (Button)findViewById(R.id.btnMemoriaSave);

        Button buttonMemoriaOpenHistory =  (Button)findViewById(R.id.btnOpenMemoryHistory);

        buttonMemoriaSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNumberToMemory(resultado);
            }
        });
        buttonMemoriaResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textResultado.setText(String.valueOf(GetCurrentMemoryNumber()));
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
                SumToMemory(resultado);
            }
        });

        buttonMemoriaSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SumToMemory(-resultado);
            }
        });

        buttonMemoriaOpenHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenMemoryHistory();
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
        historicoResultados.add(number);
        Log.println(Log.INFO, "addToMemory()", "Sucess. Added: " + number);
    }

    //-------------------------------------
    // MR MC M+ M-
    public void ClearMemory(){
        historicoResultados.clear();
        memory = 0;
        Log.println(Log.INFO, "ClearMemory()", "Memory Cleaned: " + historicoResultados);
    }

    public void SumToMemory(float number){
        Log.println(Log.INFO, "SumToMemory()", "start");
        float sum = GetCurrentMemoryNumber() + number;
        historicoResultados.add(sum);   //Não sobreescrevemos o número, mas adicionamos na memória a operação
        memory += number;
        Log.println(Log.INFO, "SumToMemory()", "Sucess: " + sum);
    }

    public void OpenMemoryHistory(){
        Intent intent = new Intent(this, MemoryHistory.class);

        for(int i = 0; i< historicoResultados.size(); i++){
            intent.putExtra("historicoResultados-"+String.valueOf(i), historicoResultados.get(i));
        }
        startActivity(intent);
    }
    //-------------------------------------

    public float GetCurrentMemoryNumber(){
        if(!historicoResultados.isEmpty() && historicoResultados.get(historicoResultados.size() - 1) != null){
            float lastNumber = historicoResultados.get(historicoResultados.size() - 1);
            return lastNumber;
        }
        return -1;
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
            default:

        }
        textResultado.setText(String.valueOf(resultado));
    }

    public void WriteHistoricToFile(){
        //Lets create an easy interface to append data
        StringBuilder stringBuilder = new StringBuilder();

        historicoResultados.forEach(value -> {
            stringBuilder.append(value);
        });

        File file = new File("C:\\Users\\6110574\\Desktop\\HistoricoCalculos.txt");
        OutputStreamWriter out = new OutputStreamWriter();

    }

}