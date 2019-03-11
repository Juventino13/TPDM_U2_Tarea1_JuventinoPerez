package com.example.juvetino_asus.tpmd_u2_t1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseProyectoCivil extends SQLiteOpenHelper {



    public BaseProyectoCivil( Context context, String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE PROYECTOS(IDPROYECTO INTEGER PRIMARY KEY AUTOINCREMENT ," +
                " DESCRIPCION VARCHAR(200) NOT NULL," +
                "UBICACION VARCHAR (200), " +
                "FECHA DATE," +
                " PRESUPUESTO FLOAT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

public Cursor viewData(){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM PROYECTOS";
        Cursor cursor = db.rawQuery(query,null);
return cursor;
}

}
