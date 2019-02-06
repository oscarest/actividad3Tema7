package com.example.pc.actividad3tema7.Logic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pc.actividad3tema7.DataBaseManager.DB_SQLite;
import com.example.pc.actividad3tema7.DataBaseManager.Esquema;
import com.example.pc.actividad3tema7.Model.Producto;

import java.util.ArrayList;
import java.util.List;

public class LogicProducto {

    public static void insertarProducto(Context context, Producto p) {
        SQLiteDatabase conn = DB_SQLite.conectar(context, DB_SQLite.OPEN_MODE_WRITE);
        ContentValues content = new ContentValues();
        content.put(Esquema.Producto.COLUMN_NAME_NOMBRE, p.getNombre());
        content.put(Esquema.Producto.COLUMN_NAME_CANTIDAD, p.getCantidad());
        content.put(Esquema.Producto.COLUMN_NAME_IDSECCION, p.getIdSeccion());
        conn.insert(Esquema.Producto.TABLE_NAME, null, content);
        DB_SQLite.desconectar(conn);
    }

    public static void eliminarProducto(Context context, Producto p) {
        SQLiteDatabase conn = DB_SQLite.conectar(context, DB_SQLite.OPEN_MODE_WRITE);
        String sqlWhere = Esquema.Producto.COLUMN_NAME_NOMBRE + " LIKE '" + p.getNombre() + "'";
        conn.delete(Esquema.Producto.TABLE_NAME, sqlWhere, null);
        DB_SQLite.desconectar(conn);
    }

    public static void editarProducto(Context context, Producto p) {
        SQLiteDatabase conn = DB_SQLite.conectar(context, DB_SQLite.OPEN_MODE_WRITE);
        ContentValues content = new ContentValues();
        content.put(Esquema.Producto.COLUMN_NAME_NOMBRE, p.getNombre());
        content.put(Esquema.Producto.COLUMN_NAME_CANTIDAD, p.getCantidad());
        content.put(Esquema.Producto.COLUMN_NAME_IDSECCION, p.getIdSeccion());
        String sqlWhere = Esquema.Producto.COLUMN_NAME_NOMBRE + " LIKE '" + p.getNombre() + "'";
        conn.update(Esquema.Producto.TABLE_NAME, content, sqlWhere, null);
        DB_SQLite.desconectar(conn);
    }

    public static Producto buscarProducto(Context context, Producto p) {
        Producto elto = p;

        String[] sqlFields = {Esquema.Producto.COLUMN_NAME_ID, Esquema.Producto.COLUMN_NAME_NOMBRE, Esquema.Producto.COLUMN_NAME_CANTIDAD, Esquema.Producto.COLUMN_NAME_IDSECCION};
        String sqlWhere = Esquema.Producto.COLUMN_NAME_NOMBRE + " LIKE '" + p.getNombre() + "'";

        SQLiteDatabase conn = DB_SQLite.conectar(context, DB_SQLite.OPEN_MODE_READ);
        Cursor cursor = conn.query(Esquema.Producto.TABLE_NAME, sqlFields, sqlWhere, null, null, null, null);

        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            Long dataId = cursor.getLong(cursor.getColumnIndex(Esquema.Producto.COLUMN_NAME_ID));
            String dataNombre = cursor.getString(cursor.getColumnIndex(Esquema.Producto.COLUMN_NAME_NOMBRE));
            Integer dataCantidad = cursor.getInt(cursor.getColumnIndex(Esquema.Producto.COLUMN_NAME_CANTIDAD));
            Long dataIdSeccion = cursor.getLong(cursor.getColumnIndex(Esquema.Producto.COLUMN_NAME_IDSECCION));
            elto = new Producto(dataId, dataNombre, dataCantidad, dataIdSeccion);
        }
        cursor.close();
        DB_SQLite.desconectar(conn);

        return elto;
    }

    public static List listaProductos(Context context) {
        List lst = new ArrayList<>();

        String[] sqlFields = {Esquema.Producto.COLUMN_NAME_ID, Esquema.Producto.COLUMN_NAME_NOMBRE, Esquema.Producto.COLUMN_NAME_CANTIDAD, Esquema.Producto.COLUMN_NAME_IDSECCION};
        String sqlWhere = "";
        String sqlOrderBy = Esquema.Producto.COLUMN_NAME_NOMBRE + " ASC";

        SQLiteDatabase conn = DB_SQLite.conectar(context, DB_SQLite.OPEN_MODE_READ);
        Cursor cursor = conn.query(Esquema.Producto.TABLE_NAME, sqlFields, sqlWhere, null, null, null, sqlOrderBy);
        if (cursor.getCount() == 0) {
            lst = null;
        } else {
            cursor.moveToFirst();
            do {
                Long dataId = cursor.getLong(cursor.getColumnIndex(Esquema.Producto.COLUMN_NAME_ID));
                String dataNombre = cursor.getString(cursor.getColumnIndex(Esquema.Producto.COLUMN_NAME_NOMBRE));
                Integer dataCantidad = cursor.getInt(cursor.getColumnIndex(Esquema.Producto.COLUMN_NAME_CANTIDAD));
                Long dataIdSeccion = cursor.getLong(cursor.getColumnIndex(Esquema.Producto.COLUMN_NAME_IDSECCION));
                lst.add(new Producto(dataId, dataNombre, dataCantidad, dataIdSeccion));
            } while (cursor.moveToNext());
        }
        cursor.close();
        DB_SQLite.desconectar(conn);

        return lst;
    }

}
