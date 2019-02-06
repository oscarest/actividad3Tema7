package com.example.pc.actividad3tema7.Logic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pc.actividad3tema7.DataBaseManager.DB_SQLite;
import com.example.pc.actividad3tema7.DataBaseManager.Esquema;
import com.example.pc.actividad3tema7.Model.Seccion;

import java.util.ArrayList;
import java.util.List;

public class LogicSeccion {


    public static void insertarSeccion(Context context, Seccion s) {
        SQLiteDatabase conn = DB_SQLite.conectar(context, DB_SQLite.OPEN_MODE_WRITE);
        ContentValues content = new ContentValues();
        content.put(Esquema.Seccion.COLUMN_NAME_DESCRIPCION, s.getDescripcion());
        content.put(Esquema.Seccion.COLUMN_NAME_MINSTOCK, s.getMinstock());
        content.put(Esquema.Seccion.COLUMN_NAME_MAXSTOCK, s.getMaxStock());
        conn.insert(Esquema.Seccion.TABLE_NAME, null, content);
        DB_SQLite.desconectar(conn);
    }

    public static void eliminarSeccion(Context context, Seccion s) {
        SQLiteDatabase conn = DB_SQLite.conectar(context, DB_SQLite.OPEN_MODE_WRITE);
        String sqlWhere = Esquema.Seccion.COLUMN_NAME_DESCRIPCION + " LIKE '" + s.getDescripcion() + "'";
        conn.delete(Esquema.Seccion.TABLE_NAME, sqlWhere, null);
        DB_SQLite.desconectar(conn);
    }

    public static void editarSeccion(Context context, Seccion s) {
        SQLiteDatabase conn = DB_SQLite.conectar(context, DB_SQLite.OPEN_MODE_WRITE);
        ContentValues content = new ContentValues();
        content.put(Esquema.Seccion.COLUMN_NAME_DESCRIPCION, s.getDescripcion());
        content.put(Esquema.Seccion.COLUMN_NAME_MINSTOCK, s.getMinstock());
        content.put(Esquema.Seccion.COLUMN_NAME_MAXSTOCK, s.getMaxStock());
        String sqlWhere = Esquema.Seccion.COLUMN_NAME_DESCRIPCION + " LIKE '" + s.getDescripcion() + "'";
        conn.update(Esquema.Seccion.TABLE_NAME, content, sqlWhere, null);
        DB_SQLite.desconectar(conn);
    }

    public static Seccion buscarSeccion(Context context, Seccion s) {
        Seccion elto = s;

        String[] sqlFields = {Esquema.Seccion.COLUMN_NAME_ID, Esquema.Seccion.COLUMN_NAME_DESCRIPCION, Esquema.Seccion.COLUMN_NAME_MINSTOCK, Esquema.Seccion.COLUMN_NAME_MAXSTOCK};
        String sqlWhere = Esquema.Seccion.COLUMN_NAME_DESCRIPCION + " LIKE '" + s.getDescripcion() + "'";

        SQLiteDatabase conn = DB_SQLite.conectar(context, DB_SQLite.OPEN_MODE_READ);
        Cursor cursor = conn.query(Esquema.Seccion.TABLE_NAME, sqlFields, sqlWhere, null, null, null, null);

        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            Long dataId = cursor.getLong(cursor.getColumnIndex(Esquema.Seccion.COLUMN_NAME_ID));
            String dataDescripcion = cursor.getString(cursor.getColumnIndex(Esquema.Seccion.COLUMN_NAME_DESCRIPCION));
            Integer dataMinStock = cursor.getInt(cursor.getColumnIndex(Esquema.Seccion.COLUMN_NAME_MINSTOCK));
            Integer dataMaxStock = cursor.getInt(cursor.getColumnIndex(Esquema.Seccion.COLUMN_NAME_MAXSTOCK));
            elto = new Seccion(dataId, dataDescripcion, dataMinStock, dataMaxStock);
        }
        cursor.close();
        DB_SQLite.desconectar(conn);

        return elto;
    }

    public static List listaSecciones(Context context) {
        List lst = new ArrayList<>();

        String[] sqlFields = {Esquema.Seccion.COLUMN_NAME_ID, Esquema.Seccion.COLUMN_NAME_DESCRIPCION, Esquema.Seccion.COLUMN_NAME_MINSTOCK, Esquema.Seccion.COLUMN_NAME_MAXSTOCK};
        String sqlWhere = "";
        String sqlOrderBy = Esquema.Seccion.COLUMN_NAME_DESCRIPCION + " ASC";

        SQLiteDatabase conn = DB_SQLite.conectar(context, DB_SQLite.OPEN_MODE_READ);
        Cursor cursor = conn.query(Esquema.Seccion.TABLE_NAME, sqlFields, sqlWhere, null, null, null, sqlOrderBy);
        if (cursor.getCount() == 0) {
            lst = null;
        } else {
            cursor.moveToFirst();
            do {
                Long dataId = cursor.getLong(cursor.getColumnIndex(Esquema.Seccion.COLUMN_NAME_ID));
                String dataDescripcion = cursor.getString(cursor.getColumnIndex(Esquema.Seccion.COLUMN_NAME_DESCRIPCION));
                Integer dataMinStock = cursor.getInt(cursor.getColumnIndex(Esquema.Seccion.COLUMN_NAME_MINSTOCK));
                Integer dataMaxStock = cursor.getInt(cursor.getColumnIndex(Esquema.Seccion.COLUMN_NAME_MAXSTOCK));
                lst.add(new Seccion(dataId, dataDescripcion, dataMinStock, dataMaxStock));
            } while (cursor.moveToNext());
        }
        cursor.close();
        DB_SQLite.desconectar(conn);

        return lst;
    }

    public static int totalSecciones(Context context) {
        SQLiteDatabase conn = DB_SQLite.conectar(context, DB_SQLite.OPEN_MODE_READ);
        Cursor cursor = conn.query(Esquema.Seccion.TABLE_NAME, null, null, null, null, null, null);
        return (cursor == null ? 0 : cursor.getCount());
    }

}