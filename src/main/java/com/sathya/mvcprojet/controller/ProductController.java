package com.sathya.mvcprojet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sathya.mvcprojet.entity.ProductEntity;
import com.sathya.mvcprojet.model.ProductModel;
import com.sathya.mvcprojet.service.ProductService;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class ProductController 
{
    @Autowired
    ProductService productService;
    
   /* @GetMapping("/emptyform")
    public String message()
    {
    	return"productform";
    }
    */
    @GetMapping("/defaultform")
    public String defaultForm(Model model)
    {
    	ProductEntity product=new ProductEntity();
    	//product.setPrice(100);
    	model.addAttribute("product",product);
    	return"productform";
    }	
    
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute ProductModel model)
    {
    	productService.saveProduct(model);
    	return "msg";
    }

      @GetMapping("/retrive") 
      public String retriveProducts(Model model) 
	  {
	  List<ProductEntity> products=productService.getAllProducts();
	  model.addAttribute("products",products);
	  return"retrivetableform"; 
	  }    
	  
        @GetMapping("/product/{id}")
	    public String getProductById(@PathVariable Long id,Model model) 
	    {
		  
		  ProductEntity product=productService.getProductById(id);
		  model.addAttribute("product",product);
	        return "productid";
	    }
	    
	    @GetMapping("/product/delete/{id}")
	    public String deleteById(@PathVariable("id") Long id) 
	    {
		  
		    productService.getDeleteById(id);
	        return "redirect:/retrive";
	    }
	    
	    @GetMapping("/products/edit/{id}")
	    public String editProduct(@PathVariable long id,Model model) 
	    {
	    	ProductModel productModel=productService.getEditProductId(id);
	    	model.addAttribute("productModel",productModel);
	    	return "edit";
	    }
	    
	    @PostMapping("/products/update")
	    public String editProduct(@PathVariable long id,@ModelAttribute ProductModel productModel) 
	    {
	         productService.updateProduct(id,productModel);
	    	return "redirect:/retrive";
	    }
	    
 
	 
	    
}
