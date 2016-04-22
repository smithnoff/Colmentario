package com.skynoff.rsmapp.colmentario;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Equipo Usuario SAINT on 02/11/2015.
 */
public class zmethod {
    private Cursor c;
    private DBhelper helper;
    private SQLiteDatabase db;
    private Manejador mn2;

    public zmethod(Context context)
    {
        helper = new DBhelper(context);
        db = helper.getWritableDatabase();
        mn2=new Manejador(context);

    }


    public String codigo_lcdv(int in,int sin, int sin2,String nmb,boolean insertar)
    {  String nuevo_codigo="";

               nuevo_codigo=String.format("%02d", in)+String.format("%02d", sin)+String.format("%02d", sin2);
        String[] col={"_id","nombre","codigo"};


        try {
            c = mn2.obtener2(col);
        }catch (Exception d)
        {

        }

        if (c.getCount()>0 ) {

            c.moveToLast();
            nuevo_codigo+=String.format("%03d", (c.getInt(0)+1));
         nuevo_codigo="LCDV"+nuevo_codigo;

        }else{
            nuevo_codigo+="001";
            nuevo_codigo="LCDV"+nuevo_codigo;

        }
        if(insertar==true)
            mn2.insertar2(nmb,nuevo_codigo);



        return nuevo_codigo;



    }

public boolean comprobar_registro(String cdg)
{

    String Query = "Select * from " + "SAPROD" + " where " + "CodProd" + " = '" + cdg+"'";
    Cursor cursor = db.rawQuery(Query, null);
    if(cursor.getCount() <= 0){
        return false;
    }else {


        return true;
    }
}

public String seleccionado(int posicion)
{
    String u="";
    if(posicion==0 )
        u="";
    if(posicion==1 )
        u="UND";
    if(posicion==2)
        u="PAQ";
    if(posicion==3)
        u="CJA";
    if(posicion==4)
        u="BLT";
    if(posicion==5)
        u="GLN";
    if(posicion==6)
        u="CTE";
    if(posicion==7)
        u="KG";
    if(posicion==8)
        u="SCO";
    if(posicion==9)
        u="TBR";



    return u;
}
public String refinar_desc(String newDes)
{       String Descf="";

    String []descripcion_completa=newDes.split(" ");


    for (int i = 0; i < descripcion_completa.length; i++) {

    Descf+=descripcion_completa[i]+" ";
    }
    Descf=Descf.trim().toUpperCase();


    return Descf;
}





    protected void onDestroy() {
        c.close();
        db.close();

    }
}
