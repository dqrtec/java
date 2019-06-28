package br.arida.cadim.controllers;

import java.io.IOException;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        System.out.println(jsonEnviado.keySet().toString());
        String cpf = jsonEnviado.get("cpf").toString();
        String dataExame = jsonEnviado.get("dataHora").toString();
        String ecgFile = jsonEnviado.get("ecgFile").toString();
        double imc = Double.parseDouble(jsonEnviado.get("imc").toString());
        String signalECG = jsonEnviado.get("signalECG").toString();

        
        String enderecoArquivo ="/opt/lampp/htdocs/cadim2/sinais/"+ ecgFile;
//      
//      
        e.setE_paciente_cpf(cpf);
        //e.setE_data_hora(dataExame);
        e.setE_ecg_file(enderecoArquivo);
        e.setE_imc(imc);
//
        ecgRepository.save(e);
//
        e.salvarArquivo(signalECG);
        
        return "envio json concluido com sucesso";
    }
    

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}