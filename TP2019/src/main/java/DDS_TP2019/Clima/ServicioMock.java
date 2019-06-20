package DDS_TP2019.Clima;

import java.io.IOException;

import org.joda.time.DateTime;

public class ServicioMock extends ServicioMeteorologico{

	public double obtenerTemperatura() throws IOException {
		return 14.0;
	}
	
	public double obtenerTemperaturaFutura(DateTime temperatura) {
		return 14.0;
	}

	@Override
	public DateTime redondearHorario(DateTime fecha) {
		// TODO Auto-generated method stub
		return null;
	}

}
