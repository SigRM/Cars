package logicaNegocio;

public class Hibrido extends Auto {

    public Hibrido(String codigoAuto, int modelo, double precio) {
        super(codigoAuto, modelo, precio);
    }

    public Hibrido() {
    }

    @Override
    public double calculaDescuento() {
        return precio * 0.3;
    }

}
