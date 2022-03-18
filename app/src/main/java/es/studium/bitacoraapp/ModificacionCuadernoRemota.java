package es.studium.bitacoraapp;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ModificacionCuadernoRemota extends AsyncTask<Void, Void, String>
{
    // Atributos
    String idCuaderno;
    String nombreCuaderno;

    // Constructor
    public ModificacionCuadernoRemota(String id,String nombre)
    {
        this.idCuaderno = id;
        this.nombreCuaderno = nombre;

    }
    // Inspectores

    protected String doInBackground(Void... voids)
    {
        try
        {
            String response = "";
            Uri uri = new Uri.Builder()
                    .scheme("http")
                    .authority("192.168.1.135")
                    .path("/ApiRest/cuadernos.php")
                    .appendQueryParameter("idCuaderno", this.idCuaderno)
                    .appendQueryParameter("nombreCuaderno", this.nombreCuaderno)

                    .build();
// Create connection
            URL url = new URL(uri.toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("PUT");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            int responseCode=connection.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK)
            {
                String line;
                BufferedReader br=new BufferedReader(new
                        InputStreamReader(connection.getInputStream()));
                while ((line=br.readLine()) != null)
                {
                    response+=line;
                }
            }
            else
            {
                response="";
            }
            connection.getResponseCode();
            if (connection.getResponseCode() == 200)
            {
// Success
                Log.println(Log.ASSERT,"Resultado", "Registro modificado:"+response);
                connection.disconnect();
            }
            else
            {
// Error handling code goes here
                Log.println(Log.ASSERT,"Error", "Error");
            }
        }
        catch(Exception e)
        {
            Log.println(Log.ASSERT,"Excepción", e.getMessage());
        }
        return null;
    }
    protected void onPostExecute(String mensaje)
    {

    }
}

