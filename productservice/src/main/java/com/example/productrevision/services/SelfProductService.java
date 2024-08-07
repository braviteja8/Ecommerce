package com.example.productrevision.services;

import com.example.productrevision.dtos.ProductRequestDto;
import com.example.productrevision.exceptions.InvalidIdException;
import com.example.productrevision.models.Category;
import com.example.productrevision.models.Product;
import com.example.productrevision.repositories.CategoryRepo;
import com.example.productrevision.repositories.ProductRepository;
import com.netflix.discovery.converters.Auto;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Qualifier("selfProductService")
public class SelfProductService implements ProductServiceInterface{

    private ProductRepository productRepository;
    private CategoryRepo categoryRepo;
    @Autowired
    private RestTemplate restTemplate;
    private RedisTemplate<String,String>redisTemplate;
    public SelfProductService(ProductRepository productRepository,CategoryRepo categoryRepo,RedisTemplate redisTemplate){
        this.productRepository=productRepository;
        this.categoryRepo=categoryRepo;
        this.redisTemplate=redisTemplate;
    }
    public Product getSingleProduct(Long id) throws InvalidIdException {

//        if (redisTemplate.opsForHash().hasKey("PRODUCTS",id)) {
//            return (Product) redisTemplate.opsForHash().get("PRODUCTS",id);
//        }

        Optional<Product>productOptional=productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new InvalidIdException();
        }
        Product product=productOptional.get();
//        redisTemplate.opsForHash().put("PRODUCTS",id,product);
//        redisTemplate.expire("PRODUCTS",1, TimeUnit.MINUTES);
        return product;
    }



    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product> productOptional=productRepository.findById(id);
        Product existingProduct=productOptional.get();
//        existingProduct.setName(
//                product.getName()!=null ?
//                        product.getName() :
//                        existingProduct.getName()
//        );
        existingProduct.setImage(
                product.getImage()!=null ?
                        product.getImage() :
                        existingProduct.getImage()
        );
        existingProduct.setPrice(
                product.getPrice()>0 ?
                        product.getPrice() :
                        existingProduct.getPrice()
        );
        existingProduct.setDescription(
                product.getDescription()!=null ?
                        product.getDescription():
                        existingProduct.getDescription()
        );
        Optional<Category> categoryToCheck=categoryRepo.findByName(product.getCategory().getName());
        if(categoryToCheck.isEmpty()){
            Category category=new Category();
            category.setName(product.getCategory().getName());
            Category savedCategory=categoryRepo.save(category);
            existingProduct.setCategory(savedCategory);
        }
        else{
            existingProduct.setCategory(categoryToCheck.get());
        }
        return productRepository.save(existingProduct);
    }

    @Override
    public Product addProduct(Product product) {
        Optional<Category> categoryToCheck=categoryRepo.findByName(product.getCategory().getName());
        if(categoryToCheck.isEmpty()){
            Category category=new Category();
            category.setName(product.getCategory().getName());
            Category savedCategory=categoryRepo.save(category);
            product.setCategory(savedCategory);
        }
        else{
            product.setCategory(categoryToCheck.get());
        }

        return productRepository.save(product);
    }
}
