package com.ferjulele.miscontactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Contacto> contactos; //me declaro mi arraylist de contactos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactos = new ArrayList<Contacto>();//instancio mis contactos
        //Añadimos 5 contactos harcodeados
        contactos.add(new Contacto("Rodrigo Gabin", "111111", "rodrigo@gmail.com"));
        contactos.add(new Contacto("Fernanda Echazarreta", "222222", "ferchu@gmail.com"));
        contactos.add(new Contacto("Juana Gabin", "333333", "juana@gmail.com"));
        contactos.add(new Contacto("LeBron Gabin", "444444", "lebron@gmail.com"));
        contactos.add(new Contacto("Buldy Silas", "555555", "buldy@gmail.com"));

        //Me creo un array para obtener solo los nombres de los contactos y asì pasarlos a la vista
        ArrayList<String> nombreContactos = new ArrayList<>();
        for (Contacto contacto: contactos
             ) {
            nombreContactos.add(contacto.getNombre());
        }
        //Ahora empezamos a generar nuestro list view
        ListView listContactos = (ListView)findViewById(R.id.listContactos);
        //ahora uso el adaptador para llenar la vista de contactos que recibe un arreglo que usa un layout de android ya
        listContactos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombreContactos));

        //Ahora creamos un intent explìcito para acceder de la activity 1 a la 2 dentro de la misma app
        //Antes necesito colocar un listener en mi lista
        listContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {//position= indice de la lista
                //aqui declaramos nuestro objeto intent y necesito enviarlo con paràmetros
                Intent intent = new Intent(MainActivity.this, DetalleContacto.class);// desde donde y hacia donde quiero ir
                intent.putExtra(getResources().getString(R.string.pnombre), contactos.get(position).getNombre());//debo enviar de a un paràmetro
                intent.putExtra(getResources().getString(R.string.ptelefono), contactos.get(position).getTelefono());//debo enviar de a un paràmetro
                intent.putExtra(getResources().getString(R.string.pemail), contactos.get(position).getEmail());//debo enviar de a un paràmetro
                //se puede hacer tambièn a traves de un array de string o tipo primitivo con los 3 elementos a la vez
                startActivity(intent);//iniciar el intent
                finish();
                //Ahora hay que irse al otro lado, a Detalle contactos para ver como recibe los datos :)
            }
        });

    }
}