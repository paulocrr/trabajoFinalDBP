package com.example.paulo.mychat;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;

public class MensajeActivity extends AppCompatActivity {
    ListView listView;
    ImageButton send;
    EditText mensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje);
        listView=(ListView)findViewById(R.id.listaMensajes);
        send = (ImageButton)findViewById(R.id.sendMessageButton);
        mensaje = (EditText)findViewById(R.id.messageEditText);
        final DBHelper db = new DBHelper(this);
        final int idContacto = Integer.parseInt(getIntent().getExtras().getString("id"));
        String nombre =getIntent().getExtras().getString("nom");
        setTitle(nombre);
        Cursor c  = db.getMensajeContacto(idContacto);
        final ArrayList<String> list = new ArrayList<>();
       while (c.moveToNext()){
           String mensaje = "Yo: "+c.getString(c.getColumnIndex("mensaje"))+"\n "+c.getString(c.getColumnIndex("fecha"));
            list.add(mensaje);
        }
        if (list.size()==0){
            list.add("No hay Mensajes");
        }
        Collections.reverse(list);
        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        listView.setSelection(listView.getAdapter().getCount()-1);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getMensaje = mensaje.getText().toString();
                if(getMensaje != " "||getMensaje!=""||getMensaje!=null){
                    String S = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
                    db.insertMensajes(idContacto,getMensaje,S);
                    list.add("Yo: " + getMensaje +"\n"+S);
                    adapter.notifyDataSetChanged();
                    mensaje.setText("");
                }

            }
        });
    }
}
