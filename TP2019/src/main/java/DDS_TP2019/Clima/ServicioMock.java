package DDS_TP2019.Clima;

import java.io.IOException;

import org.joda.time.DateTime;

import com.google.maps.model.LatLng;

public class ServicioMock extends ServicioMeteorologico{

	public double obtenerTemperatura(String ubicacion) throws IOException {
		return 14.0;
	}
	
	public double obtenerTemperaturaFutura(DateTime temperatura, String ubicacion) {
		return 14.0;
	}

	@Override
	public DateTime redondearHorario(DateTime fecha) {
		// TODO Auto-generated method stub
		return null;
	}

}
