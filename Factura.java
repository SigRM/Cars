package logicaNegocio;
import java.util.ArrayList;
import java.util.Date;
//Se debe registrar la fecha de facturacion

public class Factura {

    private static int contFacturas = 0;
    private int numeroFactura;
    private Cliente cliente;
    private Date fecha;
    private ArrayList<Auto> arrayAutos;
    private TipoFactura tipoFactura;//Contado o Credito

    public Factura(Cliente cliente) {
        this.numeroFactura = ++contFacturas;
        this.cliente = cliente;
        this.arrayAutos = new ArrayList<>();
    }

    public static int getContFacturas() {
        return contFacturas;
    }

    public static void setContFacturas(int contFacturas) {
        Factura.contFacturas = contFacturas;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<Auto> getArrayAutos() {
        return arrayAutos;
    }

    public void setArrayAutos(ArrayList<Auto> arrayAutos) {
        this.arrayAutos = arrayAutos;
    }

    public TipoFactura getTipoFactura() {
        return tipoFactura;
    }

    public void setTipoFactura(TipoFactura tipoFactura) {
        this.tipoFactura = tipoFactura;
    }

    //Logica de Negocio 
    public double precioTotalExtras() {
        double extras = 0;
        for (Auto auto : this.arrayAutos) {
            extras += auto.getPrecio();
        }
        return extras;
    }

    public double calculoDescuento() {
        return tipoFactura.getPorcentaje();

    }

    public double calculoImpuesto() {
        return (this.precioTotalExtras() - this.calculoDescuento()) * (13 / 100d);
    }

    public double calculoTotalAuto() {
        return this.precioTotalExtras() + this.calculoDescuento() + this.calculoImpuesto();
    }

    public String toString() {
        StringBuilder h = new StringBuilder();
        h.append("No.Factura:").append(this.getNumeroFactura()).append(" ").append(tipoFactura.toString()).append("\n");
        for (Auto auto : this.arrayAutos) {
            h.append(auto.toString()).append("\n");
        }
        h.append("Precio total extras: ").append(String.format("¢%10.2f", this.precioTotalExtras())).append("\n");
        h.append("Descuento: ").append(String.format("¢%10.2f", this.calculoDescuento())).append("\n");
        h.append("Impuesto: ").append(String.format("¢%10.2f", this.calculoImpuesto())).append("\n");
        h.append("Total Factura: ").append(String.format("¢%10.2f", this.calculoTotalAuto())).append("\n");
        return h.toString();

    }
}
