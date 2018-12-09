public interface Jugable {
	void rellenaConsonantes();
	void rellenaVocales(); 
	void mostrar();
	boolean compruebaAcierto(int coordX, int coordY) throws CoordenadaIncorrecta; 

}