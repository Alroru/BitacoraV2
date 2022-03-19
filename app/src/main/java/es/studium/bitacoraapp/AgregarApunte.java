package es.studium.bitacoraapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AgregarApunte extends DialogFragment {
    DialogoAltaApunte dialogoAltaApunte;
    EditText etEditarFecha;
    EditText etEditarComentario;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//Construir el diálogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialogo_apuntes, null));
        View Myview=inflater.inflate(R.layout.dialogo_apuntes,null);
        etEditarComentario=Myview.findViewById(R.id.etEditarComentario);
        etEditarFecha=Myview.findViewById(R.id.etEditarFecha);
        builder.setView(Myview);

        builder.setTitle("Crea un nuevo Apunte");
        builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try
                {
                    dialogoAltaApunte.onDataSetAltaApunte(etEditarComentario.getText().toString(),etEditarFecha.getText().toString());
                }
                catch (Exception e)
                {
                    Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getActivity(), "Guardado", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                dialogoAltaApunte.onDialogoGuardarListener();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Cerrar el diálogo simplemente
                dialog.dismiss();
                dialogoAltaApunte.onDialogoCancelarListener();
            }
        });
//Cerrar el objeto y devolverlo
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Verificamos que la actividad principal ha implementado el interfaz
        try {
            //Instanciamos OnDialogoNombreListener para poder enviar eventos a la clase principal
            dialogoAltaApunte = (DialogoAltaApunte) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + "debe implementar interfaz dialogo");

        }

    }


}
