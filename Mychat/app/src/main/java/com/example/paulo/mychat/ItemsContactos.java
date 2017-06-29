package com.example.paulo.mychat;

/**
 * Created by paulo on 6/9/2017.
 */

public class ItemsContactos {
        public int id;
        public String nombre;
        ItemsContactos(int id,String nombre){
            this.nombre = nombre;
            this.id=id;
        }
        public String getNombre(){
            return nombre;
        }
        public int getIdContacto(){
            return id;
        }
}
