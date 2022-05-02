package logicaNegocio;

public class Fosil extends Auto{
    
    public Fosil(String codigoAuto, int modelo, double precio) {
        super(codigoAuto, modelo, precio);
    }

    public Fosil() {
    }

    @Override
    public double calculaDescuento() {
        return precio;
    }
    
}
