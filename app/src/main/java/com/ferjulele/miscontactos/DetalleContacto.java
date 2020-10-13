package com.ferjulele.miscontactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class DetalleContacto extends AppCompatActivity {

    private TextView tvnombre;
    private TextView tvtelefono;
    private TextView tvemail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto);
        //Para recibir los paràmetrtos uso el objeto Bundle
        Bundle parametros = getIntent().getExtras(); // en android los paràmetros se llaman extras
        //ya tengo aqui los datos cacheados
        String nombre   = parametros.getString(getResources().getString(R.string.pnombre));
        String telefono = parametros.getString(getResources().getString(R.string.ptelefono));
        String email    = parametros.getString(getResources().getString(R.string.pemail));

        //Ya con los datos, ahora vamos a mostrarlos respectivamente
        //declaro los textview
        tvnombre   = (TextView)findViewById(R.id.Nombre);
        tvtelefono = (TextView)findViewById(R.id.Telefono);
        tvemail    = (TextView)findViewById(R.id.Email);

        //A cada view le coloco los paràmetros que me traigo de la activity anterior
        tvnombre.setText(nombre);
        tvtelefono.setText(telefono);
        tvemail.setText(email);
    }

    public void llamar(View v){

        String telefono = tvtelefono.getText().toString();//obtengo el numero de telefono en mi variable local
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telefono)));//Intent implícito

    }

    public void enviarEmail(View v){

        String email = tvemail.getText().toString();
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));// que tipo de envio sera, que accion ocurre
        emailIntent.putExtra(Intent.EXTRA_EMAIL, email);//a quien sera enviado el correo
        emailIntent.setType("message/rfc822");//indico el tipo de aplicacion, como chooser las app de email
        startActivity(Intent.createChooser(emailIntent, "Email"));//elijo la app para enviar el correo
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {//para cachear el evento de regreso

        if (keyCode == KeyEvent.KEYCODE_BACK) {// si el keyCode corresponde a la tecla back levantamos un nuevo intent que vaya a la lista
            Intent intent = new Intent(DetalleContacto.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}