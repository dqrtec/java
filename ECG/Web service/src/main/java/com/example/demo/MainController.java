package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	public static class MSGString {
		public String signalSendWebService;
		public String acquisitionDate;
	}

	@RequestMapping(value = "/string", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<MSGString> receiveStrings(@RequestBody MSGString msg) {

		FileUtils.saveFile("signal" + msg.acquisitionDate + ".txt", "\n" + msg.signalSendWebService);
		System.out.println("Date: " + msg.acquisitionDate);
		System.out.println("Signal:" + msg.signalSendWebService);

		return new ResponseEntity<MSGString>(msg, HttpStatus.OK);

	}
	
	
	/**
	 * Para testes direito no navegador
	 * @return
	 */
	@RequestMapping(value = "/teste", method = RequestMethod.GET)
	public ResponseEntity<MSGString> receiveBytes() {

		//ExportSignalToDatabase e = new ExportSignalToDatabase();
		//e.mainECG(33);
		
		OntologyUtils.makeOntology();
		
		MSGString m = new MSGString();
		return new ResponseEntity<MSGString>(m, HttpStatus.OK);
	}

	
/*
	public static void main(String[] args) {
		OntologyUtils.makeOntology();
	} 
*/
}
