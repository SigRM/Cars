package logicaNegocio;

import accesoDB.ClienteDatosDB;
import java.util.ArrayList;

public class ClienteDatos {

    String correo;
    String nombre;
    String contrasena;
    String direccion;

    public ClienteDatos(String correo, String nombre, String contrasena, String direccion) {
        this.correo = correo;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Correo=" + correo + "\n Nombre=" + nombre + "\n Contraseña=" + contrasena + "\n Direccion=" + direccion + '}';
    }

    public static ClienteDatos consultarCliente(String codigo) throws Exception {
        return ClienteDatosDB.getInstance().consultarCliente(codigo);
    }

    public static void agregarCliente(ClienteDatos cliente) throws Exception {
        ClienteDatosDB.getInstance().agregarCliente(cliente);
    }

    public static void eliminarCliente(String codigo) throws Exception {
        ClienteDatosDB.getInstance().eliminarCliente(codigo);
    }

    public static void modificarCliente(ClienteDatos cliente) throws Exception {
        ClienteDatosDB.getInstance().modificarCliente(cliente);
    }

    public static ArrayList<ClienteDatos> listadoCliente() throws Exception {
        return ClienteDatosDB.getInstance().listaCliente();
    }
}
