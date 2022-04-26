package dominio;

import modulo.Analizador;
import java.util.Random;
import java.util.stream.*;
import java.io.*;
import java.util.Arrays;
import java.util.function.Function;


    public class Estudio {

        /**
         * Análisis empírico de los algoritmos de ordenación y búsqueda.
         * Ejemplares: array de elementos aleatorios, array oredenado y array en orden inverso. Tamaños 100, 1000, 10000, 100000, 1000000, 10000000.
         * El resultado del análisis, los tiempos de ejecución de los algoritmos para los distintos ejemplares, se graba en el fichero tiempos.txt, en el directorio del proyecto
         */
        public static void desordena(long[] L) {  // desordena un array
            Random random = new Random(L.length);  // genera un número aleatorio
            for (int i = 0; i < L.length; i++) {    // intercambia elementos aleatoriamente
                int p = random.nextInt(L.length);   // genera un número aleatorio
                long aux = L[i];                     // intercambia los elementos
                L[i] = L[p];
                L[p] = aux;
            }
        }
        public static long [] quicksort(long[] A, int izq, int der) {

            long pivote=A[izq]; // tomamos primer elemento como pivote
            int i=izq;         // i realiza la búsqueda de izquierda a derecha
            int j=der;         // j realiza la búsqueda de derecha a izquierda
            long aux;

            while(i < j){                          // mientras no se crucen las búsquedas
                while(A[i] <= pivote && i < j) i++; // busca elemento mayor que pivote
                while(A[j] > pivote) j--;           // busca elemento menor que pivote
                if (i < j) {                        // si no se han cruzado
                    aux= A[i];                      // los intercambia
                    A[i]=A[j];
                    A[j]=aux;
                }
            }

            A[izq]=A[j];      // se coloca el pivote en su lugar de forma que tendremos
            A[j]=pivote;      // los menores a su izquierda y los mayores a su derecha

            if(izq < j-1)
                quicksort(A,izq,j-1);          // ordenamos subarray izquierdo
            if(j+1 < der)
                quicksort(A,j+1,der);          // ordenamos subarray derecho
            return A;
        }

        public static long [] mergesort(long []A,int izq, int der){     // método de ordenación por mezcla
            if (izq < der){                                             // si no se ha llegado al final del array
                int m=(izq+der)/2;                                      // calcula el centro del array
                mergesort(A,izq, m);                                    // ordena la primera mitad
                mergesort(A,m+1, der);                              // ordena la segunda mitad
                merge(A,izq, m, der);                                   // une las dos mitades en el array original
            }
            return A;
        }
        public static long[] merge(long []A,int izq, int m, int der){
            int i, j, k;
            long [] B = new long[A.length]; //array auxiliar
            for (i=izq; i<=der; i++)      //copia ambas mitades en el array auxiliar
                B[i]=A[i];

            i=izq; j=m+1; k=izq;

            while (i<=m && j<=der) //copia el siguiente elemento más grande
                if (B[i]<=B[j])
                    A[k++]=B[i++];
                else
                    A[k++]=B[j++];

            while (i<=m)         //copia los elementos que quedan de la
                A[k++]=B[i++]; //primera mitad (si los hay)
            return A;
        }
        public static void main(String[] args) {

            Function<long[], long[]> BusquedaLineal = L ->
            {                                                   // método de ordenación por búsqueda lineal
                long[] resultado = new long[L.length];          // crea array de resultado
                for (int i = 0; i < L.length; i++) {            // recorre el array original
                    resultado[i] = L[i];                  // copia el elemento en el array de resultado
                }
                return resultado;
            };

            // funcion busqueda binaria con un array de longs
            Function<long[], long[]> BusquedaBinaria = L ->
            {                                   // método de ordenación por búsqueda binaria
                long[] resultado = new long[L.length];       // array de longs
                for (int i = 0; i < L.length; i++) {       // copia el array original
                    resultado[i] = L[i];
                }
                Arrays.sort(resultado);                 // ordena el array  de longs
                return resultado;                        // devuelve el array de longs ordenado
            };

            Function<long[], long[]> BubbleSort = L ->
            {                                           // funcion de ordenación por burbuja
                for (int i = 1; i < L.length; i++)      // recorre el array
                    for (int j = L.length - 1; j >= i; j--)     // recorre el array desde el final
                        if (L[j - 1] > L[j]) {          // si el elemento anterior es mayor que el actual
                            long Aux = L[j];            // intercambia los elementos
                            L[j] = L[j - 1];        // intercambia los elementos
                            L[j - 1] = Aux;        // intercambia los elementos
                        }
                return L;
            };

            Function<long[], long[]> SelectSort = L ->
            {                                                 // funcion de ordenación por selección
                for (int i = 0; i < L.length - 1; i++) {     // recorre el array
                    int pmin = i;                           //posición elemento minimo enre i y lenght -1
                    for (int j = i + 1; j < L.length; j++)   // recorre el array desde i+1 hasta lenght
                        if (L[j] < L[pmin]) {                 // si el elemento actual es menor que el elemento minimo
                            pmin = j;                        // actualiza la posición del elemento minimo
                        }
                    long Aux = L[pmin];             // intercambia los elementos
                    L[pmin] = L[i];                // intercambia los elementos
                    L[i] = Aux;                   // intercambia los elementos

                }
                return L;
            };

            Function<long[], long[]> DirectInsertion = L ->
            {                                       // funcion de ordenación por inserción directa
                for (int i = 1; i < L.length; i++) {     // recorre el array
                    long carta = L[i];                 // guarda el elemento actual en una variable
                    int j = i - 1;                    // inicializa la posición del elemento anterior
                    while (j >= 0 && L[j] > carta) {     // mientras la posición del elemento anterior sea mayor que la posición actual
                        L[j + 1] = L[j];        // intercambia los elementos
                        j = j - 1;             // decrementa la posición del elemento anterior
                    }
                    L[j + 1] = carta;             // intercambia los elementos

                }
                return L;
            };

            Function<long[], long[]> MergeSort = L ->
            {                                           // funcion de ordenación por mezcla
            mergesort(L, 0, L.length - 1);        // llama a la funcion mergesort
            return L;                           // retorna el array ordenado
            };

            Function<long[], long[]>  QuickSort = L->
            {                                           // funcion de ordenación por mezcla
                quicksort(L,0,L.length-1);             // llama a la funcion quicksort
                return L;                                       // retorna el array ordenado
            };

            long[][] JuegoPruebasAleatorio = new long[6][];
            long[][] JuegoPruebasOrdenado = new long[6][];
            long[][] JuegoPruebasInverso = new long[6][];
            long tam = 100;

            Random random = new Random();

            for (int i = 0; i < 6; i++) {
                JuegoPruebasAleatorio[i] = Stream.iterate((long) 0, x -> x + 1).limit(tam).mapToLong(x -> x).toArray();
                desordena(JuegoPruebasAleatorio[i]);
                JuegoPruebasOrdenado[i] = Stream.iterate((long) 0, x -> x + 1).limit(tam).mapToLong(x -> x).toArray();
                JuegoPruebasInverso[i] = Stream.iterate(tam - 1, x -> x - 1).limit(tam).mapToLong(x -> x).toArray();
                tam *= 10;
            }
            try {
                tam = 100;
                FileWriter fichero = new FileWriter("tiempos.txt");
                PrintWriter out = new PrintWriter(fichero);
                long[] resultado;
                out.println("BubbleSort");
                for (int i = 0; i < 3; i++) {
                    out.print(tam + "\t");
                    //System.out.println("tamaño: " + tam);
                    resultado = Analizador.analiza(JuegoPruebasAleatorio[i], BubbleSort, out);
                    //System.out.println("aleatorio: "+Arrays.toString(JuegoPruebasAleatorio[i]));
                    //System.out.println("ordenado: "+Arrays.toString(resultado));

                    resultado = Analizador.analiza(JuegoPruebasOrdenado[i], BubbleSort, out);
                    //System.out.println("ya ordenado: "+Arrays.toString(JuegoPruebasOrdenado[i]));
                    //System.out.println("ordenado: "+Arrays.toString(resultado));

                    resultado = Analizador.analiza(JuegoPruebasInverso[i], BubbleSort, out);
                    //System.out.println("inverso: "+Arrays.toString(JuegoPruebasInverso[i]));
                    // System.out.println("ordenado: "+Arrays.toString(resultado));
                    out.println();
                    tam *= 10;
                }
                out.println("SelectSort");
                tam = 100;
                for (int i = 0; i < 3; i++) {
                    out.print(tam + "\t");
                    //System.out.println("tamaño: " + tam);
                    resultado = Analizador.analiza(JuegoPruebasAleatorio[i], SelectSort, out);
                    //System.out.println("aleatorio: " + Arrays.toString(JuegoPruebasAleatorio[i]));
                    //System.out.println("ordenado: " + Arrays.toString(resultado));
                    resultado = Analizador.analiza(JuegoPruebasOrdenado[i], SelectSort, out);
                    //System.out.println("ya ordenado: " + Arrays.toString(JuegoPruebasOrdenado[i]));

                    //System.out.println("ordenado: " + Arrays.toString(resultado));
                    resultado = Analizador.analiza(JuegoPruebasInverso[i], SelectSort, out);

                    //System.out.println("inverso: " + Arrays.toString(JuegoPruebasInverso[i]));
                    //System.out.println("ordenado: " + Arrays.toString(resultado));
                    out.println();
                    tam *= 10;
                }
                out.println("DirectInsertion");
                tam = 100;
                for (int i = 0; i < 3; i++) {
                    out.print(tam + "\t");
                    //System.out.println("tamaño: " + tam);
                    resultado = Analizador.analiza(JuegoPruebasAleatorio[i], BubbleSort, out);
                    //System.out.println("aleatorio: " + Arrays.toString(JuegoPruebasAleatorio[i]));
                    //System.out.println("ordenado: " + Arrays.toString(resultado));

                    resultado = Analizador.analiza(JuegoPruebasOrdenado[i], BubbleSort, out);
                    //System.out.println("ya ordenado: " + Arrays.toString(JuegoPruebasOrdenado[i]));
                    //System.out.println("ordenado: " + Arrays.toString(resultado));

                    resultado = Analizador.analiza(JuegoPruebasInverso[i], BubbleSort, out);
                    //System.out.println("inverso: " + Arrays.toString(JuegoPruebasInverso[i]));
                    //System.out.println("ordenado: " + Arrays.toString(resultado));
                    out.println();
                    tam *= 10;
                }
                out.println("QuickSort");
                tam = 100;
                for (int i = 0; i < 3; i++) {
                    out.print(tam + "\t");
                    //System.out.println("tamaño: " + tam);
                    resultado = Analizador.analiza(JuegoPruebasAleatorio[i], QuickSort, out);
                    //System.out.println("aleatorio: "+Arrays.toString(JuegoPruebasAleatorio[i]));
                    //System.out.println("ordenado: "+Arrays.toString(resultado));

                    resultado = Analizador.analiza(JuegoPruebasOrdenado[i], QuickSort, out);
                    //System.out.println("ya ordenado: "+Arrays.toString(JuegoPruebasOrdenado[i]));
                    //System.out.println("ordenado: "+Arrays.toString(resultado));

                    resultado = Analizador.analiza(JuegoPruebasInverso[i], QuickSort, out);
                    //System.out.println("inverso: "+Arrays.toString(JuegoPruebasInverso[i]));
                    //System.out.println("ordenado: "+Arrays.toString(resultado));
                    out.println();
                    tam *= 10;
                }

                out.println("MergeSort");
                tam = 100;
                for (int i = 0; i < 3; i++) {
                    out.print(tam + "\t");
                    //System.out.println("tamaño: " + tam);
                    resultado = Analizador.analiza(JuegoPruebasAleatorio[i], MergeSort, out);
                    //System.out.println("aleatorio: "+Arrays.toString(JuegoPruebasAleatorio[i]));
                    //System.out.println("ordenado: "+Arrays.toString(resultado));

                    resultado = Analizador.analiza(JuegoPruebasOrdenado[i], MergeSort, out);
                    //System.out.println("ya ordenado: "+Arrays.toString(JuegoPruebasOrdenado[i]));
                    //System.out.println("ordenado: "+Arrays.toString(resultado));

                    resultado = Analizador.analiza(JuegoPruebasInverso[i], MergeSort, out);
                    //System.out.println("inverso: "+Arrays.toString(JuegoPruebasInverso[i]));
                    //System.out.println("ordenado: "+Arrays.toString(resultado));
                    out.println();
                    tam *= 10;
                }
                out.println("BusquedaLineal");
                tam = 100;
                for (int i = 0; i < 3; i++) {
                    out.print(tam + "\t");
                    //System.out.println("tamaño: " + tam);
                    resultado = Analizador.analiza(JuegoPruebasAleatorio[i], BusquedaLineal, out);
                    //System.out.println("aleatorio: "+Arrays.toString(JuegoPruebasAleatorio[i]));
                    //System.out.println("ordenado: "+Arrays.toString(resultado));

                    resultado = Analizador.analiza(JuegoPruebasOrdenado[i], BusquedaLineal, out);
                    //System.out.println("ya ordenado: "+Arrays.toString(JuegoPruebasOrdenado[i]));
                    //System.out.println("ordenado: "+Arrays.toString(resultado));

                    resultado = Analizador.analiza(JuegoPruebasInverso[i], BusquedaLineal, out);
                    //System.out.println("inverso: "+Arrays.toString(JuegoPruebasInverso[i]));
                    //System.out.println("ordenado: "+Arrays.toString(resultado));
                    out.println();
                    tam *= 10;
                }
                out.println("BusquedaBinaria");
                tam = 100;
                for (int i = 0; i < 3; i++) {
                    out.print(tam + "\t");
                    //System.out.println("tamaño: " + tam);
                    resultado = Analizador.analiza(JuegoPruebasAleatorio[i], BusquedaBinaria, out);
                    //System.out.println("aleatorio: "+Arrays.toString(JuegoPruebasAleatorio[i]));
                    //System.out.println("ordenado: "+Arrays.toString(resultado));

                    resultado = Analizador.analiza(JuegoPruebasOrdenado[i], BusquedaBinaria, out);
                    //System.out.println("ya ordenado: "+Arrays.toString(JuegoPruebasOrdenado[i]));
                    //System.out.println("ordenado: "+Arrays.toString(resultado));

                    resultado = Analizador.analiza(JuegoPruebasInverso[i], BusquedaBinaria, out);
                    //System.out.println("inverso: "+Arrays.toString(JuegoPruebasInverso[i]));
                    //System.out.println("ordenado: "+Arrays.toString(resultado));
                    out.println();
                    tam *= 10;
                }
                out.close();
            } catch (Exception e) {
                System.out.print("error en fichero");
            }
            //System.out.print("Fin");
            Lectura.leerTiempos();
        }
    }


