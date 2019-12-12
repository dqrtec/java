import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.scheduling.annotation.EnableScheduling;


public class FileUploadController {

    @Autowired
    public FileUploadController() {}

    //@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = "application/json")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    //public String handleFileUpload(@RequestParam("file") MultipartFile file) {
    public String handleFileUpload(@RequestBody String file) {
/*
    	try {

    		Path tempFile = Files.createTempFile("dd", ".txt");

            if (file.isEmpty()) {
                return "arquivo vazio";
            }

            Files.copy(file.getInputStream(), Paths.get("/home/megazzzmata/Área de Trabalho/demo/" + tempFile.getFileName() ) ,StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            return " erro excecao ";
		}
*/
		return file;		
        //return "Upload Succesful!";
    }
}