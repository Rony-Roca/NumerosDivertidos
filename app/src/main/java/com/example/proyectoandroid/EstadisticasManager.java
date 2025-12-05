package com.example.proyectoandroid;

import android.content.Context;
import android.content.SharedPreferences;

public class EstadisticasManager {

    private static final String PREFS = "estadisticas";

    public static void sumarAcierto(Context context){
        SharedPreferences prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        int aciertos = prefs.getInt("aciertos", 0);
            prefs.edit().putInt("aciertos", aciertos + 1).apply();
    }

    public static void sumarFallo(Context context){
        SharedPreferences prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        int fallos = prefs.getInt("fallos", 0);
            prefs.edit().putInt("fallos", fallos + 1).apply();
    }

    public static int getAciertos(Context context){
        return context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).getInt("aciertos", 0);
    }

    public static int getFallos(Context context){
        return context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).getInt("fallos", 0);
    }

    public static void reiniciar(Context context){
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit().clear().apply();
    }


}
