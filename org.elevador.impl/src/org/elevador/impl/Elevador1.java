package org.elevador.impl;

import org.elevador.api.EstadoElevador;
import org.elevador.api.IElevador1;
import org.osgi.service.component.annotations.*;

@Component
public class Elevador1 implements IElevador1 {

	public Elevador1() {
		this.setIdElevador(Informacion.IdElevador1);
        this.setNombreElevador(Informacion.NombreElevador1);
        this.setPisoDefectoElevador(Informacion.PisoDefectoElevador1);
        this.setPisoActualElevador(Informacion.PisoDefectoElevador1);
        this.setPisoMininoElevador(Informacion.PisoMinimoEdificio);
        this.setPisoMaximoElevador(Informacion.PisoMaximoEdificio);
		
		System.out.println("-- " + this.getNombreElevador() + " inicializado y listo para operar.");
	}
	
	private int IdElevador;
	private String NombreElevador;
	
	private int PisoMininoElevador = 1;
	private int PisoMaximoElevador;
	
	private int PisoActualElevador;
	private int PisoDestinoElevador;
	
	private int PisoDefectoElevador;
	
	private EstadoElevador EstadoActualElevador = EstadoElevador.Disponible;
	private EstadoElevador DireccionElevador = EstadoElevador.Detenido;

	@Override
	public int getIdElevador() {
		return IdElevador;
	}

	@Override
	public void setIdElevador(int idElevador) {
		IdElevador = idElevador;
	}

	@Override
	public String getNombreElevador() {
		return NombreElevador;
	}

	@Override
	public void setNombreElevador(String nombreElevador) {
		NombreElevador = nombreElevador;
	}

	@Override
	public int getPisoMininoElevador() {
		return PisoMininoElevador;
	}

	@Override
	public void setPisoMininoElevador(int pisoMininoElevador) {
		PisoMininoElevador = pisoMininoElevador;
	}

	@Override
	public int getPisoMaximoElevador() {
		return PisoMaximoElevador;
	}

	@Override
	public void setPisoMaximoElevador(int pisoMaximoElevador) {
		PisoMaximoElevador = pisoMaximoElevador;
	}

	@Override
	public int getPisoActualElevador() {
		return PisoActualElevador;
	}

	@Override
	public void setPisoActualElevador(int pisoActualElevador) {
		PisoActualElevador = pisoActualElevador;
	}

	@Override
	public int getPisoDestinoElevador() {
		return PisoDestinoElevador;
	}

	@Override
	public void setPisoDestinoElevador(int pisoDestinoElevador) {
		PisoDestinoElevador = pisoDestinoElevador;
	}

	@Override
	public int getPisoDefectoElevador() {
		return PisoDefectoElevador;
	}

	@Override
	public void setPisoDefectoElevador(int pisoDefectoElevador) {
		PisoDefectoElevador = pisoDefectoElevador;
	}

	@Override
	public EstadoElevador getEstadoActualElevador() {
		return EstadoActualElevador;
	}

	@Override
	public void setEstadoActualElevador(EstadoElevador estadoActualElevador) {
		EstadoActualElevador = estadoActualElevador;
	}

	public EstadoElevador getDireccionElevador() {
		return DireccionElevador;
	}

	public void setDireccionElevador(EstadoElevador direccionElevador) {
		DireccionElevador = direccionElevador;
	}

	@Override
	public void desplazarElevadorDeOrigenADestino() {
		
		try {
			System.out.println("-- El elevador " + this.getIdElevador() + " esta empezando el requerimiento de desplazamiento...");
			if(this.getPisoActualElevador()<this.getPisoDestinoElevador()) {
				this.setEstadoActualElevador(EstadoElevador.EnMovimientoHaciaArriba);
				for(int i = this.getPisoActualElevador(); i<=this.getPisoDestinoElevador(); i++){
		        	System.out.println("Estado Elevador: " + this.getEstadoActualElevador() + " - Piso Actual: " + i);
		        	this.setPisoActualElevador(i);
		        	Thread.sleep(1000);
		        }
				System.out.println("-- El elevador ha llegado al piso destino numero " + this.getPisoDestinoElevador());
			}
			else {
				
				this.setEstadoActualElevador(EstadoElevador.EnMovimientoHaciaAbajo);
				for(int i = this.getPisoActualElevador(); i>=this.getPisoDestinoElevador(); i--){
		        	System.out.println("Estado Elevador " + this.getIdElevador() + ": " + this.getEstadoActualElevador() + " - Piso Actual: " + i);
		        	this.setPisoActualElevador(i);
		        	Thread.sleep(1000);
		        }
				System.out.println("-- El elevador " + this.getIdElevador() + " ha llegado al piso destino numero " + this.getPisoDestinoElevador());
				
				desplazarElevadorADefecto();
			}
			this.setEstadoActualElevador(EstadoElevador.Disponible);
        } catch (Exception e) {
        	System.out.println("Error en el elevador " + this.getIdElevador() + ". Se quedo detenido en el piso" + this.getPisoActualElevador());
        	System.out.println("Ahora el elevador " + this.getIdElevador() + " esta disponible desde el piso " + this.getPisoActualElevador());
        }
		
		//
		
	}
	
	@Override
	public void desplazarElevadorADefecto() {
		try {
			if(this.getPisoActualElevador()!=this.getPisoDefectoElevador()) {
				System.out.println("-- El elevador " + this.getIdElevador() + " esta regresando al piso defecto en " + this.getPisoDefectoElevador() + "...");
				if(this.getPisoActualElevador()<this.getPisoDefectoElevador()) {
					this.setEstadoActualElevador(EstadoElevador.EnMovimientoADefectoArriba);
					for(int i = this.getPisoActualElevador(); i<=this.getPisoDefectoElevador(); i++){
			        	System.out.println("Estado Elevador: " + this.getEstadoActualElevador() + " - Piso Actual: " + i);
			            this.setPisoActualElevador(i);
			        	Thread.sleep(1000);
			        }
					System.out.println("-- El elevador ha retornado al piso defecto en la planta numero" + this.getPisoDestinoElevador());
				}
				else {
					
					this.setEstadoActualElevador(EstadoElevador.EnMovimientoADefectoAbajo);
					for(int i = this.getPisoActualElevador(); i>=this.getPisoDefectoElevador(); i--){
			        	System.out.println("Estado Elevador " + this.getIdElevador() + ": " + this.getEstadoActualElevador() + " - Piso Actual: " + i);
			        	this.setPisoActualElevador(i);
			        	Thread.sleep(1000);
			        }
					System.out.println("-- El elevador " + this.getIdElevador() + " ha retornado al piso defecto en la planta numero " + this.getPisoDestinoElevador());
					
				}
			}
			this.setEstadoActualElevador(EstadoElevador.Disponible);
        } catch (Exception e) {
        	System.out.println("Error en el elevador " + this.getIdElevador() + ". Se quedo detenido en el piso" + this.getPisoActualElevador());
        	System.out.println("Ahora el elevador " + this.getIdElevador() + " esta disponible desde el piso " + this.getPisoActualElevador());
        }
	}
	
	@Override
	public void desplazarElevadorVacioAOrigenSolicitud() {
		try {
			System.out.println("Piso Actual: " + this.getPisoActualElevador() + " - Piso Destino: " + this.getPisoDestinoElevador());
			if(this.getPisoActualElevador()!=this.getPisoDestinoElevador()) {
				System.out.println("-- El elevador " + this.getIdElevador() + " esta en camino al piso solicitado...");
				if(this.getPisoActualElevador()<this.getPisoDestinoElevador()) {
					this.setEstadoActualElevador(EstadoElevador.EnMovimientoHaciaArriba);
					for(int i = this.getPisoActualElevador(); i<=this.getPisoDestinoElevador(); i++){
			        	System.out.println("Estado Elevador: " + this.getEstadoActualElevador() + " - Piso Actual: " + i);
			            this.setPisoActualElevador(i);
			        	Thread.sleep(1000);
			        }
					System.out.println("-- El elevador ha llegado al piso solicitado numero " + this.getPisoDestinoElevador());
				}
				else {
					
					this.setEstadoActualElevador(EstadoElevador.EnMovimientoHaciaAbajo);
					for(int i = this.getPisoActualElevador(); i>=this.getPisoDestinoElevador(); i--){
			        	System.out.println("Estado Elevador " + this.getIdElevador() + ": " + this.getEstadoActualElevador() + " - Piso Actual: " + i);
			        	this.setPisoActualElevador(i);
			        	Thread.sleep(1000);
			        }
					System.out.println("-- El elevador " + this.getIdElevador() + " ha llegado al piso solicitado numero " + this.getPisoDestinoElevador());
					
				}
			}
			System.out.println("-- El elevador " + this.getIdElevador() + " esta listo para ser abordado. ");
			this.setEstadoActualElevador(EstadoElevador.EsperandoInstruccion);
			System.out.println("Estado elevador " + this.getIdElevador() + ": " + this.getEstadoActualElevador());
			System.out.println("Ingrese el comando junto con el numero del piso para desplazarse al piso destino... ");
			
        } catch (Exception e) {}
	}

	@Override
	public void getInformacionElevador() {
		System.out.println("-- Informacion " + this.getNombreElevador() + ".");
		System.out.println("Piso actual: " + this.getPisoActualElevador() + ".");
		System.out.println("Piso defecto: " + this.getPisoDefectoElevador() + ".");
		System.out.println("Estado actual: " + this.getEstadoActualElevador() + ".");
	}

}