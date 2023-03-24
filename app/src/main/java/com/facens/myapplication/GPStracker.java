package com.facens.myapplication;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class GPStracker implements LocationListener {
    Context context;
    //Criação de um construtor para a classe GPStracker
    public GPStracker(Application c){
        context = c;
    }
    //Cria uma função para adiquirir a localização do usuario
    public Location getLocation(){
        //if para verificar se o aplicativo possui a permissão ao GPS do dispositivo
        if(ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED){
            //Caso não tenha permissão avisa que não tem a permissão
            Toast.makeText(context, "Não foi permitido", Toast.LENGTH_SHORT).show();
            return null;
        }


        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        //Caso tenha acesso ao Gps guarda a localização na variavel l e retorna o valor para imprimir no MainActivity
        if (isGPSEnabled){
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10, this);
            Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return l;
        } else {
            //Caso o usuario esteja tentando usar a função do GPS com o GPS desligado
            Toast.makeText(context, "Por favor, habilitar o GPS!", Toast.LENGTH_LONG).show();
        }
        //Reseta o valor
        return null;
    }
    //Reescreve a variavel onProviderDisabled caso mude de estado
    @Override
    public void onProviderDisabled(@NonNull String provider) {    }
    //Reescreve o valor da variavel caso mude a localização
    @Override
    public void onLocationChanged(@NonNull Location location) {    }
    //Reescreve o status do provedor de locaização
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {    }
}
