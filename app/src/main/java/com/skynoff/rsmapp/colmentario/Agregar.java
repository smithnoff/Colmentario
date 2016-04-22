package com.skynoff.rsmapp.colmentario;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Agregar extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public  Manejador mn;
    public zmethod zm;
    public Spinner sp1,sp2,sp3,sp4,sp5,sp6,sp7,sp8;
    public LinearLayout formu;
    public CheckBox code;
    public EditText etcode,etdes,etmar,etmos,etmed,etref,etunv,etund,etubi,etcntund;
    public Button barscan;
    public String codfin="",p="",z="",s="",u="",depo="";
    public String descripcion_refinada="",desc2="",desc3="";
    public Cursor ciu;
    boolean importado=false;
    public Modificar mod;
    public  String []ultimo_agregado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        code=(CheckBox)findViewById(R.id.cbcode);
        etcode=(EditText)findViewById(R.id.editText);
        sp1=(Spinner)findViewById(R.id.instancia);
        sp2=(Spinner)findViewById(R.id.spinstancia2);
        sp3=(Spinner)findViewById(R.id.spinstancia3);
        sp4=(Spinner)findViewById(R.id.Piso);
        sp5=(Spinner)findViewById(R.id.Zona);
        sp6=(Spinner)findViewById(R.id.Sector);
        sp7=(Spinner)findViewById(R.id.unidadesv);
        sp8=(Spinner)findViewById(R.id.unidadesd);
        formu=(LinearLayout)findViewById(R.id.Formulario);
        etcode=(EditText)findViewById(R.id.editText);
        etdes=(EditText)findViewById(R.id.editText2);
        etmar=(EditText)findViewById(R.id.editText3);
        etmos=(EditText)findViewById(R.id.editText4);
        etmed=(EditText)findViewById(R.id.editText5);
        etref=(EditText)findViewById(R.id.editText6);
        etunv=(EditText)findViewById(R.id.editText7);
        etund=(EditText)findViewById(R.id.editText8);
        etcntund=(EditText)findViewById(R.id.editTextcntemp);
        etubi=(EditText)findViewById(R.id.editText9);
        barscan=(Button)findViewById(R.id.bt_scan);

        mod=new Modificar();
        mn=new Manejador(this);
        zm=new zmethod(this);

        loadSpinnerProvincias();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        String []desglosado;
        String descrip_db="",frag="";

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            try {
                ciu=mn.obtener(mod.columnas);
            }catch (Exception u)
            {

            }

            ciu.moveToLast();
            descrip_db=ciu.getString(2);
            desglosado=descrip_db.split(ciu.getString(8));


            etdes.setText(desglosado[0]);
            etmar.setText(ciu.getString(8));
            etmos.setText(ciu.getString(6));
            etmed.setText(ciu.getString(7));
            etubi.setText(ciu.getString(13));
          importado=true;


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadSpinnerProvincias() {

        // Create an ArrayAdapter using the string array and a default spinner
        // layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.Instancias, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        // Apply the adapter to the spinner
        this.sp1.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(
                this, R.array.pisos, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        this.sp4.setAdapter(adapter4);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(
                this, R.array.zonas, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        this.sp5.setAdapter(adapter5);
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(
                this, R.array.sectores, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        this.sp6.setAdapter(adapter6);
        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(
                this, R.array.unidades, android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        this.sp7.setAdapter(adapter7);
        ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(
                this, R.array.unidades, android.R.layout.simple_spinner_item);
        adapter8.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        this.sp8.setAdapter(adapter8);

        // This activity implements the AdapterView.OnItemSelectedListener
        this.sp1.setOnItemSelectedListener(this);
        this.sp2.setOnItemSelectedListener(this);
        this.sp3.setOnItemSelectedListener(this);
        this.sp4.setOnItemSelectedListener(this);
        this.sp5.setOnItemSelectedListener(this);
        this.sp6.setOnItemSelectedListener(this);
        this.sp7.setOnItemSelectedListener(this);
        this.sp8.setOnItemSelectedListener(this);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        switch (parent.getId()) {
            case R.id.instancia:

                // Retrieves an array
                TypedArray arraySubinstancia1 = getResources().obtainTypedArray(
                        R.array.array_instancia_a_subinstancia);
                CharSequence[] subinstancia1 = arraySubinstancia1.getTextArray(position);
                arraySubinstancia1.recycle();

                // Create an ArrayAdapter using the string array and a default
                // spinner layout
                ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
                        this, android.R.layout.simple_spinner_item,
                        android.R.id.text1, subinstancia1);

                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);

                // Apply the adapter to the spinner
                this.sp2.setAdapter(adapter);

                break;

            case R.id.spinstancia2:

                TypedArray arraySubinstancia2;
                arraySubinstancia2 = getResources().obtainTypedArray(
                        R.array.array_subinstancia_a_subinstancia4);
                if(sp1.getSelectedItemPosition()==5)
                    arraySubinstancia2 = getResources().obtainTypedArray(
                            R.array.array_subinstancia_a_subinstancia5);
                if(sp1.getSelectedItemPosition()==4)
                    arraySubinstancia2 = getResources().obtainTypedArray(
                            R.array.array_subinstancia_a_subinstancia3);
                if(sp1.getSelectedItemPosition()==1)
                    arraySubinstancia2 = getResources().obtainTypedArray(
                            R.array.array_subinstancia_a_subinstancia2);

                CharSequence[] subinstancia2 = arraySubinstancia2.getTextArray(position);
                arraySubinstancia2.recycle();
                ArrayAdapter<CharSequence> adapter2 = new ArrayAdapter<CharSequence>(
                        this, android.R.layout.simple_spinner_item,
                        android.R.id.text1, subinstancia2);

                // Specify the layout to use when the list of choices appears
                adapter2.setDropDownViewResource(android.R.layout.simple_list_item_checked);

                // Apply the adapter to the spinner
                this.sp3.setAdapter(adapter2);


                break;

        }

        if (sp1.getSelectedItemPosition()!=0  )
        {
            formu.setVisibility(View.VISIBLE);
        }else{
            formu.setVisibility(View.GONE);
        }

        if(sp4.getSelectedItemPosition()==4)
        {
            etubi.setText("PBZ1PD");
            depo="VEANA02";
            sp5.setVisibility(View.GONE);

            sp6.setVisibility(View.GONE);
        }else {
            sp5.setVisibility(View.VISIBLE);
            sp6.setVisibility(View.VISIBLE);
            depo="VEANA01";
            if (sp4.getSelectedItemPosition() == 1)
                p = "P1";
            else if (sp4.getSelectedItemPosition() == 2)
                p = "PB";
            else if (sp4.getSelectedItemPosition() == 3)
                p = "S1";
            else
                p = "";

            if (sp5.getSelectedItemPosition() == 1)
                z = "Z1";
            else if (sp5.getSelectedItemPosition() == 2)
                z = "Z2";
            else
                z = "";
            int ult = 0;
            s = sp6.getSelectedItem().toString();
            ult = s.length() - 1;

            try {
                s = "D" + s.charAt(ult);
            } catch (Exception o) {

            }

            u = zm.seleccionado(sp7.getSelectedItemPosition());

            try {
                etunv.setText(u);
            } catch (Exception i) {

            }
            u = zm.seleccionado(sp8.getSelectedItemPosition());
            if(sp8.getSelectedItemPosition()!=0)
            {
             etcntund.setVisibility(View.VISIBLE);
            }else {
                etcntund.setVisibility(View.GONE);
                 etcntund.setText("0");
            }

            try {
                etund.setText(u);
            } catch (Exception i) {

            }


            if (sp4.getSelectedItemPosition() != 0 && sp5.getSelectedItemPosition() != 0 && sp6.getSelectedItemPosition() != 0) {
                etubi.setText(p + z + s);
                importado=false;
            }else if(importado!=true ){etubi.setText("");}
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {


    }



    public void habilita(View v)
    {
        v.clearFocus();


        if(code.isChecked()==true)
        { // etcode.setFocusable(true);
            etcode.setEnabled(true);
            barscan.setEnabled(true);
            barscan.setBackgroundColor(Color.parseColor("#64DD17"));

        }else{
            etcode.setEnabled(false);
            barscan.setEnabled(false);
            barscan.setBackgroundColor(Color.parseColor("#F50057"));
            etcode.setText("");

        }
    }
    public void onClick(View v){
        //Se responde al evento click
        if(v.getId()==R.id.bt_scan){
            //Se instancia un objeto de la clase IntentIntegrator
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            //Se procede con el proceso de scaneo
            scanIntegrator.initiateScan();
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //Se obtiene el resultado del proceso de scaneo y se parsea
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            //Quiere decir que se obtuvo resultado pro lo tanto:
            //Desplegamos en pantalla el contenido del código de barra scaneado
            String scanContent = scanningResult.getContents();
            etcode.setText(scanContent);
            //Desplegamos en pantalla el nombre del formato del código de barra scaneado

        }else{
            //Quiere decir que NO se obtuvo resultado
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No se ha recibido datos del Escaneo!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    public void verificar(View v) {

        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        codfin = codfin.trim();
        String newDes = etdes.getText().toString().trim();
        newDes = newDes + " " + etmar.getText().toString().trim() + " " + etmos.getText().toString().trim() + " " + etmed.getText().toString().trim() +
                " " + etref.getText().toString().trim();
        descripcion_refinada = zm.refinar_desc(newDes);

        if (descripcion_refinada.length() > 40 && descripcion_refinada.length() <= 80) {

            desc2 = descripcion_refinada.substring(41, (descripcion_refinada.length() ));
            desc2 = desc2.trim();
            descripcion_refinada = descripcion_refinada.substring(0, 41);
        } else {
            if (descripcion_refinada.length() > 80 && descripcion_refinada.length() <= 140) {

                desc2 = descripcion_refinada.substring(40, 80);
                desc2 = desc2.trim();
                desc3 = descripcion_refinada.substring(80, (descripcion_refinada.length() - 1));
                desc3 = desc3.trim();
                descripcion_refinada = descripcion_refinada.substring(0, 41);
            }
        }
        if (code.isChecked() == false) {
            codfin = zm.codigo_lcdv(sp1.getSelectedItemPosition(), sp2.getSelectedItemPosition(), sp3.getSelectedItemPosition(), descripcion_refinada, false);

        } else {
            codfin = etcode.getText().toString();

        }


            if (etdes.getText().toString().trim().equals("") || etdes.getText().toString().trim().equals(null) || etmos.getText().toString().trim().equals("") ||
                    etmos.getText().toString().trim().equals(null) || etmed.getText().toString().trim().equals("") || etmed.getText().toString().trim().equals(null)
                    || codfin.equals(null) || codfin.trim().equals("")) {
                alerta.setTitle("¡ADVERTENCIA!").setMessage("Hay campos vacios\nRevise los campos Descripcion, Modelo y Medida\nestos son Obligatorios").setPositiveButton("ok", null).setIcon(R.drawable.ic_logo).create().show();
//posible bug a la hora de verificar si un codigo generado existe en la bd
            } else {
                if (zm.comprobar_registro(codfin) == true) {
                    alerta.setMessage("Este Codigo ya existe, verifique que el codigo se ha introducido correctamente o pongase en contacto con el administrador");
                    alerta.setTitle("!ADVERTENCIA¡").setNegativeButton("cerrar", null).setIcon(R.drawable.ic_logo).create().show();
                    etcode.setText("");
                } else {
                    alerta.setMessage("Codigo: " + codfin + "\nDescripcion: " + newDes + "\nMarca: " + etmar.getText() + "\nModelo/Sabor: " +
                            etmos.getText() + "\nMedida: " + etmed.getText() + "\nReferencia: " + etref.getText() + "\nUnidad Venta: " + etunv.getText() + "\nUnidad Detallada: " + etund.getText() +
                            "\nUbicacion: " + etubi.getText() + "\nInstancia: " + sp1.getSelectedItem().toString() + "\nsub-instancia 1: " + sp2.getSelectedItem().toString() +
                            "\nSub-instancia 2: " + sp3.getSelectedItem().toString());
                    alerta.setTitle("¿Desea registrar estos datos?");
                    alerta.setPositiveButton("GUARDAR", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (code.isChecked() == false)
                                codfin = zm.codigo_lcdv(sp1.getSelectedItemPosition(), sp2.getSelectedItemPosition(), sp3.getSelectedItemPosition(), descripcion_refinada, true);


                            mn.insertar(codfin, descripcion_refinada, desc2.toUpperCase().trim(), desc3.toUpperCase().trim(),
                                    sp1.getSelectedItemPosition(), etmos.getText().toString().toUpperCase().trim(),
                                    etmed.getText().toString().toUpperCase().trim(), etmar.getText().toString().toUpperCase().trim(),
                                    etunv.getText().toString().toUpperCase().trim(), etund.getText().toString().toUpperCase().trim(),
                                    Integer.parseInt(etcntund.getText().toString().trim()), depo, etubi.getText().toString().toUpperCase().trim());
                            Toast.makeText(Agregar.this, "Agregado Correctamente ", Toast.LENGTH_SHORT).show();
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);
                        }
                    });


                    alerta.setNegativeButton("CANCELAR", null).setIcon(R.drawable.ic_logo);
                    alerta.create();
                    alerta.show();
                }

            }
        }
    }
