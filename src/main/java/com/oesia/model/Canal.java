package com.oesia.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "canal")
public class Canal { //Serializable*
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idcanal", unique = true, nullable = false)
	int id;
	@Column(name = "cliente")
    String cliente;
	@Column(name = "ciudad")
    String ciudad;
	@Column(name = "sede")
    String sede;
	@Column(name = "canal")
    String canal;
	@Column(name = "ip_pe")
    String ip_pe;
	@Column(name = "nombre_pe")
    String nombre_pe;
	@Column(name = "ipwan_pe")
    String ipwan_pe;
	@Column(name = "puerto_pe")
    String puerto_pe	;
	@Column(name = "ipwan_router")
    String ipwan_router;
	@Column(name = "enrutamiento")
    String enrutamiento;
	@Column(name= "loopback")
	String loopback;
	@Column(name= "VPRN")
	String vprn;


    public String getVprn() {
		return vprn;
	}

	public void setVprn(String vprn) {
		this.vprn = vprn;
	}

	public String getLoopback() {
		return loopback;
	}

	public void setLoopback(String loopback) {
		this.loopback = loopback;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setlCiente(String cliente) {
        this.cliente = cliente;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCorreo(String ciudad) {
        this.ciudad = ciudad;
    }
    
    public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}
	
	public String getIp_pe() {
		return ip_pe;
	}

	public void setIp_pe(String ip_pe) {
		this.ip_pe = ip_pe;
	}

	public String getNombre_pe() {
		return nombre_pe;
	}

	public void setNombre_pe(String nombre_pe) {
		this.nombre_pe = nombre_pe;
	}

	public String getIpwan_pe() {
		return ipwan_pe;
	}

	public void setIpwan_pe(String ipwan_pe) {
		this.ipwan_pe = ipwan_pe;
	}

	public String getPuerto_pe() {
		return puerto_pe;
	}

	public void setPuerto_pe(String puerto_pe) {
		this.puerto_pe = puerto_pe;
	}

	public String getIpwan_router() {
		return ipwan_router;
	}

	public void setIpwan_router(String ipwan_router) {
		this.ipwan_router = ipwan_router;
	}

	public String getEnrutamiento() {
		return enrutamiento;
	}

	public void setEnrutamiento(String enrutamiento) {
		this.enrutamiento = enrutamiento;
	}

}
