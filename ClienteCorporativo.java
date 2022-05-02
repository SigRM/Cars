package logicaNegocio;

public class ClienteCorporativo extends Cliente {

    private String cedulaJuridica;

    public ClienteCorporativo() {
        cedulaJuridica = " ";
    }

    public ClienteCorporativo(String cedulaJuridica) {
        this.cedulaJuridica = cedulaJuridica;
    }

    @Override
    public double calculaDescuento() {
       return 0;
    }

    public String getCedulaJuridica() {
        return cedulaJuridica;
    }

    public void setCedulaJuridica(String cedulaJuridica) {
        this.cedulaJuridica = cedulaJuridica;
    }

    @Override
    public String toString() {
        return "ClienteCorporativo{" + '}';
    }

}
