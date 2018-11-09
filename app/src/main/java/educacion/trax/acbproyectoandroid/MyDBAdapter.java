package educacion.trax.acbproyectoandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by jmalberola.
 */
public class MyDBAdapter {

    // Definiciones y constantes
    private static final String DATABASE_NAME = "clase.db";
    private static final String DATABASE_TABLE_PROFE = "profes";
    private static final String DATABASE_TABLE_ALUMNO = "alumnos";
    private static final String DATABASE_TABLE_ASIGNATURA = "asignaturas";
    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_CREATE_PROFE = "CREATE TABLE "+DATABASE_TABLE_PROFE+" (_id integer primary key autoincrement, nom text, edad integer,ciclo text,curso integer,despacho integer);";
    private static final String DATABASE_CREATE_ALUMNO = "CREATE TABLE "+DATABASE_TABLE_ALUMNO+" (_id integer primary key autoincrement, nom text, edad integer,ciclo text,curso integer,nota_media float);";
    private static final String DATABASE_CREATE_ASIGNATURA = "CREATE TABLE "+DATABASE_TABLE_ASIGNATURA+" (_id integer primary key autoincrement, nom text, horas integer);";
    private static final String DATABASE_DROP_PROFE = "DROP TABLE IF EXISTS "+DATABASE_TABLE_PROFE+";";
    private static final String DATABASE_DROP_ALUMNO = "DROP TABLE IF EXISTS "+DATABASE_TABLE_ALUMNO+";";
    private static final String DATABASE_DROP_ASIGNATURA = "DROP TABLE IF EXISTS "+DATABASE_TABLE_ASIGNATURA+";";



    // Contexto de la aplicación que usa la base de datos
    private final Context context;
    // Clase SQLiteOpenHelper para crear/actualizar la base de datos
    private MyDbHelper dbHelper;
    // Instancia de la base de datos
    private SQLiteDatabase db;

    public MyDBAdapter (Context c){
        context = c;
        dbHelper = new MyDbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        //OJO open();
    }

    public void open(){

        try{
            db = dbHelper.getWritableDatabase();
        }catch(SQLiteException e){
            db = dbHelper.getReadableDatabase();
        }

    }

    public boolean insertarProfe(String nom, int edad,String ciclo,int curso,String despacho){

        Cursor cursor = db.query(DATABASE_TABLE_PROFE,null,null,null,null,null,"ciclo");
        //Recorremos el cursor
        boolean result=true;
        if (cursor != null && cursor.moveToFirst()){
            do{
                String temp=cursor.getString(1);
                if (temp.equals(nom)==true){
                   result=false;
                }
            }while (cursor.moveToNext()&&result==true);
            if (result){
                //Creamos un nuevo registro de valores a insertar
                ContentValues newValues = new ContentValues();
                //Asignamos los valores de cada campo
                newValues.put("nom",nom);
                newValues.put("edad",edad);
                newValues.put("ciclo",ciclo);
                newValues.put("curso",curso);
                newValues.put("despacho",despacho);

                db.insert(DATABASE_TABLE_PROFE,null,newValues);
            }
        }
        return result;
    }
    public void insertarAlumno(String nom, int edad,String ciclo,int curso,float nota_media){
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put("nom",nom);
        newValues.put("edad",edad);
        newValues.put("ciclo",ciclo);
        newValues.put("curso",curso);
        newValues.put("nota_media",nota_media);

        db.insert(DATABASE_TABLE_ALUMNO,null,newValues);
    }

    public void insertarAsignatura(String nom, int horas){
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put("nom",nom);
        newValues.put("horas",horas);

        db.insert(DATABASE_TABLE_ASIGNATURA,null,newValues);
    }

    public ArrayList<String> recuperarAlumnos(){
        ArrayList<String> alumnos = new ArrayList<String>();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(DATABASE_TABLE_ALUMNO,null,null,null,null,null,"ciclo");
        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()){
            do{
                String temp=cursor.getString(3);
                if(temp.trim().equals("DAM") || temp.trim().equals("DAW")) {
                    alumnos.add(cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3)+" "+cursor.getString(4)+" "+cursor.getString(5));
                }
            }while (cursor.moveToNext());
        }
        return alumnos;
    }


    public ArrayList<String> recuperarAlumnoCiclo(String ciclo){
        ArrayList<String> alumnos = new ArrayList<String>();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(DATABASE_TABLE_ALUMNO,null,null,null,null,null,null);
        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()){
            do{
                String temp=cursor.getString(3);
                if(temp.trim().equalsIgnoreCase(ciclo)) {
                    alumnos.add(cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3)+" "+cursor.getString(4)+" "+cursor.getString(5));
                }
            }while (cursor.moveToNext());
        }
        return alumnos;
    }

    public ArrayList<String> recuperarAlumnoCurso(String curso){
        ArrayList<String> alumnos = new ArrayList<String>();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(DATABASE_TABLE_ALUMNO,null,null,null,null,null,null);
        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()){
            do{
                String temp=cursor.getString(4);
                if(temp.trim().equalsIgnoreCase(curso)) {
                    alumnos.add(cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3)+" "+cursor.getString(4)+" "+cursor.getString(5));
                }
            }while (cursor.moveToNext());
        }
        return alumnos;
    }

    public ArrayList<String> recuperarProfes(){
        ArrayList<String> profes = new ArrayList<String>();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(DATABASE_TABLE_PROFE,null,null,null,null,null,null);
        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()){
            do{
                profes.add(cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3)+" "+cursor.getString(4)+" "+cursor.getString(5));
            }while (cursor.moveToNext());
        }
        return profes;
    }

    public ArrayList<String> recuperarAsignaturaNombre(String nombre){
        ArrayList<String> asignatura = new ArrayList<String>();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query("asignaturas",null,null,null,null,null,null);
        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()){
            do{
                String temp=cursor.getString(1);
                if(temp.trim().equalsIgnoreCase(nombre)) {
                    asignatura.add(cursor.getString(1)+" "+cursor.getString(2));
                }
            }while (cursor.moveToNext());
        }
        return asignatura;
    }



    private static class MyDbHelper extends SQLiteOpenHelper {

        public MyDbHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context,name,factory,version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE_PROFE);
            db.execSQL(DATABASE_CREATE_ALUMNO);
            db.execSQL(DATABASE_CREATE_ASIGNATURA);


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DATABASE_DROP_PROFE);
            db.execSQL(DATABASE_DROP_ALUMNO);
            db.execSQL(DATABASE_DROP_ASIGNATURA);
            onCreate(db);
        }

    }
}
/*
//Establecemos los campos-valores a actualizar
ContentValues valores = new ContentValues();
valores.put("nombre","usunuevo");

//Actualizamos el registro en la base de datos
db.update("Usuarios", valores, "codigo=6", null);

//Eliminar un registro con execSQL(), utilizando argumentos
String[] args = new String[]{"usuario1"};
db.execSQL("DELETE FROM Usuarios WHERE nombre=?", args);

//Actualizar dos registros con update(), utilizando argumentos
ContentValues valores = new ContentValues();
valores.put("nombre","usunuevo");

String[] args = new String[]{"usuario1", "usuario2"};
db.update("Usuarios", valores, "nombre=? OR nombre=?", args);



db.execSQL("UPDATE comments SET comment='Esto es un comentario actualizado por el método execSQL()' WHERE user='Digital Learning'");


db.execSQL("UPDATE tabla  set campo='merdaNova' where campo=algo");

db.execSQL("DELETE FROM tabla where compo=algo");

*/