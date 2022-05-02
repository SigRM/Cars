package accesoDB;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import logicaNegocio.Partes;

public class PartesDB {

    private final String RUTA_ARCHIVO = System.getProperty("user.dir") + "\\src\\Archivo\\Partes.dat";
    private ObjectOutputStream oEscritor;
    private ObjectInputStream oLector;
    private FileInputStream archivoEntrada;
    private FileOutputStream archivoSalida;
    private ArrayList<Partes> arrayPartesTemp;

    private static PartesDB instance = null;

    private PartesDB() {

    }

    public static PartesDB getInstance() {
        if (instance == null) {
            instance = new PartesDB();
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

    public ArrayList listaPartes() throws Exception {
        ArrayList listaPartes = new ArrayList();
        try {
            abrirArchivoInput();
            while (true) {
                Partes parte1 = (Partes) oLector.readObject();
                listaPartes.add(parte1);
            }
        } catch (Exception ex) {

        } finally {
            cerrarArchivoInput();
            return listaPartes;
        }

    }

    public Partes consultarPartes(String codigoPartes) throws Exception {
        Partes parte, parteBuscada = null;
        try {
            abrirArchivoInput();
            while (true) {
                parte = (Partes) oLector.readObject();
                if (parte.getNumeroParte().equalsIgnoreCase(codigoPartes)) {
                    parteBuscada = parte;
                }
            }
        } catch (Exception e) {

        } finally {
            cerrarArchivoInput();
            return parteBuscada;
        }

    }

    public void agregarPartes(Partes parte) throws Exception {
        try {
            this.abrirArchivoOutput();
            if (oEscritor != null) {
                oEscritor.writeObject(parte);
                oEscritor.flush();
                oEscritor.reset();
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrarArchivoOutput();
        }

    }

    public void modificarPartes(Partes parte) throws Exception {
        arrayPartesTemp = new ArrayList<Partes>();
        try {
            abrirArchivoInput();
            Partes parte1 = null;
            while (true) {
                parte1 = (Partes) oLector.readObject();
                if (parte1.getNumeroParte().equalsIgnoreCase(parte.getNumeroParte())) {
                    parte1 = parte;
                }
                arrayPartesTemp.add(parte1);
            }
        } catch (Exception ex) {

        } finally {
            cerrarArchivoInput();
            pasarArrayTemporal_Archivo();
        }

    }

    public void eliminarPartes(String codigoPartes) throws Exception {
        arrayPartesTemp = new ArrayList<Partes>();
        try {
            abrirArchivoInput();
            Partes parte1 = null;
            while (true) {
                parte1 = (Partes) oLector.readObject();
                if (!parte1.getNumeroParte().equalsIgnoreCase(codigoPartes)) {
                    arrayPartesTemp.add(parte1);
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
        if (!arrayPartesTemp.isEmpty()) {
            abrirArchivoOutput();
            for (Partes parte : arrayPartesTemp) {
                oEscritor.writeObject(parte);
            }
        }
        cerrarArchivoOutput();
    }

}
