package com.skynoff.rsmapp.colmentario;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class Busqueda extends AppCompatActivity {
    private ListView lv;
    public java.io.File sdcard= Environment.getExternalStorageDirectory(),
            directory= new java.io.File(sdcard + "/Download/stock1.xls"), file;
    public WorkbookSettings wbSettings;
    String strHyouji="";
    ArrayAdapter<String> adapter;
    String tokens[];
    EditText inputSearch;
     String tokens2[]=read2();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
        CargarListView nuevaTarea = new CargarListView(this);
        nuevaTarea.execute();

        if(tokens== null)
        {
            strHyouji="No se encontro archivo";
        }


        lv = (ListView) findViewById(R.id.list_view);
        lv.setTextFilterEnabled(true);
        inputSearch = (EditText) findViewById(R.id.inputSearch);

        // adapter = new ArrayAdapter<String>(this,R.layout.list_item, R.id.codigo_token,tokens);

        //lv.setAdapter(adapter);


        // Evento para cuando doy click en algun elemento de la lista ( ListView )
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long id) {

               String prdb=  adapter.getItem(position);
                String []divi=null;
                AlertDialog.Builder describe=new AlertDialog.Builder(Busqueda.this);
                describe.setTitle("DESCRIPCION").setIcon(R.drawable.ic_logo);
                for (int i = 0; i < tokens2.length; i++) {
                    String produ=tokens2[i];

                 divi=produ.split(":");
                    if(prdb.trim().equals(divi[0].trim()))
                  {
                      describe.setMessage("Unidad: "+divi[1]+"\nNombre: "+divi[0].toUpperCase()+"\nPrecio "+divi[2]).setNeutralButton("LISTO",null).create().show();
                      break;
                  }
                }








            }

        });


        /* Activando el filtro de busqueda */
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub

                Busqueda.this.adapter.getFilter().filter(arg0);
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
    public String[] read() {
        Workbook workbook = null;
        try {
            WorkbookSettings ws = new WorkbookSettings();
            ws.setGCDisabled(true);


            workbook = Workbook.getWorkbook(directory, ws);
            Sheet sheet = workbook.getSheet(0);

            int rowCount = sheet.getRows();
            String[] result = new String[rowCount];
            for (int i = 0; i < rowCount; i++) {
                Cell[] row = sheet.getRow(i);



                result[i] = row[0].getContents();

            }
            return result;


        } catch (BiffException e) {
            strHyouji=strHyouji+ e.toString();

        } catch (IOException e) {
            strHyouji=strHyouji+ e.toString();
        } catch (Exception e) {
            strHyouji=strHyouji+ e.toString();
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }

        return null;
    }
    public String[] read2() {
        Workbook workbook = null;
        try {
            WorkbookSettings ws = new WorkbookSettings();
            ws.setGCDisabled(true);


            workbook = Workbook.getWorkbook(directory, ws);
            Sheet sheet = workbook.getSheet(0);

            int rowCount = sheet.getRows();
            String[] result = new String[rowCount];
            for (int i = 0; i < rowCount; i++) {
                Cell[] row = sheet.getRow(i);



                result[i] = row[0].getContents()+":"+row[1].getContents()+":"+row[2].getContents()+" Bs.";

            }
            return result;


        } catch (BiffException e) {
            strHyouji=strHyouji+ e.toString();

        } catch (IOException e) {
            strHyouji=strHyouji+ e.toString();
        } catch (Exception e) {
            strHyouji=strHyouji+ e.toString();
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }

        return null;
    }
    private class CargarListView extends AsyncTask<Void, Void, ArrayAdapter<String>> {
        Context context;
        ProgressDialog pDialog;

        public CargarListView(Context context){
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Cargando Lista");
            pDialog.setCancelable(true);
            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pDialog.show();
        }

        @Override
        protected ArrayAdapter<String> doInBackground(Void... arg0) {
            // TODO Auto-generated method stub

            try{
                Thread.sleep(2000);
            }catch(Exception ex){
                ex.printStackTrace();
            }
            tokens= read();
            adapter = new ArrayAdapter<String>(Busqueda.this,R.layout.list_item, R.id.codigo_token,tokens);
            return adapter;
        }

        @Override
        protected void onPostExecute(ArrayAdapter<String> result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            lv.setAdapter(result);
            pDialog.dismiss();
        }

    }
}
