package DDS_TP2019.Dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Prueba {
@Id @GeneratedValue
	private int edad;
	private String nombre;
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Prueba(int edad, String nombre) {
		super();
		this.edad = edad;
		this.nombre = nombre;
	}
	public Prueba() {
		super();
	}
	
	
}
