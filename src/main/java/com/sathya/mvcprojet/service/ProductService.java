package com.sathya.mvcprojet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathya.mvcprojet.entity.ProductEntity;
import com.sathya.mvcprojet.model.ProductModel;
import com.sathya.mvcprojet.repository.ProductRepository;

@Service
public class ProductService 
{
   @Autowired
   ProductRepository productRepository;

  public void saveProduct(ProductModel productModel) 
  {
	ProductEntity productEntity =new ProductEntity();
	productEntity.setName(productModel.getName());
	productEntity.setPrice(productModel.getPrice());
	productRepository.save(productEntity);
	
  }
  public List<ProductEntity> getAllProducts() 
  { 
	  List<ProductEntity> products=productRepository.findAll();
	  return products; 
  }
    public ProductEntity getProductById(Long id)
	{
		
		ProductEntity product= productRepository.findById(id).get();
		return product;
	}
	
	public void getDeleteById(long id)
	{
		productRepository.deleteById(id);
	}
	
	
	public ProductModel getEditProductId(long id) 
	{
		 ProductEntity productEntity=productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));;
		 ProductModel productModel=new ProductModel();
		 productModel.setName(productEntity.getName());
		 productModel.setPrice(productEntity.getPrice());
		 return productModel;
		
	}
	public void updateProduct(long id, ProductModel productModel) 
	{
		
		ProductEntity productEntity = productRepository.findById(id)
		        .orElseThrow(() -> new RuntimeException("Product not found"));

		    // Update only name and price
		    productEntity.setName(productModel.getName());
		    productEntity.setPrice(productModel.getPrice());

		    productRepository.save(productEntity);
		/*
		 * ProductEntity productEntity=new ProductEntity();
		 * productEntity.setName(productModel.getName());
		 * productEntity.setPrice(productModel.getPrice());
		 * productRepository.save(productEntity);
		 */
		
		/*
		 * return productRepository.findById(id).map(product -> {
		 * product.setName(productModel.getName());
		 * product.setPrice(productModel.getPrice()); return
		 * productRepository.save(product); }).orElse(null);
		 */
	
		/*
		 * ProductEntity productEntity =
		 * productRepository.findById(productModel.getId()) .orElseThrow(() -> new
		 * RuntimeException("Product not found"));
		 * 
		 * // Update fields productEntity.setName(productModel.getName());
		 * productEntity.setPrice(productModel.getPrice());
		 * 
		 * productRepository.save(productEntity); // Save updated entity
		 */
	}
	   
	  


	 

	    
}
