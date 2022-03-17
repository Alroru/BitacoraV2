package es.studium.bitacoraapp.Modelo;

public class Cuaderno {
    int idCuaderno;
    String nombreCuaderno;

    public Cuaderno(int idCuaderno, String nombreCuaderno)
    {
    this.idCuaderno=idCuaderno;
    this.nombreCuaderno=nombreCuaderno;
    }

    public Cuaderno(String nombreCuaderno)
    {
        this.nombreCuaderno=nombreCuaderno;
    }

    public int getIdCuaderno() {
        return idCuaderno;
    }

    public void setIdCuaderno(int idCuaderno) {
        this.idCuaderno = idCuaderno;
    }

    public String getNombreCuaderno() {
        return nombreCuaderno;
    }

    public void setNombreCuaderno(String nombreCuaderno) {
        this.nombreCuaderno = nombreCuaderno;
    }
}
