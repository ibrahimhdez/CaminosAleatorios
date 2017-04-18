import javax.swing.JPanel;

public class Cuadricula extends JPanel{
	final int SIZE = 500;
	private int miRectSize;
	private int miDensidadX;
	private int miDensidadY;
	private int sizeX;
	private int sizeY;

	public Cuadricula()
	{}
	
	/**
	 * @param rectSize Tamaño del cuadrado.
	 * @param densidadX Densidad de cuadrados en el eje X de la cuadrícula.
	 * @param densidadY Densidad de cuadrados en el eje Y de la cuadrícula.
	 */
	public Cuadricula(int rectSize, int densidadX, int densidadY){
		this.setMiRectSize(rectSize);
		this.setMiDensidadX(densidadX);
		this.setMiDensidadY(densidadY);
		this.setSizeX(this.getMiDensidadX() * this.getMiRectSize());
		this.setSizeY(this.getMiDensidadY() * this.getMiRectSize());
	}
	
	/**
	 * Método que actualiza el tamaño del cuadrado en función de la densidad de la cuadrícula, para ajustarse al marco.
	 */
	void actualizarSize(){
		Double ventana = (double) SIZE;
		Double densidad = (double) this.getMiDensidadX();
		Double size = ventana / densidad;
		
		//Mientras el tamaño del cuadrado no es exacto, aumentar la densidad.
		while(size > size.intValue() + 0.1){
			this.setMiDensidadX(this.getMiDensidadX() + 1);
			this.setMiDensidadY(this.getMiDensidadY() + 1);
			
			densidad = (double) this.getMiDensidadX();
			size = ventana / densidad;
		} 
		
		this.setMiRectSize(SIZE / this.getMiDensidadX());
		this.setSizeX(this.getMiDensidadX() * this.getMiRectSize());
		this.setSizeY(this.getMiDensidadY() * this.getMiRectSize());
	}
	
	public int getMiRectSize() {
		return miRectSize;
	}

	public void setMiRectSize(int miRectSize) {
		this.miRectSize = miRectSize;
	}

	public int getMiDensidadX() {
		return miDensidadX;
	}

	public void setMiDensidadX(int miDensidadX) {
		this.miDensidadX = miDensidadX;
		this.setSizeX(miDensidadX * this.getMiRectSize());
	}

	public int getMiDensidadY() {
		return miDensidadY;
	}

	public void setMiDensidadY(int miDensidadY) {
		this.miDensidadY = miDensidadY;
		this.setSizeY(miDensidadY * this.getMiRectSize());
	}
	
	public int getSizeX() {
		return sizeX;
	}

	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}
}
