package es.studium.bitacoraapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLEncoder;

public class ModificacionApunteRemota extends AsyncTask<Void, Void, String>
{
    // Atributos
    String idApunte;
    String fechaApunte;
    String textoApunte;
    String idCuadernoFK;
    // Constructor
    public ModificacionApunteRemota(String id,String fecha,String texto,String idFK)
    {
        this.idApunte = id;
        this.fechaApunte=fecha;
        this.textoApunte=texto;
        this.idCuadernoFK=idFK;
    }
    // Inspectores

    @Override
    protected String doInBackground(Void... voids)
    {
        try
        {
// Crear la URL de conexión al API
            URI baseUri = new URI("http://192.168.1.135/ApiRest/apuntes.php");
            String[] parametros = {"idApunte",this.idApunte,
                    "fechaApunte",this.fechaApunte,"textoApunte",this.textoApunte,"idCuadernoFK",this.idCuadernoFK};
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
