package com.example.proyectoandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainPrueba extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_prueba);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void onClicPruebaSuma(View v){
        Intent intent = new Intent(this, PruebaSuma.class);
        startActivity(intent);
    }
    public void onClicPruebaResta(View v){
        Intent intent = new Intent(this, PruebaResta.class);
        startActivity(intent);
    }
    public void onClicPruebaDivision(View v){
        Intent intent = new Intent(this, PruebaDivision.class);
        startActivity(intent);
    }
    public void onClicPruebaMultiplicacion(View v){
        Intent intent = new Intent(this, PruebaMultiplicacion.class);
        startActivity(intent);
    }
    public void onClicAtras(View v){
        Sonidos.reproducir(this,"atras");
        finish();
    }
}