package com.example.myapplication;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;



public class Game extends AppCompatActivity {

    char paraula[];
    String paraulacompleta,username;
    TextView TVtapada;
    char[] tusRespuestas;
    Button intentar,resolver;
    EditText lletra;
    int puntuacio=0, lletresintroducides=0;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstance){
    super.onCreate(savedInstance);
    setContentView(R.layout.game);
        paraulacompleta=getIntent().getStringExtra("paraula");
        username=getIntent().getStringExtra("username");
        GuardarParaula();
        paraulacompleta= String.valueOf(paraula);

        tusRespuestas = new char[paraula.length];

        for(int i = 0; i < tusRespuestas.length; i++){
            tusRespuestas[i] = '_';
        }

        lletra=findViewById(R.id.lletra);
        intentar = (Button) findViewById(R.id.intentar);

        intentar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String resp= String.valueOf(lletra.getText());
                for(int i = 0; i < paraula.length; i++){
                    if(Character.toUpperCase(paraula[i]) == Character.toUpperCase(resp.charAt(0))){
                        tusRespuestas[i] = paraula[i];
                    }
                }

                lletresintroducides++;

                mostrarparaula();

            }
        });

        TVtapada=findViewById(R.id.tapada);
        mostrarparaula();

        resolver = (Button) findViewById(R.id.resolver);

        resolver.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                puntuacio=((paraulacompleta.length() - lletresintroducides) / (paraulacompleta.length())) * 10;

                String resp= String.valueOf(lletra.getText());
                Intent intent= new Intent(Game.this, Result.class);

                 intent.putExtra("paraula",paraulacompleta);
                 intent.putExtra("resposta",resp);
                intent.putExtra("puntuacio",puntuacio);

                startActivity(intent);

            }
        });


    }

    private void GuardarParaula()
    {
        int y=0;
        paraula=new char[paraulacompleta.length()-4];
        for (int x=2; x<paraulacompleta.length()-2;x++){
            if(paraulacompleta.charAt(x)!='[' && paraulacompleta.charAt(x)!='"'){
                paraula[y]= paraulacompleta.charAt(x);
                y++;

            }
        }
    }

    private void mostrarparaula(){
        String resultat="";
        for(int i = 0; i < tusRespuestas.length; i++){
            resultat+=tusRespuestas[i];
            resultat+=" ";
        }
        TVtapada.setText(resultat);
    }

}
