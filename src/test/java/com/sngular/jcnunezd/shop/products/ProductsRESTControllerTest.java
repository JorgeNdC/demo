package com.sngular.jcnunezd.shop.products;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.Mockito.mock.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.sngular.jcnunezd.shop.products.dto.Product;


@RunWith(SpringRunner.class)
@WebMvcTest(ProductsRESTController.class)
public class ProductsRESTControllerTest {

	private static final String PRODUCT_JSON = "{ \"name\": \"Play Station 4\", \"desc\": \"Super cool console\", \"price\": 399.0 }";
	
	@Autowired
    private MockMvc mvc;
	
	@MockBean
	private ProductsRepository repository;
	
	@Test
	public void whenNewProductIsSent_ItWillBeCreatedWithUniqueId() throws Exception {
		
		Mockito.when(repository.save(Mockito.any(Product.class)))
		.thenReturn(new Product().setId("1234").setName("Play Station 4"));
		mvc.perform(	
				post("/v1/products")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON)
				.content(PRODUCT_JSON))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.id").isNotEmpty())
		.andExpect(jsonPath("$.name").value("Play Station 4"));
	}
	
	@Test
	public void whenAnExistingProductIsRequested_ItWillBeReturned() throws Exception {
		
		Mockito.when(repository.findOne(Mockito.any(String.class)))
		.thenReturn(new Product().setId("1234"));
		mvc.perform(
				get("/v1/products/1234")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value("1234"));
	}
}
