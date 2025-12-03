package com.example.proyectoandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainJuegos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_juegos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void onClicJuegoSuma(View v){
        Intent intent = new Intent(this, JuegoSuma.class);
        startActivity(intent);
    }
    public void onClicJuegoResta(View v){
        Intent intent = new Intent(this, JuegoResta.class);
        startActivity(intent);
    }
    public void onClicJuegoDivision(View v){
        Intent intent = new Intent(this, JuegoDivision.class);
        startActivity(intent);
    }
    public void onClicJuegoMultiplicacion(View v){
        Intent intent = new Intent(this, JuegoMultiplicacion.class);
        startActivity(intent);
    }
    public void onClicAtras(View v){
        Sonidos.reproducir(this,"atras");
        finish();
    }
}