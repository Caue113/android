package com.example.test_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    enum Operacoes{
        Soma,
        Subtracao,
        Multiplicacao,
        Divisao
    }


    //Botoes operações
    ImageButton buttonSum = (ImageButton)findViewById(R.id.buttonSum);
    ImageButton buttonSubtraction = (ImageButton)findViewById(R.id.buttonSubtraction);
    ImageButton buttonMultiply = (ImageButton)findViewById(R.id.buttonMultiply);
    ImageButton buttonDivision = (ImageButton)findViewById(R.id.buttonDivision);

    //Botao calcular

    Button buttonCalcular = (Button) findViewById(R.id.buttonCalcular);

    //Inputs
    EditText input1 = (EditText)findViewById(R.id.inputValor1);
    EditText input2 = (EditText) findViewById(R.id.inputValor2);

    //Resultado
    TextView textResultado = (TextView) findViewById(R.id.textResultado);


   //Variaveis inicias
    Operacoes operacaoAtual = Operacoes.Soma;
    float resultado = 0;

    List<Float> memoria;


    public void SetOperacaoAtual(Operacoes operacao){
        operacaoAtual = operacao;
    }

    public void AddNumberToMemory(float number){
        memoria.add(number);
    }

    public float GetCurrentMemoryNumber(){
        return memoria.get(memoria.lastIndexOf(memoria));
    }


    public void Calcular(){
        float num1 = Float.parseFloat(String.valueOf(input1.getText()));
        float num2 = Float.parseFloat(String.valueOf(input2.getText()));

        switch (operacaoAtual){
            case Soma:
                resultado = num1 + num2;
                break;

            case Subtracao:
                resultado = num1 - num2;
                break;

            case Multiplicacao:
                resultado = num1 * num2;
                break;

            case Divisao:
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

        AddNumberToMemory(resultado);
    }



}