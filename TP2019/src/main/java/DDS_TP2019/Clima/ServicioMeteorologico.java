package DDS_TP2019.Clima;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import org.joda.time.DateTime;


public abstract class ServicioMeteorologico{

	public abstract double obtenerTemperatura() throws IOException;
	
	public abstract double obtenerTemperaturaFutura(DateTime fechaFutura) throws IOException;
	
	public abstract DateTime redondearHorario(DateTime fecha);

	public long obtenerTimestamp(DateTime fechaFutura){
		return fechaFutura.getMillis()/1000;
	}

	public static String obtenerRespuesta(HttpURLConnection con) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response.toString();
	}
}