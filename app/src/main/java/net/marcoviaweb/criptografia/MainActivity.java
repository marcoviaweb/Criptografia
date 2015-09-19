package net.marcoviaweb.criptografia;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText mensaje = (EditText) findViewById(R.id.mensaje);
        mensaje.setText("atacar de noche");
        final TextView resultado = (TextView) findViewById(R.id.resultado);
        final Button cifrar = (Button) findViewById(R.id.cifrar);
        final Button descifrar = (Button) findViewById(R.id.descifrar);

        cifrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resultado.setText(cifrar(mensaje.getText().toString()));
            }
        });

        descifrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resultado.setText(descifrar(mensaje.getText().toString()));
            }
        });

        Log.d("Mensaje cifrado: ", cifrar("ATACAR DE NOCHE"));
        Log.d("Mensaje cifrado: ", descifrar("AAADNCETCREOH"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Funcion para cifrar un mensaje
     * @param mensaje
     * @return
     */
    public String cifrar(String mensaje) {
        //Convertir el mensaje en array de caracteres
        char mensajeArray[] = mensaje.toCharArray();
        //Establecer valor de inicio del mensaje
        mensaje = "";
        //Quitar los espacios
        for (int i = 0; i < mensajeArray.length; i++){
            if(mensajeArray[i] != ' '){
                mensaje += mensajeArray[i];
            }
        }
        //Log.d("Mensaje sin espacio: ", mensaje);

        //Convertir el mensaje sin espacios en array de caracteres
        mensajeArray = mensaje.toCharArray();

        //Generar las dos cadenas del cifrado
        String cadena1 = "";
        String cadena2 = "";
        for (int i = 0; i < mensajeArray.length; i++){
            if((i % 2) == 0)
                cadena1 += mensajeArray[i];
            else
                cadena2 += mensajeArray[i];
        }

        String cifrado = cadena1 + cadena2;

        return cifrado;
    }

    /**
     * Funcion para descifrar un mensaje
     * @param mensajeCifrado
     * @return
     */
    public String descifrar(String mensajeCifrado) {
        //Convertir el mensaje cifrado en array de caracteres
        char mensajeCifradoArray[] = mensajeCifrado.toCharArray();

        int longitudMensajeCifrado = mensajeCifradoArray.length;
        int longitudDiv = longitudMensajeCifrado / 2;
        int longitudMod = longitudMensajeCifrado % 2;

        int segundoIndice = longitudDiv + longitudMod;

        String cadena = "";
        for (int i = 0; i < segundoIndice; i++){
            cadena += mensajeCifradoArray[i];

            if ((i + segundoIndice) <= (longitudMensajeCifrado - 1))
                cadena += mensajeCifradoArray[i + segundoIndice];
        }
        String descifrado = cadena;

        return descifrado;
    }
}
