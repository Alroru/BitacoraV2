package es.studium.bitacoraapp;

public interface DialogoAltaApunte {
    public void onDialogoGuardarListener();
    public void onDialogoCancelarListener();
    public void onDataSetAltaApunte(String fechaApunte,String comentarioApunte);
}
