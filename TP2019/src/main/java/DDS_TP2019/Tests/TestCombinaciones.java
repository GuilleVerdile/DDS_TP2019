package DDS_TP2019.Tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Sets;

import DDS_TP2019.Dominio.Atuendo;
import DDS_TP2019.Dominio.Combinar;
import DDS_TP2019.Dominio.FiltrarCombinaciones;
import DDS_TP2019.Dominio.FiltrarPrenda;
import DDS_TP2019.Dominio.Prenda;
import DDS_TP2019.Dominio.TipoPrenda;

public class TestCombinaciones {
	@Test 
	public void SugerirAtuendo(){
		Set<Prenda> prendas = Sets.newHashSet();
		List <String> telas =  new ArrayList<String>();
		telas.add("tela");
		List <String> eventos =  new ArrayList<String>();
		eventos.add("FORMAL");
		Prenda saco = new Prenda("verde","rojo",new TipoPrenda("a","partesuperior",telas,eventos,2),"tela",30);
		Prenda camisa = new Prenda("verde","rojo",new TipoPrenda("a","partesuperior",telas,eventos,1),"tela",25);
		Prenda pantalon = new Prenda("verde","rojo",new TipoPrenda("a","parteinferior",telas,eventos,1),"tela",20);
		Prenda calzado = new Prenda("verde","rojo",new TipoPrenda("a","calzado",telas,eventos,1),"tela",20);
		Prenda collar = new Prenda("verde","rojo",new TipoPrenda("a","accesorio",telas,eventos,1),"tela",0);
		prendas.add(saco);
		prendas.add(camisa);
		prendas.add(pantalon);
		prendas.add(calzado);
		prendas.add(collar);
		prendas = FiltrarPrenda.getInstance().ejecutar(prendas, "FORMAL");
		Set<Atuendo> atuendosPosibles = Combinar.getInstance().ejecutar(prendas);
		double temp = 9;
		Set<Atuendo> atuendosAEvaluar = FiltrarCombinaciones.getInstance().ejecutar(atuendosPosibles, temp);
		
		Assert.assertEquals(0, atuendosAEvaluar.size());
	}
}
