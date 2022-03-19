package es.studium.bitacoraapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class AltaApunteRemota extends AsyncTask<Void, Void, String>
{
    // Atributos
    String fechaApunte;
    String comentarioApunte;
    String idCuadernoFK;

    // Constructor
    public AltaApunteRemota(String fechaApunte,String comentarioApunte,String idCuadernoFK)
    {
        this.fechaApunte = fechaApunte;
        this.comentarioApunte=comentarioApunte;
        this.idCuadernoFK=idCuadernoFK;
    // Inspectoras
    }
    protected String doInBackground(Void... argumentos)
    {
        try {
// Crear la URL de conexión al API
            URL url = new URL("http://192.168.1.135/ApiRest/apuntes.php");
// Crear la conexión HTTP
            HttpURLConnection myConnection = (HttpURLConnection) url.openConnection();
// Establecer método de comunicación.
            myConnection.setRequestMethod("POST");
// Conexión exitosa
            String response = "";
            HashMap<String, String> postDataParams = new HashMap<String, String>();
            postDataParams.put("fechaApunte", this.fechaApunte);
            postDataParams.put("textoApunte", this.comentarioApunte);
            postDataParams.put("idCuadernoFK", this.idCuadernoFK);
            myConnection.setDoInput(true);
            myConnection.setDoOutput(true);
            OutputStream os = myConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,
                    "UTF-8"));
            writer.write(getPostDataString(postDataParams));
            writer.flush();
            writer.close();
            os.close();
            myConnection.getResponseCode();
            if (myConnection.getResponseCode() == 200)
            {
// Success
                myConnection.disconnect();
            }
            else {
// Error handling code goes here
                Log.println(Log.ASSERT, "Error", "Error");
            }
        }
        catch(Exception e)
        {
            Log.println(Log.ASSERT,"Excepción", e.getMessage());
        }
        return (null);
    }
    protected void onPostExecute(String mensaje)
    {
//
    }
    private String getPostDataString(HashMap<String, String> params)
            throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet())
        {
            if (first)
            {
                first = false;
            }
            else
            {
                result.append("&");
            }
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }
}
