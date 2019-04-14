package DDS_TP2019.Dominio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;

public class Sistema {

	private List<Persona> personas;
	private List<TipoPrenda> tiposPrendas;
	private List<String> colores;
	
	public List<String> getColores() {
		return colores;
	}
	public void setColores(List<String> colores) {
		this.colores = colores;
	}
	
	public List<Persona> getPersonas() {
		return personas;
	}
	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}
	public List<TipoPrenda> getTiposPrendas() {
		return tiposPrendas;
	}
	public void setTiposPrendas(List<TipoPrenda> tiposTelas) {
		this.tiposPrendas = tiposTelas;
	}
	
	public void agregarPersona(Persona persona) {
		personas.add(persona);
	}
	
	public Sistema() {
		super();
		this.personas = new ArrayList<Persona>();
		this.tiposPrendas = new ArrayList<TipoPrenda>();
		this.colores =  new ArrayList<String>();
	}
		
	public static List<TipoPrenda> importarTipoPrendas() throws JsonParseException, JsonMappingException, IOException{		
		ObjectMapper objectMapper = new ObjectMapper();
		List <TipoPrenda> tiposPrendas = objectMapper.readValue(new File("tipoPrendas.json"), new TypeReference<List<TipoPrenda>>(){});		
		return tiposPrendas;		
	}
	
	public static List<String> importarColores() throws JsonParseException, JsonMappingException, IOException{		
		List <String>colores = new ArrayList <String>();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = mapper.readValue(new File("colores.json"),new TypeReference<Map<String, Object>>(){});
		@SuppressWarnings("unchecked")
		ArrayList<String> list = (ArrayList<String>) map.get("colores");
		for (String color : list) {
			colores.add(color);
		}	    
	    return colores;
	}
	
	public static List<Persona> importarUsuarios() throws JsonParseException, JsonMappingException, IOException{		
		ObjectMapper objectMapper = new ObjectMapper();
		List <Persona> usuarios = objectMapper.readValue(new File("usuarios.json"),  new TypeReference<List<Persona>>(){});		
		return usuarios;		
	}
		
}
