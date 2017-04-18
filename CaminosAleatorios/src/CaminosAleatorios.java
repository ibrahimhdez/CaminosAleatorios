import java.awt.BorderLayout;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

public class CaminosAleatorios extends JFrame{
	final int ESPACIO_VENTANA = 20;
	private ArrayList<Point> arrayPuntos;
	private int repeticiones;
	private int velocidad;
	private Boolean existeCamino;
	
	/**
	 * Constructor por defecto.
	 */
	public CaminosAleatorios(){
		this.setLayout(new BorderLayout());
		this.setArrayPuntos(new ArrayList<>());
		this.setExisteCamino(true);
	}
	
	/**
	 * Método que inicializa todos los componentes.
	 * @param miCuadricula Cuadrícula donde se pintará el camino aleatorio.
	 * @param miVista Vista que dibujará el programa.
	 * @param misBotones panel de botones.
	 */
	void iniciarComponentes(Cuadricula miCuadricula, Vista miVista, Botones misBotones){
		setSize(miCuadricula.getMiRectSize() * miCuadricula.getMiDensidadX() + ESPACIO_VENTANA, miCuadricula.getMiRectSize() * miCuadricula.getMiDensidadY() + ESPACIO_VENTANA + miCuadricula.getMiRectSize() / 2 - 1 + 120);
		add(miVista, BorderLayout.CENTER);
		add(misBotones, BorderLayout.SOUTH);
		
		setTitle("Caminos Aleatorios");
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	}

	/**
	 * Función que calculo los puntos por el que el camino aleatorio pasará
	 * @param miCuadricula Cuadrícula en la que se dibujara el camino.
	 */
	void calcularCamino(Cuadricula miCuadricula){
		existeCamino = true;
		this.getArrayPuntos().clear();
		Point puntoInicial = new Point(miCuadricula.getMiRectSize() * miCuadricula.getMiDensidadX() / 2, miCuadricula.getMiRectSize() * miCuadricula.getMiDensidadY() / 2);
		this.getArrayPuntos().add(puntoInicial);
		Point aux = puntoInicial;
		Point siguientePunto = new Point();
		Boolean llegarBorde = false;
		
		while((!llegarBorde) && existeCamino){
			int direccion = elegirDireccion();
		
			switch(direccion){
				case 0: siguientePunto = new Point(0, -miCuadricula.getMiRectSize());break;
				case 1: siguientePunto = new Point(miCuadricula.getMiRectSize(), 0);break;
				case 2: siguientePunto = new Point(0, miCuadricula.getMiRectSize());break;
				case 3: siguientePunto = new Point(-miCuadricula.getMiRectSize(), 0);break;
			}
		
			siguientePunto = new Point((int)(aux.getX() + siguientePunto.getX()), (int) ((int)aux.getY() + siguientePunto.getY()));
			//if(!repetido(siguientePunto)){
				if((siguientePunto.getX() == 0) || (siguientePunto.getY() == 0) || ((siguientePunto.getX() + miCuadricula.getMiRectSize() / 2 - 1) >= miCuadricula.getSizeX()) || (siguientePunto.getY() >= miCuadricula.getSizeY())){
					llegarBorde = true;
					this.getArrayPuntos().add(siguientePunto);
				}	
				else{
					this.getArrayPuntos().add(siguientePunto);
					aux = siguientePunto;		
				}
		/*	}
			else 
				siguientePunto = aux; */
		}
	}
	
	/**
	 * Detecta si un punto ya ha sido visitado.
	 * @param punto Punto que se quiere saber si está visitado.
	 * @return Si es repetido o no.
	 */
	Boolean repetido(Point punto){
		Boolean repetido = false;		
		this.setRepeticiones(this.getRepeticiones() + 1);
		if(this.getRepeticiones() < 100000){
			for(int i = 0; i < this.getArrayPuntos().size(); i++)
				if(punto.equals(this.getArrayPuntos().get(i)))
					repetido = true;
	
			return repetido;
		}
		
		else{
			//arrayPuntos.clear();
			if(arrayPuntos.isEmpty())
				System.out.println("Está vacía");
			repeticiones = 0;
			this.setExisteCamino(false);
			//calcularCamino(miCuadricula);
		}
		return repetido;
	}
	
	/**
	 * Método que devuelve un número aleatorio según la dirección de movimiento.
	 * @return 
	 */
	int elegirDireccion(){
		Random rnd = new Random();
		int numero = rnd.nextInt() % 4;
	
		if(numero < 0)
			numero *= -1;
		return numero;
	}
	
	/**
	 * Método que recibe la velocidad del spinner y la actualiza
	 * @param botones
	 * @return
	 */
	Integer actualizarVelocidad(Botones botones){
		Double velocidad = (Double)botones.getSpinnerVelocidad().getValue();
		velocidad *= 1000;
		
		return velocidad.intValue();
	}
	
	public ArrayList<Point> getArrayPuntos() {
		return arrayPuntos;
	}

	public void setArrayPuntos(ArrayList<Point> arrayPuntos) {
		this.arrayPuntos = arrayPuntos;
	}
	
	public int getRepeticiones() {
		return repeticiones;
	}

	public void setRepeticiones(int repeticiones) {
		this.repeticiones = repeticiones;
	}
	
	public Boolean getExisteCamino() {
		return existeCamino;
	}

	public void setExisteCamino(Boolean existeCamino) {
		this.existeCamino = existeCamino;
	}
	
	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad){
		this.velocidad = velocidad;
	}
}
