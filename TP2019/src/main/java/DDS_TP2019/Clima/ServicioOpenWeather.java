package DDS_TP2019.Clima;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.google.gson.Gson;
import com.google.maps.model.LatLng;

import DDS_TP2019.Clima.GoogleAPI;
import com.google.maps.errors.ApiException;

public class ServicioOpenWeather extends ServicioMeteorologico {

	public double obtenerTemperatura(String ubicacion) throws IOException, ApiException, InterruptedException{
                LatLng coords = GoogleAPI.obtenerCoordenadas(ubicacion + ", Argentina");
		URL url = new URL(
				"http://api.openweathermap.org/data/2.5/weather?lat="+coords.lat+"&lon="+coords.lng+"&units=metric&appid=5d69aeb3fb117fa7e7db02e25e3eeb54");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		String response = obtenerRespuesta(con);

		Gson gson = new Gson();
		OpenWeatherResponse weatherResponse = gson.fromJson(response, OpenWeatherResponse.class);
		return weatherResponse
				.getMain()
				.getTemp();
	}

	public double obtenerTemperaturaFutura(DateTime fecha, String ubicacion) throws IOException, ApiException, InterruptedException {
                LatLng coords = GoogleAPI.obtenerCoordenadas(ubicacion + ", Argentina");
		DateTime fechaRedondeada = redondearHorario(fecha);

		long timestamp = this.obtenerTimestamp(fechaRedondeada);

		URL url = new URL(
				"http://api.openweathermap.org/data/2.5/forecast?lat="+coords.lat+"&lon="+coords.lng+"&appid=5d69aeb3fb117fa7e7db02e25e3eeb54&units=metric");

		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		String response = obtenerRespuesta(con);

		Gson gson = new Gson();

		OpenWeatherResponse weatherResponse = gson.fromJson(response, OpenWeatherResponse.class);

		double temperatura = weatherResponse
								.getList()
								.stream()
								.filter(list -> list.getDt() == timestamp)
								.collect(Collectors.toList())
								.get(0)
								.getMain()
								.getTemp();

		return temperatura;
	}

	public DateTime redondearHorario(DateTime fecha) {
		int hora = fecha.getHourOfDay();

		int horaRedondeada = 3 * (Math.round(hora / 3));

		DateTime fechaRedondeada = new DateTime(fecha.getYear(), fecha.getMonthOfYear(), fecha.getDayOfMonth(),
				horaRedondeada, 00, DateTimeZone.forOffsetHours(0));

		return fechaRedondeada;

	}

	
}
