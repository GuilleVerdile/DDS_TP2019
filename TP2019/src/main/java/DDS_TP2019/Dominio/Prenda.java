package DDS_TP2019.Dominio;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

public class Prenda {

	private String colorPrimario;
	private String colorSecundario;
	private TipoPrenda tipoPrenda;
	private String tipoTela;
	private int calorias;
	private BufferedImage imagen;
	private String pathImagen;
	private boolean estaDisponible = true;
	
	public int getCalorias() {
		return calorias;
	}
	public void setCalorias(int calorias) {
		this.calorias = calorias;
	}
	
	public String getColorPrimario() {
		return colorPrimario;
	}
	public void setColorPrimario(String colorPrimario) {
		this.colorPrimario = colorPrimario;
	}
	public String getColorSecundario() {
		return colorSecundario;
	}
	public void setColorSecundario(String colorSecundario) {
		this.colorSecundario = colorSecundario;
	}
	public TipoPrenda getTipoPrenda() {
		return tipoPrenda;
	}
	public void setTipoPrenda(TipoPrenda tipoPrenda) {
		this.tipoPrenda = tipoPrenda;
	}
	public String getTipoTela() {
		return tipoTela;
	}
	public void setTipoTela(String tipoTela) {
		this.tipoTela = tipoTela;
	}
	public BufferedImage getImagen() {
		return imagen;
	}
	public void setImagen(BufferedImage imagen) {
		this.imagen = imagen;
	}
	public String getPathImagen() {
		return pathImagen;
	}
	public void setPathImagen(String pathImagen) {
		this.pathImagen = pathImagen;
	}
	public boolean isEstaDisponible() {
		return estaDisponible;
	}
	public void setEstaDisponible(boolean estaDisponible) {
		this.estaDisponible = estaDisponible;
	}
	public Prenda(String colorPrimario, String colorSecundario, TipoPrenda tipoPrenda, String tipoTela, int calorias) {
		super();
		this.colorPrimario = colorPrimario;
		this.colorSecundario = colorSecundario;
		this.tipoPrenda = tipoPrenda;
		this.tipoTela = tipoTela;
		this.calorias = calorias;
	}
	
	public void mostrarDetalles() {
		// TODO Auto-generated method stub
		System.out.println("Detalle Prenda:");
		System.out.println("Categoria: "+ tipoPrenda.getCategoria());
		System.out.println("Tipo Prenda: "+ tipoPrenda.getDescripcion());
		System.out.println("Tipo Tela: "+ tipoTela);
		System.out.println("Color Primario: " + colorPrimario);
		if(colorSecundario != "") {
			System.out.println("Color Secundario: " + colorSecundario + "\n");
		}
		System.out.println("Calorias: " + calorias);
		return;
	}
	
	public boolean esFormal() {
		return this.tipoPrenda.getTiposDeEvento().contains("FORMAL");
	}
	
	public boolean esInformal() {
		return this.tipoPrenda.getTiposDeEvento().contains("INFORMAL");
	}
	
	public void cargarImagen(byte[] bytesImagen, String nombreImagen) throws Exception{
		String pathImagen = this.getDirectorioImagenes() + "\\" + nombreImagen + ".jpg";
		File fileImagen = new File(pathImagen);
		cargarBytesEnFileImagen(bytesImagen, pathImagen, fileImagen);
		redimensionarImagen(fileImagen);
	}
	
	private String getDirectorioImagenes() throws Exception{
		File directorioImagenes = new File("./imagenesServidor");
		directorioImagenes.mkdir(); //si la carpeta 'imagenes' no existe, lo crea. Si existe, no hace nada.
		try {
			return directorioImagenes.getCanonicalPath(); //retorna el path absoluto del file (en este caso, del directorio)
		}
		catch(Exception e) {
			throw new Exception("Error al momento de obtener el path del directorio de imagenes");
		}
	}

	private void cargarBytesEnFileImagen(byte[] bytesImagen, String pathImagen, File fileImagen) throws Exception {
		try {
			fileImagen.createNewFile(); //si no existe el archivo, se crea
			FileOutputStream fileOuputStream = new FileOutputStream(pathImagen);
			fileOuputStream.write(bytesImagen); //escribo el file de la imagen con el array de bytes
			fileOuputStream.close();
			
			this.pathImagen = pathImagen; //seteo el atributo 'pathImagen' luego de que se haya escrito los bytes. Guardo una referencia al file de la imagen

		} catch (Exception e) {
			throw new Exception("Error al momento de cargar los bytes en el archivo de la imagen");
		}
	}
	
	private void redimensionarImagen(File fileImagen) throws Exception {
		int width = 963;
		int height = 640;
		
		BufferedImage imagen; 
		
		try {
			imagen = ImageIO.read(fileImagen); //leo el file de la imagen mediante la clase ImageIO
		}
		catch (Exception e) {
			throw new Exception("Error al momento de leer la imagen");
		}
		
		BufferedImage imagenRedimensionado = new BufferedImage(width,height,imagen.getType()); //creo una instancia de BufferedImage con los valore de width y height que necesito
		Graphics2D graphic = imagenRedimensionado.createGraphics(); //la clase Graphics2D nos provee metodos para trabajar con imagenes, en este caso nos servir  para redimensionar 
		graphic.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);//al parecer, se define una especie de liezo  
		graphic.drawImage(imagen, 0, 0, width, height, 0, 0, imagen.getWidth(), imagen.getHeight(), null); 
		graphic.dispose(); //"cerramos el graficador (graphic)"
		try {
			ImageIO.write(imagenRedimensionado, "jpg", fileImagen); //escribimos la imagen redimensionada en el file de la imagen (le indicamos tambien el formato)
		}
		catch (Exception e) {
			throw new Exception("Error al momento de escribir la imagen");
		}
	}
	
	public byte[] obtenerImagen() throws Exception {
		if(this.pathImagen == "") {
			throw new Exception("No hay ninguna asociacion a una imagen. Primero hay que cargar la imagen");
		}
		File fileImagen = new File(this.pathImagen);
		if(!fileImagen.exists()) {
			throw new Exception("No existe una imagen cargada en el path: " + this.pathImagen); 
		}
		byte[] bytesImagen = new byte[(int) fileImagen.length()];
		try {
			FileInputStream fileInputStream = new FileInputStream(fileImagen);
			fileInputStream.read(bytesImagen);//leemos desde el file de la imagen y obtenemos los bytes
			fileInputStream.close();
			return bytesImagen;
		}
		catch (Exception e) {
			throw new Exception("Error al momento de obtener los bytes del archivo de la imagen");
		}
	}
	
}
