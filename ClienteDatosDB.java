package accesoDB;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import logicaNegocio.ClienteDatos;
public class ClienteDatosDB {

    private final String RUTA_ARCHIVO = System.getProperty("user.dir") + "\\src\\Archivo\\Cliente.dat";
    private ObjectOutputStream oEscritor;
    private ObjectInputStream oLector;
    private FileInputStream archivoEntrada;
    private FileOutputStream archivoSalida;
    private ArrayList<ClienteDatos> arrayClienteTemp;

    private static ClienteDatosDB instance = null;

    private ClienteDatosDB() {

    }

    public static ClienteDatosDB getInstance() {
        if (instance == null) {
            instance = new ClienteDatosDB();
        }
        return instance;
    }

    public void abrirArchivoOutput() throws Exception {

        try {
            File oArchivo = new File(RUTA_ARCHIVO);
            if (!oArchivo.exists()) {
                archivoSalida = new FileOutputStream(RUTA_ARCHIVO, true);
                oEscritor = new ObjectOutputStream(archivoSalida);
            } else {
                archivoSalida = new FileOutputStream(RUTA_ARCHIVO, true);
                oEscritor = new MiObjectOutputStream(archivoSalida);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void abrirArchivoInput() throws Exception {

        try {
            archivoEntrada = new FileInputStream(RUTA_ARCHIVO);
            oLector = new ObjectInputStream(archivoEntrada);
        } catch (Exception e) {
            throw e;
        }
    }

    public void cerrarArchivoOutput() throws Exception {
        try {
            if (oEscritor != null) {
                oEscritor.close();
                oEscritor = null;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void cerrarArchivoInput() throws Exception {
        try {
            if (oLector != null) {
                oLector.close();
                oLector = null;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public ArrayList listaCliente() throws Exception {
        ArrayList listaClientes = new ArrayList();
        try {
            abrirArchivoInput();
            while (true) {
                ClienteDatos cliente1 = (ClienteDatos) oLector.readObject();
                listaClientes.add(cliente1);
            }
        } catch (Exception ex) {

        } finally {
            cerrarArchivoInput();
            return listaClientes;
        }

    }

    public ClienteDatos consultarCliente(String codigoCliente) throws Exception {
        ClienteDatos cliente, clienteBuscado = null;
        try {
            abrirArchivoInput();
            while (true) {
                cliente = (ClienteDatos) oLector.readObject();
                if (cliente.getNombre().equalsIgnoreCase(codigoCliente)) {
                    clienteBuscado = cliente;
                }
            }
        } catch (Exception e) {

        } finally {
            cerrarArchivoInput();
            return clienteBuscado;
        }

    }

    public void agregarCliente(ClienteDatos cliente) throws Exception {
        try {
            this.abrirArchivoOutput();
            if (oEscritor != null) {
                oEscritor.writeObject(cliente);
                oEscritor.flush();
                oEscritor.reset();
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrarArchivoOutput();
        }

    }

    public void modificarCliente(ClienteDatos cliente) throws Exception {
        arrayClienteTemp = new ArrayList<ClienteDatos>();
        try {
            abrirArchivoInput();
            ClienteDatos cliente1 = null;
            while (true) {
                cliente1 = (ClienteDatos) oLector.readObject();
                if (cliente1.getNombre().equalsIgnoreCase(cliente.getNombre())) {
                    cliente1 = cliente;
                }
                arrayClienteTemp.add(cliente1);
            }
        } catch (Exception ex) {

        } finally {
            cerrarArchivoInput();
            pasarArrayTemporal_Archivo();
        }

    }

    public void eliminarCliente(String codigoCliente) throws Exception {
        arrayClienteTemp = new ArrayList<ClienteDatos>();
        try {
            abrirArchivoInput();
            ClienteDatos cliente1 = null;
            while (true) {
                cliente1 = (ClienteDatos) oLector.readObject();
                if (!cliente1.getNombre().equalsIgnoreCase(codigoCliente)) {
                    arrayClienteTemp.add(cliente1);
                }
            }
        } catch (Exception e) {

        } finally {
            cerrarArchivoInput();
            pasarArrayTemporal_Archivo();
        }
    }

    private void pasarArrayTemporal_Archivo() throws Exception {
        File archivoOriginal = new File(RUTA_ARCHIVO);

        if (archivoOriginal.exists()) {
            archivoOriginal.delete();
        }
        if (!arrayClienteTemp.isEmpty()) {
            abrirArchivoOutput();
            for (ClienteDatos ciudad : arrayClienteTemp) {
                oEscritor.writeObject(ciudad);
            }
        }
        cerrarArchivoOutput();
    }
}
