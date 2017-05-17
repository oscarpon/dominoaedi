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
public class Jugador {
    
    private String nombre;
    private Mano mano;
    private boolean soyMano;
 

    public Jugador(String nombre, boolean soyMano) {
        this.nombre = nombre;
        this.mano = new Mano ();
        this.soyMano = soyMano;
    }

    public String getNombre() {
        return nombre;
    }
    
    public boolean getSoyMano(){
        return soyMano;
    }

    public Mano getMano() {
        return mano;
    }
   
    public void setSoyMano(boolean soyMano) {
        this.soyMano = soyMano;
    }
   
    public Ficha elegirFicha(int pos){
        
          Ficha f= mano.sacarFicha(pos);
          
          return f;
    }
    
    public Ficha verLaFicha(int pos){
        
        Ficha f = mano.verFicha(pos);
         
        return f;
    }
    
    public void añadirFichaMano (Ficha f){
        mano.cogerFicha(f);
    }
   
    //Visualiza las fichas de la mano del jugador
    public String visualizarFichasMano(){
        return mano.toString();
    }

}
