package br.arida.cadim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import br.arida.cadim.storage.StorageProperties;;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Boot
{

   public static void main(String[] args)
   {
      SpringApplication.run(Boot.class, args);
   }
}
