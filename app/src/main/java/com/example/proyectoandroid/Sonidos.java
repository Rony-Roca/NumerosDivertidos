package com.example.proyectoandroid;

import android.content.Context;
import android.media.MediaPlayer;

public class Sonidos {
    private static MediaPlayer sonido;

    public static void reproducir(Context context, String nombre) {
        if (sonido != null) {
            sonido.release();
            sonido = null;
        }

        int resId;

        switch (nombre) {
            case "bien":
                resId = R.raw.bien;
                break;
            case "okis":
                resId = R.raw.okis;
                break;
            case "no":
                resId = R.raw.no_okis;
                break;
            case "clic":
                resId = R.raw.ganaste;
                break;
            case "atras":
                resId = R.raw.ok;
                break;
            case "datos":
                resId = R.raw.ganaste_a;
                break;
            case "ronda":
                resId = R.raw.interface_positive;
                break;
            case "victoria":
                resId = R.raw.level_complete;
                break;
            case "calc":
                resId = R.raw.otro;
                break;
            default:
                resId = R.raw.no_okis;
        }

        sonido = MediaPlayer.create(context, resId);
        sonido.setOnCompletionListener(mp -> {
            mp.release();
        });

        sonido.start();
    }
}


