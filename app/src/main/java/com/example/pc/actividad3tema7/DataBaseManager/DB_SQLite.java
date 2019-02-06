package com.example.pc.actividad3tema7.DataBaseManager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_SQLite extends SQLiteOpenHelper {

    public static final int OPEN_MODE_READ = 1;
    public static final int OPEN_MODE_WRITE = 2;

    private static final String DATABASE_NAME = "tienda.sqlite";
    private static final int DATABASE_VERSION = 1;

    private static final String sql_D_01 ="DROP TABLE IF EXISTS " + Esquema.Seccion.TABLE_NAME;

    private static final String sql_D_02 ="DROP TABLE IF EXISTS " + Esquema.Producto.TABLE_NAME;

    private static final String sql_C_01 = "CREATE TABLE " + Esquema.Seccion.TABLE_NAME + " (" +
            Esquema.Seccion.COLUMN_NAME_ID + " " + Esquema.Seccion.COLUMN_TYPE_ID + " PRIMARY KEY AUTOINCREMENT, " +
            Esquema.Seccion.COLUMN_NAME_DESCRIPCION + " " + Esquema.Seccion.COLUMN_TYPE_DESCRIPCION + ", " +
            Esquema.Seccion.COLUMN_NAME_MINSTOCK + " " + Esquema.Seccion.COLUMN_TYPE_MINSTOCK + ", " +
            Esquema.Seccion.COLUMN_NAME_MAXSTOCK + " " + Esquema.Seccion.COLUMN_TYPE_MAXSTOCK + ")";

    private static final String sql_C_02 = "CREATE TABLE " + Esquema.Producto.TABLE_NAME + " (" +
            Esquema.Producto.COLUMN_NAME_ID + " " + Esquema.Producto.COLUMN_TYPE_ID + " PRIMARY KEY AUTOINCREMENT, " +
            Esquema.Producto.COLUMN_NAME_NOMBRE + " " + Esquema.Producto.COLUMN_TYPE_NOMBRE + ", " +
            Esquema.Producto.COLUMN_NAME_CANTIDAD + " " + Esquema.Producto.COLUMN_TYPE_CANTIDAD + ", " +
            Esquema.Producto.COLUMN_NAME_IDSECCION + " " + Esquema.Producto.COLUMN_TYPE_IDSECCION + ")";
    // " FOREIGN KEY (" + Esquema.Producto.COLUMN_NAME_ID + ") REFERENCES " + Esquema.Seccion.TABLE_NAME + "(" + Esquema.Seccion.COLUMN_NAME_ID + ")" +
    // " ON UPDATE CASCADE ON DELETE CASCADE)";


    public DB_SQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(sql_C_01);
        db.execSQL(sql_C_02);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(sql_D_01);
        db.execSQL(sql_D_02);
        onCreate(db);
    }

    public static SQLiteDatabase conectar(Context context, int open_mode) {
        DB_SQLite db = new DB_SQLite(context);
        SQLiteDatabase conn;
        if (open_mode == OPEN_MODE_READ)
            conn = db.getWritableDatabase();
        else
            conn = db.getReadableDatabase();
        return conn;
    }

    public static void desconectar(SQLiteDatabase conn) {
        conn.close();
    }

}
