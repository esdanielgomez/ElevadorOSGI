package org.elevador.impl;

import org.elevador.api.IElevador1;
import org.elevador.api.IElevador2;
import org.elevador.api.*;
import org.osgi.service.component.annotations.Component;

@Component
public class MecanismoPiso2 implements IMecanismoPiso2 {

	private int PisoMecanismo;
	private boolean estaActivadoElBoton = false;

	public MecanismoPiso2() {
		this.PisoMecanismo = 2;
		System.out.println("-- El mecanismo del piso " + this.getPisoMecanismo() + " inicializado y listo para operar.");
	}
	
	@Override
	public int getPisoMecanismo() {
		return PisoMecanismo;
	}

	@Override
	public void setPisoMecanismo(int pisoMecanismo) {
		PisoMecanismo = pisoMecanismo;
	}
	
	@Override
	public boolean isEstaActivadoElBoton() {
		return estaActivadoElBoton;
	}

	@Override
	public void setEstaActivadoElBoton(boolean estaActivadoElBoton) {
		this.estaActivadoElBoton = estaActivadoElBoton;
	}
	
	@Override
	public int solicitarElevador(IElevador1 elevador1, IElevador2 elevador2) {
		
		System.out.println("-- Buscando un elevador disponible... ");
		
		if(elevador1.getEstadoActualElevador().equals(EstadoElevador.Disponible) && elevador2.getEstadoActualElevador().equals(EstadoElevador.Disponible)){
			if(Math.abs(PisoMecanismo - elevador1.getPisoActualElevador())<=Math.abs(PisoMecanismo - elevador2.getPisoActualElevador())){
				return elevador1.getIdElevador();
			}
			else {
				return elevador2.getIdElevador();
			}
		}
		else if(elevador1.getEstadoActualElevador().equals(EstadoElevador.Disponible) && !elevador2.getEstadoActualElevador().equals(EstadoElevador.Disponible)){
			return elevador1.getIdElevador();
		}
		else if(!elevador1.getEstadoActualElevador().equals(EstadoElevador.Disponible) && elevador2.getEstadoActualElevador().equals(EstadoElevador.Disponible)){
			return elevador2.getIdElevador();
		}
		else {
			return -1;
		}
	}
	
	@Override
	public void getInformacionMecanismo() {
		System.out.println("-- Informacion Mecanismo Piso " + this.getPisoMecanismo() + ".");
		System.out.println("Piso mecanismo: " + this.getPisoMecanismo() + ".");
		System.out.println("El boton encendido (llamada solicitada): " + this.isEstaActivadoElBoton() + ".");
	}

}
