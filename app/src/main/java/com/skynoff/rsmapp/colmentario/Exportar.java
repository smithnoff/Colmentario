package com.skynoff.rsmapp.colmentario;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.*;
import jxl.write.Number;
import jxl.write.biff.RowsExceededException;

public class Exportar extends AppCompatActivity {

    public File sdCard, directory, file,file2,file3;
    public WorkbookSettings wbSettings;
    public WritableWorkbook workbook,workbook2,workbook3;
    public WritableSheet sheet, sheet2,sheet3,sheet4,sheet5;
    public Manejador mn;
    private Cursor c;
    public SQLiteDatabase db;
    String currentDate="";
    public DBhelper helper;
    Label label, label1, label2, label3, label4, label5, label6, label7, label8, label9,
            label10, label11, label12, label13, label14, label15, label16, label17, label18,
            label19, label20, label21, label22, label23, label24, label25, label26, label27,
            label28, label29, label30, label31, label32, label33, label34, label35, label36,
            label37, label38, label39, label40, label41, label42, label43, label44, label45,
            label46, label47, label48, label49, label50, label51, label52, label53,label54;
jxl.write.Number num1,num2,num3,num4,num5,num6,num7,num8,num9,num10,num11,num12,num13,num14,num15,num16,num17,num18,
        num19,num20,num21,num22,num23,num24,num25,num26,num27,num28,num29,num30,num31,num32,num33,num34,num35;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exportar);
        helper = new DBhelper(this);
        db = helper.getWritableDatabase();
        mn = new Manejador(this);
        String[] columnas = {mn.cid, mn.Codigo_de_producto, mn.Descripción_1,mn.Instancia,  mn.Descripción_2, mn.Descripción_3,
                "ModSab","Medida", mn.Marca,"UnidComp","UnidVent","CantUnd","Deposito",  mn.Ubicacion};
        c = mn.obtener(columnas);
      currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String Fnamexls = "Inventario_Colmena_" +currentDate+ ".xls";
        sdCard = Environment.getExternalStorageDirectory();
        directory = new File(sdCard.getAbsolutePath() + "/Download");
        directory.mkdirs();
        file = new File(directory, Fnamexls);
        file2 = new File(directory, "Carga_de_invetario_" +currentDate+ ".xls");
        file3 = new File(directory, "Impuesto_Colmena_" +currentDate+ ".xls");
        wbSettings = new WorkbookSettings();

        wbSettings.setLocale(new Locale("es", "ES"));
        try {

            workbook = Workbook.createWorkbook(file, wbSettings);
            workbook2 = Workbook.createWorkbook(file2, wbSettings);


            sheet = workbook.createSheet("SAPROD", 0);

        } catch (Exception i) {

        }
    }

    public void generar(View v) {
        int fila = 0;


        try {label = new Label(0, fila, "CodProd");
            label2 = new Label(1, fila, "Descrip");
            label1 = new Label(2, fila, "CodInst");
            label3 = new Label(3, fila, "Activo");
            label4 = new Label(4, fila, "Descrip2");
            label5 = new Label(5, fila, "Descrip3");
            label6 = new Label(6, fila, "Refere");
            label7 = new Label(7, fila, "Marca");
            label8 = new Label(8, fila, "Unidad");
            label9 = new Label(9, fila, "UndEmpaq");
            label10 = new Label(10, fila, "CantEmpaq");
            label11 = new Label(11, fila, "Precio1");
            label12 = new Label(12, fila, "Precio2");
            label13 = new Label(13, fila, "PrecioU2");
            label14 = new Label(14, fila, "Precio3");
            label15 = new Label(15, fila, "PrecioU3");
            label16 = new Label(16, fila, "PrecioU");
            label17 = new Label(17, fila, "CostAct");
            label18 = new Label(18, fila, "CostPro");
            label19 = new Label(19, fila, "CostAnt");
            label20 = new Label(20, fila, "Existen");
            label21 = new Label(21, fila, "ExUnidad");
            label22 = new Label(22, fila, "Compro");
            label23 = new Label(23, fila, "Pedido");
            label24 = new Label(24, fila, "Minimo");
            label25 = new Label(25, fila, "Maximo");
            label26 = new Label(26, fila, "Tara");
            label27 = new Label(27, fila, "DEsComp");
            label28 = new Label(28, fila, "DEsComi");
            label29 = new Label(29, fila, "DEsSeri");
            label30 = new Label(30, fila, "EsReten");
            label31 = new Label(31, fila, "DEsLote");
            label32 = new Label(32, fila, "DEsVence");
            label33 = new Label(33, fila, "EsImport");
            label34 = new Label(34, fila, "EsExento");
            label35 = new Label(35, fila, "EsEnser");
            label36 = new Label(36, fila, "EsOferta");
            label37 = new Label(37, fila, "EsPesa");
            label38 = new Label(38, fila, "EsEmpaque");
            label39 = new Label(39, fila, "ExDecimal");
            label40 = new Label(40, fila, "DiasEntr ");
            label41 = new Label(41, fila, "FechaUV");
            label42 = new Label(42, fila, "FechaUC");
            label43 = new Label(43, fila, "Peso");
            label44 = new Label(44, fila, "Volumen");
            label45 = new Label(45, fila, "UndVol");



            sheet.addCell(label);
            sheet.addCell(label1);
            sheet.addCell(label2);
            sheet.addCell(label3);
            sheet.addCell(label4);
            sheet.addCell(label5);
            sheet.addCell(label6);
            sheet.addCell(label7);
            sheet.addCell(label8);
            sheet.addCell(label9);
            sheet.addCell(label10);
            sheet.addCell(label11);
            sheet.addCell(label12);
            sheet.addCell(label13);
            sheet.addCell(label14);
            sheet.addCell(label15);
            sheet.addCell(label16);
            sheet.addCell(label17);
            sheet.addCell(label18);
            sheet.addCell(label19);
            sheet.addCell(label20);
            sheet.addCell(label21);
            sheet.addCell(label22);
            sheet.addCell(label23);
            sheet.addCell(label24);
            sheet.addCell(label25);
            sheet.addCell(label26);
            sheet.addCell(label27);
            sheet.addCell(label28);
            sheet.addCell(label29);
            sheet.addCell(label30);
            sheet.addCell(label31);
            sheet.addCell(label32);
            sheet.addCell(label33);
            sheet.addCell(label34);
            sheet.addCell(label35);
            sheet.addCell(label36);
            sheet.addCell(label37);
            sheet.addCell(label38);
            sheet.addCell(label39);
            sheet.addCell(label40);
            sheet.addCell(label41);
            sheet.addCell(label42);
            sheet.addCell(label43);
            sheet.addCell(label44);
            sheet.addCell(label45);

        } catch (RowsExceededException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (WriteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        fila++;

        //createExcel(excelSheet);

 WritableCellFormat entero=new WritableCellFormat(NumberFormats.INTEGER);
 WritableCellFormat decimal=new WritableCellFormat(NumberFormats.FLOAT);

        if (fila != 0) {
            if (c.moveToFirst()) {

                do {
                    try {
                        label=new Label(0,fila,c.getString(1));
                        label2=new Label(1,fila,c.getString(2));
                       num1=new Number(2, fila,c.getInt(3),entero);
                       num2=new Number(3,fila,1,entero);
                        label3=new Label(4,fila,c.getString(4));
                        label4=new Label(5,fila,c.getString(5));
                        label5=new Label(6,fila,c.getString(1));
                        label6=new Label(7,fila,c.getString(8));
                        label7=new Label(8,fila,c.getString(9));
                        label8=new Label(9,fila,c.getString(10));
                        num3=new Number(10, fila,c.getInt(11),entero);
                        num4=new Number(11,fila,0,decimal);
                        num5=new Number(12,fila,0,decimal);
                        num6=new Number(13,fila,0,decimal);
                        num7=new Number(14,fila,0,decimal);
                        num8=new Number(15,fila,0,decimal);
                        num9=new Number(16,fila,0,decimal);
                        num10=new Number(17,fila,0,decimal);
                        num11=new Number(18,fila,0,decimal);
                        num12=new Number(19,fila,0,decimal);
                        num13=new Number(20,fila,0,decimal);
                        num14=new Number(21,fila,0,decimal);
                        num15=new Number(22,fila,0,decimal);
                        num16=new Number(23,fila,0,decimal);
                        num17=new Number(24,fila,0,decimal);
                        num18=new Number(25,fila,0,decimal);
                        num35=new Number(26,fila,0,decimal);

                        num19=new Number(27,fila,0,entero);
                        num20=new Number(28,fila,0,entero);
                        num21=new Number(29,fila,0,entero);
                        num22=new Number(30,fila,0,entero);
                        num23=new Number(31,fila,0,entero);
                        num24=new Number(32,fila,0,entero);
                        num25=new Number(33,fila,0,entero);
                        num26=new Number(34,fila,0,entero);
                        num27=new Number(35,fila,0,entero);
                        num28=new Number(36,fila,0,entero);
                        num29=new Number(37,fila,0,entero);
                        num30=new Number(38,fila,1,entero);
                        num31=new Number(39,fila,0,entero);
                        num32=new Number(40,fila,0,entero);
                        label9=new Label(41,fila,null);
                        label10=new Label(42,fila,null);
                        num33=new Number(43,fila,0,decimal);
                        num34=new Number(44,fila,0,decimal);
                        label11=new Label(45,fila,"");


                        sheet.addCell(label);
                        sheet.addCell(label2);
                        sheet.addCell(num1);
                        sheet.addCell(num2);
                        sheet.addCell(label3);
                        sheet.addCell(label4);
                        sheet.addCell(label5);
                        sheet.addCell(label6);
                        sheet.addCell(label7);
                        sheet.addCell(label8);
                        sheet.addCell(num3);
                        sheet.addCell(num4);
                        sheet.addCell(num5);
                        sheet.addCell(num6);
                        sheet.addCell(num7);
                        sheet.addCell(num8);
                        sheet.addCell(num9);
                        sheet.addCell(num10);
                        sheet.addCell(num11);
                        sheet.addCell(num12);
                        sheet.addCell(num13);
                        sheet.addCell(num14);
                        sheet.addCell(num15);
                        sheet.addCell(num16);
                        sheet.addCell(num17);
                        sheet.addCell(num18);
                        sheet.addCell(num35);

                        sheet.addCell(num19);
                        sheet.addCell(num20);
                        sheet.addCell(num21);
                        sheet.addCell(num22);
                        sheet.addCell(num23);
                        sheet.addCell(num24);
                        sheet.addCell(num25);
                        sheet.addCell(num26);
                        sheet.addCell(num27);
                        sheet.addCell(num28);
                        sheet.addCell(num29);
                        sheet.addCell(num30);
                        sheet.addCell(num31);
                        sheet.addCell(num32);
                        sheet.addCell(label9);
                        sheet.addCell(label10);
                        sheet.addCell(num33);
                        sheet.addCell(num34);
                        sheet.addCell(label11);

                    } catch (RowsExceededException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (WriteException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }


                    fila++;




                } while (c.moveToNext());
            }

            fila = 0;
            try {

                sheet2 = workbook.createSheet("SAEXIS", 1);
            } catch (Exception i) {

            }
            try {
                label54=new Label(0,fila,"CodProd");
                label46 = new Label(1, fila, "CodUbi");
                label47 = new Label(2, fila, "PuestoI");
                label48 = new Label(3, fila, "Existen");
                label49 = new Label(4, fila, "ExUnidad");
                label50 = new Label(5, fila, "CantPed");
                label51 = new Label(6, fila, "UnidPed");
                label52 = new Label(7, fila, "CantCom");
                label53 = new Label(8, fila, "UnidCom");
                sheet2.addCell(label54);
                sheet2.addCell(label46);
                sheet2.addCell(label47);
                sheet2.addCell(label48);
                sheet2.addCell(label49);
                sheet2.addCell(label50);
                sheet2.addCell(label51);
                sheet2.addCell(label52);
                sheet2.addCell(label53);
            } catch (Exception t) {
            }
            fila++;
            c.moveToFirst();
            if (fila != 0) {
                if (c.moveToFirst()) {
                    //Recorremos el cursor hasta que no haya más registros
                    do {
                        try {
                            label54=new Label(0,fila,c.getString(1));
                            label46 = new Label(1, fila, c.getString(12));
                            label47 = new Label(2, fila, c.getString(13));
                            num1 = new Number(3, fila, 0,decimal);
                            num2 = new Number(4, fila, 0,decimal);
                            num3 = new Number(5, fila, 0,decimal);
                            num4 = new Number(6, fila, 0,decimal);
                            num5 = new Number(7, fila, 0,decimal);
                            num6 = new Number(8, fila, 0,decimal);
                            sheet2.addCell(label54);
                            sheet2.addCell(label46);
                            sheet2.addCell(label47);
                            sheet2.addCell(num1);
                            sheet2.addCell(num2);
                            sheet2.addCell(num3);
                            sheet2.addCell(num4);
                            sheet2.addCell(num5);
                            sheet2.addCell(num6);

                        } catch (Exception u) {
                        }
                        fila++;
                    } while (c.moveToNext());
                }
            }

fila=0;
            try {





                sheet3 = workbook.createSheet("SATAXPRD", 2);
            } catch (Exception i) {

            }

            try {
                label54=new Label(0,fila,"CodProd");
                label46 = new Label(1, fila, "CodTaxs");
                label47 = new Label(2, fila, "Monto");
                label48 = new Label(3, fila, "EsPorct");

                sheet3.addCell(label54);
                sheet3.addCell(label46);
                sheet3.addCell(label47);
                sheet3.addCell(label48);

            } catch (Exception t) {
            }
            fila++;
            c.moveToFirst();
            if (fila != 0) {
                if (c.moveToFirst()) {
                    //Recorremos el cursor hasta que no haya más registros
                    do {
                        try {
                            label54=new Label(0,fila,c.getString(1));
                            label46 = new Label(1, fila,"IVA");
                            num1 = new Number(2, fila, 12,decimal);
                            num2 = new Number(3, fila, 1,entero);

                            sheet3.addCell(label54);
                            sheet3.addCell(label46);
                            sheet3.addCell(num1);
                            sheet3.addCell(num2);


                        } catch (Exception u) {
                        }
                        fila++;
                    } while (c.moveToNext());
                }
            }
            try {


                workbook.write();
                workbook.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }




            c.close();
            db.close();
            Toast.makeText(this, "LOS DOCUMENTOS SE HAN CREADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();


        }
    }

    public void generar_carga(View v)
    {
        WritableCellFormat entero=new WritableCellFormat(NumberFormats.INTEGER);
        WritableCellFormat decimal=new WritableCellFormat(NumberFormats.FLOAT);
            db=helper.getWritableDatabase();
        String[] columnas = {mn.cid, mn.Codigo_de_producto, mn.Descripción_1,mn.Instancia,  mn.Descripción_2, mn.Descripción_3,
                "ModSab","Medida", mn.Marca,"UnidComp","UnidVent","CantUnd","Deposito",  mn.Ubicacion};
        c = mn.obtener(columnas);

        try {
            sheet4 = workbook2.createSheet("SAOPEI", 0);
        } catch (Exception i) {

        }

       int fila=0;
        try {
            label  =new Label(0  ,fila, "CodSucu");
            label1  =new Label(1 ,fila, "TipoOpI");
            label2  =new Label(2 ,fila, "NumeroD");
            label3  =new Label(3 ,fila, "NroUnico");
            label4  =new Label(4 ,fila, "Status");
            label5  =new Label(5 ,fila, "CodEsta");
            label6  =new Label(6 ,fila, "CodUsua");
            label7  =new Label(7 ,fila, "CodUbic");
            label8  =new Label(8 ,fila, "CodUbic2");
            label9  =new Label(9 ,fila, "Signo");
            label10  =new Label(10 ,fila, "FechaT");
            label11  =new Label(11 ,fila, "OTipo");
            label12  =new Label(12,fila, "ONumero");
            label13  =new Label(13  ,fila, "Autori");
            label14  =new Label(14  ,fila, "Respon");
            label15  =new Label(15  ,fila, "UsoMat");
            label16  =new Label(16  ,fila, "OrdenC");
            label17  =new Label(17  ,fila, "Monto");
            label18  =new Label(18  ,fila, "FechaE");
            label19  =new Label(19  ,fila, "FechaV");
            label20  =new Label(20  ,fila, "CodOper");
            label21  =new Label(21  ,fila, "UsoInterno");
            label22  =new Label(22  ,fila, "Notas1");
            label23  =new Label(23  ,fila, "Notas2");
            label24  =new Label(24  ,fila, "Notas3");
            label25  =new Label(25  ,fila, "Notas4");
            label26  =new Label(26  ,fila, "Notas5");
            label27  =new Label(27  ,fila, "Notas6");
            label28  =new Label(28  ,fila, "Notas7");
            label29  =new Label(29  ,fila, "Notas8");
            label30  =new Label(30  ,fila, "Notas9");
            label31  =new Label(31  ,fila, "Notas10");

            sheet4.addCell(label);
            sheet4.addCell(label1);
            sheet4.addCell(label2);
            sheet4.addCell(label3);
            sheet4.addCell(label4);
            sheet4.addCell(label5);
            sheet4.addCell(label6);
            sheet4.addCell(label7);
            sheet4.addCell(label8);
            sheet4.addCell(label9);
            sheet4.addCell(label10);
            sheet4.addCell(label11);
            sheet4.addCell(label12);
            sheet4.addCell(label13);
            sheet4.addCell(label14);
            sheet4.addCell(label15);
            sheet4.addCell(label16);
            sheet4.addCell(label17);
            sheet4.addCell(label18);
            sheet4.addCell(label19);
            sheet4.addCell(label20);
            sheet4.addCell(label21);
            sheet4.addCell(label22);
            sheet4.addCell(label23);
            sheet4.addCell(label24);
            sheet4.addCell(label25);
            sheet4.addCell(label26);
            sheet4.addCell(label27);
            sheet4.addCell(label28);
            sheet4.addCell(label29);
            sheet4.addCell(label30);
            sheet4.addCell(label31);

        } catch (Exception t) {
        }
        fila++;
        currentDate=currentDate.replace("-","");
        c.moveToFirst();
        if (fila != 0) {
            if (c.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
                do {
                    try {
                        label   =new Label(0,fila,"00000");
                        label1  =new Label(1  ,fila,"R");
                        label2  =new Label(2 ,fila,currentDate);
                        num1  =new Number(3,fila,fila,entero);
                        label4  =new Label(4,fila,"");
                        label5  =new Label(5 ,fila,"SERVIDOR");
                        label6  =new Label(6,fila,"001");
                        label7  =new Label(7,fila,"VEANA01");
                        label8  =new Label(8  ,fila,"");
                        num2     =new Number(9 ,fila,0,entero);
                        label10  =new Label(10,fila,"");
                        label11  =new Label(11  ,fila,"");
                        label12  =new Label(12,fila,"");
                        label13  =new Label(13  ,fila,"TRANSACCION EN ESPERA");
                        label14  =new Label(  14,fila,"");
                        label15  =new Label(15  ,fila,"");
                        label16  =new Label(16  ,fila,"");
                        num3     =new Number(17,fila,0,decimal);
                        label17  =new Label(18  ,fila,"");
                        label18  =new Label(19,fila,"");
                        label19  =new Label(20  ,fila,"");
                        num4  =new Number(  21,fila,0,entero);
                        label34  =new Label(22  ,fila,"");
                        label37  =new Label(23,fila,"");
                        label40  =new Label(24  ,fila,"");
                        label41  =new Label(25,fila,"");
                        label42  =new Label(26  ,fila,"");
                        label43  =new Label(27  ,fila,"");
                        label44  =new Label(28  ,fila,"");
                        label45  =new Label(29  ,fila,"");
                        label46  =new Label(30  ,fila,"");
                        label47  =new Label(31  ,fila,"");



                        sheet4.addCell(label);
                        sheet4.addCell(label1);
                        sheet4.addCell(label2);
                        sheet4.addCell(num1);
                        sheet4.addCell(label4);
                        sheet4.addCell(label5);
                        sheet4.addCell(label6);
                        sheet4.addCell(label7);
                        sheet4.addCell(label8);
                        sheet4.addCell(num2);
                        sheet4.addCell(label10);
                        sheet4.addCell(label11);
                        sheet4.addCell(label12);
                        sheet4.addCell(label13);
                        sheet4.addCell(label14);
                        sheet4.addCell(label15);
                        sheet4.addCell(label16);
                        sheet4.addCell(num3);
                        sheet4.addCell(label17);
                        sheet4.addCell(label18);
                        sheet4.addCell(label19);
                        sheet4.addCell(num4);
                        sheet4.addCell(label34);
                        sheet4.addCell(label37);
                        sheet4.addCell(label40);
                        sheet4.addCell(label41);
                        sheet4.addCell(label42);
                        sheet4.addCell(label43);
                        sheet4.addCell(label44);
                        sheet4.addCell(label45);
                        sheet4.addCell(label46);
                        sheet4.addCell(label47);





                    } catch (Exception u) {
                    }
                    fila++;
                } while (c.moveToNext());
            }
        }
        try {
            sheet5 = workbook2.createSheet("SAITEMOPI", 1);
        } catch (Exception i) {

        }
        fila=0;
        try{

            label   =new Label(0,fila,"CodSucu");
            label1  =new Label(1  ,fila,"TipoOpI");
            label2  =new Label(2 ,fila,"NumeroD");
            label3  =new Label(3,fila,"NroLinea");
            label4  =new Label(4  ,fila,"NroLineaC");
            label5  =new Label(5 ,fila,"Status");
            label6  =new Label(6,fila,"CodItem");
            label7  =new Label(7,fila,"CodUbic");
            label8  =new Label(8  ,fila,"CodUbic2");
            label9  =new Label(9 ,fila,"Descrip1");
            label10 =new Label(10,fila,"Descrip2");
            label11  =new Label(11  ,fila,"Descrip3");
            label12  =new Label(12,fila,"Descrip4");
            label13  =new Label(13  ,fila,"Descrip5");
            label14  =new Label(  14,fila,"Descrip6");
            label15  =new Label(15  ,fila,"Descrip7");
            label16  =new Label(  16,fila,"Descrip8");
            label17  =new Label(17  ,fila,"Descrip9");
            label18  =new Label(  18,fila,"Descrip10");
            label19  =new Label(19  ,fila,"Refere");
            label20  =new Label(  20,fila,"Signo");
            label21  =new Label(21  ,fila,"Tara");
            label22  =new Label(  22,fila,"CantidadD");
            label23  =new Label(23  ,fila,"Cantidad");
            label24  =new Label(  24,fila,"CantidadO");
            label25  =new Label(25  ,fila,"ExistAntU");
            label26  =new Label(  26,fila,"ExistAnt");
            label27  =new Label(27  ,fila,"ExistAntU2");
            label28  =new Label(28  ,fila,"ExistAnt2");
            label29  =new Label(29  ,fila,"CantidadU");
            label30  =new Label(30  ,fila,"CantidadA");
            label31  =new Label(  31,fila,"CantidadUA");
            label32  =new Label(32  ,fila,"Costo");
            label33  =new Label(  33,fila,"Descto");
            label34  =new Label(34  ,fila,"CodMeca");
            label35  =new Label(  35,fila,"TotalItem");
            label36  =new Label(36  ,fila,"NroUnicoL");
            label37  =new Label(  37,fila,"NroLote");
            label38  =new Label(38  ,fila,"Precio");
            label39  =new Label(  39,fila,"PrecioU");
            label40  =new Label(40  ,fila,"FechaE");
            label41  =new Label(  41,fila,"FechaL");
            label42  =new Label(42  ,fila,"FechaV");
            label43  =new Label(43  ,fila,"EsServ");
            label44  =new Label(  44,fila,"EsUnid");
            label45  =new Label(45  ,fila,"EsFreeP");
            label46  =new Label(  46,fila,"EsPesa");
            label47  =new Label(47  ,fila,"EsExento");
            label48  =new Label(  48,fila,"UsaServ");
            label49  =new Label(49  ,fila,"DEsLote");
            label50  =new Label(  50,fila,"DEsSeri");
            sheet5.addCell(label);
            sheet5.addCell(label1);
            sheet5.addCell(label2);
            sheet5.addCell(label3);
            sheet5.addCell(label4);
            sheet5.addCell(label5);
            sheet5.addCell(label6);
            sheet5.addCell(label7);
            sheet5.addCell(label8);
            sheet5.addCell(label9);
            sheet5.addCell(label10);
            sheet5.addCell(label11);
            sheet5.addCell(label12);
            sheet5.addCell(label13);
            sheet5.addCell(label14);
            sheet5.addCell(label15);
            sheet5.addCell(label16);
            sheet5.addCell(label17);
            sheet5.addCell(label18);
            sheet5.addCell(label19);
            sheet5.addCell(label20);
            sheet5.addCell(label21);
            sheet5.addCell(label22);
            sheet5.addCell(label23);
            sheet5.addCell(label24);
            sheet5.addCell(label25);
            sheet5.addCell(label26);
            sheet5.addCell(label27);
            sheet5.addCell(label28);
            sheet5.addCell(label29);
            sheet5.addCell(label30);
            sheet5.addCell(label31);
            sheet5.addCell(label32);
            sheet5.addCell(label33);
            sheet5.addCell(label34);
            sheet5.addCell(label35);
            sheet5.addCell(label36);
            sheet5.addCell(label37);
            sheet5.addCell(label38);
            sheet5.addCell(label39);
            sheet5.addCell(label40);
            sheet5.addCell(label41);
            sheet5.addCell(label42);
            sheet5.addCell(label43);
            sheet5.addCell(label44);
            sheet5.addCell(label45);
            sheet5.addCell(label46);
            sheet5.addCell(label47);
            sheet5.addCell(label48);
            sheet5.addCell(label49);
            sheet5.addCell(label50);

        }catch (Exception o)
        {

        }
        fila++;
        currentDate=currentDate.replace("-","");
        c.moveToFirst();
        if (fila != 0) {
            if (c.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
                do {
                    try {
                        label   =new Label(0,fila,"00000");
                        label1  =new Label(1  ,fila,"R");
                        label2  =new Label(2 ,fila,currentDate);
                        num1  =new Number(3,fila,fila,entero);
                        num2  =new Number(4,fila,0,entero);
                        label5  =new Label(5 ,fila,"");
                        label6  =new Label(6,fila,c.getString(1));
                        label7  =new Label(7,fila,"VEANA01");
                        label8  =new Label(8  ,fila,"");
                        label9  =new Label(9 ,fila,c.getString(2));
                        label10 =new Label(10,fila,c.getString(4));
                        label11  =new Label(11  ,fila,c.getString(5));
                        label12  =new Label(12,fila,"");
                        label13  =new Label(13  ,fila,"");
                        label14  =new Label(  14,fila,"");
                        label15  =new Label(15  ,fila,"");
                        label16  =new Label(  16,fila,"");
                        label17  =new Label(17  ,fila,"");
                        label18  =new Label(  18,fila,"");
                        label19  =new Label(19  ,fila,c.getString(1));
                        num3  =new Number(  20,fila,0,entero);
                        num4  =new Number(21  ,fila,0,decimal);
                        num5  =new Number(  22,fila,0,decimal);
                        num6  =new Number(23  ,fila,c.getInt(11),decimal);
                        num7  =new Number(  24,fila,0,decimal);
                        num8  =new Number(25  ,fila,0,decimal);
                        num9  =new Number(26  ,fila,0,decimal);
                        num10  =new Number(27  ,fila,0,decimal);
                        num11  =new Number(28  ,fila,0,decimal);
                        num12  =new Number(29  ,fila,0,decimal);
                        num13  =new Number(30  ,fila,0,decimal);
                        num14  =new Number(31  ,fila,0,decimal);
                        num15  =new Number(32  ,fila,0,decimal);
                        num16  =new Number(33  ,fila,0,decimal);
                        label34  =new Label(34  ,fila,"");
                        num17  =new Number(35  ,fila,0,decimal);
                        num18  =new Number(36  ,fila,0,entero);
                        label37  =new Label(  37,fila,"");
                        num19  =new Number(38  ,fila,0,decimal);
                        num20  =new Number(39  ,fila,0,decimal);
                        label40  =new Label(40  ,fila,"");
                        label41  =new Label(  41,fila,"");
                        label42  =new Label(42  ,fila,"");
                        num21  =new Number(43  ,fila,0,entero);
                        num22  =new Number(44  ,fila,0,entero);
                        num23  =new Number(45  ,fila,0,entero);
                        num24  =new Number(46  ,fila,0,entero);
                        num25  =new Number(47  ,fila,0,entero);
                        num26  =new Number(48  ,fila,0,entero);
                        num27  =new Number(49  ,fila,0,entero);
                        num28  =new Number(50  ,fila,0,entero);

                        sheet5.addCell(label);
                        sheet5.addCell(label1);
                        sheet5.addCell(label2);
                        sheet5.addCell(num1);
                        sheet5.addCell(num2);
                        sheet5.addCell(label5);
                        sheet5.addCell(label6);
                        sheet5.addCell(label7);
                        sheet5.addCell(label8);
                        sheet5.addCell(label9);
                        sheet5.addCell(label10);
                        sheet5.addCell(label11);
                        sheet5.addCell(label12);
                        sheet5.addCell(label13);
                        sheet5.addCell(label14);
                        sheet5.addCell(label15);
                        sheet5.addCell(label16);
                        sheet5.addCell(label17);
                        sheet5.addCell(label18);
                        sheet5.addCell(label19);
                        sheet5.addCell(num3);
                        sheet5.addCell(num4);
                        sheet5.addCell(num5);
                        sheet5.addCell(num6);
                        sheet5.addCell(num7);
                        sheet5.addCell(num8);
                        sheet5.addCell(num9);
                        sheet5.addCell(num10);
                        sheet5.addCell(num11);
                        sheet5.addCell(num12);
                        sheet5.addCell(num13);
                        sheet5.addCell(num14);
                        sheet5.addCell(num15);
                        sheet5.addCell(num16);
                        sheet5.addCell(label34);
                        sheet5.addCell(num17);
                        sheet5.addCell(num18);
                        sheet5.addCell(label37);
                        sheet5.addCell(num19);
                        sheet5.addCell(num20);
                        sheet5.addCell(label40);
                        sheet5.addCell(label41);
                        sheet5.addCell(label42);
                        sheet5.addCell(num21);
                        sheet5.addCell(num22);
                        sheet5.addCell(num23);
                        sheet5.addCell(num24);
                        sheet5.addCell(num25);
                        sheet5.addCell(num26);
                        sheet5.addCell(num27);
                        sheet5.addCell(num28);




                    } catch (Exception u) {
                    }
                    fila++;
                } while (c.moveToNext());
            }
        }


        try {


            workbook2.write();
            workbook2.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


          c.close();
        db.close();
        Toast.makeText(this, "EL DOCUMENTO DE CARGA SE HA CREADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
    }




}