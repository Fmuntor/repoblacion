package repoblacion.modelo;

public enum Especie {
    ALAMO("álamo"),
    ENCINA("encina"),
    CASTANO("castaño"),
    CIPRES("ciprés"),
    PINO("pino"),
    ROBLE("roble"),
    OLIVO("olivo");

    private final String especie;

    public String cadenaAMostrar(String especie){
        return this.especie;
    }

    Especie(String especie){
        this.especie = especie;
    }

    @Override
    public String toString() {
        return especie;
    }

    
}
