
    package com.example.juvetino_asus.tpmd_u2_t1;


    import android.content.Intent;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.Toast;

    public class Main4Activity extends AppCompatActivity {
        EditText desc, ubi, fecha, presupuesto;

        Button eliminar, actualizar, regresar;
        Proyecto proyecto;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main4);

            desc = findViewById(R.id.editDescripcion);
            ubi = findViewById(R.id.editUbicacion);
            fecha = findViewById(R.id.editFecha);
            presupuesto = findViewById(R.id.editPresupuesto);
            eliminar = findViewById(R.id.btnEliminar);
            actualizar = findViewById(R.id.btnActualizar);
            regresar = findViewById(R.id.btnRegresar);
            proyecto=new Proyecto(this);

            eliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Proyecto proyect=new Proyecto(Main4Activity.this);
                    if (proyect.eliminar(proyecto)){
                        Toast.makeText(Main4Activity.this,"ELIMINADO",Toast.LENGTH_LONG).show();
                        Intent i=new Intent(Main4Activity.this,MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else{
                        Toast.makeText(Main4Activity.this, "¡ERROR!", Toast.LENGTH_LONG).show();
                    }
                }
            });

            actualizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Proyecto proyect=new Proyecto(Main4Activity.this);
                    if (proyect.actualizar(new Proyecto(proyecto.getId(),desc.getText().toString(),ubi.getText().toString(),fecha.getText().toString(),Float.parseFloat(presupuesto.getText().toString())))){
                        Toast.makeText(Main4Activity.this,"ACTUALIZADO",Toast.LENGTH_LONG).show();
                        Intent i=new Intent(Main4Activity.this,MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else{
                        Toast.makeText(Main4Activity.this, "Ocurrio un error", Toast.LENGTH_LONG).show();
                    }
                }
            });

            regresar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(Main4Activity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
            });

        }
        protected void onStart(){
            super.onStart();
            Bundle extras=getIntent().getExtras();
            proyecto=new Proyecto(extras.getInt("id"),extras.getString("des"),extras.getString("ubica"),extras.getString("fecha"),extras.getFloat("presupuesto"));
            desc.setText(proyecto.getDescripcion());
            ubi.setText(proyecto.getUbicacion());
            fecha.setText(proyecto.getFecha());
            presupuesto.setText(proyecto.getPresupuesto()+"");
        }
    }