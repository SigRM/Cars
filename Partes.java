package logicaNegocio;
import java.io.Serializable;
import accesoDB.PartesDB;
import java.util.ArrayList;

public class Partes implements Serializable{
    private String numeroParte;
    private String tipo;
    private int modelo;
    private double precio;
    private Estado estado;

    public Partes(String numeroParte, String tipo, int modelo, double precio) {
        this.numeroParte = numeroParte;
        this.tipo = tipo;
        this.modelo = modelo;
        this.precio = precio;    
    }

    public String getNumeroParte() {
        return numeroParte;
    }

    public void setNumeroParte(String numeroParte) {
        this.numeroParte = numeroParte;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Partes del auto " + 
                "\nNumero de Parte: " + numeroParte + 
                "\nTipo: " + tipo + 
                "\nModelo: " + modelo + 
                "\nPrecio: " + precio + 
                "\nEstado: " + estado.toString();
    } 
    
public static Partes consultarPartes(String codigo) throws Exception{
    return PartesDB.getInstance().consultarPartes(codigo);
}

public static void agregarPartes(Partes partes) throws Exception{
    PartesDB.getInstance().agregarPartes(partes);
}
    
public static void eliminarPartes(String codigo) throws  Exception{
    PartesDB.getInstance().eliminarPartes(codigo);
}
    
public static void modificarPartes(Partes partes) throws Exception{
    PartesDB.getInstance().modificarPartes(partes);
}
    
public static ArrayList<Partes> listadoPartes() throws Exception{
    return PartesDB.getInstance().listaPartes(); 
}


}
