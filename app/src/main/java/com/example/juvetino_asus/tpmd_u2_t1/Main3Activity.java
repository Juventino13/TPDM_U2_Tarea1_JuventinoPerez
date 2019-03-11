package com.example.juvetino_asus.tpmd_u2_t1;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
BaseProyectoCivil db;
    Button eliminar;
    EditText descripcion, ubicacion,fecha, presupuesto;
    TextView resultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
//        eliminar = findViewById(R.id.eliminar);
        db = new BaseProyectoCivil(this, "Basecita",null,1);

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pedirID("eliminar"); //Este método se usa tanto para eliminar como para actualizar
            }
        });
    }

    private void  consultarID(String tipoAccion, String id){
        try{
            SQLiteDatabase consultar = db.getReadableDatabase();
            String SQL = "SELECT * FROM PERSONA WHERE IDPERSONA="+id;

            Cursor respuesta = consultar.rawQuery(SQL,null);
            if(respuesta.moveToFirst()){
                String nom = respuesta.getString(1);
                String dom = respuesta.getString(2);

                confirmarAccion(tipoAccion, id, nom, dom); //Se invoca al método para confirmar acción

            } else {
                mensaje("ERROR", "No se encontró el ID ["+id+"] buscado para "+tipoAccion );
            }
            consultar.close();
        }catch (SQLiteException e){
            mensaje("ERROR", e.getMessage());
        }
    }

    private void confirmarAccion(final String tipoAccion, final String id, String nom, String dom){
        AlertDialog.Builder confirmar = new AlertDialog.Builder(this);

    View contenido = getLayoutInflater().inflate(R.layout.layout_actualizar,null);
        final EditText nombreEditar = contenido.findViewById(R.id.editnombre);
        final EditText domicilioEditar = contenido.findViewById(R.id.editdomicilio);

        confirmar.setTitle("Proceso de "+tipoAccion);

        if(tipoAccion.startsWith("eliminar")){
            String mensaje = "¿Está seguro que desea eliminar a "+nom+"\nCon domicilio en: "+dom+"?";
            confirmar.setMessage(mensaje);
        } else {

            confirmar.setView(contenido); //NO DEBE LLEVAR SETMESSAGE SINO SETVIEW por tener muchos views
            nombreEditar.setText(nom); //SE ASINGA EL VALOR AL CAMPO NOMBRE
            domicilioEditar.setText(dom); //SE ASIGNA EL DOMICILIO AL CAMPO DOMICILIEDITAR
        }
        confirmar.setNegativeButton("Cancelar",null)
                .setPositiveButton("Aplicar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(tipoAccion.startsWith("eliminar")){
                            eliminar(id);
                            dialog.dismiss();
                        } else {

                            actualizar(id,nombreEditar.getText().toString(), domicilioEditar.getText().toString());
                            dialog.dismiss();
                        }
                    }
                }).show();
    }

    private void actualizar(String id, String nomb, String dom){
        try{
            SQLiteDatabase actualiz = base.getWritableDatabase();
            String SQL = "UPDATE PERSONA SET NOMBRE='"+nomb+"', DOMICILIO='"+dom+"' WHERE IDPERSONA="+id;
            actualiz.execSQL(SQL);
            actualiz.close();
            mensaje("EXITO!","SE ACTUALIZO!");
            consultar();
        }catch (SQLiteException e){
            mensaje("Error de insercion",e.getMessage());
        }
    }
    private void pedirID(final String tipoAccion){

        AlertDialog.Builder dialogoPedirId = new AlertDialog.Builder(this); //Se construye
        final EditText idbuscar =new EditText(this);

        idbuscar.setInputType(InputType.TYPE_CLASS_NUMBER); //Se especializa para capturar numeros enteros
        idbuscar.setHint("Valor DEBE ser mayor de 0");

        dialogoPedirId.setTitle("Atención").setMessage("Escriba el ID a "+tipoAccion+": ")
                .setView(idbuscar).setNegativeButton("Cancelar",null)
                .setPositiveButton("Buscar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(idbuscar.getText().toString().isEmpty()){ //Si deja vacío el campo de texto
                            mensaje("Error", "No escribiste un ID a buscar"); //se reutiliza el método ID
                            return;
                        }
                        consultarID(tipoAccion, idbuscar.getText().toString()); //ID a buscar se lleva el tipoAccion (eliminar o actualizar) y el ID escrito para buscarlo
                        dialog.dismiss(); //para quitar el dialogo
                    }
                }).show();
    }

    private void mensaje(String titulo, String Mensaje) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);

        a.setTitle(titulo)
                .setMessage(Mensaje)
                .setPositiveButton("Aceptar",null)
                .show();
    }

}
