package com.skynoff.rsmapp.colmentario;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button ba,bm,be,bb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.drawable.ic_logo);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Powered by:\n" +
                        "Cesar Smith and Sary Hay", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        bb=(Button)findViewById(R.id.bt_el);
        be=(Button)findViewById(R.id.bt_ex);
        Bundle bun=getIntent().getExtras();

        if(bun.getString("nivel").equals("3"))
        {
            bb.setVisibility(View.GONE);
            be.setVisibility(View.GONE);


        }else {
            if(bun.getString("nivel").equals("2"))
            {
                bb.setVisibility(View.GONE);
            }
        }
        Toast.makeText(MainActivity.this, "Bienvenido "+bun.getString("usuario"), Toast.LENGTH_LONG).show();
    }

    public void abrir(View v)
    {
        Intent i=new Intent(this,Agregar.class);
        if(v.getId()==R.id.bt_ag)
            i=new Intent(this,Agregar.class);
        else if(v.getId()==R.id.bt_mo)
            i=new Intent(this,Modificar.class);
        else if(v.getId()==R.id.bt_ex)
            i=new Intent(this,Exportar.class);
        else if(v.getId()==R.id.bt_el)
            i=new Intent(this,Eliminar.class);
        else if(v.getId()==R.id.bt_bu)
            i=new Intent(this,Busqueda.class);


        startActivity(i);


    }
}
