package accesoDB;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import logicaNegocio.Auto;

public class AutoDB {

    private final String RUTA_ARCHIVO = System.getProperty("user.dir") + "\\src\\Archivo\\Auto.dat";
    private ObjectOutputStream oEscritor;
    private ObjectInputStream oLector;
    private FileInputStream archivoEntrada;
    private FileOutputStream archivoSalida;
    private ArrayList<Auto> arrayAutoTemp;

    private static AutoDB instance = null;

    private AutoDB() {

    }

    public static AutoDB getInstance() {
        if (instance == null) {
            instance = new AutoDB();
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

    public ArrayList listaAuto() throws Exception {
        ArrayList listaAutos = new ArrayList();
        try {
            abrirArchivoInput();
            while (true) {
                Auto auto1 = (Auto) oLector.readObject();
                listaAutos.add(auto1);
            }
        } catch (Exception ex) {

        } finally {
            cerrarArchivoInput();
            return listaAutos;
        }

    }

    public Auto consultarAuto(String codigoAuto) throws Exception {
        Auto auto, autoBuscado = null;
        try {
            abrirArchivoInput();
            while (true) {
                auto = (Auto) oLector.readObject();
                if (auto.getCodigoAuto().equalsIgnoreCase(codigoAuto)) {
                    autoBuscado = auto;
                }
            }
        } catch (Exception e) {

        } finally {
            cerrarArchivoInput();
            return autoBuscado;
        }

    }

    public void agregarAuto(Auto auto) throws Exception {
        try {
            this.abrirArchivoOutput();
            if (oEscritor != null) {
                oEscritor.writeObject(auto);
                oEscritor.flush();
                oEscritor.reset();
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrarArchivoOutput();
        }

    }

    public void modificarAuto(Auto auto) throws Exception {
        arrayAutoTemp = new ArrayList<Auto>();
        try {
            abrirArchivoInput();
            Auto auto1 = null;
            while (true) {
                auto1 = (Auto) oLector.readObject();
                if (auto1.getCodigoAuto().equalsIgnoreCase(auto.getCodigoAuto())) {
                    auto1 = auto;
                }
                arrayAutoTemp.add(auto1);
            }
        } catch (Exception ex) {

        } finally {
            cerrarArchivoInput();
            pasarArrayTemporal_Archivo();
        }

    }

    public void eliminarAuto(String codigoAuto) throws Exception {
        arrayAutoTemp = new ArrayList<Auto>();
        try {
            abrirArchivoInput();
            Auto auto1 = null;
            while (true) {
                auto1 = (Auto) oLector.readObject();
                if (!auto1.getCodigoAuto().equalsIgnoreCase(codigoAuto)) {
                    arrayAutoTemp.add(auto1);
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
        if (!arrayAutoTemp.isEmpty()) {
            abrirArchivoOutput();
            for (Auto auto : arrayAutoTemp) {
                oEscritor.writeObject(auto);
            }
        }
        cerrarArchivoOutput();
    }

}
