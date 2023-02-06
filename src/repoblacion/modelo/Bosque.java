package repoblacion.modelo;

import java.util.Arrays;
import java.util.Random;

public class Bosque {

    public static final int MAX_ALTURA = 500;
    private final int MAX_ANCHURA = 1000;
    private final int MINIMO = 10;
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
        this.arboles = new Arbol[poblacion];
        repoblar();
        realizarCalculos();
    }

    // Realizamos una copia profunda para evitar el aliasing
    public Arbol[] duplicaBosque(){
        Arbol[] bosqueDuplicado = new Arbol[arboles.length];
        for (int i = 0; i < bosqueDuplicado.length; i++) {
            bosqueDuplicado[i] = new Arbol(arboles[i]);
        }
        return bosqueDuplicado;
    }

    public void checkPoblacion(int poblacion){
        if(poblacion<=0){
            throw new IllegalArgumentException("ERROR: La población debe ser mayor que cero.");
        }
        if(poblacion > (2*(this.ancho+this.alto))){
            throw new IllegalArgumentException("ERROR: La población no puede superar el perímetro del bosque."); 
        }
    }
    

    private void repoblar(){
        generador=new Random();
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
            arboles[0] = new Arbol(especieAleatoria, posicionAleatoria);
            arbolesActuales++;
            especiesActuales++;
            System.out.println("Arbol "+arbolesActuales+":"+arboles[0]);
        }
        
        // Una vez creado el primer arbol, creamos el resto segun las restricciones.
        Especie especieRandom = null;
        Posicion posicionRandom = null;

        for(int i=1;i<poblacion;i++){
            while(especieRandom == null){
                especieRandom = generarEspecieAleatoria(arboles[i-1].getEspecie());
            }
            while(posicionRandom == null){
                posicionRandom = generarPosicionAleatoria(arboles[i-1].getPosicion());
            }
            arboles[i] = new Arbol(especieRandom, posicionRandom);
            arbolesActuales++;
            System.out.println("Arbol "+(arbolesActuales)+":"+arboles[i]);
            especieRandom=null;
            posicionRandom=null;
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
            boolean existe=false;
                for(int i=0;i<arbolesActuales;i++){
                    // Si algun arbol del array tiene la misma especie, se añade al array
                    if(arboles[i].getEspecie() == especieElegida){
                        existe=true;
                    }
                }
                if(existe){
                    return especieElegida;
                }else{
                    // Si la especie existe y hay menos de 4 especies, se añade la especie generada y aumenta la cantidad de especies actuales.
                    if(especiesActuales<MAX_ESPECIES){
                        especiesActuales++;
                        return especieElegida;
                }else{
                    return null;
            }
            // Si intenta meter una especie nueva y no podra meterla nunca, se actualizan las especies posibles pa nop generar la misma varias veces
        }
    }

    private Posicion generarPosicionAleatoria(Posicion posicion){
        Posicion posicionAleatoria = null;
        try{
            posicionAleatoria = new Posicion(generador.nextDouble(-ancho/2, ancho/2), generador.nextDouble(-alto, alto));
        }catch(IllegalArgumentException e){
            posicionAleatoria = null;
        }
            for(int i=0; i<arbolesActuales; i++){
            if(arboles[i].getPosicion() == posicionAleatoria){
                return null;
            }
        }return posicionAleatoria;
    }

    public void realizarCalculos(){
        Posicion centro=new Posicion(0,0);
        double distanciaMaxima = Double.MAX_VALUE;
        double distanciaMinima = Double.MIN_VALUE;
        arbolMasCentrado = null;
        arbolMasAlejado = null;
        int lejos=0, cerca=0;
        //Comparamos la distancia entre cada arbol y el centro.
        /*
        for(int i=0;i<arboles.length-1;i++){
            double distanciaActual = arboles[i].getPosicion().distancia(centro);
            if(distanciaActual <= distanciaMaxima){
                distanciaMaxima = distanciaActual;
                arbolMasCentrado = arboles[i];
                cerca=i;
            }
        }
        for(int i=0;i<arboles.length-1;i++){
            double distanciaActual = arboles[i].getPosicion().distancia(centro);
            if(distanciaActual > distanciaMinima){
                distanciaMinima = distanciaActual;
                arbolMasAlejado = arboles[i];
                lejos=i;
            }
        }
        */

        distanciaMaxima = Double.MIN_VALUE;
        distanciaMinima = Double.MAX_VALUE;
        for(int i=0;i<arboles.length;i++){
            double distanciaActual = arboles[i].getPosicion().distancia(centro);

            if(distanciaActual > distanciaMaxima){
                distanciaMaxima = distanciaActual;
                arbolMasAlejado = arboles[i];
                lejos=i;
            }

            if(distanciaActual < distanciaMinima){
                distanciaMinima = distanciaActual;
                arbolMasCentrado = arboles[i];
                cerca=i;
            }
        }

    
            //if(arboles[i].getPosicion().distancia(arboles[i].getPosicion()) > arboles[i+1].getPosicion().distancia(arboles[i+1].getPosicion())){
           //     arbolMasAlejado = new Arbol(arboles[i].getEspecie(),arboles[i].getPosicion());
            
        System.out.println("El arbol mas centrado es: Arbol "+(cerca+1)+": "+arbolMasCentrado);System.out.println("El arbol mas alejado es: Arbol "+(lejos+1)+": "+arbolMasAlejado);
    }

    public void mostrar(){
        for (int i = 0; i < arboles.length; i++) {
            System.out.println(arboles[i]);
        }
    }

    @Override
    public String toString() {
        return "Bosque [arbolMasAlejado=" + arbolMasAlejado + ", arbolMasCentrado=" + arbolMasCentrado + ", arboles="
                + Arrays.toString(arboles) + ", poblacion=" + poblacion + "]";
    }
}
    /*TODO: Error altura/anchura no valida al intentar meter demasiada poblacion. TEST. Si se crea una poblacion de 1 o 2 no calcula el mas alejado ni centrado. */