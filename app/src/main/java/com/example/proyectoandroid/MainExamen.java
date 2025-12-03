package com.example.proyectoandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainExamen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_examen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void onClicExamenSuma(View v){
        Intent intent = new Intent(this, ExamenSuma.class);
        startActivity(intent);
    }
    public void onClicExamenResta(View v){
        Intent intent = new Intent(this, ExamenResta.class);
        startActivity(intent);
    }
    public void onClicExamenDivision(View v){
        Intent intent = new Intent(this, ExamenDivision.class);
        startActivity(intent);
    }
    public void onClicExamenMultiplicacion(View v){
        Intent intent = new Intent(this, ExamenMultiplicacion.class);
        startActivity(intent);
    }
    public void onClicAtras(View v) {
        finish();
        Sonidos.reproducir(this,"atras");
    }
}