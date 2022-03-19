package es.studium.bitacoraapp;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

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
        this.nombreCuaderno=nombre;
    }
    // Inspectores

    @Override
    protected String doInBackground(Void... voids)
    {
        try
        {
// Crear la URL de conexión al API
            URI baseUri = new URI("http://192.168.1.135/ApiRest/cuadernos.php");
            String[] parametros = {"idCuaderno",this.idCuaderno,
            "nombreCuaderno",this.nombreCuaderno};
            URI uri = applyParameters(baseUri, parametros);
// Create connection
            HttpURLConnection myConnection = (HttpURLConnection)
                    uri.toURL().openConnection();
// Establecer método. Por defecto GET.
            myConnection.setRequestMethod("PUT");
            if (myConnection.getResponseCode() == 200)
            {
                Log.println(Log.ASSERT,"Resultado", "Registro Modificado");
                myConnection.disconnect();
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

    URI applyParameters(URI uri, String[] urlParameters)
    {
        StringBuilder query = new StringBuilder();
        boolean first = true;
        for (int i = 0; i < urlParameters.length; i += 2)
        {
            if (first)
            {
                first = false;
            }
            else
            {
                query.append("&");
            }
            try
            {
                query.append(urlParameters[i]).append("=")
                        .append(URLEncoder.encode(urlParameters[i + 1], "UTF-8"));
            }
            catch (UnsupportedEncodingException ex)
            {
                /* As URLEncoder are always correct, this exception
                 * should never be thrown. */
                throw new RuntimeException(ex);
            }
        }
        try
        {
            return new URI(uri.getScheme(), uri.getAuthority(),
                    uri.getPath(), query.toString(), null);
        }
        catch (Exception ex)
        {
            /* As baseUri and query are correct, this exception
             * should never be thrown. */
            throw new RuntimeException(ex);
        }
    }
}

