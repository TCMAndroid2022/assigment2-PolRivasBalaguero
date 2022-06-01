package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Result extends AppCompatActivity {

    String resultat,resposta;
    TextView TVresultat, respostacorrecta;
    Button tornar;
    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.result);
        resultat=getIntent().getStringExtra("paraula");
        resposta=getIntent().getStringExtra("resposta");

        TVresultat= findViewById(R.id.resultat);
        respostacorrecta= findViewById(R.id.paraulares);
        respostacorrecta.setText(resultat);
        if (resposta.equals(resultat)) TVresultat.setText("CORRECTE! \uD83E\uDD20\u200B\u200B ");

        else TVresultat.setText("INCORRECTE! \uD83D\uDE25\u200B ");

        tornar= (Button) findViewById(R.id.tornar);
        tornar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent= new Intent(Result.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
    }
