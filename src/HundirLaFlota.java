import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
public class HundirLaFlota {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre del jugador");
        String nombre = sc.nextLine();
        char[][] tablerojugador = new char[11][11];
        char[][] tablerodisparos = new char[11][11];
        char[][] tableropc = new char[11][11];
        int[] barcos = new int[]{4,3,3,3,2,2,1,1};
        int vidajugador = sumaCeldas(barcos);
        int vidapc = sumaCeldas(barcos);
        inicializarTablero(tablerojugador);
        inicializarTablero(tablerodisparos);
        inicializarTablero(tableropc);
        visualizarTablero(tablerojugador, tablerodisparos);

        colocarBarcosJugador(tablerojugador, tablerodisparos, barcos);
        colocarBarcosPC(tableropc,barcos);
        do {
            if (disparoJugador(tablerodisparos,tableropc)){
                vidapc = vidapc-1;
            }
            if (disparoPC(tablerojugador)){
                vidajugador = vidajugador-1;
            }
            visualizarTablero(tablerojugador,tablerodisparos);

        }while (vidajugador>0 && vidapc >0);
        if (vidajugador>vidapc){
            System.out.println();
            System.out.println("Enhorabuena " + nombre + " Has ganado");
        }
        else {
            System.out.println("Has perdido");
        }
    }

    public static boolean disparoJugador(char[][] tableroDisparosJugador, char[][] tableroPC) {
        // Metodo que implementa el disparo del jugador
        Scanner sc = new Scanner(System.in);
        int fila;
        int columna;
        do {
            System.out.println("Escribe la fila en la que quieres disparar (A-J)");
            fila = sc.next().toUpperCase().charAt(0)-'A';
            System.out.println("En que columna quieres disparar (0-9)");
            columna = sc.nextInt()+1;
        }while (columna>10 || fila > 9);
        if (tableroPC[fila][columna] == 'B'){
            tableroDisparosJugador[fila][columna] = 'T';
            return true;
        }
        else {
            tableroDisparosJugador[fila][columna] = '*';
        }


        return false;
    }


    public static boolean disparoPC(char[][] tableroJugador) {
        // Metodo que implementa el disparo del PC
        Random aleatorio = new Random();
        int fila;
        int columna;
        do {
            fila = aleatorio.nextInt(9 - 0 + 1) + 0;
            columna = aleatorio.nextInt(9 - 1 + 1) + 1;
        }while (tableroJugador[fila][columna] == 'T' ||tableroJugador[fila][columna] == '*' );
        if (tableroJugador[fila][columna] == 'B') {
            tableroJugador[fila][columna] = 'T';
            return true;
        }
        else {
            tableroJugador[fila][columna] = '*';
        }

        return false;

    }

    public static void inicializarTablero(char[][] tablero) {

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < 11; j++) {
                if (j == 0) {
                    if (i == tablero.length - 1)
                        tablero[i][j] = ' ';
                    else
                        tablero[i][j] = (char) (i + 'A');

                }
                else if (i == tablero.length -1) {
                    tablero[i][j] =(""+(j-1)).charAt(0);

                }
                else {
                    char simb = '~';
                    tablero[i][j] = simb;
                }
            }
        /* Este metodo inicializa cada tablero de la siguiente manera:

        A ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
        B ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
        C ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
        D ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
        E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
        F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
        G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
        H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
        I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
        J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
         0 1 2 3 4 5 6 7 8 9 */
        }
    }


        public static void visualizarTablero ( char[][] tablero, char[][] tableroDisparos){
            for (int i = 0; i < tablero.length; i++){
                for (int j = 0; j < tablero.length; j++){
                    System.out.print(tablero[i][j] + " ") ;}
                System.out.print("\t\t");
                for (int j = 0; j < tableroDisparos.length; j++){
                    System.out.print(tableroDisparos[i][j] + " ") ;}
                System.out.println();
            }


            //Este metodo visualiza el tablero por pantalla
        }



        public static int sumaCeldas ( int[] unVector){
            // Este metodo suma todos los valores de un vector
            int suma = 0;
            for (int i = 0; i< unVector.length; i++){
                suma += unVector[i];
            }


            return suma;
        }


        public static void colocarBarcosJugador ( char[][] tablero, char[][] tableroDisparos, int[] barcos){
            //Este metodo coloca los barcos pasados como vector dentro del tablero del Jugador
            //Los barcos son del siguiente tamaño:  4, 3, 3, 3, 2, 2, 1, 1
            Scanner sc =new Scanner(System.in);

            for (int i = 0; i < barcos.length; i++) {

                int fila;
                int columna;
                int orientacion;
                int longitudbarco;

                do {
                    System.out.println("Vamos a introducir un barco");
                    System.out.println("Introduce la coordenada Y (A-J)");
                    fila = sc.next().toUpperCase().charAt(0) -'A';
                    System.out.println("Introduce la coordenada X (0-9)");
                    columna = sc.nextInt()+1;
                    System.out.println("Introduce la orientación \n(0)Vertical\n(1)Horizontal");
                    orientacion = sc.nextInt();
                    longitudbarco = barcos[i];
                }
                while (hayColision(tablero,longitudbarco,fila,columna,orientacion) || orientacion<0 || orientacion>1 || columna<0 || columna>10 || fila<0 || fila > 9);




                colocarBarco(tablero, longitudbarco,fila,columna,orientacion);
                visualizarTablero(tablero,tableroDisparos);
            }

        }


        public static void colocarBarcosPC ( char[][] tablero, int[] barcos){
            //Este metodo coloca los barcos pasados como vector dentro del tablero del PC
            //Los barcos son del siguiente tamaño:  4, 3, 3, 3, 2, 2, 1, 1
            Random aleatorio=new Random();
            int fila;
            int columna;
            int orientacion;
            for (int i = 0; i < barcos.length; i++){
                do{
                    orientacion = aleatorio.nextInt(0,1);
                    columna = aleatorio.nextInt(9-1+1)+1;
                    fila = aleatorio.nextInt(9-0+1)+0;

                }
                while (hayColision(tablero,barcos[i],fila,columna,orientacion));
                if (orientacion == 1) {
                    for (int j = columna;j<columna+barcos[i];j++) {
                        tablero[fila][j] = 'B';
                    }

                }
                if(orientacion == 0) {
                    for (int j = fila;j<fila+barcos[i];j++) {
                        tablero[j][columna] = 'B';
                    }
                }
            }
    }




        public static boolean hayColision ( char[][] tablero, int longitudBarco, int fila, int columna, int orientacion)  {
            //Este metodo comprueba si hay algun barco en la zona del barco a colocar
            //Comprueba tambien si el barco a colocar se sale del tablero

            if (orientacion == 0) {
                if (fila + longitudBarco > tablero.length-1) {


                    return true;
                }
                else {
                    for (int i=columna; i<longitudBarco; i++){
                        if (tablero[i][columna] == 'B'){
                            return true;
                        }
                    }
                }
            }
            if (orientacion == 1){
                if (columna + longitudBarco >tablero.length) {

                    return true;

                }
                else {
                    for (int i = fila; i <longitudBarco; i++){
                        if (tablero[fila][i] == 'B')
                            return true;
                    }
                }

            }

            return false;
        }



        public static boolean colocarBarco ( char[][] tablero, int longitudBarco, int fila, int columna,
        int orientacion){
            //Este metodo coloca un barco en una posicion si este cabe en tablero y no coincide ninguna posicion con otro barco en la zon
            if (!hayColision(tablero,longitudBarco,fila,columna,orientacion)) {
                for (int i = 0; i < longitudBarco; i++) {
                    if (orientacion == 1) {
                        tablero[fila][columna + i] = 'B';

                    }
                    if(orientacion == 0) {
                        tablero[fila + i][columna] = 'B';
                    }
                }

            }


            return false;

        }

}



