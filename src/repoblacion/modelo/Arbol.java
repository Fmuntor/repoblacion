package repoblacion.modelo;
public class Arbol {
    
    Especie especie; 
    Posicion posicion;
    
    public Arbol(Especie especie, Posicion posicion){
        setPosicion(posicion);
        setEspecie(especie);
    }
    
    public Arbol(Arbol a){
        if(a == null){
            throw new NullPointerException("ERROR: No se puede copiar un árbol nulo.");
        }
        setPosicion(a.getPosicion());
        setEspecie(a.getEspecie());
    }

    public Arbol(){

    }

    public Especie getEspecie(){
        return especie;
    }

    public Posicion getPosicion(){
        return posicion;
    }

    public void setEspecie(Especie especie) {
        if (especie == null) {
            throw new NullPointerException("ERROR: La especie no puede ser nula.");
        }
        this.especie = especie;
    }

    public void setPosicion(Posicion posicion) {
        if (posicion == null) {
            throw new NullPointerException("ERROR: La posición no puede ser nula.");
        }
        this.posicion = posicion;
    }

    @Override
    public String toString() {
        return "especie=" + especie + ", posicion=(" + posicion + ")";
    }


}
