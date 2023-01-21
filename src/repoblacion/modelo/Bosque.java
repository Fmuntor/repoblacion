package repoblacion.modelo;

import java.util.Arrays;
import java.util.Random;

import repoblacion.utilidides.Consola;

public class Bosque {

    private final int MAX_ALTURA = 1000;
    private final int MAX_ANCHURA = 1000;
    private final int MINIMO = 5;
    private final int MAX_ESPECIES = 4;

    Arbol arbolMasAlejado;
    Arbol arbolMasCentrado;
    private Arbol[] arboles;
    Random generador;
    int ancho;
    int alto;
    int poblacion;
    int arbolesActuales = 0;
    int especiesActuales = 0;
    Especie especieElegida = null;
    private final int MAX_POBLACION = (alto*2)+(ancho*2);

    public Arbol getArbolMasAlejado() {
        return arbolMasAlejado;
    }

    public Arbol getArbolMasCentrado() {
        return arbolMasCentrado;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        if(ancho <= MINIMO || ancho > MAX_ANCHURA){
            throw new IllegalArgumentException("ERROR: Anchura no válida.");
        }
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        Consola.leerAltura();
        if(alto <= MINIMO || alto > MAX_ALTURA){
            throw new IllegalArgumentException("ERROR: Altura no válida.");
        }
        this.alto = alto;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(int poblacion) {
        checkPoblacion(poblacion);
        this.poblacion = poblacion;
    }

    public Bosque(int ancho, int alto, int poblacion){
        setAncho(ancho);
        setAlto(alto);
        setPoblacion(poblacion);
        repoblar();
    }
    // Realizamos una copia profunda para evitar el aliasing
    private Arbol[] duplicaBosque(){
        Arbol[] bosqueDuplicado = new Arbol[arboles.length];
        for (int i = 0; i < arboles.length; i++) {
            bosqueDuplicado[i] = new Arbol(arboles[i]);
        }
        return bosqueDuplicado;
    }

    public void checkPoblacion(int poblacion){
        if(poblacion<=0){
            throw new IllegalArgumentException("ERROR: La población debe ser mayor que cero.");
        }
        if(poblacion >= MAX_POBLACION){
            throw new IllegalArgumentException("ERROR: Altura no válida.");
        }
    }

    private void repoblar(){
        if(arboles[0] == null){
            // Obtener el tamaño del enumerado Especie.
        Especie[] especies = Especie.values();
        int tamañoEspecie = especies.length;

        // Obtener indice aleatorio en el rango anterior
        int indice = generador.nextInt(tamañoEspecie);

        // Asignar al indice el elemento del enumerado
        Especie especieAleatoria = especies[indice];

        // Una vez se ha generado la primera especie, generamos su posicion aleatoria dentro del rango introducido.
        int Xrandom = generador.nextInt(-ancho/2, ancho/2);
        int Yrandom = generador.nextInt(-alto/2, alto/2);

        Posicion posicionAleatoria = new Posicion(Xrandom, Yrandom);

        // Crear el primer arbol con los parametros anteriores e introducirlo en la primera posicion del array arboles
        Arbol arbol1 = new Arbol(especieAleatoria, posicionAleatoria);
        arboles[0] = arbol1;
        arbolesActuales++;
        especiesActuales++;
        }
        
        // Una vez creado el primer arbol, creamos el resto segun las restricciones.
        Especie especieRandom = null;
        Posicion posicionRandom = null;

        for(int i=1;i<poblacion;i++){
            while(especieRandom == null){
                especieRandom = generarEspecieAleatoria(arboles[i-1].getEspecie());
            }
            while(especieRandom == null){
                posicionRandom = generarPosicionAleatoria(arboles[i-1].getPosicion());
            }
            arboles[i] = new Arbol(especieRandom, posicionRandom);
            arbolesActuales++;
        }
    }

    // Este metodo recibe una especie, comprueba la especie y asigna la especie mientras sea correcta
    private Especie generarEspecieAleatoria(Especie especie){
        // Especificar los valores para asignarlos a especies de la siguiente manera:
        // Alamo=0 Encina=1 Castaño=2 Cipres=3, Pino=4, Roble=5, Olivo=6
        int[] valoresAlamo = {0,1,4,5}, valoresEncina = {0,1,2,3,4,5}, valoresCastano = {1,2,3,4,5,6}, valoresCipres = {1,2,3,4,5,6},
        valoresPino = {0,1,2,3,4,5,6}, valoresRoble = {0,1,2,3,4,5,6}, valoresOlivo = {2,3,4,5,6};
            switch (especie) {
                case ALAMO:
                    int indiceAlamo = generador.nextInt(valoresAlamo.length);
                    if(indiceAlamo == 0){
                        especieElegida = Especie.ALAMO;
                    }else if(indiceAlamo == 1){
                        especieElegida = Especie.ENCINA;
                    }else if(indiceAlamo == 4){
                        especieElegida = Especie.PINO;
                    }else if(indiceAlamo == 5){
                        especieElegida = Especie.ROBLE;
                    }
                    break;

                case ENCINA:
                    int indiceEncina = generador.nextInt(valoresEncina.length);
                    if(indiceEncina == 0){
                        especieElegida = Especie.ALAMO;
                    }else if(indiceEncina == 1){
                        especieElegida = Especie.ENCINA;
                    }else if(indiceEncina == 2){    
                        especieElegida = Especie.CASTANO;
                    }else if(indiceEncina == 3){
                        especieElegida = Especie.CIPRES;
                    }else if(indiceEncina == 4){
                        especieElegida = Especie.PINO;
                    }else if(indiceEncina == 5){
                        especieElegida = Especie.ROBLE;
                    }
                    break;

                case CASTANO:
                    int indiceCastano = generador.nextInt(valoresCastano.length);
                    if(indiceCastano == 1){
                        especieElegida = Especie.ENCINA;
                    }else if(indiceCastano == 2){    
                        especieElegida = Especie.CASTANO;
                    }else if(indiceCastano == 3){
                        especieElegida = Especie.CIPRES;
                    }else if(indiceCastano == 4){
                        especieElegida = Especie.PINO;
                    }else if(indiceCastano == 5){
                        especieElegida = Especie.ROBLE;
                    }else if(indiceCastano == 6){
                        especieElegida = Especie.OLIVO;
                    }
                    break;

                case CIPRES:
                    int indiceCipres = generador.nextInt(valoresCipres.length);
                    if(indiceCipres == 1){
                        especieElegida = Especie.ENCINA;
                    }else if(indiceCipres == 2){    
                        especieElegida = Especie.CASTANO;
                    }else if(indiceCipres == 3){
                        especieElegida = Especie.CIPRES;
                    }else if(indiceCipres == 4){
                        especieElegida = Especie.PINO;
                    }else if(indiceCipres == 5){
                        especieElegida = Especie.ROBLE;
                    }else if(indiceCipres == 6){
                        especieElegida = Especie.OLIVO;
                    }
                    break;

                case PINO:
                    int indicePino = generador.nextInt(valoresPino.length);
                    if(indicePino == 0){
                        especieElegida = Especie.ALAMO;
                    }else if(indicePino == 1){
                        especieElegida = Especie.ENCINA;
                    }else if(indicePino == 2){    
                        especieElegida = Especie.CASTANO;
                    }else if(indicePino == 3){
                        especieElegida = Especie.CIPRES;
                    }else if(indicePino == 4){
                        especieElegida = Especie.PINO;
                    }else if(indicePino == 5){
                        especieElegida = Especie.ROBLE;
                    }else if(indicePino == 6){
                        especieElegida = Especie.OLIVO;
                    }
                    break;

                case ROBLE:
                    int indiceRoble = generador.nextInt(valoresRoble.length);
                    if(indiceRoble == 0){
                        especieElegida = Especie.ALAMO;
                    }else if(indiceRoble == 1){
                        especieElegida = Especie.ENCINA;
                    }else if(indiceRoble == 2){    
                        especieElegida = Especie.CASTANO;
                    }else if(indiceRoble == 3){
                        especieElegida = Especie.CIPRES;
                    }else if(indiceRoble == 4){
                        especieElegida = Especie.PINO;
                    }else if(indiceRoble == 5){
                        especieElegida = Especie.ROBLE;
                    }else if(indiceRoble == 6){
                        especieElegida = Especie.OLIVO;
                    }
                    break;

                case OLIVO:
                    int indiceOlivo = generador.nextInt(valoresOlivo.length);
                    if(indiceOlivo == 2){    
                        especieElegida = Especie.CASTANO;
                    }else if(indiceOlivo == 3){
                        especieElegida = Especie.CIPRES;
                    }else if(indiceOlivo == 4){
                        especieElegida = Especie.PINO;
                    }else if(indiceOlivo == 5){
                        especieElegida = Especie.ROBLE;
                    }else if(indiceOlivo == 6){
                        especieElegida = Especie.OLIVO;
                    }
                    break;            

                default:
                    break;
        }
            // Si las especies actuales son menores que 4, se consulta si hay especies diferentes para aumentar o no la variable
            if(especiesActuales<MAX_ESPECIES){
                for(int i=0; i<arboles.length; i++){
                    // Si la especie generada no se ha introducido en el bosque, aumentamos la variable que indica las especies diferentes del bosque
                    if(arboles[i].getEspecie() != especieElegida){
                        especiesActuales++;
                        break;
                    }
                }
            // Si ya hay 4 especies diferentes, comprueba que la especie elegida no es diferente a las ya existentes
            }else{
                for(int i=0; i<arboles.length; i++){
                    // Si la especie generada es diferente que alguna especie existente en el bosque, devolvemos una especie nula.
                    if(arboles[i].getEspecie() != especieElegida){
                       return null;
                    }
                }
            }return especieElegida;     
    }

    private Posicion generarPosicionAleatoria(Posicion posicion){
        Posicion posicionAleatoria = new Posicion(generador.nextDouble(-posicion.getX()/2, posicion.getX()/2), generador.nextDouble(-posicion.getY(), posicion.getY()));
        for(int i=0; i<arboles.length; i++){
            if(arboles[i].getPosicion() == posicionAleatoria){
                return null;
            }else{}
        }return posicionAleatoria;
    }

    public void realizarCalculos(){
        arbolMasCentrado = new Arbol();
        arbolMasAlejado = new Arbol();
        //Comparamos la distancia entre cada arbol y el centro
        for(int i=0;i<arboles.length;i++){
            if(arboles[i].getPosicion().distancia(arboles[i].getPosicion()) < arboles[i+1].getPosicion().distancia(arboles[i+1].getPosicion())){
                arbolMasCentrado = arboles[i];
            }
            if(arboles[i].getPosicion().distancia(arboles[i].getPosicion()) > arboles[i+1].getPosicion().distancia(arboles[i+1].getPosicion())){
                arbolMasAlejado = arboles[i];
            }
        }
    }

    @Override
    public String toString() {
        return "Arboles = " + Arrays.toString(arboles) + ", ancho = " + ancho + ", alto = " + alto + "Arbol mas alejado del centro = "+arbolMasAlejado+
        "Arbol mas centrado = "+arbolMasCentrado;
    }
}
