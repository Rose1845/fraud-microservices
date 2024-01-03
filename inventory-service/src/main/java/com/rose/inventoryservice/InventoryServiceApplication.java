package com.rose.inventoryservice;

import com.rose.inventoryservice.model.Inventory;
import com.rose.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(InventoryRepository inventoryRepository){
        return args -> {
            Inventory inventory =new Inventory();
            inventory.setQuantity(0);
            inventory.setSkuCode("SM456");
            Inventory inventory1= new Inventory();
            inventory1.setSkuCode("PL234");
            inventory1.setQuantity(3);
            inventoryRepository.save(inventory);

            inventoryRepository.save(inventory1);
        };
    }
}
