package dominio;
import java.io.*;
import java.util.Scanner;

//clase que lea el fichero tiempos.txt y lo almacene en un array de longs (tiempos) con scanner

public class Lectura {
    static int [][] arrayTiempos = new int [7][9];

/*    public static int[][] getTiempos() {
        return tiempos;
    }

    public void setTiempos(int[][] tiempos) {
        this.tiempos = tiempos;
    }*/

    public static int[][] leerTiempos() {
        File file = new File("tiempos.txt");
        int numeros []; //array de longs
        try (Scanner scanner = new Scanner(file)) {

            int y = 0;
            int x = 0;
            while (scanner.hasNext()) {


                String s = scanner.next();
                boolean esNumero = s.matches("[0-9]+");
                if (esNumero&&!s.equals("100")&&!s.equals("1000")&&!s.equals("10000")) {
                    if(y<8){
                        y++;
                    }if(y>8){
                        x++;
                        y=0;
                    }
                    System.out.println(s);
                    arrayTiempos[x][y] = Integer.parseInt(s);

                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        }

        return arrayTiempos;
    }


}


