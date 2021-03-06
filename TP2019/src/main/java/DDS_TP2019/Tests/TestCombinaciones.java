package DDS_TP2019.Tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import DDS_2019.DAOs.EventoDAO;
import DDS_2019.DAOs.GuardarropaDAO;
import DDS_2019.DAOs.PersonaDAO;
import DDS_TP2019.Clima.ServicioApixU;
import DDS_TP2019.Clima.ServicioMeteorologico;
import DDS_TP2019.Clima.ServicioMock;
import DDS_TP2019.Clima.ServicioOpenWeather;
import DDS_TP2019.Dominio.Atuendo;
import DDS_TP2019.Dominio.Evento;
import DDS_TP2019.Dominio.Guardarropa;
import DDS_TP2019.Dominio.Persona;
import DDS_TP2019.Dominio.Prenda;
import DDS_TP2019.Dominio.TipoPrenda;
import DDS_TP2019.Dominio.UsuarioGratuito;
import DDS_TP2019.Dominio.UsuarioPremium;
import DDS_TP2019.Estados.Estado;
import db.EntityManagerHelper;

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

    private Estado decision1;
    private Estado decision2;
    private Estado decision3;
    
    Guardarropa guardarropa;
    Guardarropa guardarropaCompartido;
    Guardarropa guardarropaTest1;
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
	List<String> telas;
	 List<String> tipoEventoFormal;
	 List<String> tipoEventoInformal;
	 List<String> tipoEventoMixto;
    private Persona usuarioNotificar;
	
	  @Before
	  public void initialize(){
		telas =  new ArrayList<String>();
		telas.add("algodon");
	    tipoEventoFormal = new ArrayList<String>();
	    tipoEventoFormal.add("FORMAL");
	    tipoEventoInformal = new ArrayList<String>();
	    tipoEventoInformal.add("INFORMAL");
	    tipoEventoMixto =  new ArrayList<String>();
	    tipoEventoMixto.add("FORMAL");
	    tipoEventoMixto.add("INFORMAL");
		 camisaMixta = new Prenda("negro","",new TipoPrenda("camisa","partesuperior",telas,tipoEventoMixto,2),"algodon",10);
		 remeraBlancaSport = new Prenda("blanco","rojo",new TipoPrenda("remera","partesuperior",telas,tipoEventoInformal,2),"algodon",10);
		 remeraReunionNegra = new Prenda("negro","",new TipoPrenda("remeraReunionNegra","partesuperior",telas,tipoEventoFormal,2),"algodon",10);
		 remeraReunionGris = new Prenda("gris","",new TipoPrenda("remeraReunionGris","partesuperior",telas,tipoEventoFormal,2),"algodon",10);
		 sweterReunionAzul = new Prenda("azul","",new TipoPrenda("sweter","partesuperior",telas,tipoEventoFormal,3),"lana",70);
		 camisetaBlancaMixta = new Prenda("blanco","",new TipoPrenda("camiseta","partesuperior",telas,tipoEventoMixto,1),"algodon",10);
		 camisetaVerdeMixta = new Prenda("verde","",new TipoPrenda("camiseta","partesuperior",telas,tipoEventoMixto,1),"algodon",10);
		 sacoVestirFormal = new Prenda("rojo","negro",new TipoPrenda("saco","partesuperior",telas,tipoEventoFormal,3),"algodon",70);
		 sacoAdidasInformal = new Prenda("amarillo","azul",new TipoPrenda("saco","partesuperior",telas,tipoEventoInformal,3),"algodon",70);
		 sacoTrabajo = new Prenda("blanco","negro",new TipoPrenda("saco","partesuperior",telas,tipoEventoFormal,3),"algodon",80);
		 sacoConverse = new Prenda("celeste","blanco",new TipoPrenda("saco","partesuperior",telas,tipoEventoMixto,3),"algodon",65);
		 camperaInviernoMixta = new Prenda("azul","blanco",new TipoPrenda("campera","partesuperior",telas,tipoEventoMixto,4),"algodon",90);
		 guantes = new Prenda("azul","blanco",new TipoPrenda("guantes","accesorio",telas,tipoEventoMixto,0),"algodon",20);
		 bufanda = new Prenda("verde","negro",new TipoPrenda("bufanda","accesorio",telas,tipoEventoMixto,0),"algodon",20);
		 gorroRF = new Prenda("rojo","blanco",new TipoPrenda("gorro","accesorio",telas,tipoEventoInformal,0),"algodon",10);
		 shortNike = new Prenda("negro","violeta",new TipoPrenda("short","parteinferior",telas,tipoEventoInformal,2),"algodon",10);
		 boxerNegro = new Prenda("negro","",new TipoPrenda("boxer","parteinferior",telas,tipoEventoMixto,1),"algodon",5);
		 boxerAzul = new Prenda("azul","",new TipoPrenda("boxer","parteinferior",telas,tipoEventoMixto,1),"algodon",5);
		 boxerblanco = new Prenda("blanco","",new TipoPrenda("boxer","parteinferior",telas,tipoEventoMixto,1),"algodon",10);
		 jean = new Prenda("azul","",new TipoPrenda("jeanMixto","parteinferior",telas,tipoEventoMixto,2),"gabardina",10);
		 jogging = new Prenda("negro","rojo",new TipoPrenda("jogging","parteinferior",telas,tipoEventoInformal,2),"seda",10);
		 jeanTrabajo = new Prenda("gris","",new TipoPrenda("jeanTrabajo","parteinferior",telas,tipoEventoFormal,2),"gabardina",10);
		 zapatos = new Prenda("negro","",new TipoPrenda("zapatos","calzado",telas,tipoEventoFormal,2),"cuero",10);
		 zapatillasCalle = new Prenda("rojo","negro",new TipoPrenda("zapatillas","calzado",telas,tipoEventoInformal,2),"cuero",10);
		 medias1 = new Prenda("negro","",new TipoPrenda("medias","calzado",telas,tipoEventoMixto,1),"algodon",10);
		 medias2 = new Prenda("rojo","negro",new TipoPrenda("medias","calzado",telas,tipoEventoMixto,1),"algodon",10);
	
		guardarropaTest1 = new Guardarropa();
//		guardarropaTest1.agregarPrenda(medias2);
		guardarropaTest1.agregarPrenda(medias1);
//		guardarropaTest1.agregarPrenda(zapatillasCalle);
		guardarropaTest1.agregarPrenda(zapatos);
		guardarropaTest1.agregarPrenda(jeanTrabajo);
//		guardarropaTest1.agregarPrenda(jogging);
//		guardarropaTest1.agregarPrenda(jean);
		guardarropaTest1.agregarPrenda(boxerblanco);
//		guardarropaTest1.agregarPrenda(shortNike);
//		guardarropaTest1.agregarPrenda(camisetaVerdeMixta);
		guardarropaTest1.agregarPrenda(camisetaBlancaMixta);
//		guardarropaTest1.agregarPrenda(camisaMixta);
//		guardarropaTest1.agregarPrenda(remeraBlancaSport);
		guardarropaTest1.agregarPrenda(remeraReunionNegra);
		guardarropaTest1.agregarPrenda(remeraReunionGris);
		
        guardarropa1 = new Guardarropa();
        guardarropa1.agregarPrenda(medias2);
        guardarropa1.agregarPrenda(medias1);
        guardarropa1.agregarPrenda(zapatillasCalle);
        guardarropa1.agregarPrenda(zapatos);
        guardarropa1.agregarPrenda(jeanTrabajo);
        guardarropa1.agregarPrenda(jogging);
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
	        usuarioGratuito.agregarGuardarropa(guardarropaTest1);
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
//	        decision1 = new Aceptar(atuendo1,"ACEPTADO");
//	        decision2 = new Calificar(atuendo2,"CALIFICADO");
//	        decision3 = new Rechazar(atuendo3,"RECHAZADO");
	        
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
	        
	        
//	        Long idGuardarropa = Long.valueOf(211);
//	       	 GuardarropaDAO guardarropaDAO = new GuardarropaDAO(EntityManagerHelper.getEntityManager());
//	       	 guardarropa = guardarropaDAO.obtenerGuardarropa(idGuardarropa);
//	       	PrendaDAO prendaDAO = new PrendaDAO(EntityManagerHelper.getEntityManager());
//	       	Long idPrenda;
//	       	
//	       	guardarropa.agregarPrenda(medias1);
//	       	medias1.setGuardarropa(guardarropa);
//	       	guardarropaDAO.actualizarGuardarropa(guardarropa);
//	    	idPrenda = prendaDAO.obtenerUltimoIDPrendaInsertada();
//	    	medias1.setId(idPrenda);
//	        
//	    	 guardarropa.agregarPrenda(zapatos);
//	    	 zapatos.setGuardarropa(guardarropa);
//		     guardarropaDAO.actualizarGuardarropa(guardarropa);
//	    	idPrenda = prendaDAO.obtenerUltimoIDPrendaInsertada();
//	    	zapatos.setId(idPrenda);
//		    	
//	    	guardarropa.agregarPrenda(jeanTrabajo);
//	    	jeanTrabajo.setGuardarropa(guardarropa);
//	       	guardarropaDAO.actualizarGuardarropa(guardarropa);
//	    	idPrenda = prendaDAO.obtenerUltimoIDPrendaInsertada();
//	    	jeanTrabajo.setId(idPrenda);
//	    	
//	    	guardarropa.agregarPrenda(jean);
//	    	jean.setGuardarropa(guardarropa);
//	       	guardarropaDAO.actualizarGuardarropa(guardarropa);
//	    	idPrenda = prendaDAO.obtenerUltimoIDPrendaInsertada();
//	    	jean.setId(idPrenda);
//	    	
//	    	guardarropa.agregarPrenda(boxerblanco);
//	    	boxerblanco.setGuardarropa(guardarropa);
//	       	guardarropaDAO.actualizarGuardarropa(guardarropa);
//	    	idPrenda = prendaDAO.obtenerUltimoIDPrendaInsertada();
//	    	boxerblanco.setId(idPrenda);
//	    	
//	    	guardarropa.agregarPrenda(camisetaBlancaMixta);
//	    	camisetaBlancaMixta.setGuardarropa(guardarropa);
//	       	guardarropaDAO.actualizarGuardarropa(guardarropa);
//	    	idPrenda = prendaDAO.obtenerUltimoIDPrendaInsertada();
//	    	camisetaBlancaMixta.setId(idPrenda);
//	    	
//	    	guardarropa.agregarPrenda(camisaMixta);
//	    	camisaMixta.setGuardarropa(guardarropa);
//	       	guardarropaDAO.actualizarGuardarropa(guardarropa);
//	    	idPrenda = prendaDAO.obtenerUltimoIDPrendaInsertada();
//	    	camisaMixta.setId(idPrenda);
//	    	
//	    	guardarropa.agregarPrenda(remeraReunionNegra);
//	    	remeraReunionNegra.setGuardarropa(guardarropa);
//	       	guardarropaDAO.actualizarGuardarropa(guardarropa);
//	    	idPrenda = prendaDAO.obtenerUltimoIDPrendaInsertada();
//	    	remeraReunionNegra.setId(idPrenda);
	    	
	  }
	
	
	
//	@Test
//    public void verificarCargaImagen() {	
//		String path = "./src/main/java/imagenes/camisa_negra.jpg";
//		
//		File file = new File(path);
//		byte[] fileArray = new byte[(int) file.length()];
//		try {
//			FileInputStream fileInputStream = new FileInputStream(file);
//			fileInputStream.read(fileArray);
//			fileInputStream.close();
//		}
//		catch (Exception e) {
//			throw new RuntimeException();
//		}	
//		List<String> telas =  new ArrayList<String>();
//		telas.add("algodon");
//		List<String> tiposEvento =  new ArrayList<String>();
//		telas.add("FORMAL");
//	    Prenda prendaCamisa1 = new Prenda("negra","",new TipoPrenda("camisa","superior",telas,tiposEvento,1),"algodon",50);
//		try {
//			prendaCamisa1.cargarImagen(fileArray, "camisa_negra_redimensionado");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		byte[] bytesImagen = null;
//		try {
//			bytesImagen = prendaCamisa1.obtenerImagen();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		Assert.assertTrue(bytesImagen.length != 0);
//    }
//	
//	
//	 @Test
//	    public void obtenerTemperaturaServicioImpostor() throws IOException, ApiException, InterruptedException {
//	        double temperatura;
//	        temperatura = servicioImpostor.obtenerTemperatura("Buenos Aires");
//	        Assert.assertEquals(14.0, temperatura, 0);
//	    }
//	    
//	    @Test (expected = Exception.class)
//	    public void fallaPorMaximoDePrendasAlcanzado() throws Exception{
//	    	try {
//				usuarioGratuito.agregarPrendaAGuardarropa(0,camperaInviernoMixta);
//	    	}catch(RuntimeException re)
//	        {
//	            String message = "El guardarropas alcanzo el maximo de prendas permitido";          
//	            Assert.assertEquals(message, re.getMessage());
//	            throw re;
//	        }
//	    }
//
//	    @Test
//	    public void esUnEventoProximo(){
//	        Assert.assertTrue(evento1.estaProximo());
//	    }
//
//	    @Test
//	    public void esUnEventoLejano(){
//	        Assert.assertFalse(evento2.estaProximo());
//	    }
//
//	    @Test (expected = Exception.class)
//	    public void fallaPorFechaAnterior() throws Exception{
//	        usuario.agregarEvento("Ir a trabajar",new DateTime(2010,5,30,23,00),new DateTime(2010,5,30,23,10),"Buenos Aires","FORMAL");
//	    }
	    
	    @Test
	    public void comprobarGeneracionAtuendos1() throws Exception{
	    	Set <Atuendo> atuendos = guardarropaTest1.sugerirAtuendos(21.00, "FORMAL", new DateTime(2010,5,30,23,00), new DateTime(2010,5,30,23,20));
	    	System.out.println("Cantidad atuendos ..: " + atuendos.size());
	    	atuendos.stream().forEach(a -> a.mostrarPrendas());
	    	
//	    	
//	    	Guardarropa guardarropaTest2 = new Guardarropa();
////	    	guardarropaTest2.agregarPrenda(medias2);
//	    	guardarropaTest2.agregarPrenda(medias1);
////	    	guardarropaTest2.agregarPrenda(zapatillasCalle);
//	    	guardarropaTest2.agregarPrenda(zapatos);
//	    	guardarropaTest2.agregarPrenda(jeanTrabajo);
////	    	guardarropaTest2.agregarPrenda(jogging);
//	    	guardarropaTest2.agregarPrenda(jean);
//	    	guardarropaTest2.agregarPrenda(boxerblanco);
////	    	guardarropaTest2.agregarPrenda(shortNike);
////	    	guardarropaTest2.agregarPrenda(camisetaVerdeMixta);
//	    	guardarropaTest2.agregarPrenda(camisetaBlancaMixta);
//	    	guardarropaTest2.agregarPrenda(camisaMixta);
////	    	guardarropaTest2.agregarPrenda(remeraBlancaSport);
//	    	guardarropaTest2.agregarPrenda(remeraReunionNegra);
//	    	guardarropaTest2.agregarPrenda(remeraReunionGris);
			
//	    	Evento evento = new Evento("prueba",new DateTime(2010,5,30,23,00), new DateTime(2010,5,30,23,20),"Buenos Aires","FORMAL");
//	    	  Long idGuardarropa = Long.valueOf(231);
//		       	 GuardarropaDAO guardarropaDAO = new GuardarropaDAO(EntityManagerHelper.getEntityManager());
//		       	 guardarropa = guardarropaDAO.obtenerGuardarropa(idGuardarropa);
//	    	Set <Atuendo> atuendos2 = guardarropa.sugerirAtuendos(21.00, "FORMAL", new DateTime(2010,5,30,23,00), new DateTime(2010,5,30,23,20));
//	    	atuendos2.stream().forEach(a -> a.mostrarPrendas());
//	    	for (Atuendo a : atuendos2) {
//	    		System.out.println("Detalle Atuendo: ");
//	    		a.mostrarPrendas();
//	    		System.out.println("-------");
//	    	}
	    	
//	    	usuarioGratuito.agregarGuardarropa(guardarropaTest2);
	    	
//	    	usuarioGratuito.obtenerAtuendosParaEventoProximo(evento, servicioMeteorologico);
//	    	 EventoDAO eventoDAO = new EventoDAO(EntityManagerHelper.getEntityManager());
//	    	Evento evento = eventoDAO.obtenerEvento(35);
//	    	Persona.persistirAtuendosDelEvento(evento, atuendos2);
//	    	System.out.println("Termine de persistir los atuendos. ");
	    }
//	    
	    
//	    @Test
//	    public void comprobarGeneracionAtuendos2() throws Exception{
//	    	  PersonaDAO personaDAO = new PersonaDAO(EntityManagerHelper.getEntityManager());
//			  Persona persona = personaDAO.obtenerPersona(Long.valueOf(4));
//			  EventoDAO eventoDAO = new EventoDAO(EntityManagerHelper.getEntityManager());
//			  Evento evento = eventoDAO.obtenerEvento(Long.valueOf(42));
//			  persona.obtenerAtuendosParaEventoProximo(evento, new ServicioOpenWeather());
//	    }
	    
	    
//	    @Test
//	    public void aceptarAtuendoEvento() throws IOException {
//	    	 System.out.println("Aceptando Atuendo..");
//			 Long idAtuendo = Long.valueOf(5);
//			 System.out.println("idAtuendo: " + idAtuendo);
//			 Long idEvento = Long.valueOf(12);
//		     System.out.println("cookie eventoID : " + idEvento);
//		     EventoDAO eventoDAO = new EventoDAO(EntityManagerHelper.getEntityManager());
//		     Evento evento = eventoDAO.obtenerEvento(idEvento);
//			 AtuendoDAO atuendoDAO = new AtuendoDAO(EntityManagerHelper.getEntityManager());
////			 Atuendo atuendo = atuendoDAO.obtenerAtuendo(idAtuendo);
//			 PersonaDAO personaDAO = new PersonaDAO(EntityManagerHelper.getEntityManager());
//			 Persona persona = personaDAO.obtenerPersona(Long.valueOf(2));
////			 persona.aceptarAtuendo(evento, atuendo);
////			 atuendo.setPersona(persona);
////			 atuendo.setEventoAceptado(evento);
////			 eventoDAO.actualizarEvento(evento);
////			 personaDAO.actualizarPersona(persona);
////			 atuendoDAO.actualizarAtuendo(atuendo);
//			 evento.getAtuendosSugeridos().stream().forEach(atuendoSugerido -> {
//				   if(atuendoSugerido.getId() != evento.getAtuendoAceptado().getId()){
//					   System.out.println("id : " + atuendoSugerido.getId());
//					   persona.rechazarAtuendo(evento, atuendoSugerido);
//					   atuendoSugerido.setPersona(persona);
//					   atuendoSugerido.setEventoRechazado(evento);
////					   eventoDAO.actualizarEvento(evento);
//					   personaDAO.actualizarPersona(persona);
////					   atuendoDAO.actualizarAtuendo(atuendoSugerido); 
//				   }
//			    });
//			 
//	    }
	    
	    
	    
//	    @Test
//	    public void verificarTemperaturaFutura() throws IOException {
//	    	Mockito.when(servicioAUMock.obtenerTemperaturaFutura(evento3.getFechaInicioEvento()).thenReturn(20.0);
//	    	double temperaturaFutura = evento3.temperatura(servicioAUMock);
//	    	//usuarioQueComparte1.calificarAtuendo();
//	    	Assert.assertEquals(20.0,temperaturaFutura,0);
//	    }

	
}
