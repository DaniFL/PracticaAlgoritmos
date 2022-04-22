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
        public static void desordena(long[] L) {
            Random random = new Random(L.length);
            for (int i = 0; i < L.length; i++) {
                int p = random.nextInt(L.length);
                long aux = L[i];
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

        public static long [] mergesort(long []A,int izq, int der){
            if (izq < der){
                int m=(izq+der)/2;
                mergesort(A,izq, m);
                mergesort(A,m+1, der);
                merge(A,izq, m, der);
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

            Function<long[], long[]> BusquedaLineal = L -> {
                long[] resultado = new long[L.length];
                for (int i = 0; i < L.length; i++) {
                    resultado[i] = L[i];
                }
                return resultado;
            };

            // funcion busqueda binaria con un array de longs
            Function<long[], long[]> BusquedaBinaria = L -> {
                long[] resultado = new long[L.length];  // array de longs
                for (int i = 0; i < L.length; i++) {    // copia el array original
                    resultado[i] = L[i];
                }
                Arrays.sort(resultado);                 // ordena el array  de longs
                return resultado;                        // devuelve el array de longs ordenado
            };

            Function<long[], long[]> BubbleSort = L ->
            {
                for (int i = 1; i < L.length; i++)
                    for (int j = L.length - 1; j >= i; j--)
                        if (L[j - 1] > L[j]) {
                            long Aux = L[j];
                            L[j] = L[j - 1];
                            L[j - 1] = Aux;
                        }
                return L;
            };

            Function<long[], long[]> SelectSort = L ->
            {
                for (int i = 0; i < L.length - 1; i++) {
                    int pmin = i; //posición elemento minimo enre i y lenght -1
                    for (int j = i + 1; j < L.length; j++)
                        if (L[j] < L[pmin]) {
                            pmin = j;
                        }
                    long Aux = L[pmin];
                    L[pmin] = L[i];
                    L[i] = Aux;

                }
                return L;
            };

            Function<long[], long[]> DirectInsertion = L ->
            {
                for (int i = 1; i < L.length; i++) {
                    long carta = L[i];
                    int j = i - 1;
                    while (j >= 0 && L[j] > carta) {
                        L[j + 1] = L[j];
                        j = j - 1;
                    }
                    L[j + 1] = carta;

                }
                return L;
            };

            Function<long[], long[]>  MergeSort = L->
                    mergesort(L,0,L.length-1);


            Function<long[], long[]>  QuickSort = L->
                    quicksort(L,0,L.length-1);


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
                    System.out.println("tamaño: " + tam);
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
                    System.out.println("tamaño: " + tam);
                    resultado = Analizador.analiza(JuegoPruebasAleatorio[i], SelectSort, out);
                    System.out.println("aleatorio: " + Arrays.toString(JuegoPruebasAleatorio[i]));
                    System.out.println("ordenado: " + Arrays.toString(resultado));

                    resultado = Analizador.analiza(JuegoPruebasOrdenado[i], SelectSort, out);
                    System.out.println("ya ordenado: " + Arrays.toString(JuegoPruebasOrdenado[i]));
                    System.out.println("ordenado: " + Arrays.toString(resultado));

                    resultado = Analizador.analiza(JuegoPruebasInverso[i], SelectSort, out);
                    System.out.println("inverso: " + Arrays.toString(JuegoPruebasInverso[i]));
                    System.out.println("ordenado: " + Arrays.toString(resultado));
                    out.println();
                    tam *= 10;
                }
                out.println("DirectInsertion");
                tam = 100;
                for (int i = 0; i < 3; i++) {
                    out.print(tam + "\t");
                    System.out.println("tamaño: " + tam);
                    resultado = Analizador.analiza(JuegoPruebasAleatorio[i], BubbleSort, out);
                    System.out.println("aleatorio: " + Arrays.toString(JuegoPruebasAleatorio[i]));
                    System.out.println("ordenado: " + Arrays.toString(resultado));

                    resultado = Analizador.analiza(JuegoPruebasOrdenado[i], BubbleSort, out);
                    System.out.println("ya ordenado: " + Arrays.toString(JuegoPruebasOrdenado[i]));
                    System.out.println("ordenado: " + Arrays.toString(resultado));

                    resultado = Analizador.analiza(JuegoPruebasInverso[i], BubbleSort, out);
                    System.out.println("inverso: " + Arrays.toString(JuegoPruebasInverso[i]));
                    System.out.println("ordenado: " + Arrays.toString(resultado));
                    out.println();
                    tam *= 10;
                }
                out.println("QuickSort");
                tam = 100;
                for (int i = 0; i < 3; i++) {
                    resultado = Analizador.analiza(JuegoPruebasAleatorio[i], QuickSort, out);
                    System.out.println("aleatorio: "+Arrays.toString(JuegoPruebasAleatorio[i]));
                    System.out.println("ordenado: "+Arrays.toString(resultado));

                    resultado = Analizador.analiza(JuegoPruebasOrdenado[i], QuickSort, out);
                    System.out.println("ya ordenado: "+Arrays.toString(JuegoPruebasOrdenado[i]));
                    System.out.println("ordenado: "+Arrays.toString(resultado));

                    resultado = Analizador.analiza(JuegoPruebasInverso[i], QuickSort, out);
                    System.out.println("inverso: "+Arrays.toString(JuegoPruebasInverso[i]));
                    System.out.println("ordenado: "+Arrays.toString(resultado));
                    out.println();
                    tam *= 10;
                }

                out.println("MergeSort");
                tam = 100;
                for (int i = 0; i < 3; i++) {
                    resultado = Analizador.analiza(JuegoPruebasAleatorio[i], MergeSort, out);
                    System.out.println("aleatorio: "+Arrays.toString(JuegoPruebasAleatorio[i]));
                    System.out.println("ordenado: "+Arrays.toString(resultado));

                    resultado = Analizador.analiza(JuegoPruebasOrdenado[i], MergeSort, out);
                    System.out.println("ya ordenado: "+Arrays.toString(JuegoPruebasOrdenado[i]));
                    System.out.println("ordenado: "+Arrays.toString(resultado));

                    resultado = Analizador.analiza(JuegoPruebasInverso[i], MergeSort, out);
                    System.out.println("inverso: "+Arrays.toString(JuegoPruebasInverso[i]));
                    System.out.println("ordenado: "+Arrays.toString(resultado));
                    out.println();
                    tam *= 10;
                }
                out.println("BusquedaLineal");
                tam = 100;
                for (int i = 0; i < 3; i++) {
                    resultado = Analizador.analiza(JuegoPruebasAleatorio[i], BusquedaLineal, out);
                    System.out.println("aleatorio: "+Arrays.toString(JuegoPruebasAleatorio[i]));
                    System.out.println("ordenado: "+Arrays.toString(resultado));

                    resultado = Analizador.analiza(JuegoPruebasOrdenado[i], BusquedaLineal, out);
                    System.out.println("ya ordenado: "+Arrays.toString(JuegoPruebasOrdenado[i]));
                    System.out.println("ordenado: "+Arrays.toString(resultado));

                    resultado = Analizador.analiza(JuegoPruebasInverso[i], BusquedaLineal, out);
                    System.out.println("inverso: "+Arrays.toString(JuegoPruebasInverso[i]));
                    System.out.println("ordenado: "+Arrays.toString(resultado));
                    out.println();
                    tam *= 10;
                }
                out.println("BusquedaBinaria");
                tam = 100;
                for (int i = 0; i < 3; i++) {
                    resultado = Analizador.analiza(JuegoPruebasAleatorio[i], BusquedaBinaria, out);
                    System.out.println("aleatorio: "+Arrays.toString(JuegoPruebasAleatorio[i]));
                    System.out.println("ordenado: "+Arrays.toString(resultado));

                    resultado = Analizador.analiza(JuegoPruebasOrdenado[i], BusquedaBinaria, out);
                    System.out.println("ya ordenado: "+Arrays.toString(JuegoPruebasOrdenado[i]));
                    System.out.println("ordenado: "+Arrays.toString(resultado));

                    resultado = Analizador.analiza(JuegoPruebasInverso[i], BusquedaBinaria, out);
                    System.out.println("inverso: "+Arrays.toString(JuegoPruebasInverso[i]));
                    System.out.println("ordenado: "+Arrays.toString(resultado));
                    out.println();
                    tam *= 10;
                }
                out.close();
            } catch (Exception e) {
                System.out.print("error en fichero");
            }
            System.out.print("Fin");
        }
    }


