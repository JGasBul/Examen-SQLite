package educacion.trax.acbproyectoandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Asignaturas extends AppCompatActivity implements View.OnClickListener {
    MyDBAdapter table=new MyDBAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignaturas);
        final Button añadir=findViewById(R.id.añadir);
        añadir.setOnClickListener(this);
        final Button buscar=findViewById(R.id.buscar);
        buscar.setOnClickListener(this);
        table.open();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.añadir){
            Intent i=new Intent(getApplicationContext(),newAsignatura.class);
            startActivityForResult(i,1);
        }
        if (v.getId()==R.id.buscar){
            Intent i=new Intent(getApplicationContext(),buscarAsignatura.class);
            startActivity(i);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            if (resultCode==RESULT_OK){
                Bundle b=data.getExtras();
                ClaseAsignatura asignatura=b.getParcelable("asignatura");
                String nom=asignatura.getNom();
                int horas=asignatura.getHoras();
                table.insertarAsignatura(nom,horas);
            }
        }
    }
}
