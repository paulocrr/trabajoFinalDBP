package com.example.paulo.mychat;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper db = new DBHelper(this);
        List<ItemsContactos> contactos = new ArrayList<>();
        Cursor c  = db.getTodosContactos();
        //db.insertarContacto("Stefanie","Muroya","prueba@gmail.com");
        while (c.moveToNext()){
            String nombre = c.getString(c.getColumnIndex("nombre"));
            String apellido = c.getString(c.getColumnIndex("apellido"));
            String fullname = nombre +" "+apellido;
            contactos.add(new ItemsContactos(Integer.parseInt(c.getString(c.getColumnIndex("id"))),fullname));
        }
        /*contactos.add(new ItemsContactos(0,"Stefanie Muroya"));
        contactos.add(new ItemsContactos(1,"Paulo Rodriguez"));
        contactos.add(new ItemsContactos(2,"Diego Melendez"));
        contactos.add(new ItemsContactos(3,"Janet Rodriguez"));*/
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listaCcntactos);
        RecyclerViewContactos adapter = new RecyclerViewContactos(contactos,getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
