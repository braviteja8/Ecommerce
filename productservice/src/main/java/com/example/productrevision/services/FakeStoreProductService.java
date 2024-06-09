package com.example.productrevision.services;

import com.example.productrevision.dtos.ProductRequestDto;
import com.example.productrevision.dtos.ProductResponseDto;
import com.example.productrevision.exceptions.InvalidIdException;
import com.example.productrevision.models.Category;
import com.example.productrevision.models.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("fakeStoreProductService")
public class FakeStoreProductService implements ProductServiceInterface{
    private final RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }
    public Product getProduct(ProductResponseDto productResponseDto){
        Product product=new Product();
        product.setId(productResponseDto.getId());
        product.setName(productResponseDto.getTitle());
        product.setImage(productResponseDto.getImage());
        product.setPrice(productResponseDto.getPrice());
        product.setDescription(productResponseDto.getDescription());
        Category category=new Category();
        category.setName(productResponseDto.getCategory());
        product.setCategory(category);
        return product;
    }

//    @Override
//    public Product getSingleProduct(Long id) {
//        ProductResponseDto responseDto=restTemplate.getForObject("https://fakestoreapi.com/products/"+id,ProductResponseDto.class);
//        Product product=getProduct(responseDto);
//        return product;
//    }

    @Override
    public Product getSingleProduct(Long id, String token) throws InvalidIdException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        ProductResponseDto[] responseDtos=restTemplate.getForObject("https://fakestoreapi.com/products",ProductResponseDto[].class);
        List<Product>products=new ArrayList<>();
        for(ProductResponseDto responseDto:responseDtos){
            products.add(getProduct(responseDto));
        }
        return products;
    }

    @Override
    public Product updateProduct(Long id, ProductRequestDto productRequestDto) {

        RequestCallback requestCallback=restTemplate.httpEntityCallback(productRequestDto,ProductResponseDto.class);
        HttpMessageConverterExtractor<ProductResponseDto> responseExtractor =
                new HttpMessageConverterExtractor<>(ProductResponseDto.class,restTemplate.getMessageConverters());
        ProductResponseDto responseDto=restTemplate.execute("https://fakestoreapi.com/products/7", HttpMethod.PUT,
                requestCallback, responseExtractor);

//        RequestCallback requestCallback = httpEntityCallback(request);
//        execute(url, HttpMethod.PUT, requestCallback, null, uriVariables);

        return getProduct(responseDto);
    }

    @Override
    public Product addProduct(ProductRequestDto productRequestDto) {
        return null;
    }
}
