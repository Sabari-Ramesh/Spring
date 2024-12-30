package SpringEcom.service;

import SpringEcom.Entity.Product;
import SpringEcom.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;
    public List<Product> getAllProducts() {
       List<Product> products= productRepo.findAll();
       return products;
    }

    public Product findByProductId(int id) {
        Optional<Product> product =productRepo.findById(id);
        return product.orElse(null);
    }

    public Product addProduct(Product product, MultipartFile image) throws IOException {
        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImageData(image.getBytes());
        productRepo.save(product);
        return product;
    }
}
