package SpringEcom.Controller;


import SpringEcom.Entity.Product;
import SpringEcom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
       List<Product> product=productService.getAllProducts();
       return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable int id){
        Product product=productService.findByProductId(id);
        if(product!=null){
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/product")
    public ResponseEntity<Product> addProduct(@RequestPart Product product, @RequestPart MultipartFile image){
        try {
            Product resproduct=productService.addProduct(product,image);
            return new ResponseEntity<>(resproduct,HttpStatus.OK);
        } catch (IOException e) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
