package com.example.juvetino_asus.tpmd_u2_t1;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView lista;
    Proyecto[] listaProyectoCivil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista=findViewById(R.id.listaDesplegable);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alerta=new AlertDialog.Builder(MainActivity.this);
                alerta.setTitle("Alert")
                        .setMessage("Editar Proyecto")

                        .setPositiveButton("Si",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent datos=new Intent(MainActivity.this,Main4Activity.class);
                                datos.putExtra("ID",listaProyectoCivil[position].getId());
                                datos.putExtra("DESCRIPCION",listaProyectoCivil[position].getDescripcion());
                                datos.putExtra("FECHA",listaProyectoCivil[position].getFecha());
                                datos.putExtra("UBICACION",listaProyectoCivil[position].getUbicacion());
                                datos.putExtra("PRESUPUESTO",listaProyectoCivil[position].getPresupuesto());

                                startActivity(datos);
                                finish();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        Proyecto proyecto=new Proyecto(this);
        Proyecto vec[]=proyecto.consultar();
        listaProyectoCivil=vec;
        String[] descripcion=null;

        if (vec==null){
            descripcion=new String[1];
            descripcion[0]="No hay proyectos registrados";
            lista.setEnabled(false);
        }
        else{
            descripcion=new String[vec.length];
            for (int i=0; i<vec.length; i++){
                Proyecto temperal=vec[i];
                descripcion[i]=temperal.getDescripcion();
            }


            lista.setEnabled(true);
        }
        ArrayAdapter<String> adapt=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,descripcion);
        lista.setAdapter(adapt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menudinamico,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.agregar:
                Intent insertar =new Intent(this,Main2Activity.class);
                startActivity(insertar);
                finish();
                break;
            case R.id.consultar:
                Intent consultar=new Intent(this,Main3Activity.class);
                startActivity(consultar);
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}

