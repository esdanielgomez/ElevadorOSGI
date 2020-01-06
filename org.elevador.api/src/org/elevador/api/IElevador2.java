package org.elevador.api;

public interface IElevador2 {
	
	public int getIdElevador();
	public void setIdElevador(int idElevador);
	public String getNombreElevador();
	public void setNombreElevador(String nombreElevador);
	public int getPisoMininoElevador();
	public void setPisoMininoElevador(int pisoMininoElevador);
	public int getPisoMaximoElevador();
	public void setPisoMaximoElevador(int pisoMaximoElevador);
	public int getPisoActualElevador();
	public void setPisoActualElevador(int pisoActualElevador);
	public int getPisoDestinoElevador();
	public void setPisoDestinoElevador(int pisoDestinoElevador);
	public int getPisoDefectoElevador();
	public void setPisoDefectoElevador(int pisoDefectoElevador);
	
	public EstadoElevador getEstadoActualElevador();
	public void setEstadoActualElevador(EstadoElevador estadoActualElevador);
	
	public void desplazarElevadorDeOrigenADestino();
	public void desplazarElevadorADefecto();
	public void desplazarElevadorVacioAOrigenSolicitud();

	public void getInformacionElevador();
}
