package org.elevador.command;

import org.elevador.impl.Elevador1;
import org.elevador.impl.Informacion;
import org.elevador.api.*;

import java.util.ArrayList;

import org.apache.felix.service.command.CommandProcessor;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(property = {
        CommandProcessor.COMMAND_SCOPE + "=entorno",
        CommandProcessor.COMMAND_FUNCTION + "=llamar1",
        CommandProcessor.COMMAND_FUNCTION + "=llamar2",
        CommandProcessor.COMMAND_FUNCTION + "=llamar3",
        CommandProcessor.COMMAND_FUNCTION + "=llamar4",
        CommandProcessor.COMMAND_FUNCTION + "=llamar5",
        CommandProcessor.COMMAND_FUNCTION + "=mir1",
        CommandProcessor.COMMAND_FUNCTION + "=mir2",
        CommandProcessor.COMMAND_FUNCTION + "=e1",
        CommandProcessor.COMMAND_FUNCTION + "=e2",
        CommandProcessor.COMMAND_FUNCTION + "=m1",
        CommandProcessor.COMMAND_FUNCTION + "=m2",
        CommandProcessor.COMMAND_FUNCTION + "=m3",
        CommandProcessor.COMMAND_FUNCTION + "=m4",
        CommandProcessor.COMMAND_FUNCTION + "=m5",
        
    },
    service=EntornoCommand.class
)

public class EntornoCommand {
	
	public EntornoCommand(){
		System.out.println("------------------------------------------------");
	}
	
	private IMecanismoPiso1 MecanismoPiso1Svc;
	private IMecanismoPiso2 MecanismoPiso2Svc;
	private IMecanismoPiso3 MecanismoPiso3Svc;
	private IMecanismoPiso4 MecanismoPiso4Svc;
	private IMecanismoPiso5 MecanismoPiso5Svc;
	private IElevador1 Elevador1Svc;
	private IElevador2 Elevador2Svc;

	@Reference
    public void setElevador1(IElevador1 Elevador1Svc) {
        this.Elevador1Svc = Elevador1Svc;
    }
	
	@Reference
    public void setElevador2(IElevador2 Elevador2Svc) {
        this.Elevador2Svc = Elevador2Svc;
    }
	
    @Reference
    public void setMecanismoPiso1(IMecanismoPiso1 MecanismoPiso1Svc) {
        this.MecanismoPiso1Svc = MecanismoPiso1Svc;
    }

    @Reference
    public void setMecanismoPiso2(IMecanismoPiso2 MecanismoPiso2Svc) {
        this.MecanismoPiso2Svc = MecanismoPiso2Svc;
    }
    
    @Reference
    public void setMecanismoPiso3(IMecanismoPiso3 MecanismoPiso3Svc) {
        this.MecanismoPiso3Svc = MecanismoPiso3Svc;
    }
    
    @Reference
    public void setMecanismoPiso4(IMecanismoPiso4 MecanismoPiso4Svc) {
        this.MecanismoPiso4Svc = MecanismoPiso4Svc;
    }
    
    @Reference
    public void setMecanismoPiso5(IMecanismoPiso5 MecanismoPiso5Svc) {
        this.MecanismoPiso5Svc = MecanismoPiso5Svc;
    }
    
    public void mir1(int destino) throws InterruptedException {
    	
    	int pisoOrigen = this.Elevador1Svc.getPisoActualElevador();
    	
    	if(this.Elevador1Svc.getEstadoActualElevador().equals(EstadoElevador.EsperandoInstruccion)) {
    		
    		if(destino>5 || destino<-1) {
    			System.out.println("-- Error, el piso destino solicitado no existe. ");
    		}
    		else {
    			this.Elevador1Svc.setPisoDestinoElevador(destino);
        		this.Elevador1Svc.desplazarElevadorDeOrigenADestino();
    		}
    	}
    	else {
    		System.out.println("-- Acceso denegado. Primero solicite el elevador hasta su piso actual.");
    	}
    	
    	if(pisoOrigen == 1) 
    		this.MecanismoPiso1Svc.setEstaActivadoElBoton(false);
    	else if(pisoOrigen == 2)
    		this.MecanismoPiso2Svc.setEstaActivadoElBoton(false);
    	else if(pisoOrigen == 3)
    		this.MecanismoPiso3Svc.setEstaActivadoElBoton(false);
    	else if(pisoOrigen == 4)
    		this.MecanismoPiso4Svc.setEstaActivadoElBoton(false);
    	else if(pisoOrigen == 5)
    		this.MecanismoPiso5Svc.setEstaActivadoElBoton(false);
    	
    	System.out.println("------------------------------------------------");
    	
    	//Llevar al ascensor al piso de espera
    	if(!Informacion.ColaEspera.isEmpty()) {
    		int PisoSolicitudEspera = Informacion.ColaEspera.remove();
    		
    		System.out.println("-- Requerimiento de espera desde el piso " + PisoSolicitudEspera + " esta siendo atendido." );
    		this.llamarElevador(PisoSolicitudEspera);
    	}
    	else {
    		this.Elevador1Svc.desplazarElevadorADefecto();
    		this.Elevador1Svc.setEstadoActualElevador(EstadoElevador.Disponible);
    	}
    }
    
    public void mir2(int destino) throws InterruptedException {
    	
    	int pisoOrigen = this.Elevador2Svc.getPisoActualElevador();
    	
    	if(this.Elevador2Svc.getEstadoActualElevador().equals(EstadoElevador.EsperandoInstruccion)) {
    		
    		if(destino>5 || destino<-1) {
    			System.out.println("-- Error, el piso destino solicitado no existe.");
    		}
    		else {
    			this.Elevador2Svc.setPisoDestinoElevador(destino);
        		this.Elevador2Svc.desplazarElevadorDeOrigenADestino();
    		}
    	}
    	else {
    		System.out.println("-- Acceso denegado. Primero solicite el elevador hasta su piso actual.");
    	}
    	
    	if(pisoOrigen == 1) 
    		this.MecanismoPiso1Svc.setEstaActivadoElBoton(false);
    	else if(pisoOrigen == 2)
    		this.MecanismoPiso2Svc.setEstaActivadoElBoton(false);
    	else if(pisoOrigen == 3)
    		this.MecanismoPiso3Svc.setEstaActivadoElBoton(false);
    	else if(pisoOrigen == 4)
    		this.MecanismoPiso4Svc.setEstaActivadoElBoton(false);
    	else if(pisoOrigen == 5)
    		this.MecanismoPiso5Svc.setEstaActivadoElBoton(false);
    	
    	//Llevar al ascensor al piso de espera
    	if(!Informacion.ColaEspera.isEmpty()) {
    		int PisoSolicitudEspera = Informacion.ColaEspera.remove();
    		
    		System.out.println("-- Requerimiento de espera desde el piso " + PisoSolicitudEspera + " esta siendo atendido." );
    		this.llamarElevador(PisoSolicitudEspera);
    	}
    	else {
    		this.Elevador2Svc.desplazarElevadorADefecto();
    		this.Elevador2Svc.setEstadoActualElevador(EstadoElevador.Disponible);
    	}
    	
    	System.out.println("------------------------------------------------");
    }
    
    private void llamarElevador(int PisoLlamada) {
    	
    	int idElevador = -1;
    	
    	if(PisoLlamada == 1) 
    		idElevador = this.MecanismoPiso1Svc.solicitarElevador(Elevador1Svc, Elevador2Svc);
    	else if(PisoLlamada == 2)
    		idElevador = this.MecanismoPiso2Svc.solicitarElevador(Elevador1Svc, Elevador2Svc);
    	else if(PisoLlamada == 3)
    		idElevador = this.MecanismoPiso3Svc.solicitarElevador(Elevador1Svc, Elevador2Svc);
    	else if(PisoLlamada == 4)
    		idElevador = this.MecanismoPiso4Svc.solicitarElevador(Elevador1Svc, Elevador2Svc);
    	else if(PisoLlamada == 5)
    		idElevador = this.MecanismoPiso5Svc.solicitarElevador(Elevador1Svc, Elevador2Svc);
    	
    	if(idElevador == -1) {
    		System.out.println("Los elevadores estan ocupados, espere un momento a que alguno se desocupe...");
    		Informacion.ColaEspera.add(PisoLlamada);
    	}
    	else {
    		System.out.println("El elevador " + idElevador + " esta en camino...");
    		if(idElevador == Elevador1Svc.getIdElevador()) {
    			Elevador1Svc.setPisoDestinoElevador(PisoLlamada);
    			Elevador1Svc.desplazarElevadorVacioAOrigenSolicitud();
    			
    		}
    		else {
    			Elevador2Svc.setPisoDestinoElevador(PisoLlamada);
    			Elevador2Svc.desplazarElevadorVacioAOrigenSolicitud();
    		}
    	}
    	
    	System.out.println("------------------------------------------------");
    }
    
    public void llamar1() throws InterruptedException {
    	if(this.MecanismoPiso1Svc.isEstaActivadoElBoton()) {
    		System.out.println("-- Beep beep. Su peticion esta en proceso...");
    	}
    	else
    	{
    		this.MecanismoPiso1Svc.setEstaActivadoElBoton(true);
        	llamarElevador(this.MecanismoPiso1Svc.getPisoMecanismo());
    	}
    }
    
    public void llamar2() throws InterruptedException {
    	if(this.MecanismoPiso2Svc.isEstaActivadoElBoton()) {
    		System.out.println("-- Beep beep. Su peticion esta en proceso...");
    	}
    	else
    	{
    		this.MecanismoPiso2Svc.setEstaActivadoElBoton(true);
    		System.out.println("Aqui....");
        	llamarElevador(this.MecanismoPiso2Svc.getPisoMecanismo());
    	}
    }
    
    public void llamar3() throws InterruptedException {
    	if(this.MecanismoPiso3Svc.isEstaActivadoElBoton()) {
    		System.out.println("-- Beep beep. Su peticion esta en proceso...");
    	}
    	else
    	{
    		this.MecanismoPiso3Svc.setEstaActivadoElBoton(true);
        	llamarElevador(this.MecanismoPiso3Svc.getPisoMecanismo());
    	}
    }
    
    public void llamar4() throws InterruptedException {
    	if(this.MecanismoPiso4Svc.isEstaActivadoElBoton()) {
    		System.out.println("-- Beep beep. Su peticion esta en proceso...");
    	}
    	else
    	{
    		this.MecanismoPiso4Svc.setEstaActivadoElBoton(true);
        	llamarElevador(this.MecanismoPiso4Svc.getPisoMecanismo());
    	}
    }
    
    public void llamar5() throws InterruptedException {
    	
    	if(this.MecanismoPiso5Svc.isEstaActivadoElBoton()) {
    		System.out.println("-- Beep beep. Su peticion esta en proceso...");
    	}
    	else
    	{
    		this.MecanismoPiso5Svc.setEstaActivadoElBoton(true);
        	llamarElevador(this.MecanismoPiso5Svc.getPisoMecanismo());
    	}
    }
    
    public void e1() {
    	this.Elevador1Svc.getInformacionElevador();
    	System.out.println("------------------------------------------------");
    }
    
    public void e2() {
    	this.Elevador2Svc.getInformacionElevador();
    	System.out.println("------------------------------------------------");
    }
    
    public void m1() {
    	this.MecanismoPiso1Svc.getInformacionMecanismo();
    	System.out.println("------------------------------------------------");
    }
    
    public void m2() {
    	this.MecanismoPiso2Svc.getInformacionMecanismo();
    	System.out.println("------------------------------------------------");
    }
    
    public void m3() {
    	this.MecanismoPiso3Svc.getInformacionMecanismo();
    	System.out.println("------------------------------------------------");
    }
    
    public void m4() {
    	this.MecanismoPiso4Svc.getInformacionMecanismo();
    	System.out.println("------------------------------------------------");
    }
    
    public void m5() {
    	this.MecanismoPiso5Svc.getInformacionMecanismo();
    	System.out.println("------------------------------------------------");
    }
}
