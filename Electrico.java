package logicaNegocio;

public class Electrico extends Auto {

    public Electrico(String codigoAuto, int modelo, double precio) {
        super(codigoAuto, modelo, precio);
    }

    public Electrico() {
    }

    @Override
    public double calculaDescuento() {
        return precio * 0.5;
    }

}
