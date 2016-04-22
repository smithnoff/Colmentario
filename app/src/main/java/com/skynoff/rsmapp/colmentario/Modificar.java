package com.skynoff.rsmapp.colmentario;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class Modificar extends AppCompatActivity {
      public EditText busqueda;
    public Manejador mn;
    private Cursor c;
    public TextView producto_ultimo;
    public ListView lista_prod;
    private ArrayAdapter<String> customAdapter;
    int conteo=0;
    public String[] columnas= {mn.cid,
            mn.Codigo_de_producto,
            mn.Descripción_1,
            mn.Descripción_2,
            mn.Descripción_3,
            mn.Instancia,
            "ModSab",
            "Medida",
            mn.Marca,
            "UnidComp",
            "UnidVent",
            "CantUnd",
            "Deposito",
            mn.Ubicacion,
    mn.Fecha_Reg},token;
    DBhelper helper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        producto_ultimo = (TextView) findViewById(R.id.tv_prod);
        helper = new DBhelper(this);
        db = helper.getWritableDatabase();
        mn = new Manejador(this);
          busqueda= (EditText) findViewById(R.id.searchViewp);
        lista_prod = (ListView) findViewById(R.id.listView);
        lista_prod.setTextFilterEnabled(true);


        try {
            c = mn.obtener(columnas);
        } catch (Exception d) {

        }



        String tpu = "";
        if(c==null || c.getCount()<=0)
        {
            tpu = "No hay Informacion";
            producto_ultimo.setText(tpu);
            Toast.makeText(this, "No Hay Informacion", Toast.LENGTH_SHORT).show();
            c.close();
        }
        else {



        if (c != null || c.getCount() > 0) {
            c.moveToLast();
            tpu = "Codigo: " + c.getString(1) + "\nDescripcion: " + c.getString(2) + "\nUbicacion: " + c.getString(13)+"\nFecha: " + c.getString(14)+"\nNro de Registros: "+c.getCount();
            c.moveToFirst();
            token=new String[c.getCount()];
            do {
                token[conteo]=c.getString(2);
                conteo++;

            }while (c.moveToNext());
            conteo=0;

            customAdapter = new ArrayAdapter<String>(Modificar.this,R.layout.list_item,R.id.codigo_token,token);


            lista_prod.setAdapter(customAdapter);



            producto_ultimo.setText(tpu);
            lista_prod.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                AlertDialog.Builder describe=new AlertDialog.Builder(Modificar.this);
                public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                    String seleciop=customAdapter.getItem(pos);
                    c.moveToFirst();

                    do {
                        if(seleciop.trim().equals(c.getString(1).trim()))
                        {
                            describe.setMessage("codigo: "+c.getString(1)+"\nProducto: "+c.getString(2)+"\nUbicacion: "+c.getString(13)).setNeutralButton("LISTO",null).setIcon(R.drawable.ic_logo).create().show();
                          break;
                        }

                    }while(c.moveToNext());





                }

            });
        }
        }

        busqueda.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub

               Modificar.this.customAdapter.getFilter().filter(arg0);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub

            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
        c.close();
    }


}
