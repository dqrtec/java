package br.arida.cadim.model;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "ecg")
public class Ecg implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer e_ecg_id;
	private String e_paciente_cpf;
	private String e_ecg_file;
	private Double e_imc;
	private java.util.Date e_data_hora;

	
	public void salvarArquivo(String dadosArquivo) {
		try {
			File file = new File(e_ecg_file);
	        file.createNewFile();
	        Files.write(Paths.get(e_ecg_file),dadosArquivo.getBytes(), StandardOpenOption.APPEND);
		}catch(Exception e) {
			System.out.println(e);
		}
	}


	public Integer getE_ecg_id() {
		return e_ecg_id;
	}


	public void setE_ecg_id(Integer e_ecg_id) {
		this.e_ecg_id = e_ecg_id;
	}


	public String getE_paciente_cpf() {
		return e_paciente_cpf;
	}


	public void setE_paciente_cpf(String e_paciente_cpf) {
		this.e_paciente_cpf = e_paciente_cpf;
	}


	public String getE_ecg_file() {
		return e_ecg_file;
	}


	public void setE_ecg_file(String e_ecg_file) {
		this.e_ecg_file = e_ecg_file;
	}


	public Double getE_imc() {
		return e_imc;
	}


	public void setE_imc(Double e_imc) {
		this.e_imc = e_imc;
	}


	public java.util.Date getE_data_hora() {
		return e_data_hora;
	}


	public void setE_data_hora(java.util.Date e_data_hora) {
		this.e_data_hora = e_data_hora;
	}

	
	
	
}
