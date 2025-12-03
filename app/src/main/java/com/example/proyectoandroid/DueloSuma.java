package com.example.proyectoandroid;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Random;

public class DueloSuma extends AppCompatActivity {
    TextView etDSOperA, etDSOperB, etDSCorrectosA, etDSCorrectosB, etDSFallidosA, etDSFallidosB;
    TextView tvJugadorA, tvJugadorB;

    Button[] botonesA = new Button[4];
    Button[] botonesB = new Button[4];

    int resultado, contador = 0;
    int correctosA = 0, correctosB = 0;
    int fallidosA = 0, fallidosB = 0;
    final int MAX_INTENTOS = 10;

    String nombreA, nombreB;
    private HashMap<Button, Integer> coloresOriginales = new HashMap<>();

    private boolean respuestaProcesada = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_duelo_suma);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvJugadorA = findViewById(R.id.tvJugadorA);
        tvJugadorB = findViewById(R.id.tvJugadorB);

        Intent intent = getIntent();
        nombreA = intent.getStringExtra("EXTRA_NOMBRE_A");
        nombreB = intent.getStringExtra("EXTRA_NOMBRE_B");

        tvJugadorA.setText(nombreA != null ? nombreA : "Jugador A");
        tvJugadorB.setText(nombreB != null ? nombreB : "Jugador B");

        etDSOperA = findViewById(R.id.etDSOperA);
        etDSOperB = findViewById(R.id.etDSOperB);
        etDSCorrectosA = findViewById(R.id.etDSCorrectosA);
        etDSCorrectosB = findViewById(R.id.etDSCorrectosB);
        etDSFallidosA = findViewById(R.id.etDSFallidosA);
        etDSFallidosB = findViewById(R.id.etDSFallidosB);

        botonesA[0] = findViewById(R.id.btnDSRes1A);
        botonesA[1] = findViewById(R.id.btnDSRes2A);
        botonesA[2] = findViewById(R.id.btnDSRes3A);
        botonesA[3] = findViewById(R.id.btnDSRes4A);

        botonesB[0] = findViewById(R.id.btnDSRes1B);
        botonesB[1] = findViewById(R.id.btnDSRes2B);
        botonesB[2] = findViewById(R.id.btnDSRes3B);
        botonesB[3] = findViewById(R.id.btnDSRes4B);
        guardarColoresIniciales();

        for (Button b : botonesA) {
            b.setOnClickListener(v -> verificarRespuesta((Button) v, true));
        }
        for (Button b : botonesB) {
            b.setOnClickListener(v -> verificarRespuesta((Button) v, false));
        }
        nuevaRonda();
    }

    private void guardarColoresIniciales() {
        coloresOriginales.put(botonesA[0], getResources().getColor(R.color.ama));
        coloresOriginales.put(botonesA[1], getResources().getColor(R.color.nara));
        coloresOriginales.put(botonesA[2], getResources().getColor(R.color.rosa));
        coloresOriginales.put(botonesA[3], getResources().getColor(R.color.azul));

        coloresOriginales.put(botonesB[0], getResources().getColor(R.color.ama));
        coloresOriginales.put(botonesB[1], getResources().getColor(R.color.nara));
        coloresOriginales.put(botonesB[2], getResources().getColor(R.color.rosa));
        coloresOriginales.put(botonesB[3], getResources().getColor(R.color.azul));
    }
    private void nuevaRonda() {

        if (contador == MAX_INTENTOS) {
            mostrarGanador();
            return;
        }
        respuestaProcesada = false;
        Random rd = new Random();
        int a = rd.nextInt(10) + 1;
        int b = rd.nextInt(10) + 1;
        resultado = a + b;

        String operacion = a + "  +  " + b + "  =  ?";
        etDSOperA.setText(operacion);
        etDSOperB.setText(operacion);

        int respuestaCorrecta = resultado;
        int posCorrecta = rd.nextInt(4);

        int[] respuestas = new int[4];
        for (int i = 0; i < 4; i++) {
            respuestas[i] = (i == posCorrecta) ? respuestaCorrecta : rd.nextInt(20) + 1;
            if (i != posCorrecta && respuestas[i] == respuestaCorrecta)
                respuestas[i] += 3;
        }
        for (int i = 0; i < 4; i++) {
            botonesA[i].setText(String.valueOf(respuestas[i]));
            botonesB[i].setText(String.valueOf(respuestas[i]));
        }
    }

    private void verificarRespuesta(Button boton, boolean esJugadorA) {
        if (respuestaProcesada) {
            return;
        }

        respuestaProcesada = true;
        int valor = Integer.parseInt(boton.getText().toString());
        if (valor == resultado) {
            cambiarColorTemporal(boton, Color.GREEN, 500);
            if (esJugadorA) {
                correctosA++;
                etDSCorrectosA.setText(String.valueOf(correctosA));
                Sonidos.reproducir(this, "okis");
            } else {
                correctosB++;
                etDSCorrectosB.setText(String.valueOf(correctosB));
                Sonidos.reproducir(this, "okis");
            }
        } else {
            cambiarColorTemporal(boton, Color.RED, 500);
            if (esJugadorA) {
                fallidosA++;
                etDSFallidosA.setText(String.valueOf(fallidosA));
                Sonidos.reproducir(this, "no");
            } else {
                fallidosB++;
                etDSFallidosB.setText(String.valueOf(fallidosB));
                Sonidos.reproducir(this, "no");
            }
        }

        new Handler().postDelayed(() -> {
            contador++;
            nuevaRonda();
        }, 500);

    }

    public void onClicAtras(View v){
        Sonidos.reproducir(this,"atras");
        finish();
    }

    private void cambiarColorTemporal(Button button, int colorTemporal, int duracionMs) {
        button.setBackgroundColor(colorTemporal);

        new Handler().postDelayed(() -> resetearColor(button), duracionMs);
    }

    private void resetearColor(Button button) {
        if (coloresOriginales.containsKey(button)) {
            button.setBackgroundColor(coloresOriginales.get(button));
        } else {
            button.setBackgroundColor(Color.BLUE);
        }
    }

    private void mostrarGanador() {
        String mensajeFinal;

        if (correctosA > correctosB) {
            if (correctosA == 10){
                mensajeFinal = "¡" + nombreB + " gana y humilla a "+ nombreA+"!" + " con " + correctosA + "puntos";
            }
            else {
                mensajeFinal = "¡" + nombreB + " gana a "+ nombreA+"!" +  " con " + correctosA + "puntos";
            }
        } else if (correctosB > correctosA) {
            if (correctosB == 10){
                mensajeFinal = "¡" + nombreA + " gana y humilla a "+ nombreB+"!" +  " con " + correctosB + "puntos";
            }
            else {
                mensajeFinal = "¡" + nombreA + " gana a "+ nombreB+"!"  + " con " + correctosB + "puntos";
            }
        } else {
            mensajeFinal = "¡Empate, ninguno gana ..! " + correctosB + " puntos " + correctosA + " puntos ";
        }

        Sonidos.reproducir(this, "victoria");
        new AlertDialog.Builder(this)
                .setTitle(mensajeFinal)
                .setMessage("¿Quieres jugar otra ronda?")
                .setCancelable(false)
                .setPositiveButton("Sí", (dialog, which) -> {
                    correctosA = correctosB = fallidosA = fallidosB = contador = 0;
                    etDSCorrectosA.setText("0");
                    etDSCorrectosB.setText("0");
                    etDSFallidosA.setText("0");
                    etDSFallidosB.setText("0");
                    nuevaRonda();
                    Sonidos.reproducir(this, "ronda");
                })
                .setNegativeButton("No", (dialog, which) -> {
                    Toast.makeText(this, "Gracias por jugar", Toast.LENGTH_SHORT).show();
                    finish();
                    Sonidos.reproducir(this, "atras");
                })
                .show();
    }
}