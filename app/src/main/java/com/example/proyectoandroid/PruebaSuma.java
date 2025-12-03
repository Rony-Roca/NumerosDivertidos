package com.example.proyectoandroid;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class PruebaSuma extends AppCompatActivity {
    TextView tvCorrectos, tvIncorrectos, tvOper, bCierto, bFalso;
    int contarCorrectos, contarFallidos, resultado, contador;
    final int MAXCONTAR = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_prueba_suma);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvCorrectos = findViewById(R.id.etSDCorrectos);
        tvIncorrectos = findViewById(R.id.etSDFallidos);
        tvOper = findViewById(R.id.etSDOper);
        bCierto = findViewById(R.id.btnCierto);
        bFalso = findViewById(R.id.btnFalso);

        bCierto.setOnClickListener(this::verificar);
        bFalso.setOnClickListener(this::verificar);
        iniciarJuego();
    }

    public void iniciarJuego() {
        Sonidos.reproducir(this, "clic");
        contarCorrectos = 0;
        contarFallidos = 0;
        resultado = 0;
        contador = 0;
        tvCorrectos.setText(String.valueOf(contarCorrectos));
        tvIncorrectos.setText(String.valueOf(contarFallidos));
        jugar();
    }

    public void jugar() {
        if (contador == MAXCONTAR) {
            alerta("¿Deseas continuar jugando?");
            return;
        }
        int a, b, num;
        Random rd = new Random();
        boolean xyz = rd.nextBoolean();
        a = rd.nextInt(10) + 1;
        b = rd.nextInt(10) + 1;
        resultado = a + b;
        if (xyz){
            num = resultado;
        } else {
            num = rd.nextInt(20) + 1;
            if (num == resultado){
                num += 1;
            }
        }
        String oper = Integer.toString(a) + " + " + Integer.toString(b) + " = " + num;
        tvOper.setText(oper);

    }

    public void verificar(View v) {
        String textoOperacion = tvOper.getText().toString();
        int numeroMostrado = Integer.parseInt(textoOperacion.split(" = ")[1]);

        if ((v.getId() == R.id.btnCierto && numeroMostrado == resultado) ||
                (v.getId() == R.id.btnFalso && numeroMostrado != resultado)) {
            contarCorrectos++;
            tvCorrectos.setText(String.valueOf(contarCorrectos));
            Sonidos.reproducir(this, "okis");
        } else {
            contarFallidos++;
            tvIncorrectos.setText(String.valueOf(contarFallidos));
            Sonidos.reproducir(this, "no");
        }
        contador++;
        jugar();
    }

    public void onclickAtras(View view){
        Sonidos.reproducir(this, "atras");
        finish();
    }
    public void alerta (String msg){
        AlertDialog.Builder diag = new AlertDialog.Builder(this);
        diag.setTitle("JUEGO TERMINADO");
        diag.setMessage(msg);
        diag.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                iniciarJuego();
            }
        });
        diag.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        diag.setCancelable(false);
        AlertDialog alertDialog = diag.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }
}

