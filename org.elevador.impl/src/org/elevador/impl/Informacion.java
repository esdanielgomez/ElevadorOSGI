package org.elevador.impl;

import java.util.LinkedList;
import java.util.Queue;

public class Informacion {

	public static int IdElevador1 = 1;
	public static String NombreElevador1 = "Elevador 1";
	public static int PisoDefectoElevador1 = 1;
	
	public static int IdElevador2 = 2;
	public static String NombreElevador2 = "Elevador 2";
	public static int PisoDefectoElevador2 = 5;
	
	public static int PisoMinimoEdificio = 1;
	public static int PisoMaximoEdificio = 5;
	
	public static Queue<Integer> ColaEspera = new LinkedList<Integer>();

}
