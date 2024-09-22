package com.example.demo;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class CrudOperation {

    @Autowired
    private ProductRepository prdRepo;

    public void saveMethod() {  
        Product prd = new Product();

        // Setting product fields
        prd.setName("Product 1");
        prd.setDescription("Product 1 Description");
        prd.setSku("100ABC");
        prd.setPrice(new BigDecimal(1000));
        prd.setActive(true);
        prd.setImageUrl("ABC.net");

        // Saving product
        prdRepo.save(prd);
    }
    
    public void Update() {
    	
    	//STEP 1 TO FIND
    	long id=1;
    	Product prd=prdRepo.findById(id).get();
    	
    	//NOW Update
    	prd.setName("Updated Product");
    	prdRepo.save(prd);
    	
    }
    
    public void findById() {
    	
    	//Find By ID
    	
    	long id=1;
    	Product p=prdRepo.findById(id).get();
    	System.out.println(p);
    }
    
    
    public void saveAll() {
    	 Product prd = new Product();
    	 prd.setName("Product 7");
         prd.setDescription("Product 2 Description");
         prd.setSku("100525");
         prd.setPrice(new BigDecimal(1000));
         prd.setActive(true);
         prd.setImageUrl("ABC.net");
         
         
         //2.record
         Product prd1 = new Product();
    	 prd1.setName("Product 8");
         prd1.setDescription("Product 3 Description");
         prd1.setSku("100135");
         prd1.setPrice(new BigDecimal(1000));
         prd1.setActive(true);
         prd1.setImageUrl("ABC.net");
         
         //SAVE ALL
         prdRepo.saveAll(List.of(prd,prd1));
    }
    
    public void findAll() {
    	List<Product> prd=prdRepo.findAll();
    	System.out.println(prd);
    }
    
    public void deleteById() {
    	long id=3;
    	prdRepo.deleteById(id);
    }
    
    public void delete() {        
         //1.Find the Id
    	Product p=prdRepo.findById((long) 1).get();
    	
         //2.Delete the record
    	
    	prdRepo.delete(p);
         
    }
    
    public void deleteAll() {
    	prdRepo.deleteAll();
    }
    
    public void deleteAllAnother() {
    	
    	Product p1=prdRepo.findById((long)9).get();
    	Product p2=prdRepo.findById((long)10).get();
    	
    	prdRepo.deleteAll(List.of(p1,p2));
    	
    }
    public void count() {
    	
    	long num=prdRepo.count();
    	System.out.println(num);
    	
    }
    
    public void exits() {
    boolean flag=prdRepo.existsById((long)11);
    System.out.println(flag);
    	
    }
    
}
