/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino;

/**
 *
 * @author Usuario
 */
public class Mano {
    
    private int numFichas;
    private Ficha fichasJugador [];
    private int puntuacion;

    
    public Mano() {
       
        this.numFichas=0;
        fichasJugador = new Ficha [21];
    }

    public int getNumFichas() {
        
        return numFichas;
        
    }
 
    //Saca una ficha de la mano y la elimina
    public Ficha sacarFicha (int pos){
        
        Ficha f = fichasJugador[pos];
       
        for(int i = pos + 1; i < getNumFichas(); i++){
            fichasJugador[i - 1] = fichasJugador[i];
        }
        numFichas--;
       
        return f;
             
    }
    
   //Visualiza la ficha, pero no la elimina de la mano
    public Ficha verFicha ( int pos ){
        
        Ficha f = fichasJugador[pos];
        
        return f;
        
    }
    
    //Coge ficha del montón para añadirla a la mano del jugador
    public void cogerFicha (Ficha f){
        
        fichasJugador [numFichas++] = f;
        
    }
    
    public int puntuacionTotal(){
        puntuacion = 0; //Iniciamos en 0 para que cada vez que empiece una nueva ronda los contadores estén en 0;
        for(int i = 0; i < numFichas; i++){
           puntuacion +=  fichasJugador[i].getNUM_DERECHA() + fichasJugador[i].getNUM_IZQUIERDA();
        }
        return puntuacion;
    }
     
    //Visualiza las fichas de la mano 
    @Override
    public String toString(){
        StringBuilder toret = new StringBuilder();
        
        for (int i=0; i < numFichas; i++){
            toret.append(" \t"+ i + "." + fichasJugador[i].toString());
        }
        return toret.toString();
        
    }
}
