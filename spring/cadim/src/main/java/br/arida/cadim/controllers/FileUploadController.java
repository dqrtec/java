package br.arida.cadim.controllers;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.arida.cadim.model.Ecg;
import br.arida.cadim.repositories.EcgRepository;
import br.arida.cadim.repositories.UserRepository;
import br.arida.cadim.storage.StorageFileNotFoundException;
import br.arida.cadim.storage.StorageService;

@Controller
public class FileUploadController {

    private final StorageService storageService;
    
    @Autowired
	EcgRepository ecgRepository;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/a")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
    
    @PostMapping("/json")
    public String EnvioJson(@RequestBody Map<String, Object> jsonEnviado) {
    	Ecg e = new Ecg();
    	e.setPac_id(Integer.parseInt(jsonEnviado.get("pac_id").toString()));
    	e.setEcg_file(jsonEnviado.get("ecg_file").toString());
    	e.setImc(Double.parseDouble(jsonEnviado.get("imc").toString()));
    	e.setMarcapasso( jsonEnviado.get("marcapasso").toString());
    	e.setPressao_sistolica(Integer.parseInt(jsonEnviado.get("pressao_sistolica").toString()));
    	e.setCancer(jsonEnviado.get("cancer").toString());
    	e.setPressao_diastolica(Integer.parseInt(jsonEnviado.get("pressao_diastolica").toString()));
    	e.setTabagismo(jsonEnviado.get("tabagismo").toString());
    	e.setAlcoolosmo(jsonEnviado.get("alcoolismo").toString());
    	e.setSincope(jsonEnviado.get("sincope").toString());
    	e.setSedentarismo(jsonEnviado.get("sedentarismo").toString());
    	e.setFibrilacao_flutter(jsonEnviado.get("fibrilacao_fluter").toString());
    	e.setAvc(jsonEnviado.get("avc").toString());

    	ecgRepository.save(e);

    	e.salvarArquivo(jsonEnviado.get("file").toString());
    	
    	return "envio json concluido com sucesso";
    }

    @PostMapping("/")
    public String handleFileUpload(
    		@RequestParam("file") MultipartFile file,
    		@RequestParam("pac_id") Integer pac_id,
    		@RequestParam("ecg_file") String ecg_file,
    		@RequestParam("imc") Double imc,
    		@RequestParam("marcapasso") char marcapasso,
    		@RequestParam("pressao_sistolica") Integer pressao_sistolica,
    		@RequestParam("cancer") char cancer,
    		@RequestParam("pressao_diastolica") Integer pressao_diastolica,
    		@RequestParam("tabagismo") char tabagismo,
    		@RequestParam("alcoolismo") char alcoolismo,
    		@RequestParam("sincope") char sincope,
    		@RequestParam("sedentarismo") char sedentarismo,
    		@RequestParam("fibrilacao_fluter") char fibrilacao_fluter,
    		@RequestParam("avc") char avc) {
    	
    	Ecg e = new Ecg();
    	e.setPac_id(pac_id);
    	e.setEcg_file(ecg_file);
    	e.setImc(imc);
    	//e.setMarcapasso(marcapasso);
    	e.setPressao_sistolica(pressao_sistolica);
    	/*
    	e.setCancer(cancer);
    	e.setPressao_diastolica(pressao_diastolica);
    	e.setTabagismo(tabagismo);
    	e.setAlcoolosmo(alcoolismo);
    	e.setSincope(sincope);
    	e.setSedentarismo(sedentarismo);
    	e.setFibrilacao_flutter(fibrilacao_fluter);
    	e.setAvc(avc);
    	*/
    	ecgRepository.save(e);
    	
        storageService.store(file);
        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
