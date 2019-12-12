package br.arida.cadim.repositories;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.arida.cadim.model.StorageService;

@Service
public class FileSystemStorageService implements StorageService {

	private final Path rootLocation;
	
	@Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }
	
	@Override
	public void init() {
		try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            System.out.print("FALHA!");
        }
	}

	@Override
	public void store(MultipartFile file) {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
            	System.out.print("FALHA!");
            }
            
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
        	System.out.print("FALHA!");
        }

	}

}
