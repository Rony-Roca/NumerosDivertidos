package com.example.proyectoandroid;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.reflect.Array;

public class Calculadora extends AppCompatActivity {
    EditText numero1;
    EditText numero2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculadora);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        numero1 = findViewById(R.id.numero1);
        numero2 = findViewById(R.id.numero2);
        Spinner spinner = findViewById(R.id.spinnerOperaciones);
        Button btnCalcular = findViewById(R.id.btnCalcular);

        String[] operaciones = {"Suma", "Resta", "Multiplicación", "División"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                operaciones
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        btnCalcular.setOnClickListener(view -> {
            Sonidos.reproducir(this, "calc");
            try {
                double num1 = Integer.parseInt(numero1.getText().toString());
                double num2 = Integer.parseInt(numero2.getText().toString());
                String operacion = spinner.getSelectedItem().toString();
                double resultadoFinal = 0;

                switch (operacion) {
                    case "Suma":
                        resultadoFinal = num1 + num2;
                        break;
                    case "Resta":
                        resultadoFinal = num1 - num2;
                        break;
                    case "Multiplicación":
                        resultadoFinal = num1 * num2;
                        break;
                    case "División":
                        if (num2 != 0) {
                            resultadoFinal = num1 / num2;
                        } else {
                            Toast.makeText(this, "No se puede dividir entre 0", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        break;
                }
                String resultadoTexto;
                if (resultadoFinal % 1 == 0) {
                    resultadoTexto = String.format("El resultado es: %.0f", resultadoFinal);
                } else {
                    resultadoTexto = String.format("El resultado es: %.2f", resultadoFinal);
                }
                alerta(resultadoTexto);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Por favor, ingresa números válidos", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void onclickAtras(View v){
        Sonidos.reproducir(this, "atras");
        finish();
    }

    public void alerta (String msg){
        AlertDialog.Builder diag = new AlertDialog.Builder(this);
        diag.setTitle("¡OPERACIÓN REALIZADA!");
        diag.setMessage(msg);
        diag.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                numero1.clearFocus();
                numero2.clearFocus();
                dialogInterface.dismiss();
            }
        });
        diag.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
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