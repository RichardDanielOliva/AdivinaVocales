import java.util.InputMismatchException;
import java.util.Scanner;

public class AdivinaVocales implements Jugable{
	private char[][] tablero;
	public int numVocales;
	private final int TAMANOTABLERO = 20;
	private String auxVocales = "AEIOU";
	private char auxConsonante = 'A';

	public AdivinaVocales(int numVocales){
		this.numVocales = numVocales;
		tablero = new char[TAMANOTABLERO][TAMANOTABLERO];
		rellenaVocales();
		rellenaConsonantes();
	}

	public void rellenaVocales(){
		int intAleatorio;
		int coordX;
		int coordY;
		char letra;

		int a = 0;
		while (a < numVocales){
			intAleatorio = (int)(Math.random() * auxVocales.length());
			coordX = (int)(Math.random() * TAMANOTABLERO);
			coordY = (int)(Math.random() * TAMANOTABLERO);
			if (compruebaQueEsteVacio(coordX, coordY)) {
				insertarDato(auxVocales.charAt(intAleatorio), coordX, coordY); 
				a++;
			}
		}
	}

	private boolean compruebaQueEsteVacio(int coordX, int coordY){
		return tablero[coordX][coordY] == '\u0000';
	}

	private void insertarDato(char letra, int coordX, int coordY){
		tablero[coordX][coordY] = letra;
	}

	public void rellenaConsonantes(){
		int intAleatorio;
		char consontanteAleatorio;

		for (int a = 0; a < TAMANOTABLERO; a++) {
			for (int b = 0;b <TAMANOTABLERO; b++) {
				do {
					intAleatorio = (int)(Math.random() * 26);
					consontanteAleatorio = (char)(auxConsonante + intAleatorio);
				} while (vocal(consontanteAleatorio));
				if (compruebaQueEsteVacio(a, b))
					insertarDato(consontanteAleatorio, a, b);
			}
		}
	}

	public boolean vocal(char letra){
		return ((letra == 'A') || (letra == 'E') || (letra == 'I') || (letra == 'O') || (letra == 'U'));
	}
	
	public void mostrar(){
		for (int a = 0; a < TAMANOTABLERO; a++) {
			for (int b = 0; b < TAMANOTABLERO; b++) {
				System.out.print(tablero[a][b]);
				System.out.print(" | ");
			}
			System.out.println("");
		}
	}

	public boolean compruebaAcierto(int coordX, int coordY) throws CoordenadaIncorrecta{
		if (coordX < 0 ||
			coordX >= TAMANOTABLERO ||
			coordY < 0 ||
		 	coordY >= TAMANOTABLERO) throw new CoordenadaIncorrecta("ERROR: Coordenada fuera del rango");
		
		return (vocal(tablero[coordX][coordY]));
	}

	public void juega(){
	 	Scanner teclado = new Scanner (System.in);
	 	int coordX = 0;
	 	int coordY = 0;
	 	boolean acierto = false;

	 	do{		
	 		mostrar();
			try {
				System.out.println("Ingresa una coordenada X");
	 			coordX = teclado.nextInt();
	 			System.out.println("Ingresa una coordenada Y");
	 			coordY = teclado.nextInt();

	 			acierto = compruebaAcierto(coordX, coordY);
	 		} catch (CoordenadaIncorrecta e1){
	 			System.out.println(e1.getMessage());
	 		} catch (InputMismatchException e2){
	 			e2.printStackTrace(); //Metodo que nos permite conocer las clases y metodos que producen la excepcion.
	 			System.out.println("Ingresa un numero entero");
	 			String coord = teclado.nextLine(); //Instruccion AUXILIAR para evitar un bucle indefinido en la ejecucion del Terminal.
	 		}
	 		if (acierto) {
	 			tablero[coordX][coordY] = '*';
	 			numVocales--;
	 		}
	 	} while (numVocales > 0);
	 } 	
}
