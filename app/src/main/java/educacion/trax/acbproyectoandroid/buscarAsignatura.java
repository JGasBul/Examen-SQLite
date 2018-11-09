package educacion.trax.acbproyectoandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

public class buscarAsignatura extends AppCompatActivity {
    MyDBAdapter table=new MyDBAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_asignatura);
        final EditText nom=findViewById(R.id.nom);
        final TextView result=findViewById(R.id.result);
        final Button buscar=findViewById(R.id.buscar);
        table.open();
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText("");
                String nomS= String.valueOf(nom.getText());
                ArrayList<String> asignaturas=table.recuperarAsignaturaNombre(nomS);
                Iterator it=asignaturas.iterator();
                while(it.hasNext()){
                    String asignatura= String.valueOf(it.next());
                    result.setText(""+result.getText()+"\n"+asignatura);
                }
            }
        });
    }
}
/* mostrar.setText("");
                String cicloS= String.valueOf(ciclo.getText());
                ArrayList<String> alumnos =table.recuperarAlumnoCiclo(cicloS);
                Iterator it=alumnos.iterator();
                while(it.hasNext()){
                    String alumno= String.valueOf(it.next());
                    mostrar.setText(""+mostrar.getText()+"\n"+alumno);
                }*/
