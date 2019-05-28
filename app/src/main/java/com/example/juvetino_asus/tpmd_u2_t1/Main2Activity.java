package com.example.juvetino_asus.tpmd_u2_t1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    BaseProyectoCivil base;
    Button  insertar;
    EditText descripcion, ubicacion,fecha, presupuesto;
    TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        base = new BaseProyectoCivil(this, "basesota",null,1);

        insertar = findViewById(R.id.guardar);
        descripcion = findViewById(R.id.descripcion);
        ubicacion= findViewById(R.id.ubicacion);
        fecha = findViewById(R.id.fecha);
        presupuesto = findViewById(R.id.presupuesto);
        insertar = findViewById(R.id.guardar);

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insercion();

            }
        });

    }


    private void insercion() {
        try{
            SQLiteDatabase inser = base.getWritableDatabase();
            String SQL = "INSERT INTO PROYECTOS VALUES(NULL,'%1','%2','%3','%4')";

            SQL = SQL.replace("%1",descripcion.getText().toString());
            SQL = SQL.replace("%2",ubicacion.getText().toString());
            SQL = SQL.replace("%3",fecha.getText().toString());
            SQL = SQL.replace("%4",presupuesto.getText().toString());
            inser.execSQL(SQL);

            inser.close();
            descripcion.setText("");
            presupuesto.setText("");
            fecha.setText("");
            presupuesto.setText("");

            mensaje("EXITO!","SE PUDO INSERTAR!");
        }catch (SQLiteException e){
            mensaje("Error de insercion",e.getMessage());
        }
    }

    private void mensaje(String titulo, String Mensaje) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);

        a.setTitle(titulo)
                .setMessage(Mensaje)
                .setPositiveButton("Aceptar",null)
                .show();
    }

}
