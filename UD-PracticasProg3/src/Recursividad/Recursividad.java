package Recursividad;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("unused")
/**
 * @author Mentxaka
 * 
 * Método invertirFrase, que recibe un String y lo devuelve invertido letra a letra, de forma recursiva.
 * Método invertirPalabras, que recibe un String y lo devuelve invertido palabra a palabra (considerando los separadores habituales espacio, tabulador, salto de línea, símbolos de puntuación), de forma recursiva.
 * Método longAHexa, que recibe un long y devuelve la conversión de ese long a hexadecimal, de forma recursiva.
 * Método sacaPalabras, que recibe un fichero de texto y devuelve un ArrayList de Strings con todas las palabras del fichero de texto en orden inverso a como aparecen, de forma recursiva.
 * Método ordenaQuick, que recibe un arraylist de Strings (por ejemplo las palabras del 4.4.) y devuelve ese arraylist ordenado alfabéticamente por el método quicksort, de forma recursiva (observa que puede haber palabras repetidas).
 * Método buscaPalabra, que recibe un arraylist de Strings ordenado y una palabra, y devuelve la posición en la que se encuentra esa palabra (si está repetida, la posición de la última ocurrencia). De forma recursiva y utilizando un proceso de coste logarítmico. 
 *
 */
public class Recursividad {

	public static void main(String[] args) {

		//		System.out.println("recursividad");
		//      System.out.println(invertirFrase("Buenos días"));
		//      invertirPalabras("Buyer persona",0,"");
		//      System.out.println(longAHexa(17));
		//		try {			System.out.println(sacaPalabras(new Scanner(new FileInputStream( new File("src/Recursividad/texto.txt"))),new ArrayList<>()));} catch (FileNotFoundException e) {		}
		ArrayList<String> array = new ArrayList<String>();
		array.add("Volvo");
		array.add("BMW");
		array.add("Ford");
		array.add("Audi");
		array.add("Mazda");
		//		Collections.sort(array);
		ordenaQuick(array,0,array.size()-1);
		//		System.out.println(buscaPalabra(array,0,array.size()-1,"Ford"));
	}



	private static void ordenaQuick(ArrayList<String> array, int desde, int hasta) {
		if (hasta - desde < 2) {
			System.out.println(array);
		}else {
			int p = desde + ((hasta-desde)/2);
			p = particion(array,p,desde,hasta);
			ordenaQuick(array, desde, p);
			ordenaQuick(array, p+1, hasta);
		}
	}

	private static int partition(int[] array, int p, int desde, int hasta) {
		int l = desde;
		int h = hasta - 2;
		int elegido = array[p];
		swap(array,p,hasta-1);

		while (h > l) {
			if (elegido > array[l]) {
				l++;
			} else if (array[h] >= elegido) { 
				h--;
			} else { 
				swap(array,l,h);
			}
		}
		int idx = h;
		if (array[h] < elegido) idx++;
		swap(array,hasta-1,idx);
		return idx;
	}
	private static void swap(int[] array, int i, int j) { 
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}


	private static int particion(ArrayList<String> array, int p, int desde, int hasta) {
		// TODO Auto-generated method stub
		return 0;
	}



	private static int buscaPalabra(ArrayList<String> array, int ini, int fin, String string) {
		int semisuma = (ini+fin)/2;
		int resultado = array.get(semisuma).compareTo(string);
		System.out.println("------------------------");
		System.out.println(ini+","+fin+" ");
		System.out.println("resultado "+resultado);
		System.out.println("valor " + array.get(semisuma));
		if (resultado == 0) {
			return semisuma + 1;
		}else {
			if(resultado > 0) {
				return buscaPalabra(array, ini, semisuma, string);	
			}else {
				return buscaPalabra(array, semisuma, fin, string);	
			}

		}
	}

	private static ArrayList<String> sacaPalabras(Scanner read, ArrayList<String> array) {
		if(!read.hasNext()) {
			return array;
		}else{
			String palabra = read.next();
			System.out.println(palabra);
			array.add(palabra);
			sacaPalabras(read, array);
		}
		return array;
	}

	private static String longAHexa(int i) {
		if (i<16) {
			return i+"";
		}else {
			switch (i%16){
			case 0:
				return longAHexa(i/16)+'0';
			case 1:
				return longAHexa(i/16)+'1';
			case 2:
				return longAHexa(i/16)+'2';
			case 3:
				return longAHexa(i/16)+'3';
			case 4:
				return longAHexa(i/16)+'4';
			case 5:
				return longAHexa(i/16)+'5';
			case 6:
				return longAHexa(i/16)+'6';
			case 7:
				return longAHexa(i/16)+'7';
			case 8:
				return longAHexa(i/16)+'8';
			case 9:
				return longAHexa(i/16)+'9';
			case 10:
				return longAHexa(i/16)+'A';
			case 11:
				return longAHexa(i/16)+'B';
			case 12:
				return longAHexa(i/16)+'C';
			case 13:
				return longAHexa(i/16)+'D';
			case 14:
				return longAHexa(i/16)+'E';
			case 15:
				return longAHexa(i/16)+'F';
			}
		}
		return null;
	}

	private static void invertirPalabras(String string, int n, String palabraEnCurso) { 
		if(string.length() == n ) {
			System.out.println(palabraEnCurso);
		}else{
			if(string.charAt(n) == ' ' ) {
				System.out.println(palabraEnCurso);
				invertirPalabras(string, n+1,"");
			}else {
				invertirPalabras(string, n+1,  string.charAt(n) + palabraEnCurso );
			}
		}
	}

	private static String invertirFrase(String string) {
		if(string.length()==1) {
			return string;
		}else{
			//DECREMENTO LOS CARACTERES DE STRING, NO UN ENTERO COMO EN MLP
			return invertirFrase(string.substring(1)) + string.charAt(0);
		}
	}
}
