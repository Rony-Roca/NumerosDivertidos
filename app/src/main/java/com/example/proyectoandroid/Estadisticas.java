package com.example.proyectoandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Estadisticas extends AppCompatActivity {
    Button btnAciertos, btnFallos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_estadisticas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnAciertos = findViewById(R.id.aciertos);
        btnFallos = findViewById(R.id.fallos);

        try {
            int aciertos = EstadisticasManager.getAciertos(this);
            int fallos = EstadisticasManager.getFallos(this);
            btnAciertos.setText(String.valueOf(aciertos));
            btnFallos.setText(String.valueOf(fallos));
        } catch (Exception exception) {
            Toast.makeText(this, "No hay datos", Toast.LENGTH_SHORT).show();
        }

    }

    public void atras(View v){
        finish();
    }

}