package com.example.proyectoandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void onClicJuego(View v){
        Intent intent = new Intent(this, MainJuegos.class);
        startActivity(intent);
        Sonidos.reproducir(this,"clic");
    }
    public void onClicDuelo(View v){
        Intent intent = new Intent(this, MainDuelo.class);
        startActivity(intent);
        Sonidos.reproducir(this,"clic");
    }
    public void onClicExamen(View v){
        Intent intent = new Intent(this, MainExamen.class);
        startActivity(intent);
        Sonidos.reproducir(this,"clic");
    }
    public void onClicMainPrueba(View v){
        Intent intent = new Intent(this, MainPrueba.class);
        startActivity(intent);
        Sonidos.reproducir(this,"clic");
    }
    public void onClicMainCalculadora(View v){
        Intent intent = new Intent(this, Calculadora.class);
        startActivity(intent);
        Sonidos.reproducir(this,"clic");
    }

    public void Salir(View v){
        finish();
    }
    public void onClicAyuda(View v){
        Intent intent = new Intent(this, Ayuda.class);
        startActivity(intent);
        Sonidos.reproducir(this,"clic");
    }


}