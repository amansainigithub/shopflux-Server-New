package com.bezkoder.springjwt.adminControllers.productController;

import com.bezkoder.springjwt.entities.productEntities.ProductForm;
import com.bezkoder.springjwt.entities.productEntities.ProductSizeSetForProductForm;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.services.productCategoryServices.ProductSizeSetForProductImple;
import com.bezkoder.springjwt.urlMappings.CartOrdersUrlMappings;
import com.bezkoder.springjwt.urlMappings.URLMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(URLMappings.AUTH_ACCESS_URL_ADMIN)
public class ProductSizeSetForProductController {


    @Autowired
    private ProductSizeSetForProductImple productSizeSetForProductImple;



    @PostMapping(CartOrdersUrlMappings.SAVE_PRODUCT_SIZE_SET_FOR_PRODUCT)
    public ResponseEntity<?> saveProductSizeSetForProduct(@PathVariable String productId, @RequestBody ProductSizeSetForProductForm productSizeSetForProductForm, HttpServletResponse response)
    {
        ProductSizeSetForProductForm   productSizeSetForProductData= this.productSizeSetForProductImple.
                                                    saveProductSizeSetProductForm
                                                            (productId,productSizeSetForProductForm);
        if(productSizeSetForProductData != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(productSizeSetForProductData);
        }
        else
        {
            response.setHeader("message","Something went wrong ! ERROR.");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }

    }

    @DeleteMapping(CartOrdersUrlMappings.REMOVE_PRODUCT_SIZE_FOR_PRODUCT_BY_ID)
    public ResponseEntity<?> removeProductSizeForProductById(@PathVariable String productSizeId, HttpServletResponse response)
    {
        if(this.productSizeSetForProductImple.removeProductSizeForProductById(productSizeId))
        {
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Delete Success"));
        }
        else
        {
            response.setHeader("message","Something went wrong ! ERROR.");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }

    }

    @GetMapping(CartOrdersUrlMappings.GET_PRODUCT_SIZE_FOR_PRODUCT_BY_ID)
    public ResponseEntity<?> getProductSizeForProductById(@PathVariable String productSizeId, HttpServletResponse response)
    {
        ProductSizeSetForProductForm   productSizeSetForProductData  =   this.productSizeSetForProductImple.getProductSizeForProductById(productSizeId);
        if(productSizeSetForProductData != null )
        {
            return ResponseEntity.status(HttpStatus.OK).body(productSizeSetForProductData);
        }
        else
        {
            response.setHeader("message","Something went wrong ! ERROR.");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }

    }


}
