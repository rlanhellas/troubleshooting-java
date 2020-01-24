package br.com.lanhellas.troubleshooting;

import br.com.lanhellas.troubleshooting.model.Customer;
import br.com.lanhellas.troubleshooting.repository.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@RequestMapping
@RestController
public class MainRestController {

    private final CustomerRepository repository;

    public MainRestController(CustomerRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
//        for(int i = 0; i < 100000; i++){
//            Customer customer = new Customer();
//            customer.setId(UUID.randomUUID().toString());
//            customer.setName(UUID.randomUUID().toString());
//            repository.save(customer);
//        }
    }

    @GetMapping("database")
    public ResponseEntity<Customer> hitHere() {
        return ResponseEntity.ok(repository.findById(UUID.randomUUID().toString()).orElse(null));
    }

    @GetMapping("memory-leak")
    public void hitHereMemoryLeak() {
        startLeaking();
    }

    @GetMapping("memory-leak-for")
    public void hitHereMemoryLeakFor() {
        startLeaking();
    }

    private void startLeaking() {
        try {
            StringBuilder input = new StringBuilder();
            URLConnection conn = new URL("https://github.com/rlanhellas/troubleshooting-java").openConnection();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));

            while (br.readLine() != null) {
                input.append(br.readLine());
            }

//            br.close();

        } catch (Exception e) {
            startLeaking();
        }
    }

}
