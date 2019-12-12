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
	private Integer ecg_id;
	
	private String ecg_file;
	private Double imc;
	private String marcapasso;
	private Integer pressao_sistolica;
	private String cancer;
	private Integer pressao_diastolica;
	private String tabagismo;
	private String alcoolosmo;
	private String sincope;
	private String sedentarismo;
	private java.util.Date data_hora;
	private String fibrilacao_flutter;
	private String avc;
	
	public void salvarArquivo(String dadosArquivo) {
		try {
			File file = new File(ecg_file);
	        file.createNewFile();
	        Files.write(Paths.get(ecg_file),dadosArquivo.getBytes(), StandardOpenOption.APPEND);
		}catch(Exception e) {
			System.out.println(e);
		}
	}

	private Integer pac_id;
	public Integer getEcg_id() {
		return ecg_id;
	}

	public void setEcg_id(Integer ecg_id) {
		this.ecg_id = ecg_id;
	}

	public Integer getPac_id() {
		return pac_id;
	}

	public void setPac_id(Integer pac_id) {
		this.pac_id = pac_id;
	}

	public String getEcg_file() {
		return ecg_file;
	}

	public void setEcg_file(String ecg_file) {
		this.ecg_file = ecg_file;
	}

	public Double getImc() {
		return imc;
	}

	public void setImc(Double imc) {
		this.imc = imc;
	}

	public String getMarcapasso() {
		return marcapasso;
	}

	public void setMarcapasso(String marcapasso) {
		this.marcapasso = marcapasso;
	}

	public Integer getPressao_sistolica() {
		return pressao_sistolica;
	}

	public void setPressao_sistolica(Integer pressao_sistolica) {
		this.pressao_sistolica = pressao_sistolica;
	}

	public String getCancer() {
		return cancer;
	}

	public void setCancer(String cancer) {
		this.cancer = cancer;
	}

	public Integer getPressao_diastolica() {
		return pressao_diastolica;
	}

	public void setPressao_diastolica(Integer pressao_diastolica) {
		this.pressao_diastolica = pressao_diastolica;
	}

	public String getTabagismo() {
		return tabagismo;
	}

	public void setTabagismo(String tabagismo) {
		this.tabagismo = tabagismo;
	}

	public String getAlcoolosmo() {
		return alcoolosmo;
	}

	public void setAlcoolosmo(String alcoolosmo) {
		this.alcoolosmo = alcoolosmo;
	}

	public String getSincope() {
		return sincope;
	}

	public void setSincope(String sincope) {
		this.sincope = sincope;
	}

	public String getSedentarismo() {
		return sedentarismo;
	}

	public void setSedentarismo(String sedentarismo) {
		this.sedentarismo = sedentarismo;
	}

	public java.util.Date getData_hora() {
		return data_hora;
	}

	public void setData_hora(java.util.Date data_hora) {
		this.data_hora = data_hora;
	}

	public String getFibrilacao_flutter() {
		return fibrilacao_flutter;
	}

	public void setFibrilacao_flutter(String fibrilacao_flutter) {
		this.fibrilacao_flutter = fibrilacao_flutter;
	}

	public String getAvc() {
		return avc;
	}

	public void setAvc(String avc) {
		this.avc = avc;
	}
	
	
}
