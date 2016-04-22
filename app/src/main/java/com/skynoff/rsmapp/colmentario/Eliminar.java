package com.skynoff.rsmapp.colmentario;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Eliminar extends AppCompatActivity {
    public SQLiteDatabase db;
    public      DBhelper helper;
    public  Manejador mn;
    public Button bt5el;
    public EditText et5el;
    public Cursor c;
    public TextView prod;
    String tpu="";
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);
        helper = new DBhelper(this);
        db = helper.getWritableDatabase();
        mn=new Manejador(this);

        ImageView image = new ImageView(this);
        image.setImageResource(R.mipmap.ic_logo);
        bt5el= (Button) findViewById(R.id.button5el);
        et5el= (EditText) findViewById(R.id.editText10el);
        prod= (TextView) findViewById(R.id.textView3e);
    }
    public void prueba(View v)
    {
        AlertDialog.Builder alerta=new AlertDialog.Builder(this);
        alerta.setTitle("¡ADVERTENCIA!").setMessage("Esta apunto de Eliminar la base de datos\n¿Esta seguro que desea realizar esta operacion?").setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mn.eliminar();Intent intent = getIntent();
                finish();
                startActivity(intent);
                Toast.makeText(Eliminar.this, "SE HA ELIMINADO LA BASE DE DATOS CORRECTAMENTE", Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("CANCELAR",null).setIcon(R.drawable.ic_logo).create().show();





    }
    public void prueba2(View v)
    {AlertDialog.Builder alerta=new AlertDialog.Builder(this);
           boolean encontro=false;
        String[] columnas = {mn.cid, mn.Codigo_de_producto, mn.Descripción_1,mn.Instancia,  mn.Descripción_2, mn.Descripción_3,
                "ModSab","Medida", mn.Marca,"UnidComp","UnidVent","CantUnd","Deposito",  mn.Ubicacion};
        try {
            c = mn.obtener(columnas);
        } catch (Exception d) {

        }



      tpu =et5el.getText().toString();
        if(c==null || c.getCount()<=0)
        {



            c.close();
        }else {
               if(c.moveToFirst())
               {
                   do {
                          if(c.getString(1).equals(tpu)) {
                              alerta.setTitle("¡ADVERTENCIA!").setMessage("Esta apunto de Eliminar el siguiente registro datos\nDescripcion: " + c.getString(2) + "\nCodigo:" + c.getString(1)).
                                      setPositiveButton("SI", new DialogInterface.OnClickListener() {
                                          @Override
                                          public void onClick(DialogInterface dialog, int which) {

                                              mn.eliminarReg(tpu);
                                              Intent intent = getIntent();
                                              finish();
                                              startActivity(intent);
                                              Toast.makeText(Eliminar.this, "SE HA ELIMINADO LA BASE DE DATOS CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                                          }
                                      }).setNegativeButton("CANCELAR", null).create().show();
                              encontro=true;
                          }
                   }while (c.moveToNext());
                   if(encontro==false)
                       Toast.makeText(this, "No se Encontro el producto", Toast.LENGTH_SHORT).show();

               }



        }
        c.close();
    }






}
