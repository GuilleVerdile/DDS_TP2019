package DDS_TP2019.Clima;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.google.gson.Gson;
import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;

public class ServicioApixU extends ServicioMeteorologico {

	public double obtenerTemperatura(String ubicacion) throws IOException, ApiException, InterruptedException  {
		LatLng coords = GoogleAPI.obtenerCoordenadas(ubicacion + ", Argentina");
		URL url = new URL(
				"http://api.apixu.com/v1/current.json?key=4bbc11298d18402eb07201229192505&q="+coords.lat+","+coords.lng);
		
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
		String response = obtenerRespuesta(con);
		Gson gson = new Gson();
	    ApixUResponse weatherResponse = gson.fromJson(response, ApixUResponse.class);
	    
	    return weatherResponse
				.getCurrent()
				.getTemp_c();
	}
	
	public double obtenerTemperaturaFutura(DateTime fecha, String ubicacion) throws IOException, ApiException, InterruptedException {
                LatLng coords = GoogleAPI.obtenerCoordenadas(ubicacion + ", Argentina");
                DateTime fechaRedondeada = redondearHorario(fecha);

		long timestamp = this.obtenerTimestamp(fechaRedondeada);

		URL url = new URL(
				"http://api.apixu.com/v1/forecast.json?key=4bbc11298d18402eb07201229192505&q="+coords.lat+","+coords.lng+"&days=3");

		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		String response = obtenerRespuesta(con);

		Gson gson = new Gson();

		ApixUResponse weatherResponse = gson.fromJson(response, ApixUResponse.class);

		double temperatura = weatherResponse
								.getForecast()
								.getForecastDay()
								.stream()
								.filter(forecastDay -> forecastDay.getDate_epoch() == timestamp)
								.collect(Collectors.toList()).get(0)
								.getDay()
								.getAvgtemp_c();

		return temperatura;
    }


	public DateTime redondearHorario(DateTime fecha) {
		DateTime fechaRedondeada = new DateTime(fecha.getYear(), fecha.getMonthOfYear(), fecha.getDayOfMonth(),
				00, 00, DateTimeZone.forOffsetHours(0)); //offset para el timezone

		return fechaRedondeada;
	}

}
