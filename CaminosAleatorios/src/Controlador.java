import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import javax.swing.JColorChooser;

public class Controlador{
	private Vista miVista;
	private Cuadricula miCuadricula;
	private Botones misBotones;
	private CaminosAleatorios misCaminos;
	private int iteraciones;
	private Timer timer;
	
	/**
	 * Constructor que inicializa la vista, la cuadrícula, los botones y el modelo.
	 * @param rectSize Tamaño de los cuadrados de la cuadrícula.
	 * @param densidadX Densidad de cuadrados en el eje X de la cuadrícula.
	 * @param densidadY Densidad de cuadrados en el eje Y de la cuadrícula.
	 */
	public Controlador(int rectSize, int densidadX, int densidadY){
		this.setMiVista(new Vista());
		this.setMiCuadricula(new Cuadricula(rectSize, densidadX, densidadY));	
		this.setMisBotones(new Botones());
		this.setMisCaminos(new CaminosAleatorios());
		this.getMisCaminos().setVelocidad(500);
		this.setIteraciones(0);
		
		this.getMisBotones().getBotonElegirColor().addActionListener(new Oyente());
		this.getMisBotones().getBotonParar().addActionListener(new Oyente());
		this.getMisBotones().getBotonEmpezar().addActionListener(new Oyente());
		this.getMisBotones().getBotonTerminar().addActionListener(new Oyente());
		this.getMisBotones().getBotonDensidad().addActionListener(new Oyente());
	}
	
	/**
	 * Método que realiza los cálculos del programa.
	 */
	void iniciarComponentes(){
		this.getMisCaminos().calcularCamino(this.getMiCuadricula());
		this.getMiVista().setMiCuadricula(this.getMiCuadricula());
		this.getMiVista().setArrayPuntos(this.getMisCaminos().getArrayPuntos());
		this.getMisBotones().iniciarComponentes();
		this.getMisBotones().actualizarColorActual(this.getMiVista().getMiColor());
		this.getMisCaminos().iniciarComponentes(this.getMiCuadricula(), this.getMiVista(), this.getMisBotones());
	}
	
	/**
	 * Método que inicia un timer con una velocidad que se le especifica con un spinner.
	 * Este timer pinta cada paso del camino.
	 */
	void iniciarCamino(){
		this.setTimer( new Timer(this.getMisCaminos().actualizarVelocidad(this.getMisBotones()), new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	            pintarCamino();
	        }
		}));
	 
		this.getTimer().start();
	}
	
	/**
	 * Función que llama a la vista a pintar el camino.
	 */
	void pintarCamino(){    
		if(this.getIteraciones() < getMisCaminos().getArrayPuntos().size()){
			getMiVista().repaint();
	  		this.setIteraciones(this.getIteraciones() + 1);
	    }    	
		else
			this.getTimer().stop();
	}
	
	public Vista getMiVista() {
		return miVista;
	}

	public void setMiVista(Vista miVista) {
		this.miVista = miVista;
	}

	public Cuadricula getMiCuadricula() {
		return miCuadricula;
	}

	public void setMiCuadricula(Cuadricula miCuadricula) {
		this.miCuadricula = miCuadricula;
	}
		
	public Botones getMisBotones() {
		return misBotones;
	}

	public void setMisBotones(Botones misBotones) {
		this.misBotones = misBotones;
	}
	
	public CaminosAleatorios getMisCaminos() {
		return misCaminos;
	}

	public void setMisCaminos(CaminosAleatorios misCaminos) {
		this.misCaminos = misCaminos;
	}
	
	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	
	public int getIteraciones() {
		return iteraciones;
	}

	public void setIteraciones(int iteraciones) {
		this.iteraciones = iteraciones;
	}
	
	/**
	 * Clase que controla los eventos de ratón.
	 */
	class Oyente implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == getMisBotones().getBotonElegirColor()){
				getMiVista().setMiColor(getMiVista().colorAleatorio());
				getMisBotones().actualizarColorActual(getMiVista().getMiColor());
			}
				
			else if(e.getSource() == getMisBotones().getBotonEmpezar())
				iniciarCamino();
			
			else if(e.getSource() == getMisBotones().getBotonParar())
				getTimer().stop();
			
			else if(e.getSource() == getMisBotones().getBotonTerminar()){
				getTimer().stop();
				getMiVista().setPosicion(getMiVista().getArrayPuntos().size());
				getMiVista().repaint();
			}
			
			else if(e.getSource() == getMisBotones().getBotonDensidad()){
				String cadenaTextField = getMisBotones().getDensidadCuadricula().getText();
				
				getMiCuadricula().setMiDensidadX(new Integer(cadenaTextField));
				getMiCuadricula().setMiDensidadY(new Integer(cadenaTextField));
				getMiCuadricula().actualizarSize();
				getMisCaminos().calcularCamino(getMiCuadricula());
				getMiVista().repaint();
			}
		}
	}
}
