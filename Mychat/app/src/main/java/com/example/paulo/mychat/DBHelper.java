package com.example.paulo.mychat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.IOException;

/**
 * Created by paulo on 6/28/2017.
 */

public class DBHelper extends SQLiteOpenHelper{
    public static final String Database_Name = "MyChat.db";
    public DBHelper(Context context) {
        super(context, Database_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tcontactos(id integer primary key AUTOINCREMENT,nombre text,apellido text,email text)");
        db.execSQL("CREATE TABLE tmensajes(id integer primary key AUTOINCREMENT,mensaje text,fecha timestamp DEFAULT CURRENT_TIMESTAMP,id_contacto integer,foreign key(id_contacto) references tcontactos(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tmensajes");
        db.execSQL("DROP TABLE IF EXISTS tcontactos");
    }

    public boolean insertarContacto(String nombre, String apellido,String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues datos = new ContentValues();
        datos.put("nombre",nombre);
        datos.put("apellido",apellido);
        datos.put("email",email);
        db.insert("tcontactos",null,datos);
        return true;
    }
    public Cursor getTodosContactos(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM tcontactos",null);
        return res;
    }
    public Cursor getInfoContacto(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM tcontactos WHERE id="+id+"",null);
        return res;
    }
    public Cursor getMensajeContacto(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM tmensajes WHERE id_contacto="+id+" ORDER BY fecha DESC LIMIT 12",null);
        return res;
    }
    public boolean insertMensajes(int id,String mensaje,String fecha){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues datos = new ContentValues();
        datos.put("mensaje",mensaje);
        datos.put("fecha",fecha);
        datos.put("id_contacto",id);
        db.insert("tmensajes",null,datos);
        return true;
    }
    public boolean updateContacto(Integer id,String nombre, String apellido, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put("nombre",nombre);
        data.put("apellido",apellido);
        data.put("email",email);
        db.update("tcontactos",data,"id = ? ",new String[]{Integer.toString(id)});
        return true;
    }
    public void emptyMensajes(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("tmensajes","1",new String[]{"1"});
    }
}
