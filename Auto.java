package logicaNegocio;
import accesoDB.PartesDB;
import java.util.ArrayList;
import java.io.Serializable;
import accesoDB.AutoDB;


public abstract class Auto implements Serializable{
    private String codigoAuto;
    private int modelo;
    protected double precio;
    private ArrayList<Partes> arrayPartes;
    

    public Auto(String codigoAuto, int modelo, double precio) {
        this.codigoAuto = codigoAuto;
        this.modelo = modelo;
        this.precio = precio;
        this.arrayPartes = new ArrayList<>();
    }

    public Auto() {
    }

    public String getCodigoAuto() {
        return codigoAuto;
    }

    public void setCodigoAuto(String codigoAuto) {
        this.codigoAuto = codigoAuto;
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

    public ArrayList<Partes> getArrayPartes() {
        return arrayPartes;
    }

    public void setArrayPartes(ArrayList<Partes> arrayPartes) {
        this.arrayPartes = arrayPartes;
    }

    public void agregarPartes(Partes parte) {
        this.arrayPartes.add(parte);
    }

    public abstract double calculaDescuento();
    
    
public static Auto consultarAuto(String codigo) throws Exception{
    return AutoDB.getInstance().consultarAuto(codigo);
}

public static void agregarAuto(Auto auto) throws Exception{
    AutoDB.getInstance().agregarAuto(auto);
}
    
public static void eliminarAuto(String codigo) throws  Exception{
    AutoDB.getInstance().eliminarAuto(codigo);
}
    
public static void modificarAuto(Auto auto) throws Exception{
    AutoDB.getInstance().modificarAuto(auto);
}
    
public static ArrayList<Auto> listadoAuto() throws Exception{
    return AutoDB.getInstance().listaAuto(); 
}

}
