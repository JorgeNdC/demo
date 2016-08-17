package com.sngular.jcnunezd.shop.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sngular.jcnunezd.shop.products.dto.Product;

@RestController
@RequestMapping("v1/products")
public class ProductsRESTController {

	@Autowired
	private ProductsRepository repository;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Product> create(@RequestBody Product product) {

		return new ResponseEntity<Product>(repository.save(product), HttpStatus.CREATED);
	}

	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public Product get(@PathVariable String id) {
		
		return repository.findOne(id);
	}
}
