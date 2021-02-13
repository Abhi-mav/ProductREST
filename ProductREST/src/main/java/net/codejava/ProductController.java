package net.codejava;
 
import java.util.*;
 
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
 
import org.springframework.web.bind.annotation.*;
 
@RestController
public class ProductController {
 
    @Autowired
    private ProductService service;
     
    @GetMapping("/products")
    public List<Product> list() {
        return service.listAll();
    }
     
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> get(@PathVariable Integer id) {
        try {
            Product product = service.get(id);
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }      
    }
     
    @PostMapping("/products")
    public void add(@RequestBody Product product) {
        service.save(product);
    }
    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
