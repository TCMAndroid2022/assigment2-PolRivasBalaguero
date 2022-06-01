package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Result extends AppCompatActivity {

    String resultat,resposta;
    TextView TVresultat, respostacorrecta,puntuacion,TVrespostauser;
    Button tornar;
    int puntacio;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.result);
        resultat=getIntent().getStringExtra("paraula");
        resposta=getIntent().getStringExtra("resposta");
        puntacio=getIntent().getIntExtra("puntuacio",0);

        puntuacion= findViewById(R.id.puntuacion);
        TVresultat= findViewById(R.id.resultat);
        respostacorrecta= findViewById(R.id.paraulares);
        TVrespostauser=findViewById(R.id.respostausuari);

        respostacorrecta.setText(resultat);
        if (resposta.equals(resultat)){
            TVresultat.setText("CORRECTE! \uD83E\uDD20\u200B\u200B ");
            TVrespostauser.setTextColor(Color.parseColor("#00FF00"));
        }

        else{
            TVresultat.setText("INCORRECTE! \uD83D\uDE25\u200B ");
            TVrespostauser.setTextColor(Color.parseColor("#FF0000"));

        }

        puntuacion.setText("Puntuacio: "+ puntacio);

        TVrespostauser.setText(resposta);

        tornar= (Button) findViewById(R.id.tornar);
        tornar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent= new Intent(Result.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
    }
