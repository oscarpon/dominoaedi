/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino;
import java.util.Stack;
import java.util.Scanner;
/**
 *
 * @author oscar
 */
public class Domino {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner scn = new Scanner (System.in);
         
        Jugador [] jugadores;
        String nombre;
        int numJugadores;
        boolean finalJuego;
        finalJuego = false;
        int elegirLado;
        int eleccion;
        int elegirFicha;
        int turno = 0;//Inicializamos una variable turno para controlar la primera jugada, porque va a ser tratada de una forma diferente al resto
        Mesa m = new Mesa();
        int toret=0;
        Ficha f;
        
        Stack<Ficha> basura = new Stack<>();//Creamos una pila para evitar que si el jugador escoge una ficha, y no la inserta finalmente, no se pierda de la mano
                                            //Porque queda guardada en la pila y luego la recupera cuando le vuelva a tocar el turno. 
        Monton monton = new Monton ();
        
        System.out.println ("Bienvenido al Dominó");
        System.out.println ("Introduce el numero de jugadores: ");
        
        do {
            System.out.println ("2 Jugadores | 3 Jugadores | 4 Jugadores ");
            numJugadores = leerEnteros();
        }while (numJugadores > 4 || numJugadores < 2 );
        
        jugadores = new Jugador [numJugadores];//Creamos el array según el numero de jugadores que introduzca
       
        for (int i= 0; i < numJugadores; i++){
            System.out.println ("Introduce tu nombre, jugador " + i + ": ");
            nombre = leerString();//Lee los nombres de los jugadores
            jugadores[i] = new Jugador (nombre, false);//Guarda los jugadores en el array de jugadores, inicializa todos los jugadores a que no sean mano
           
        }
        
        jugadores[0].setSoyMano(true);//Sentencia para que el jugador 1 sea mano
        
        repartirFichas (jugadores, monton);
        int i=0;
       
        do{//Final de juego
                     
            do{
                    System.out.println("*******************************");
                    System.out.println("MESA: "+ m.toString()); //Muestra todas las fichas de la mesa en la consola
                    System.out.println("*******************************");
                    System.out.println ("TURNO: " + jugadores[i].getNombre() + " 1. Jugar ficha | 2. Coger ficha del montón ");
                    System.out.println("Puntuacion: " + jugadores[i].getMano().puntuacionTotal());//Cada vez que le toca el turno a un jugador, muestra la puntuación actual de este
                    System.out.println (jugadores[i].visualizarFichasMano());//Muestra las fichas de la mano del jugador
           
            
                    System.out.println(" Introduce la opción a jugar: ");
                    eleccion = leerEnteros();
            
            }while(eleccion != 1 && eleccion != 2);//Repite siempre que introduzca números que no sean ni 1 ni 2 
           
            if(eleccion == 1){//si la elección es 1
             
                do{ //Controla si el numero que introduces está dentro del array
                    
                        
                    System.out.println(" Escoge la ficha a jugar: ");
                    elegirFicha = leerEnteros();
                    basura.push(jugadores[i].verLaFicha(elegirFicha));//Guarda las fichas que saca el jugador en una pila llamada basura para evitar la pérdida de algunas fichas
                    f = jugadores[i].elegirFicha(elegirFicha);
                           
                }while(elegirFicha > jugadores[i].getMano().getNumFichas() || elegirFicha < 0);
                
                        
                 
                if(turno == 0){//Como el turno es el primero, entonces va a poder insertar
                                      
                    m.insertarFicha(f, 2);//Inserta la ficha en la mesa, y por defecto, la inserta por la derecha (2)
                    turno = 5;//para que no vuelva a entrar en este if
                    
                }else{
                    
                        if(cierre(m) == true){//Comprueba si la condición de cierre es true, para que así el juego no se siga ejecuntando
                            finalJuego = true;
                        }
                        if(m.consultarIzquierda() == 0 && f.getNUM_IZQUIERDA() == 0){//Para evitar algunos problemas en la ejecución, la opción de darle la vuelta a una ficha con 0, ha sido tratada de una forma especial
                            f.darVueltaFicha();//Le damos siempre la vuelta, ya que el 0 siempre esta a la izquierda
                            m.insertarFicha(f, 1);//Automatcamente la inserta a la izquierda de la mesa(1)
                        }
                        
                        else if (m.consultarIzquierda() != f.getNUM_DERECHA() && m.consultarDerecha() != f.getNUM_IZQUIERDA()
                                && m.consultarDerecha() != f.getNUM_IZQUIERDA() && m.consultarDerecha() != f.getNUM_DERECHA()){//Si la ficha no se puede insertar
                            System.err.println("No se puede insertar la ficha");
                            jugadores[i].añadirFichaMano(basura.pop());//Recupera la ficha de la pila, porque el jugador ha elegido una ficha, por lo que ya ha sido eliminada del array
                        }
                        else{
                           
                            basura.remove(basura.pop());//Si la inserta correctamente, la elimina de la pila de basura
                            System.out.println ("1. INSERTAR IZQUIERDA | 2. INSERTA DERECHA");
                            elegirLado = leerEnteros();
                        
                            if (elegirLado ==1){
                                if (m.consultarIzquierda() == (f.getNUM_DERECHA())){
                                m.insertarFicha(f, 1);
                         
                                }
                                else if (m.consultarIzquierda()== f.getNUM_IZQUIERDA()){
                                f.darVueltaFicha();
                                m.insertarFicha(f, 1);
                                
                                }   
                            }
                            else{
                                if(m.consultarDerecha() == f.getNUM_IZQUIERDA()){
                                m.insertarFicha(f, 2);
                       
                                }
                                else if (m.consultarDerecha()== (f.getNUM_DERECHA())){
                                    f.darVueltaFicha();
                                    m.insertarFicha(f, 2);

                                }                     
                            }
                    
                        }
               
                }
                                 
            }
            if (eleccion==2){
                   
                if(monton.getNumFichas() == 0){
                    System.err.println(" No quedan fichas en el monton ");
                }else{
                    System.out.println("*******************");
                    System.out.println("Tus fichas ahora son: ");
                    jugadores[i].añadirFichaMano(monton.cogerFicha());//Coge una ficha aleatoria del monton
                    System.out.println(jugadores[i].visualizarFichasMano());//Visualiza las fichas de la mano actualizadas
                    System.out.println("******************");
               
                }  
            }
            
            if( jugadores[i].getMano().getNumFichas() == 0 ){//Cuando un jugador se queda sin fichas gana el juego
                    
                System.out.println("El ganador del juego es: " + jugadores[i].getNombre());
                System.out.println("FIN DEL JUEGO");
                finalJuego = true;
                
            } 
            else if(cierre(m) == true){//en el caso de que tengamos el mismo numero en los extremos, y ese numero este repetido 8 veces. Comprobamos las 
                                      //puntuaciones de los jugadores y guardamos la minima
                int minima = 0;
                int inicio = jugadores[0].getMano().puntuacionTotal();
                inicio = minima;
                for (int j = 0; j < jugadores.length; j++) {
                    if(jugadores[j].getMano().puntuacionTotal() < minima){
                        int aux = jugadores[j].getMano().puntuacionTotal();
                        aux = minima;
                    }
                }
                System.out.println("El jugador ganador es el de puntuación : " + minima);
                for (int k = 0; k < 10; k++) {
                     if(jugadores[0].getMano().puntuacionTotal() == jugadores[k].getMano().puntuacionTotal()){//En el caso de que dos jugadores ttengan la misma puntuación, gana la mano
                    System.out.println("El jugador ganador es: " + jugadores[0].getNombre());
                    }
                }
               
            }
                
            if (i < numJugadores-1){
                i++;
            }
            else{
                i=0;
            }   
            
        }while (finalJuego != true ); 
        
    }
    
    
    
    public static int leerEnteros(){//Para leer enteros y evitar errores a la hora de introducir numeros
        boolean repite;
        int toRet = 0;   
        Scanner sc = new Scanner (System.in);
            do{
                repite= false;
                try{
                    toRet = Integer.parseInt(sc.nextLine());
                }
                catch (NumberFormatException exc){
                    System.err.println("El valor introducido no es correcto");
                    repite = true;
                }
            }while (repite);
          
        return toRet;
    }
    
    public static String leerString(){//Leer cadenas de caracteres
       
        String toRet; 
        Scanner sc = new Scanner (System.in);
     
        toRet = sc.nextLine();
             
        return toRet;
    }
    
    public static void repartirFichas(Jugador [] j, Monton m){//Metodo para repartir fichas
        
        for (int i=0; i < j.length;i++){
            for (int k=0; k < 7; k++){
                Ficha c = m.cogerFicha();//coge ficha del monton
                j[i].añadirFichaMano(c);//añade la  ficha c del monton a la mano
            }
        }
    }
    
    public static boolean cierre(Mesa m){//Funcion para controlar que los numeros que estén en los extremos, sean iguales, y además 
                                         //estén ya 8 veces, por lo que nadie va a poder insertar fichas
        if(m.consultarDerecha() == m.consultarIzquierda()){
            
            if(m.consultarNumVecesNum(m.consultarDerecha()) == 7){
                return true;
            }
        }else if(m.consultarNumVecesNum(m.consultarDerecha()) == 7 && m.consultarNumVecesNum(m.consultarIzquierda()) == 7){
            return true;
        }
        
        return false;
    }
    
}
