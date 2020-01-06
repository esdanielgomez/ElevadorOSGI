package org.elevador.api;

public interface IMecanismoPiso1 {

	public int getPisoMecanismo();
	public void setPisoMecanismo(int pisoMecanismo);

	public int solicitarElevador(IElevador1 elevador1, IElevador2 elevador2);
	public void getInformacionMecanismo();
	
	boolean isEstaActivadoElBoton();
	void setEstaActivadoElBoton(boolean estaActivadoElBoton);
}
