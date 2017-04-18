import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Vista extends JPanel{
	private static final long serialVersionUID = 1L;
	private Cuadricula miCuadricula;
	private ArrayList<Point> arrayPuntos;
	private Color miColor;
	private int miGrosor;
	private int posicionActualX;
	private int posicionActualY;
	private int posicion;
	private int ajustaPixeles;
	
	public Vista(){
		this.setMiCuadricula(new Cuadricula());
		this.setMiGrosor(3);
		this.setPosicion(0);
		this.setAjustaPixeles(10);
		this.setMiColor(Color.RED);
	}
	
	/**
	 * Método que se ejecuta por defecto, pinta la cuadrícula y el camino.
	 */
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		pintarCuadricula(g);
		pintarPunto(g);
	}
	
	/**
	 * Método que pinta la cuadrícula
	 * @param g Gráfica
	 */
	void pintarCuadricula(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		for(int i = 0; i < this.getMiCuadricula().getMiDensidadX(); i++)
			for(int j = 0; j < this.getMiCuadricula().getMiDensidadY(); j++)
				g2d.drawRect(i * this.getMiCuadricula().getMiRectSize() + this.getAjustaPixeles(), j * this.getMiCuadricula().getMiRectSize() + this.getAjustaPixeles(), this.getMiCuadricula().getMiRectSize(), this.getMiCuadricula().getMiRectSize());
	}
	
	/**
	 * Método que pinta un punto
	 */
	void pintarPunto(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(this.getMiColor());
		g2d.setStroke(new BasicStroke(this.getMiGrosor()));
		
		for(int i = 0; i < this.getPosicion() - 1; i++)
			g2d.drawLine((int)this.getArrayPuntos().get(i).getX() + this.getAjustaPixeles(), (int)this.getArrayPuntos().get(i).getY() + this.getAjustaPixeles(), (int)this.getArrayPuntos().get(i + 1).getX()  + this.getAjustaPixeles(), (int)this.getArrayPuntos().get(i + 1).getY() + this.getAjustaPixeles());
		
		if(!(this.getPosicion() == this.getArrayPuntos().size()))
			this.setPosicion(this.getPosicion() + 1);
	}
	
	/**
	 * Función que elige un color aleatorio.
	 * @return color elegido.
	 */
	Color colorAleatorio(){
		Random rand = new Random();
		float red = rand.nextFloat();
		float green = rand.nextFloat();
		float blue = rand.nextFloat();
		
		return new Color(red, green, blue);
	}
		
	public void setMiCuadricula(Cuadricula miCuadricula) {
		this.miCuadricula = miCuadricula;
	}

	public Cuadricula getMiCuadricula(){
		return miCuadricula;
	}
	
	public int getMiGrosor() {
		return miGrosor;
	}

	public void setMiGrosor(int miGrosor) {
		this.miGrosor = miGrosor;
	}

	public void setArrayPuntos(ArrayList<Point> arrayPuntos) {
		this.arrayPuntos = arrayPuntos;
	}

	public int getPosicionActualX() {
		return posicionActualX;
	}

	public void setPosicionActualX(int posicionActualX) {
		this.posicionActualX = posicionActualX;
	}

	public int getPosicionActualY() {
		return posicionActualY;
	}

	public void setPosicionActualY(int posicionActualY) {
		this.posicionActualY = posicionActualY;
	}
	
	public ArrayList<Point> getArrayPuntos() {
		return arrayPuntos;
	}
	
	public Color getMiColor() {
		return miColor;
	}

	public void setMiColor(Color miColor) {
		this.miColor = miColor;
	}
	
	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	
	public int getAjustaPixeles() {
		return ajustaPixeles;
	}

	public void setAjustaPixeles(int ajustaPixeles) {
		this.ajustaPixeles = ajustaPixeles;
	}
}
