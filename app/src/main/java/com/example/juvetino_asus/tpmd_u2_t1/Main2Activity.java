package com.example.juvetino_asus.tpmd_u2_t1;

import android.content.Intent;
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
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {



    EditText descripcion, ubicacion,fecha, presupuesto;
    Button  btninsertar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        btninsertar = findViewById(R.id.guardar);
        descripcion = findViewById(R.id.descripcion);
        ubicacion= findViewById(R.id.ubicacion);
        fecha = findViewById(R.id.fecha);
        presupuesto = findViewById(R.id.presupuesto);


        btninsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Proyecto proyecto=new Proyecto(Main2Activity.this);
                if (proyecto.insertar(new Proyecto(1,descripcion.getText().toString(),ubicacion.getText().toString(),fecha.getText().toString(),Float.parseFloat(presupuesto.getText().toString())))){
                    Toast.makeText(Main2Activity.this,"GUARDADO",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(Main2Activity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else{
                          Toast.makeText(Main2Activity.this, "ERROR", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

//FORMA DE SUSTITUIR
    /* private void insercion() {
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
    }*/


/*
    private void mensaje(String titulo, String Mensaje) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);

        a.setTitle(titulo)
                .setMessage(Mensaje)
                .setPositiveButton("Aceptar",null)
                .show();
    }

    MENSAJE
*/
}
