package modelo;

public class Posicion {
    private double x , y = 0;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public Posicion(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Posicion(Posicion p){
        this(p.getX(), p.getY());
    }

    public double distancia(Posicion posicion){
        if(posicion == null){
            throw new NullPointerException("No se ha podido relaizar la operacion.");
        }else{
                double diferenciaX = this.x - posicion.getX();
                double diferenciaY = this.y - posicion.getY();
                double distanciaFinal = Math.sqrt(Math.pow(diferenciaX , 2) + Math.pow(diferenciaY , 2));
                if(Double.isInfinite(distanciaFinal)){
                    throw new IllegalArgumentException("Distancia infinita!");
                }

                if(Double.isNaN(distanciaFinal)){
                throw new IllegalArgumentException("La distancia resultante no es un número.");
                }
                
                return distanciaFinal;
        }

    }
}
