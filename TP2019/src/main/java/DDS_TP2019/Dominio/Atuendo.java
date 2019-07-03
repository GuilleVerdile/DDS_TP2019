package DDS_TP2019.Dominio;

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
		return this.contieneDeCategoria("parteSuperior") &&
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
		return this.filtrarPorCategoria(categoria).size() >= 1 && this.filtrarPorCategoria(categoria).size() <= 3;
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

	public boolean noRepiteNivelesDePrendas() {
		return ((this.filtrarPorNivel(1).size() == 1)	
			    && (this.filtrarPorNivel(2).size() == 1)	
				&& (this.filtrarPorNivel(3).size() == 1 || this.filtrarPorNivel(3).size() == 0)	
				&& (this.filtrarPorNivel(4).size() == 1 || this.filtrarPorNivel(4).size() == 0)	
				&& (this.filtrarPorNivel(5).size() == 1 || this.filtrarPorNivel(5).size() == 0)) ;							
	}
	
	private Set<Prenda> filtrarPorNivel(int nivel) {
		return this.prendas.stream().filter(unaPrenda -> unaPrenda.getTipoPrenda().getNivel() == nivel).collect(Collectors.toSet());

	}
	public Set<Prenda> getPrendas() {
		return this.prendas;
	}

}