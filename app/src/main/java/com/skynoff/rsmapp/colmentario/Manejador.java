package com.skynoff.rsmapp.colmentario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Equipo Usuario SAINT on 27/10/2015.
 */
public class Manejador {
    private DBhelper helper;
    private SQLiteDatabase db;
    public static final String tabla="SAPROD";
    public static final String cid="_id";
    public static final String Codigo_de_producto="CodProd";
    public static final String Instancia=	"CodInst";
    public static final String Descripción_1="Descrip";
    public static final String activo="Activo";
    public static final String Descripción_2="Descrip2";
    public static final String Descripción_3="Descrip3";
    public static final String Referencia="Refere";
    public static final String Marca="Marca";
    public static final String Unidad="Unidad";
    public static final String Unidad_del_Empaque="UndEmpaq";
    public static final String Cantidad_del_Empaque="CantEmpaq";
    public static final String Precio_1="Precio1";
    public static final String Precio_2="Precio2";
    public static final String Precio_Unitario_2="PrecioU2";
    public static final String Precio_3="Precio3";
    public static final String Precio_Unitario_3="PrecioU3";
    public static final String Precio_Unitario_empaque="PrecioU";
    public static final String Costo_Actual="CostAct";
    public static final String Costo_Promedio="CostPro";
    public static final String Costo_Anterior="CostAnt";
    public static final String Existencia="Existen";
    public static final String Existencia_en_Unidad="ExUnidad";
    public static final String Comprometido="Compro";
    public static final String Pedido="Pedido";
    public static final String Minimo ="Minimo";
    public static final String Maximo="Maximo";
    public static final String Tara="Tara";
    public static final String Depar_Maneja_Compuesto="DEsComp";
    public static final String Depar_Maneja_Comición="DEsComi";
    public static final String Depar_Maneja_Seriales="DEsSeri";
    public static final String EsRetencion ="EsReten";
    public static final String Depar_Maneja_Lote="DEsLote";
    public static final String Depar_Maneja_Vencimiento="DEsVence";
    public static final String Es_Importado="EsImport";
    public static final String Es_Exento="EsExento";
    public static final String Es_Enser="EsEnser";
    public static final String Es_Ofertable="EsOferta";
    public static final String Maneja_Peso="EsPesa";
    public static final String Maneja_Empaque="EsEmpaque";
    public static final String Existencia_Decimal="ExDecimal";
    public static final String Dia_de_Reposición="DiasEntr";
    public static final String Fecha_Ultima_Venta="FechaUV";
    public static final String Fecha_Ultima_Compra="FechaUC";
    public static final String Peso="Peso";
    public static final String Volumen ="Volumen";
    public static final String Unidad_de_volumen= "UndVol";
    public static final String Ubicacion= "Ubicacion";
    public static final String Fecha_Reg= "FechaRegistro";


    public static final String crear_tabla=" create table "+tabla+" ("+cid+" integer primary key autoincrement,"+
            Codigo_de_producto+" text not null,"+ Descripción_1+" text,"+
            Instancia+" integer not null,"+ Descripción_2+" text ,"+Descripción_3+" text,"+
            "ModSab text,"+ "Medida text,"+ "UnidComp text,"+ "UnidVent text,"+ "CantUnd integer,"+
            "Deposito text,"+Marca+" text,"+Ubicacion+" text not null,"+Fecha_Reg+" text);";

    public static final String crear_tabla2=" create table sincodigo " +
            "(_id integer primary key autoincrement, nombre text, codigo text not null);";


    public Manejador(Context context)
    {
        helper = new DBhelper(context);
        db = helper.getWritableDatabase();
    }
    private ContentValues generarContentValues(String v0,String v1,String v2,String
            v3,int v4,String v5,String v6,String v7,String v8,String v9,int v10,String v11, String v12,String v13) {

        ContentValues valores = new ContentValues();
        valores.put( Codigo_de_producto,v0);
        valores.put( Descripción_1,v1);
        valores.put( Instancia,v4);
        valores.put( Descripción_2,v2);
        valores.put( Descripción_3,v3);

        valores.put( "ModSab",v5);
        valores.put( "Medida",v6);
        valores.put( Marca,v7);
        valores.put( "UnidComp",v8);
        valores.put( "UnidVent",v9);
        valores.put( "CantUnd",v10);
        valores.put( "Deposito",v11);
        valores.put( Ubicacion,v12);
        valores.put( Fecha_Reg,v13);
        return valores;
    }

    public void insertar(String v0,String v1,String v2,String v3,int v4,
                         String v5,String v6,String v7,String v8,String v9,int v10,String v11, String v12) {
        //bd.insert(TABLA, NullColumnHack, ContentValues);
        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        db.insert(tabla, null, generarContentValues(v0, v1, v2, v3, v4, v5, v6, v7, v8, v9,v10,v11,v12,currentDate ));

    }
    private ContentValues generarCodes(String v0,String v1){
        ContentValues valores = new ContentValues();
        valores.put("nombre",v0);
        valores.put("codigo", v1);
        return valores;
    }


    public void insertar2(String v0,String v1) {

        db.insert("sincodigo", null, generarCodes(v0, v1));

    }
    public Cursor obtener(String []campos)
    {
        Cursor ci = db.query("SAPROD", campos, null, null, null, null, null);


        return ci;
    }
    public void eliminar()
    {
        db.delete(tabla,null,null);
        db.delete("sincodigo",null,null);
    }

    public Cursor obtener2(String []campos)
    {
        Cursor ci = db.query("sincodigo", campos, null, null, null, null, null);



        return ci;
    }
    public void eliminarReg(String codex)
    {
        db.delete(tabla,Codigo_de_producto+"="+codex,null);

    }







    public void cerrar()
    {
        db.close();
    }

}