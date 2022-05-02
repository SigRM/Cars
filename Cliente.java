package logicaNegocio;
import java.io.Serializable;
import accesoDB.ClienteDatosDB;
import accesoDB.PartesDB;
import java.util.ArrayList;

public abstract class Cliente implements Serializable{
    private Usuario usuario;

    public Cliente() {
    }

    public abstract double calculaDescuento();

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Cliente{" + '}';
    }
    
public static ClienteDatos consultarCliente (String codigoCliente) throws Exception{
    return ClienteDatosDB.getInstance().consultarCliente(codigoCliente);
}

public static void agregarCliente (ClienteDatos cliente ) throws Exception{
    ClienteDatosDB.getInstance().agregarCliente(cliente);
}
    
public static void eliminarCliente (String codigo) throws  Exception{
    ClienteDatosDB.getInstance().eliminarCliente (codigo);
}
    
public static void modificarCliente (ClienteDatos cliente ) throws Exception{
    ClienteDatosDB.getInstance().modificarCliente(cliente);
}
    
public static ArrayList<Cliente> listadoCliente () throws Exception{
    return ClienteDatosDB.getInstance().listaCliente (); 
}
}
