/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino;

/**
 *
 * @author oscar
 */
public class Ficha {
    
    private int NUM_DERECHA;
    private int NUM_IZQUIERDA;

    public Ficha(int NUM_IZQUIERDA, int NUM_DERECHA) {
        this.NUM_IZQUIERDA = NUM_IZQUIERDA;
        this.NUM_DERECHA = NUM_DERECHA;
       
    }

    
    public int getNUM_DERECHA() {
        return NUM_DERECHA;
    }

    public int getNUM_IZQUIERDA() {
        return NUM_IZQUIERDA;
    }


    
    public void darVueltaFicha (){
        
        int aux;
       aux= NUM_DERECHA;
       NUM_DERECHA=NUM_IZQUIERDA;
       NUM_IZQUIERDA= aux;
       
    }
    
    @Override
    public String toString(){
        
        StringBuilder toret = new StringBuilder();
       
        toret.append("[");
        toret.append(getNUM_IZQUIERDA());
        toret.append(" | ");
        toret.append(getNUM_DERECHA());
        toret.append("]");
       
        
        return toret.toString();
        
    }
}
