package com.sngular.jcnunezd.shop.products;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sngular.jcnunezd.shop.products.dto.Product;

public interface ProductsRepository extends MongoRepository<Product, String>{

}
