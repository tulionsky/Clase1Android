package gt.edu.umg.p2c1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import gt.edu.umg.p2c1.BaseDatos.DbContactos;

public class NuevoActivity extends AppCompatActivity {

    EditText txtNombre, txtTelefono, txtEmail;
    Button btnGuardar;
    DbContactos dbContactos;
    Button btn_regresar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        //inicializar texto y boton
        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtEmail = findViewById(R.id.txtEmail);
        btnGuardar = findViewById(R.id.btnGuardar);
        btn_regresar = findViewById(R.id.btn_regresar);

        btn_regresar.setOnClickListener(view -> {
            finish();
        });

        //inicializar la base de datos
        dbContactos = new DbContactos(this);

        //lo que hara el boton cuando le hagan click
        btnGuardar.setOnClickListener(v ->{
            //obtener los valores
            String nombre = txtNombre.getText().toString();
            String telefono = txtTelefono.getText().toString();
            String email = txtEmail.getText().toString();

            //insertar los datos a la base de datos
            long id = dbContactos.insertaContacto(nombre, telefono, email);
            if (id>0){
                Toast.makeText(this, "VAMOOOO, SI FUNCAAAAA!!!!!", Toast.LENGTH_SHORT).show();

                //limpiar los campos de texto
                txtNombre.setText("");
                txtTelefono.setText("");
                txtEmail.setText("");
            } else {
                Toast.makeText(this,"Error al guardar contacto", Toast.LENGTH_SHORT).show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}