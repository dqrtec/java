package br.arida.cadim.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.arida.cadim.model.StorageService;
import br.arida.cadim.repositories.FileSystemStorageService;

@Controller
public class FileUploadController {
	
	public final FileSystemStorageService storageService = null;
	
	@PostMapping("/upload")
	void upload(@RequestParam("file") MultipartFile file ){
		storageService.store(file);
	}
}
