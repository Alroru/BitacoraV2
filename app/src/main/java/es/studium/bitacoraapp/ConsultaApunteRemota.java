package es.studium.bitacoraapp;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import es.studium.bitacoraapp.Modelo.Apunte;


public class ConsultaApunteRemota extends AsyncTask<Void, Void, String> {

    String textoApunte;
    String fechaApunte;
    int idApunte;
    List<Apunte> lista=new ArrayList<>();
    Apunte apunte;
    JSONArray result;
    JSONObject jsonobject;
    int idCuadernoFK;


    // Constructor
    public ConsultaApunteRemota(int idCuadernoFK)
    {

        this.idCuadernoFK=idCuadernoFK;
    }

    // Inspectores

    @Override
    protected String doInBackground(Void... voids) {
        try{
            // Crear la URL de conexión al API
            URL url = new URL("http://192.168.1.135/ApiRest/apuntes.php?idCuaderno="+idCuadernoFK);
            Log.i("ALE", "id"+idCuadernoFK);
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
                Log.i("ALE", "resultleng"+result.length());

                jsonobject = result.getJSONObject(i);


                // Sacamos dato a dato obtenido

                idApunte = jsonobject.getInt("idApunte");
                fechaApunte=jsonobject.getString("fechaApunte");
                textoApunte = jsonobject.getString("textoApunte");





                apunte= new Apunte(idApunte, fechaApunte,textoApunte,idCuadernoFK);
                lista.add(apunte);
                Log.i("ALE", "listasize"+lista.size());

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
