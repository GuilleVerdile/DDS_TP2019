package DDS_TP2019.Dominio;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Atuendo {
	private Set<Prenda> prendas;

	public Atuendo(Set<Prenda> prendas) {
		this.prendas = prendas;		
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

}