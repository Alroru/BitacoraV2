package es.studium.bitacoraapp.Modelo;

public class Apunte {
    int idApunte;
    String fechaApunte;
    String textoApunte;
    int idCuadernoFK;



    public Apunte(int idApunte, String fechaApunte, String textoApunte, int idCuadernoFK)
    {
        this.idApunte=idApunte;
        this.fechaApunte=fechaApunte;
        this.textoApunte=textoApunte;
        this.idCuadernoFK=idCuadernoFK;


    }

    public int getIdCuadernoFK() {
        return idCuadernoFK;
    }

    public void setIdCuadernoFK(int idCuadernoFK) {
        this.idCuadernoFK = idCuadernoFK;
    }

    public int getIdApunte() {
        return idApunte;
    }

    public void setIdApunte(int idApunte) {
        this.idApunte = idApunte;
    }

    public String getFechaApunte() {
        return fechaApunte;
    }

    public void setFechaApunte(String fechaApunte) {
        this.fechaApunte = fechaApunte;
    }

    public String getTextoApunte() {
        return textoApunte;
    }

    public void setTextoApunte(String textoApunte) {
        this.textoApunte = textoApunte;
    }
}
