package logicaNegocio;

public class ClientePersona extends Cliente {

    private String cedulaFisica;

    public ClientePersona(String cedulaFisica) {
        this.cedulaFisica = cedulaFisica;
    }

    public ClientePersona() {
        cedulaFisica = "";
    }

    @Override
    public double calculaDescuento() {
        return 0;
    }

    public String getCedulaFisica() {
        return cedulaFisica;
    }

    public void setCedulaFisica(String cedulaFisica) {
        this.cedulaFisica = cedulaFisica;
    }

    @Override
    public String toString() {
        return "ClientePersona{" + '}';
    }
}
