package com.bezkoder.springjwt.adminControllers.productController;

import com.bezkoder.springjwt.entities.productEntities.ProductForm;
import com.bezkoder.springjwt.entities.productEntities.ProductSizeCreateForm;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.services.productCategoryServices.ProductSizeCreateServiceImple;
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
public class ProductSizeCreateController {

    @Autowired
    private ProductSizeCreateServiceImple productSizeCreateServiceImple;


    @PostMapping(URLMappings.CREATE_PRODUCT_SIZE)
    public ResponseEntity<?> createProductSize(@RequestBody ProductSizeCreateForm productSizeCreateForm, HttpServletResponse response)
    {
        ProductSizeCreateForm productSizeData= this.productSizeCreateServiceImple.createProductSize(productSizeCreateForm);

        if(productSizeData != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(productSizeData);
        }
        else
        {
            response.setHeader("message","Something went wrong ! ERROR.");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }

    }

    @PutMapping(URLMappings.UPDATE_PRODUCT_SIZE)
    public ResponseEntity<?> updateProductSize(@RequestBody ProductSizeCreateForm productSizeCreateForm, HttpServletResponse response)
    {
        ProductSizeCreateForm productSizeData= this.productSizeCreateServiceImple.createProductSize(productSizeCreateForm);

        if(productSizeData != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(productSizeData);
        }
        else
        {
            response.setHeader("message","Something went wrong ! ERROR.");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }

    }

    @DeleteMapping(URLMappings.DELETE_PRODUCT_SIZE)
    public ResponseEntity<?> deleteProductSize(@PathVariable String id , HttpServletResponse response)
    {
        if( this.productSizeCreateServiceImple.deleteProductSize(id))
        {
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("success"));
        }
        else
        {
            response.setHeader("message","Something went wrong ! ERROR.");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }

    }


    @GetMapping(URLMappings.GET_PRODUCT_DETAILS_SIZE_BY_ID)
    public ResponseEntity<?> getProductDetailsSizeById(@PathVariable String sizeId , HttpServletResponse response)
    {
        ProductSizeCreateForm productSizeCreateForm =  this.productSizeCreateServiceImple.getProductDetailsSizeByid(sizeId);
        if(productSizeCreateForm != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(productSizeCreateForm);
        }
        else
        {
            response.setHeader("message","Something went wrong ! ERROR.");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }

    }

    @GetMapping(URLMappings.GET_PRODUCT_SIZE_LIST)
    public ResponseEntity<?> getProductSizeList( HttpServletResponse response)
    {
        List<ProductSizeCreateForm> list =  this.productSizeCreateServiceImple.getProductSizeList();
        if(list != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }
        else
        {
            response.setHeader("message","Something went wrong ! ERROR.");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }

    }

}
