package com.example.juvetino_asus.tpmd_u2_t1;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    EditText descripcion_ID, descripcion, ubicacion, fecha, presupuesto;
    Button consultar,regresar;
    Proyecto proyecto1,proyecto2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        descripcion_ID = findViewById(R.id.editID);
        consultar = findViewById(R.id.btnBuscar);
        descripcion = findViewById(R.id.editDescripcion);
        ubicacion = findViewById(R.id.editUbicacion);
        fecha = findViewById(R.id.editFecha);
        presupuesto = findViewById(R.id.editPresupuesto);
        regresar = findViewById(R.id.btnRegresar);
        proyecto1=new Proyecto(this);

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String busqueda=descripcion_ID.getText().toString();
                int id=0;
                try{
                    id=Integer.parseInt(busqueda);
                }
                catch(NumberFormatException e){
                    e.getMessage();
                }

                if (id==0){
                    proyecto2=proyecto1.consultar(busqueda);
                }
                else{
                    proyecto2=proyecto1.consultar(id);
                }

                if (proyecto2==null){
                    Toast.makeText(Main3Activity.this, "No existe registro", Toast.LENGTH_LONG).show();
                }
                else{
                    descripcion.setText(proyecto2.getDescripcion());
                    ubicacion.setText(proyecto2.getUbicacion());
                    fecha.setText(proyecto2.getFecha());
                    presupuesto.setText(proyecto2.getPresupuesto()+"");
                }
            }
        });



        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Main3Activity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}

