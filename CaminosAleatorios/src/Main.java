public class Main{
	/**
	 * Método principal que inicializa el programa.
	 * @param args
	 * @throws InterruptedException
	 */
	static public void main(String args[]) throws InterruptedException{
		Controlador miControlador = new Controlador(new Integer(args[0]), new Integer(args[1]), new Integer(args[2]));
		miControlador.iniciarComponentes();
	}
}
