package com.example.juvetino_asus.tpmd_u2_t1;

import android.content.Intent;
import android.database.Cursor;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BaseProyectoCivil db;

ArrayList<String> listItem;

ArrayAdapter adapter;

ListView userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new BaseProyectoCivil(this,"basesota",null,1);

        listItem = new ArrayList<>();
        userList = findViewById(R.id.lista);


viewData();


    userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            String text = userList.getItemAtPosition(position).toString();
            Toast.makeText(MainActivity.this,""+text, Toast.LENGTH_SHORT).show();

        }
    });






    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menudinamico,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.agregar:
                Intent agregar = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(agregar);
                return true;
            case R.id.eliminar:
                Intent eli = new Intent(MainActivity.this, Main3Activity.class);
                startActivity(eli);
                return true;
            case R.id.regresar:
                finish();
                break;

        }
        return true;
    }



    private void viewData() {
        Cursor cursor = db.viewData();

        if (cursor.getCount()==0) {
            Toast.makeText(this, "No hay datos ", Toast.LENGTH_LONG).show();
        }else{
            while (cursor.moveToNext()){
                listItem.add(cursor.getString(1));

            }
            adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listItem);
            userList.setAdapter(adapter);
        }
    }



}