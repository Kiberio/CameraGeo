package com.facens.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
    //Criação de uma variavel timer
    private final Timer timer = new Timer();
    TimerTask timerTask;
    //Criação da tela activity_Splash quando abre o aplicativo
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //Quando passar o tempo do TimerTask mostrar a tela principal do aplicativo
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                      gotoMainActivity();

                    }
                });

                }
            };
        //Declaração do tempo do TimerTask
        timer.schedule(timerTask, 3000);
        }
        //função para ir para a tela principal do aplicativo
        private void gotoMainActivity(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        }
    }
