package DDS_TP2019.Dominio;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Atuendo {
	private Set<Prenda> prendas;
	private String estado;
	private int calificacion;
	
	public Atuendo(Set<Prenda> prendas) {
		this.prendas = prendas;	
		this.estado = "NUEVO";
	}
	
	/*
	public boolean esAtuendoPosible() {
		return this.prendas.size() >= 3;
	}*/
	
	public boolean cubreTodoElCuerpo() {
		return this.contieneDeCategoria("partesuperior") &&
				this.contieneDeCategoria("parteinferior") &&
				this.contieneDeCategoria("calzado");
	}
	
	public boolean contieneDeCategoria(String unaCategoria) {
		return this.prendas.stream().anyMatch(unaPrenda -> unaPrenda.getTipoPrenda().getCategoria() == unaCategoria);
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public void setPrendas(Set<Prenda> prendas) {
		this.prendas = prendas;
	}


	public void calificar(int unaCalificacion) throws Exception{
		if(unaCalificacion < 0 || unaCalificacion > 5){   //No es una calificacion valida, usamos rango de 1 a 5 
			throw new Exception("La calificacion no esta dentro del rango solicitado");
		}
		if(this.estado == "ACEPTADO"){
			this.calificacion = unaCalificacion;
			this.setEstado("CALIFICADO");
		}
	}
	
	public boolean esMuyAbrigado() {
		return this.sumaNivelDeAbrigo(90,100);
	}
	
	public boolean esAbrigado() {
		return this.sumaNivelDeAbrigo(65,90);
	}
	
	public boolean esChill() {
		return this.sumaNivelDeAbrigo(50,65);
	}
	
	public boolean esTemplado() {
		return this.sumaNivelDeAbrigo(35,50);
	}
	
	public boolean esFresco() {
		return this.sumaNivelDeAbrigo(10,35);
	}
	
	public boolean sumaNivelDeAbrigo(int unMinimo,int unMaximo) {
		int nivelDeAbrigo = this.prendas.stream().mapToInt(unaPrenda -> unaPrenda.getCalorias()).sum();
		return nivelDeAbrigo > unMinimo && nivelDeAbrigo <= unMaximo;
	}
	
	public Set<Prenda> filtrarPorCategoria(String unaCategoria) {
		return this.prendas.stream().filter(unaPrenda -> unaPrenda.getTipoPrenda().getCategoria() == unaCategoria).collect(Collectors.toSet());
	}
	
	public boolean noHayMasDe3DelTipo(String categoria) {
		return this.filtrarPorCategoria(categoria).size() <= 3;
	}
		
	public boolean esAtuendoFormal() {
		return this.prendas.stream().allMatch(unaPrenda -> unaPrenda.getTipoPrenda().getTiposDeEvento().contains("FORMAL"));
	}
	
	public boolean esAtuendoInformal() {
		return this.prendas.stream().allMatch(unaPrenda -> unaPrenda.getTipoPrenda().getTiposDeEvento().contains("INFORMAL"));
	}
	
	public boolean esAtuendoDiario() {
		return this.prendas.stream().allMatch(unaPrenda -> unaPrenda.getTipoPrenda().getTiposDeEvento().contains("DIARIO"));
	}
	
	public boolean noRepiteNivelPorCategoria() {
		return (noRepiteNivel(filtrarPrendasCategoria("partesuperior"))&&
				noRepiteNivel(filtrarPrendasCategoria("parteinferior"))&&
				noRepiteNivel(filtrarPrendasCategoria("calzado"))&&
				noRepiteNivel(filtrarPrendasCategoria("accesorio")));
	}
	private  Set<Prenda> filtrarPrendasCategoria(String categoria) {
		return this.prendas.stream().filter(prenda -> prenda.getTipoPrenda().getCategoria() == categoria).collect(Collectors.toSet());
	}
	private boolean noRepiteNivel(Set<Prenda> prendaXCategoria) {
		return ((prendaXCategoria.stream().filter(prenda -> prenda.getTipoPrenda().getNivel() == 0).collect(Collectors.toSet()).size()  <= 1) &&
		(prendaXCategoria.stream().filter(prenda -> prenda.getTipoPrenda().getNivel() == 1).collect(Collectors.toSet()).size() == 1) &&
		(prendaXCategoria.stream().filter(prenda -> prenda.getTipoPrenda().getNivel() == 2).collect(Collectors.toSet()).size()  <= 1) &&
		(prendaXCategoria.stream().filter(prenda -> prenda.getTipoPrenda().getNivel() == 3).collect(Collectors.toSet()).size()  <= 1) &&
		(prendaXCategoria.stream().filter(prenda -> prenda.getTipoPrenda().getNivel() == 4).collect(Collectors.toSet()).size()  <= 1));
	}
	public Set<Prenda> getPrendas() {
		return this.prendas;
	}
	
	public void setPrendasDisponibles(boolean disponibilidad) {
		this.prendas.forEach(unaPrenda -> unaPrenda.setEstaDisponible(disponibilidad));
	}
	
	public boolean estaDisponible() {
		return this.prendas.stream().allMatch(unaPrenda -> unaPrenda.isEstaDisponible());
	}
}