package com.oesia.model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import com.jcraft.jsch.*;


public class PortFR {   // Shell/Exce/TCP conexión.
	
	private  String username = "opegpae1";
	private  String passwordA = "Telefonica18*", passwordB = "FN5ihAJo";
	private  String hostA = "10.30.4.165", hostB = "10.250.2.152";
	private  JSch jSch = new JSch();
	private  int forwardedPort;
	private  Session sessionA;
	private  Session sessionB;

		
	 
	 public void conectar() throws IOException, InterruptedException{
		 sesionA();
		 sesionB();
	 }
	
	 public void sesionA(){

		 try {
			sessionA = jSch.getSession(username, hostA, 22);  
			Properties config = new Properties(); 
	        config.put("StrictHostKeyChecking", "no");
	        sessionA.setConfig(config);
	        sessionA.setPassword(passwordA);
	        forwardedPort = 2222;
        	sessionA.setPortForwardingL(forwardedPort, hostB, 22);	
	        sessionA.connect();
	        sessionA.openChannel("direct-tcpip"); //*****************
	        
	        
	        if(sessionA.isConnected()) {
	        	System.out.println("Connected host A!");
	        		
	        }
			
		} catch (JSchException e) {
			e.printStackTrace();
		}
	 }
	 
	 
	 
	 public void sesionB() throws IOException, InterruptedException {
		

			try {
				sessionB = jSch.getSession(username, "localhost", forwardedPort);

				Properties config = new Properties(); 
		        config.put("StrictHostKeyChecking", "no");
		        sessionB.setConfig(config);
		        sessionB.setPassword(passwordB);
				sessionB.connect();
				
		      if(sessionB.isConnected()) {
		         System.out.println("Connected host B!"); 
		            
			  }
		    } catch (JSchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        	        
	 }
	 
	 
	
	public List<String> Execute(List<String> comandos) throws IOException, JSchException, InterruptedException{
		
		  List<String> respuesta = new ArrayList<String>();;
		  
		  Channel channel = sessionB.openChannel("shell");
		  InputStream in = channel.getInputStream();
		  BufferedReader br = new BufferedReader(new InputStreamReader(in));
		  OutputStream out = channel.getOutputStream();
		  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
		
		  channel.connect();
		  
		  if(channel.isConnected()) { 

			  String myCommand = ("show service id 500151940 interface 10.20.30.1 \n \n  \n");
			  bw.write(myCommand);
			  Thread.sleep(100);
			  String myCommand2 = ("show router 500151940 bgp summary neighbor 10.20.30.2 \n \n \n");
			  bw.write(myCommand2);
			  Thread.sleep(100);
			  String myCommand3 = ("show port description 1/2/1  \n \n \n");
			  bw.write(myCommand3);
			  Thread.sleep(100);
			  String myCommand5 = ("show port 1/2/1 detail \n \n");
			  bw.write(myCommand5);
			  Thread.sleep(100);
			  String myCommand6 = ("x \n \n");
			  bw.write(myCommand6);
		      String myCommand4 = ("ping 10.20.30.2 router 500151940 rapid count 5 \n \n \n");
			  bw.write(myCommand4);
			  Thread.sleep(100);
			  bw.flush();
		  }
		  

	      String received = null;
	      
	      
	      
	      /*
	       if (Objects.equals(br.readLine(), null)) {
				    // iguales
				} else {
				    // diferentes
				} 
					       */
	      new Timer().schedule(new TimerTask() {
	    	    @Override
	    	    public void run() {
	    	    	try {
						bw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    	    }
	    	}, 4000);
	      
	      
	//     while((br.readLine())!=null) // Opcional
	             
		  for(int x=0;x<200;x++)
	      {
	    	  received=br.readLine();
	    	  
	      //    sb.append(received+"\n");

	    	    
	          if(received.contains("logout")) {break;}
	          
	          if(received.equals(null)) {break;}
	          
	          if (channel.isClosed()) {
	              System.out.println("exit-status: " + channel.getExitStatus());
	              break;
	          }
	          try{Thread.sleep(10);}catch(Exception ee){}
	          
	    	    respuesta.add(received+"\n");

	      }
			return respuesta;
	}
	
	

	public void close() {
	      sessionA.disconnect();
	      sessionB.disconnect();
	}

}

