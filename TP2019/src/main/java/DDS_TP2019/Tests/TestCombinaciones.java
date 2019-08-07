package DDS_TP2019.Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import DDS_TP2019.Clima.ServicioApixU;
import DDS_TP2019.Clima.ServicioMeteorologico;
import DDS_TP2019.Clima.ServicioMock;
import DDS_TP2019.Clima.ServicioOpenWeather;
import DDS_TP2019.Decisiones.Aceptar;
import DDS_TP2019.Decisiones.Calificar;
import DDS_TP2019.Decisiones.Decision;
import DDS_TP2019.Decisiones.Rechazar;
import DDS_TP2019.Dominio.Atuendo;
import DDS_TP2019.Dominio.Evento;
import DDS_TP2019.Dominio.Guardarropa;
import DDS_TP2019.Dominio.Persona;
import DDS_TP2019.Dominio.Prenda;
import DDS_TP2019.Dominio.TipoPrenda;
import DDS_TP2019.Dominio.UsuarioGratuito;
import DDS_TP2019.Dominio.UsuarioPremium;
import com.google.maps.errors.ApiException;

public class TestCombinaciones {
//	@Test 
//	public void SugerirAtuendo(){
//		Set<Prenda> prendas = Sets.newHashSet();
//		List <String> telas =  new ArrayList<String>();
//		telas.add("tela");
//		List <String> eventos =  new ArrayList<String>();
//		eventos.add("FORMAL");
//		Prenda saco = new Prenda("verde","rojo",new TipoPrenda("a","partesuperior",telas,eventos,2),"tela",30);
//		Prenda camisa = new Prenda("verde","rojo",new TipoPrenda("a","partesuperior",telas,eventos,1),"tela",25);
//		Prenda pantalon = new Prenda("verde","rojo",new TipoPrenda("a","parteinferior",telas,eventos,1),"tela",20);
//		Prenda calzado = new Prenda("verde","rojo",new TipoPrenda("a","calzado",telas,eventos,1),"tela",20);
//		Prenda collar = new Prenda("verde","rojo",new TipoPrenda("a","accesorio",telas,eventos,1),"tela",0);
//		prendas.add(saco);
//		prendas.add(camisa);
//		prendas.add(pantalon);
//		prendas.add(calzado);
//		prendas.add(collar);
//		prendas = FiltrarPrenda.getInstance().ejecutar(prendas, "FORMAL");
//		Set<Atuendo> atuendosPosibles = Combinar.getInstance().ejecutar(prendas);
//		double temp = 9;
//		Set<Atuendo> atuendosAEvaluar = FiltrarCombinaciones.getInstance().ejecutar(atuendosPosibles, temp);
//		
//		Assert.assertEquals(0, atuendosAEvaluar.size());
//	}
	
    private Atuendo atuendo1,atuendo2,atuendo3,atuendo4,atuendo5,atuendo6,atuendo7,atuendo8,atuendo9;
    private Guardarropa guardarropa1;
  
    private Guardarropa guardarropaUsuarioGratuito;
    
    private UsuarioPremium tipoDeUsuario;
    private Persona usuario;
    
    private UsuarioGratuito tipoDeUsuarioGratuito;
    private Persona usuarioGratuito;
    
    private Set<Prenda> listaDePrendas;
    private Set<Prenda> listaDePrendas2;
    private Set<Prenda> listaDePrendas3;
    private Set<Prenda> listaDePrendas4;
    private Set<Prenda> listaDePrendas5;
    private Set<Prenda> listaDePrendas6;
    private Set<Atuendo> atuendos;
    
    private Set<Set<Atuendo>> listaDeAtuendos;

    private ServicioMeteorologico servicioImpostor;
    
    private ServicioOpenWeather servicioOWMock;
    private ServicioApixU servicioAUMock;

    private DateTime fechaFutura;
    private DateTime fechaActual;
    
    private Evento evento1;
    private Evento evento2;
    private Evento evento3;

    private Decision decision1;
    private Decision decision2;
    private Decision decision3;
    
    Guardarropa guardarropaCompartido;
    UsuarioGratuito tipoDeUsuarioQueComparte1;
    UsuarioGratuito tipoDeUsuarioQueComparte2;
    Persona usuarioQueComparte1;
    Persona usuarioQueComparte2;
    
    Prenda camisaMixta;
	Prenda remeraBlancaSport ;
	Prenda remeraReunionNegra;
	Prenda remeraReunionGris ;
	Prenda sweterReunionAzul ;
	Prenda camisetaBlancaMixta;
	Prenda camisetaVerdeMixta;
	Prenda sacoVestirFormal ;
	Prenda sacoAdidasInformal ;
	Prenda sacoTrabajo ;
	Prenda sacoConverse ;
	Prenda camperaInviernoMixta ;
	Prenda guantes ;
	Prenda bufanda ;
	Prenda gorroRF ;
	Prenda shortNike ;
	Prenda jean ;
	Prenda boxerNegro;
	Prenda boxerAzul ;
	Prenda boxerblanco;
	Prenda jogging ;
	Prenda jeanTrabajo ;
	Prenda zapatos;
	Prenda zapatillasCalle;
	Prenda medias1 ;
	Prenda medias2 ;
    
    private Persona usuarioNotificar;
	
	  @Before
	  public void initialize(){
		List<String> telas =  new ArrayList<String>();
		telas.add("algodon");
	    List<String> tipoEventoFormal = new ArrayList<String>();
	    tipoEventoFormal.add("FORMAL");
	    List<String> tipoEventoInformal = new ArrayList<String>();
	    tipoEventoInformal.add("INFORMAL");
	    List<String> tipoEventoMixto =  new ArrayList<String>();
	    tipoEventoMixto.add("FORMAL");
	    tipoEventoMixto.add("INFORMAL");
		Prenda camisaMixta = new Prenda("negro","",new TipoPrenda("camisa","partesuperior",telas,tipoEventoMixto,1),"algodon",50);
		Prenda remeraBlancaSport = new Prenda("blanco","rojo",new TipoPrenda("remera","partesuperior",telas,tipoEventoInformal,1),"algodon",20);
		Prenda remeraReunionNegra = new Prenda("negro","",new TipoPrenda("remera","partesuperior",telas,tipoEventoFormal,1),"algodon",30);
		Prenda remeraReunionGris = new Prenda("gris","",new TipoPrenda("remera","partesuperior",telas,tipoEventoFormal,1),"algodon",30);
		Prenda sweterReunionAzul = new Prenda("azul","",new TipoPrenda("sweter","partesuperior",telas,tipoEventoFormal,2),"lana",70);
		Prenda camisetaBlancaMixta = new Prenda("blanco","",new TipoPrenda("camiseta","partesuperior",telas,tipoEventoMixto,0),"algodon",10);
		Prenda camisetaVerdeMixta = new Prenda("verde","",new TipoPrenda("camiseta","partesuperior",telas,tipoEventoMixto,0),"algodon",10);
		Prenda sacoVestirFormal = new Prenda("rojo","negro",new TipoPrenda("saco","partesuperior",telas,tipoEventoFormal,2),"algodon",70);
		Prenda sacoAdidasInformal = new Prenda("amarillo","azul",new TipoPrenda("saco","partesuperior",telas,tipoEventoInformal,2),"algodon",70);
		Prenda sacoTrabajo = new Prenda("blanco","negro",new TipoPrenda("saco","partesuperior",telas,tipoEventoFormal,2),"algodon",80);
		Prenda sacoConverse = new Prenda("celeste","blanco",new TipoPrenda("saco","partesuperior",telas,tipoEventoMixto,2),"algodon",65);
		Prenda camperaInviernoMixta = new Prenda("azul","blanco",new TipoPrenda("campera","partesuperior",telas,tipoEventoMixto,3),"algodon",90);
		Prenda guantes = new Prenda("azul","blanco",new TipoPrenda("guantes","accesorio",telas,tipoEventoMixto,0),"algodon",20);
		Prenda bufanda = new Prenda("verde","negro",new TipoPrenda("bufanda","accesorio",telas,tipoEventoMixto,0),"algodon",20);
		Prenda gorroRF = new Prenda("rojo","blanco",new TipoPrenda("gorro","accesorio",telas,tipoEventoInformal,0),"algodon",10);
		Prenda shortNike = new Prenda("negro","violeta",new TipoPrenda("short","parteinferior",telas,tipoEventoInformal,2),"algodon",10);
		Prenda jean = new Prenda("azul","",new TipoPrenda("jean","parteinferior",telas,tipoEventoMixto,2),"gabardina",20);
		Prenda boxerNegro = new Prenda("negro","",new TipoPrenda("boxer","parteinferior",telas,tipoEventoMixto,1),"algodon",5);
		Prenda boxerAzul = new Prenda("azul","",new TipoPrenda("boxer","parteinferior",telas,tipoEventoMixto,1),"algodon",5);
		Prenda boxerblanco = new Prenda("blanco","",new TipoPrenda("boxer","parteinferior",telas,tipoEventoMixto,1),"algodon",5);
		Prenda jogging = new Prenda("negro","rojo",new TipoPrenda("jogging","parteinferior",telas,tipoEventoInformal,2),"seda",20);
		Prenda jeanTrabajo = new Prenda("gris","",new TipoPrenda("jean","parteinferior",telas,tipoEventoFormal,2),"gabardina",20);
		Prenda zapatos = new Prenda("negro","",new TipoPrenda("zapatos","calzado",telas,tipoEventoFormal,2),"cuero",10);
		Prenda zapatillasCalle = new Prenda("rojo","negro",new TipoPrenda("zapatillas","calzado",telas,tipoEventoInformal,2),"cuero",10);
		Prenda medias1 = new Prenda("negro","",new TipoPrenda("zapatos","calzado",telas,tipoEventoMixto,1),"algodon",5);
		Prenda medias2 = new Prenda("rojo","negro",new TipoPrenda("zapatillas","calzado",telas,tipoEventoMixto,1),"algodon",5);
	
        guardarropa1 = new Guardarropa();
        guardarropa1.agregarPrenda(medias2);
        guardarropa1.agregarPrenda(medias1);
        guardarropa1.agregarPrenda(zapatillasCalle);
        guardarropa1.agregarPrenda(zapatos);
        guardarropa1.agregarPrenda(jeanTrabajo);
        guardarropa1.agregarPrenda(jogging);
        guardarropa1.agregarPrenda(boxerblanco);
        guardarropa1.agregarPrenda(boxerAzul);
        guardarropa1.agregarPrenda(boxerNegro);
        guardarropa1.agregarPrenda(jean);
        guardarropa1.agregarPrenda(shortNike);
        guardarropa1.agregarPrenda(gorroRF);
        guardarropa1.agregarPrenda(bufanda);
        guardarropa1.agregarPrenda(guantes);
        guardarropa1.agregarPrenda(camperaInviernoMixta);
        guardarropa1.agregarPrenda(sacoConverse);
        guardarropa1.agregarPrenda(sacoTrabajo);
        guardarropa1.agregarPrenda(sacoAdidasInformal);
        guardarropa1.agregarPrenda(camisetaVerdeMixta);
        guardarropa1.agregarPrenda(camisetaBlancaMixta);
        guardarropa1.agregarPrenda(sweterReunionAzul);
        guardarropa1.agregarPrenda(remeraReunionGris);
        guardarropa1.agregarPrenda(remeraReunionNegra);
        guardarropa1.agregarPrenda(remeraBlancaSport);
        guardarropa1.agregarPrenda(camisaMixta);
        
		///////////////////Atuendos esperados (para primer guardarropa)///////////////////////////
//        listaDePrendas = Sets.newHashSet();
//        listaDePrendas.add(prendaRemera1);
//        listaDePrendas.add(prendaPantalon1);
//        listaDePrendas.add(prendaCalzado1);
//        
//		atuendo1 = new Atuendo(listaDePrendas);
//		atuendo2 = new Atuendo(listaDePrendas);
//		atuendo3 = new Atuendo(listaDePrendas);
//		atuendo4 = new Atuendo(listaDePrendas);
//		atuendo5 = new Atuendo(listaDePrendas);
//		atuendo6 = new Atuendo(listaDePrendas);
//		atuendo7 = new Atuendo(listaDePrendas);
//		atuendo8 = new Atuendo(listaDePrendas);
        
	        tipoDeUsuario = new UsuarioPremium();
	        usuario = new Persona("Jonathan",tipoDeUsuario);
	        usuario.agregarGuardarropa(guardarropa1); //guardarropa1 es un nuevo guardarropa con valores ya cargados

//	       usuario.agregarGuardarropa(guardarropa2); //guardarropa2 es un nuevo guardarropa sin valores, solo instanciado
//	        usuario.agregarPrendaAGuardarropa(guardarropa2, prendaRemera_a);
//	        usuario.agregarPrendaAGuardarropa(guardarropa2, prendaRemera_b);
//	        usuario.agregarPrendaAGuardarropa(guardarropa2, prendaPantalon_a);
//	        usuario.agregarPrendaAGuardarropa(guardarropa2, prendaPantalon_b);
//	        usuario.agregarPrendaAGuardarropa(guardarropa2, prendaCalzado_a);
//	        usuario.agregarPrendaAGuardarropa(guardarropa2, prendaCalzado_b);
//	        usuario.agregarPrendaAGuardarropa(guardarropa2, prendaGorro);
//	        usuario.agregarPrendaAGuardarropa(guardarropa2, prendaBufanda);
//	        usuario.agregarPrendaAGuardarropa(guardarropa2, prendaGuantes);
	        
//	        listaDeAtuendos = Sets.newHashSet();
	        /*lista1.add(atuendo1);
	        lista2.add(atuendo2);
	        listaDeAtuendos.add(lista1);
	        listaDeAtuendos.add(lista2);*/
	        
//	        guardarropa3 = new Guardarropa();
//	        guardarropa4 = new Guardarropa();
//	        guardarropa5 = new Guardarropa();

	        servicioImpostor = new ServicioMock();
	        servicioOWMock = Mockito.mock(ServicioOpenWeather.class);
	        servicioAUMock = Mockito.mock(ServicioApixU.class);
	        
	        ///////////////////////////Usuario Gratuito///////////////////////////
	        guardarropaUsuarioGratuito = new Guardarropa();
	        
	        guardarropaUsuarioGratuito.agregarPrenda(medias1);
	        guardarropaUsuarioGratuito.agregarPrenda(zapatillasCalle);
	        guardarropaUsuarioGratuito.agregarPrenda(zapatos);
	        guardarropaUsuarioGratuito.agregarPrenda(jogging);
	        guardarropaUsuarioGratuito.agregarPrenda(boxerblanco);
	        guardarropaUsuarioGratuito.agregarPrenda(jean);
	        guardarropaUsuarioGratuito.agregarPrenda(shortNike);
	        guardarropaUsuarioGratuito.agregarPrenda(sacoConverse);
	        guardarropaUsuarioGratuito.agregarPrenda(sacoAdidasInformal);
	        guardarropaUsuarioGratuito.agregarPrenda(sweterReunionAzul);
	        guardarropaUsuarioGratuito.agregarPrenda(remeraReunionGris);
	        
	        tipoDeUsuarioGratuito = new UsuarioGratuito(11);
	        usuarioGratuito = new Persona("Guille",tipoDeUsuarioGratuito);
	        
	        usuarioGratuito.agregarGuardarropa(guardarropaUsuarioGratuito);
	        
	        ///////////////////////////Usuarios que comparten////////////////////
	     

	        ///////////////////////////Eventos///////////////////////////
	        fechaFutura = new DateTime().plusHours(1);
	        fechaActual = new DateTime();
	        try {
				evento1 = new Evento("Entrevista", fechaActual,fechaFutura, "Buenos Aires", "FORMAL");
				evento2 = new Evento("Partido", new DateTime().plusDays(5).plusHours(4),new DateTime().plusDays(7).plusHours(4), "Buenos Aires", "INFORMAL");
			    evento3 = new Evento ("Salir con amigos",fechaActual, new DateTime().plusHours(7),"Buenos Aires", "INFORMAL");
			       
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        //////////////////////////Decisiones////////////////////////
	        decision1 = new Aceptar(atuendo1,"ACEPTADO");
	        decision2 = new Calificar(atuendo2,"CALIFICADO");
	        decision3 = new Rechazar(atuendo3,"RECHAZADO");
	        
	        /////////
	        
	        guardarropaCompartido = new Guardarropa();
	    	tipoDeUsuarioQueComparte1 = new UsuarioGratuito(3);
	    	tipoDeUsuarioQueComparte2 = new UsuarioGratuito(3);
	    	
	        usuarioQueComparte1 = new Persona("Juan",tipoDeUsuarioQueComparte1);
	        usuarioQueComparte2 = new Persona("Luis",tipoDeUsuarioQueComparte2);
	        
	        usuarioQueComparte1.agregarGuardarropa(guardarropaCompartido);
	        usuarioQueComparte2.agregarGuardarropa(guardarropaCompartido);
	        
	        //////////////////////////Acciones////////////////////////       
	        
	        usuarioNotificar = new Persona("Pedro",tipoDeUsuario);
	        
	        usuarioNotificar.setMail("usuario.ejemplo20@gmail.com");
	        usuarioNotificar.setPassword("aq1sw2de3fr4");
	        
	        usuarioNotificar.agregarGuardarropa(guardarropa1);
	        
		  
	  }
	
	
	
	@Test
    public void verificarCargaImagen() {	
		String path = "./src/main/java/imagenes/camisa_negra.jpg";
		
		File file = new File(path);
		byte[] fileArray = new byte[(int) file.length()];
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(fileArray);
			fileInputStream.close();
		}
		catch (Exception e) {
			throw new RuntimeException();
		}	
		List<String> telas =  new ArrayList<String>();
		telas.add("algodon");
		List<String> tiposEvento =  new ArrayList<String>();
		telas.add("FORMAL");
	    Prenda prendaCamisa1 = new Prenda("negra","",new TipoPrenda("camisa","superior",telas,tiposEvento,1),"algodon",50);
		try {
			prendaCamisa1.cargarImagen(fileArray, "camisa_negra_redimensionado");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		byte[] bytesImagen = null;
		try {
			bytesImagen = prendaCamisa1.obtenerImagen();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assert.assertTrue(bytesImagen.length != 0);
    }
	
	
	 @Test
	    public void obtenerTemperaturaServicioImpostor() throws IOException, ApiException, InterruptedException {
	        double temperatura;
	        temperatura = servicioImpostor.obtenerTemperatura("Buenos Aires");
	        Assert.assertEquals(14.0, temperatura, 0);
	    }
	    
	    @Test (expected = Exception.class)
	    public void fallaPorMaximoDePrendasAlcanzado() throws Exception{
	    	try {
				usuarioGratuito.agregarPrendaAGuardarropa(0,camperaInviernoMixta);
	    	}catch(RuntimeException re)
	        {
	            String message = "El guardarropas alcanzo el maximo de prendas permitido";          
	            Assert.assertEquals(message, re.getMessage());
	            throw re;
	        }
	    }

	    @Test
	    public void esUnEventoProximo(){
	        Assert.assertTrue(evento1.estaProximo());
	    }

	    @Test
	    public void esUnEventoLejano(){
	        Assert.assertFalse(evento2.estaProximo());
	    }

	    @Test (expected = Exception.class)
	    public void fallaPorFechaAnterior() throws Exception{
	        usuario.agregarEvento("Ir a trabajar",new DateTime(2010,5,30,23,00),new DateTime(2010,5,30,23,10),"Buenos Aires","FORMAL");
	    }
	    
//	    @Test
//	    public void verificarTemperaturaFutura() throws IOException {
//	    	Mockito.when(servicioAUMock.obtenerTemperaturaFutura(evento3.getFechaInicioEvento()).thenReturn(20.0);
//	    	double temperaturaFutura = evento3.temperatura(servicioAUMock);
//	    	//usuarioQueComparte1.calificarAtuendo();
//	    	Assert.assertEquals(20.0,temperaturaFutura,0);
//	    }

	
}
