package logicaNegocio;

public enum TipoFactura {
    CONTADO(5),
    CREDITO(0);
    private int porcentaje;
    
    private TipoFactura(int porcentaje){
        this.porcentaje = porcentaje;
    }

    public int getPorcentaje() {
        return porcentaje;
    } 
}
