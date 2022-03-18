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

public class AgregarCuaderno extends DialogFragment {
    DialogoAltaCuaderno dialogoAltaCuaderno;
    EditText altaCuaderno;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//Construir el diálogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialogo_cuaderno, null));
        View Myview=inflater.inflate(R.layout.dialogo_cuaderno,null);
        altaCuaderno=Myview.findViewById(R.id.etEditarNombre);
        builder.setView(Myview);

        builder.setTitle("Crea un nuevo Cuaderno");
        builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try
                {
                    dialogoAltaCuaderno.onDataSet(altaCuaderno.getText().toString());
                }
                catch (Exception e)
                {
                    Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getActivity(), "Guardado", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                dialogoAltaCuaderno.onDialogoGuardarListener();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Cerrar el diálogo simplemente
                dialog.dismiss();
                dialogoAltaCuaderno.onDialogoCancelarListener();
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
            dialogoAltaCuaderno = (DialogoAltaCuaderno) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + "debe implementar interfaz dialogo");

        }

    }


}
