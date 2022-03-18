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

public class ModificarCuaderno extends DialogFragment {
    DialogoModificarCuaderno dialogoModificarCuaderno;
    EditText modificarCuaderno;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//Construir el diálogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialogo_cuaderno, null));
        View Myview=inflater.inflate(R.layout.dialogo_cuaderno,null);
        modificarCuaderno=Myview.findViewById(R.id.etEditarNombre);
        builder.setView(Myview);

        builder.setTitle("Da un nuevo nombre al Cuaderno");
        builder.setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try
                {
                    dialogoModificarCuaderno.onDataSetModificar(modificarCuaderno.getText().toString());
                }
                catch (Exception e)
                {
                    Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getActivity(), "Modificado", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                dialogoModificarCuaderno.onDialogoGuardarListener();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Cerrar el diálogo simplemente
                dialog.dismiss();
                dialogoModificarCuaderno.onDialogoCancelarListener();
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
            dialogoModificarCuaderno = (DialogoModificarCuaderno)  context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + "debe implementar interfaz dialogo");

        }

    }


}
