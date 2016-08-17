package com.sngular.jcnunezd.shop.products.dto;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain=true)
public class Product {
	
	@Id
	private String id;
	private String name;
	private String desc;
	private double price;

}
