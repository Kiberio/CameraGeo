package com.facens.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Criar um elemento de imagem e um botão
    private ImageView imageViewFoto;
    private Button btnGeo;

    //Executar layout criando os botões e imagens
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Gerar o botão e verificar se tem acesso
        btnGeo = (Button) findViewById(R.id.btn_gps);
        ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},123);
        //responsavel por dar função
        btnGeo.setOnClickListener(new View.OnClickListener(){
            //Ao usuario clickar coleta a localização
            @Override
            public void onClick(View view){
                GPStracker g = new GPStracker(getApplication());
                Location l = g.getLocation();
                //Mostra na tela a localização do usuario
                    if(l != null){
                     double lat = l.getLatitude();
                     double lon = l.getLongitude();
                        Toast.makeText(getApplicationContext(), "LATITUDE:" +lat+"\n LONGITUDE:"+lon,Toast.LENGTH_LONG).show();
                    }
            }
        });
        //Vefirica se tem acesso a câmera , caso não tem acesso pede acesso
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, 0);
        }
        //Após clicar no botão armazena
        imageViewFoto = (ImageView) findViewById(R.id.image_foto);
        findViewById(R.id.btn_pic).setOnClickListener(new View.OnClickListener(){
            //Após tirar a foto mostrar na view
            @Override
            public void onClick(View view) {
                tirarFoto();
            }
        });
    }
    //Função para armazenar a imagem
    private void tirarFoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }
    //Substitui o valor das variaveis
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //Trocar informações e imagens da foto.
        if(requestCode == 1 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imagem = (Bitmap) extras.get("data");
            imageViewFoto.setImageBitmap(imagem);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}