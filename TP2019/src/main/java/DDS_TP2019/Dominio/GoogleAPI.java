/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DDS_TP2019.Dominio;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.errors.ApiException;
import java.io.IOException;

public class GoogleAPI {
    
    private static final String API_KEY = "AIzaSyB2fOnd-sfzaSyEjgYqUhcnzWotnpBTdJk";    
    
    	public static String buscarDireccion(String direccion) throws ApiException, InterruptedException, IOException {
		
		GeoApiContext contexto = new GeoApiContext.Builder()
			    .apiKey(API_KEY)
			    .build();
			GeocodingResult[] results =  GeocodingApi.geocode(contexto,
			    direccion).await();
			
			String direccionFormateada = (results[0].formattedAddress);
		
		return direccionFormateada;
	}
        
        public static LatLng obtenerCoordenadas(String direccion) throws ApiException, InterruptedException, IOException {

	GeoApiContext contexto = new GeoApiContext.Builder()
		    .apiKey(API_KEY)
		    .build();
	GeocodingResult[] results =  GeocodingApi.geocode(contexto,
	  direccion).await();
	
		LatLng coords = (results[0].geometry.location);
	return coords;
}
}
