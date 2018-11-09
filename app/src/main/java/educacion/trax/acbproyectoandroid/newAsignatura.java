package educacion.trax.acbproyectoandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class newAsignatura extends AppCompatActivity {
    ClaseAsignatura asignatura;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_asignatura);
        final EditText nom=findViewById(R.id.nomA);
        final EditText horas=findViewById(R.id.horasA);
        final Button send=findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomS= String.valueOf(nom.getText());
                int horasS=Integer.parseInt(String.valueOf(horas.getText()));
                asignatura=new ClaseAsignatura(nomS,horasS);
                Intent i=new Intent();
                Bundle b=new Bundle();
                b.putParcelable("asignatura",asignatura);
                i.putExtras(b);
                setResult(RESULT_OK,i);
                finish();
            }
        });
    }
}
