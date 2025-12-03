package com.example.proyectoandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainDuelo extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_duelo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Toast.makeText(this, "Ingrese nombres por favor", Toast.LENGTH_SHORT).show();

    }

    public void onClicDueloSuma(View v){
        EditText etNombreA = findViewById(R.id.etJugadorA);
        EditText etNombreB = findViewById(R.id.etJugadorB);

        if (etNombreA.getText().toString().trim().isEmpty()) {
            etNombreA.setText("Perrito");
        }
        if (etNombreB.getText().toString().trim().isEmpty()) {
            etNombreB.setText("Gatito");
        }
        String nombreA = etNombreA.getText().toString().trim();
        String nombreB = etNombreB.getText().toString().trim();

        Intent intent = new Intent(this, DueloSuma.class);
        intent.putExtra("EXTRA_NOMBRE_A", nombreA);
        intent.putExtra("EXTRA_NOMBRE_B", nombreB);
        startActivity(intent);
        Sonidos.reproducir(this,"clic");
    }
    public void onClicDueloResta(View v){
        EditText etNombreA = findViewById(R.id.etJugadorA);
        EditText etNombreB = findViewById(R.id.etJugadorB);
        if (etNombreA.getText().toString().trim().isEmpty()) {
            etNombreA.setText("Perrito");
        }
        if (etNombreB.getText().toString().trim().isEmpty()) {
            etNombreB.setText("Gatito");
        }
        String nombreA = etNombreA.getText().toString().trim();
        String nombreB = etNombreB.getText().toString().trim();

        Intent intent = new Intent(this, DueloResta.class);
        intent.putExtra("EXTRA_NOMBRE_A", nombreA);
        intent.putExtra("EXTRA_NOMBRE_B", nombreB);
        startActivity(intent);
        Sonidos.reproducir(this,"clic");
    }
    public void onClicDueloMultiplicacion(View v){
        EditText etNombreA = findViewById(R.id.etJugadorA);
        EditText etNombreB = findViewById(R.id.etJugadorB);
        if (etNombreA.getText().toString().trim().isEmpty()) {
            etNombreA.setText("Perrito");
        }
        if (etNombreB.getText().toString().trim().isEmpty()) {
            etNombreB.setText("Gatito");
        }
        String nombreA = etNombreA.getText().toString().trim();
        String nombreB = etNombreB.getText().toString().trim();

        Intent intent = new Intent(this, DueloMultiplicacion.class);
        intent.putExtra("EXTRA_NOMBRE_A", nombreA);
        intent.putExtra("EXTRA_NOMBRE_B", nombreB);
        startActivity(intent);
        Sonidos.reproducir(this,"clic");
    }
    public void onClicDueloDivision(View v){
        EditText etNombreA = findViewById(R.id.etJugadorA);
        EditText etNombreB = findViewById(R.id.etJugadorB);
        if (etNombreA.getText().toString().trim().isEmpty()) {
            etNombreA.setText("Perrito");
        }
        if (etNombreB.getText().toString().trim().isEmpty()) {
            etNombreB.setText("Gatito");
        }
        String nombreA = etNombreA.getText().toString().trim();
        String nombreB = etNombreB.getText().toString().trim();

        Intent intent = new Intent(this, DueloDivision.class);
        intent.putExtra("EXTRA_NOMBRE_A", nombreA);
        intent.putExtra("EXTRA_NOMBRE_B", nombreB);
        startActivity(intent);
        Sonidos.reproducir(this,"clic");
    }
    public void onClicAtras(View v){
        finish();
        Sonidos.reproducir(this,"atras");
    }
}