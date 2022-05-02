package logicaNegocio;

public class Pais {
    private String pais;
    private String nombre;

    public Pais(String pais, String nombre) {
        this.pais = pais;
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Pais{" + '}';
    }

}
