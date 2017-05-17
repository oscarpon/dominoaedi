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
import java.util.Deque;
import java.util.LinkedList;
public class Mesa {
    
    private Deque <Ficha> tablero;

    public Mesa() {
        
        this.tablero = new LinkedList<>();
    }
    
    public void insertarFicha(Ficha f, int num){
   
        if (num == 1){//Inserta por la izquierda
            tablero.addFirst(f);
        }
        if (num == 2){//Inserta por la derecha
            tablero.addLast(f);
        }
           
    }
    
    public int consultarNumVecesNum(int n){//Recorre el tablero, y comprueba si los numeros de las fichas son iguales a n
                                           //si son iguales a n, aumenta toret
    int toret = 0;
    for (Ficha f: tablero){
        if (f.getNUM_DERECHA()== n){
            toret++;
        }
        if (f.getNUM_IZQUIERDA()== n){
            toret++;
        }
    }
    
       return toret;
    }
    
    public int consultarDerecha(){//Consultar numero de la derecha de la mesa
        
        return tablero.getLast().getNUM_DERECHA();
        
    }
    
    public int consultarIzquierda(){//Consultar numero de la izquierda de la mesa
        
        return tablero.getFirst().getNUM_IZQUIERDA();
        
    }
    
    @Override
    public String toString(){
        
      StringBuilder toret = new StringBuilder();
      
        toret.append("La fichas de la mesa son: ");
         for (Ficha f: tablero){
         toret.append(f);
         toret.append(" ··· ");
         }
      
      return toret.toString();
    }
   
}
