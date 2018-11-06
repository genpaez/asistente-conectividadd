package com.oesia.model;

import java.util.ArrayList;
import java.util.List;

public class Compositor {
	
	List<String> comandos = new ArrayList<String>();
	private String sap_pre;
	private String interfaz, ping, routing, puerto;
	
	

	public List<String> crearComandos(String nombre_pe, String vprn, String ipwanpe, String ipwanrouter, String puertope, String enrutamiento){
		
		// *********Si No es PE huawei***********
		if(nombre_pe.indexOf("-H") != -1){  // Substring "-H" //if(nombre_pe.charAt(0)=='<') 
			
			
			if(puertope.contains(".")){
			String[] sap = puertope.split("."); 
			sap_pre = sap[0];  // Elimina VLAN de SAP, dejando solo la interfaz -Huawei-	
			}
			else {sap_pre = puertope;} //Si no tiene el separador lo toma literal
			interfaz = "ok";
			ping = "ok2";
			routing = "ok3";
			puerto = "ok4";
			
			
			/*
			<IBA-PIJ-H>display interface GigabitEthernet1/0/10.1520 // Estado de la interfaz en PE

			<IBA-PIJ-H>display interface GigabitEthernet1/0/10  // Ver interconezión (Requiere salto línea)

			<IBA-PIJ-H>ping -vpn-instance 500100550 10.30.1.54  // Ping 

			<IBA-PIJ-H>display bgp vpnv4 vpn-instance 500100550 peer // Sesión BGP

			<IBA-PIJ-H>display interface description GigabitEthernet1/0/10.1520
			*/
		
		} 
		
		
			
		else {   // *********Si No es PE huawei***********
			
			if(puertope.contains(":")){
			String[] sap = puertope.split(":"); 
			sap_pre = sap[0];  // Elimina VLAN de SAP, dejando solo el puerto	
			}
			else {sap_pre = puertope;} //Si no tiene el separador lo toma literal
			
		interfaz = "show service id " + vprn + " interface " + ipwanpe + "\n\n";
		ping = "ping " + ipwanrouter + " router " + vprn + "\n\n";
		puerto = "show port " + sap_pre + " detail \n\n";  
		
		
			if(enrutamiento.equals("BGP")) { //  Si tiene enrutamiento BGP
				routing = "show router " + vprn + " bgp summary neighbor " + ipwanrouter + "\n\n";
			}
			else if(enrutamiento.equals("OSPF")) { //  Si tiene enrutamiento OSPF
				routing = "show router " + vprn + " ospf neighbor " + ipwanpe + "\n\n";
			}
			else if(enrutamiento.equals("Estático")) { //  Si tiene enrutamiento Estático
				routing = "show router " + vprn + " static next-hop " + ipwanrouter + "\n\n";
			}
		}
		
		
		comandos.add(interfaz);
		comandos.add(ping);
		comandos.add(routing);
		comandos.add(puerto);

		return comandos;
	}
	
}
