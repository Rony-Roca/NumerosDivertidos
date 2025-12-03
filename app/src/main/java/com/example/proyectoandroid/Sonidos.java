package com.example.proyectoandroid;

import android.content.Context;
import android.media.MediaPlayer;

public class Sonidos {
    public static void reproducir (Context context, String nombre){
        MediaPlayer sonido;
        switch (nombre){
            case "bien":{
                sonido = MediaPlayer.create(context, R.raw.bien);
                break;
            }
            case "okis":{
                sonido = MediaPlayer.create(context, R.raw.okis);
                break;
            }
            case "no":{
                sonido = MediaPlayer.create(context, R.raw.no_okis);
                break;
            }
            case "clic":{
                sonido = MediaPlayer.create(context, R.raw.ganaste);
                break;
            }
            case "atras":{
                sonido = MediaPlayer.create(context, R.raw.ok);
                break;
            }
            case "datos":{
                sonido = MediaPlayer.create(context, R.raw.ganaste_a);
                break;
            }
            case "ronda":{
                sonido = MediaPlayer.create(context, R.raw.interface_positive);
                break;
            }
            case "victoria":{
                sonido = MediaPlayer.create(context, R.raw.level_complete);
                break;
            }
            case "calc":{
                sonido = MediaPlayer.create(context, R.raw.otro);
                break;
            }
            default:
                sonido = MediaPlayer.create(context, R.raw.no_okis);
                break;

        }
        sonido.start();
    }
}
