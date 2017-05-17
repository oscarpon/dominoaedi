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
import java.util.Random;
public class Monton {
    
    private Ficha [] arrayFichas;
    private final int MAX_FICHAS = 28;
    private int numFichas;

    public Monton() {
        
        arrayFichas = new Ficha[MAX_FICHAS];
        this.numFichas = 0;
    
        for (int i = 0; i <= 6; i++) {//Crea las fichas
            for (int j = i; j <= 6; j++) {
                Ficha F = new Ficha(i,j);
                arrayFichas[numFichas++]=F;
            }
        }
       barajar();//Después de crear las fichas las baraja para que no estén ordenadas
    
    }

    public int getNumFichas() {
        return numFichas;
    }
   
    public Ficha cogerFicha(){//Coge ficha del monton y disminuye su tamaño en 1
        
        return arrayFichas[--numFichas];
        
    }
    
    private void barajar(){
        
        Random barajar = new Random();
        
        for(int primera = 0; primera < arrayFichas.length; primera++){
            
            int segunda = barajar.nextInt(MAX_FICHAS);
            Ficha temp = arrayFichas[primera];
            
            arrayFichas[primera] = arrayFichas[segunda];
            arrayFichas[segunda] = temp;
            
        }
        
    }
  
}
