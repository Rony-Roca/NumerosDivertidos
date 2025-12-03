package com.example.proyectoandroid;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class JuegoMultiplicacion extends AppCompatActivity {
    TextView tvCorrectos, tvIncorrectos, tvOper;
    Button bRes1, bRes2, bRes3, bRes4;
    int contarCorrectos, contarFallidos, resultado, res1, res2, res3, res4, contador;
    final int MAXCONTAR = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_juego_multiplicacion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvCorrectos = findViewById(R.id.etMCorrectos);
        tvIncorrectos = findViewById(R.id.etMFallidos);
        tvOper = findViewById(R.id.etMOper);
        bRes1 = findViewById(R.id.btnMRes1);
        bRes2 = findViewById(R.id.btnMRes2);
        bRes3 = findViewById(R.id.btnMRes3);
        bRes4 = findViewById(R.id.btnMRes4);
        iniciarJuego();
    }
    public void  iniciarJuego(){
        Sonidos.reproducir(this, "clic");
        contarCorrectos = 0;
        contarFallidos = 0;
        resultado = 0;
        res1 = 0;
        res2 = 0;
        res3 = 0;
        res4 = 0;
        contador = 0;
        tvCorrectos.setText( String.valueOf(contarCorrectos));
        tvIncorrectos.setText( String.valueOf(contarFallidos));
        jugar();
    }

    public void jugar(){
        if (contador == MAXCONTAR) {
            alerta("¿Deseas continuar jugando?");
            return;
        }
        int a, b, pos, r1, r2, r3, r4;
        Random rd = new Random();
        a = rd.nextInt(10)+1;
        b = rd.nextInt(10)+1;
        resultado = a * b;
        String oper = Integer.toString(a) + " x " + Integer.toString(b) + " = ?";
        tvOper.setText(oper);
        pos = rd.nextInt(3)+1;
        String suma = Integer.toString(resultado);
        r1 = rd.nextInt(19)+1;
        r2 = rd.nextInt(19)+1;
        r3 = rd.nextInt(19)+1;
        r4 = rd.nextInt(19)+1;
        switch (pos){
            case 1:{
                bRes1.setText(suma);
                bRes2.setText(String.valueOf(r2));
                bRes3.setText(String.valueOf(r3));
                bRes4.setText(String.valueOf(r4));
                break;
            }
            case 2:{
                bRes2.setText(suma);
                bRes1.setText(String.valueOf(r2));
                bRes3.setText(String.valueOf(r3));
                bRes4.setText(String.valueOf(r4));
                break;
            }
            case 3:{
                bRes3.setText(suma);
                bRes2.setText(String.valueOf(r2));
                bRes1.setText(String.valueOf(r3));
                bRes4.setText(String.valueOf(r4));
                break;
            }
            case 4:{
                bRes4.setText(suma);
                bRes2.setText(String.valueOf(r2));
                bRes3.setText(String.valueOf(r3));
                bRes1.setText(String.valueOf(r4));
                break;
            }
        }
    }
    public  void clickR1 (View view){
        int valor;
        Button btnAux = (Button) findViewById(view.getId()) ;
        valor = Integer.valueOf(btnAux.getText().toString()) ;
        if (valor == resultado) {
            contarCorrectos++;
            tvCorrectos.setText( String.valueOf(contarCorrectos));
            Sonidos.reproducir(this, "okis");
        }
        else {
            contarFallidos++;
            tvIncorrectos.setText( String.valueOf(contarFallidos));
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