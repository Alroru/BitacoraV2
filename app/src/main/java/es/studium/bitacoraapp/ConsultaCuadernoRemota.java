package es.studium.bitacoraapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import es.studium.bitacoraapp.Modelo.Cuaderno;

public class ConsultaCuadernoRemota extends AsyncTask<Void, Void, String> {
    String nombreCuaderno;
    int idCuaderno;
    List<Cuaderno> lista=new ArrayList<>();
    Cuaderno c;
    JSONArray result;
    JSONObject jsonobject;



    // Constructor
    public ConsultaCuadernoRemota()
    {

    }


    // Inspectores

    @Override
    protected String doInBackground(Void... voids) {
        try{
            // Crear la URL de conexión al API
            URL url = new URL("http://192.168.1.135/ApiRest/cuadernos.php?");
            // Crear la conexión HTTP
            HttpURLConnection myConnection = (HttpURLConnection) url.openConnection();
            // Establecer método de comunicación. Por defecto GET.
            myConnection.setRequestMethod("GET");

            if (myConnection.getResponseCode() == 200) {
                // Conexión exitosa
                // Creamos Stream para la lectura de datos desde el servidor
                InputStream responseBody = myConnection.getInputStream();
                InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                // Creamos Buffer de lectura
                BufferedReader bR = new BufferedReader(responseBodyReader);
                String line = "";
                StringBuilder responseStrBuilder = new StringBuilder();
                // Leemos el flujo de entrada
                while ((line = bR.readLine()) != null) {
                    responseStrBuilder.append(line);
                }
                // Parseamos respuesta en formato JSON
                 result = new JSONArray(responseStrBuilder.toString());

                responseBody.close();
                responseBodyReader.close();
                myConnection.disconnect();
            }
        else
        {
// Error en la conexión
            Log.println(Log.ERROR,"Error", "¡Conexión fallida!");
        }
    }
catch (Exception e)
    {
        Log.println(Log.ERROR,"Error", "¡Conexión fallida!"+e.getLocalizedMessage());
    }
return (null);
}
@Override
protected void onPostExecute(String mensaje)
{
// Actualizamos los cuadros de texto
    try {
        int i;
        for (i = 0; i <= result.length(); i++) {

            jsonobject = result.getJSONObject(i);


            // Sacamos dato a dato obtenido

            idCuaderno = jsonobject.getInt("idCuaderno");


            nombreCuaderno = jsonobject.getString("nombreCuaderno");

             c = new Cuaderno(idCuaderno, nombreCuaderno);
             lista.add(c);
        }

    } catch (JSONException e) {
        e.printStackTrace();
    }
}

public List getLista()
{

    return this.lista;
}

}

