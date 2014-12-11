package com.example.park4me;

import java.util.List;

public class Regiao {

	String nome;
	double latitude;
	double longitude;
	List<Estacionamento> estacionamentos;
	
	public Regiao(String nome, double latitude, double longitude, List<Estacionamento> estacionamentos) {
		this.nome = nome;
		this.latitude = latitude;
		this.longitude = longitude;
		this.estacionamentos = estacionamentos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public List<Estacionamento> getEstacionamentos() {
		return estacionamentos;
	}

	public void setEstacionamentos(List<Estacionamento> estacionamentos) {
		this.estacionamentos = estacionamentos;
	}
}
