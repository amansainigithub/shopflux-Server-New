package com.bezkoder.springjwt.adminControllers.productController;

import com.bezkoder.springjwt.helper.ResponseData;
import com.bezkoder.springjwt.services.productCategoryServices.ProductRootCategoryImple;
import com.bezkoder.springjwt.entities.productEntities.ProductRootCategoryForm;
import com.bezkoder.springjwt.urlMappings.URLMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(URLMappings.AUTH_ACCESS_URL_ADMIN)
public class ProductRootCategoryController {

    @Autowired
    private ProductRootCategoryImple productRootCategoryImple;

    @PostMapping(URLMappings.SAVE_PRODUCT_ROOT_CATEGORY)
    public ResponseEntity< Map<String,Object> > saveProductRootCategory(@RequestBody ProductRootCategoryForm productRootCategoryForm)
    {
        HashMap<String,Object> data=new HashMap<String, Object>();
      try {
          ProductRootCategoryForm productRootCategoryData=this.productRootCategoryImple.saveProductRootCategory(productRootCategoryForm);
          if(productRootCategoryData != null)
          {
              data.put("productRootCategoryForm",productRootCategoryForm);
              return ResponseEntity.ok(data);
          }
          else
          {
              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
          }
      }
      catch (Exception e)
      {
          ResponseData responseData=new ResponseData("Something Went Wrong");
          data.put("message",responseData);
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(data);
      }

    }

    @GetMapping(URLMappings.GET_ROOT_CATEGORY_LIST)
    public ResponseEntity<?> getRootCategoryList()
    {
        if(this.productRootCategoryImple.getRootCategoryList() != null)
            return  ResponseEntity.status(HttpStatus.OK).body(this.productRootCategoryImple.getRootCategoryList());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }


    @DeleteMapping(URLMappings.REMOVE_ROOT_CATEGORY)
    public ResponseEntity<?> removeRootCategory(@PathVariable("rootCategoryId") long rootCategoryId)
    {
        if(this.productRootCategoryImple.removeRootCategory(rootCategoryId))
        {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).header("something went wrong").build();
        }

    }


    @GetMapping(URLMappings.GET_ROOT_CATEGORY_BY_ID)
    public ResponseEntity<?> getRootCategoryById(@PathVariable("rootCategoryId") long rootCategoryId)
    {
       ProductRootCategoryForm productRootCategoryForm= this.productRootCategoryImple.getRootCategoryById(rootCategoryId);
        if(productRootCategoryForm != null)
            return  ResponseEntity.status(HttpStatus.OK).body(this.productRootCategoryImple.getRootCategoryById(rootCategoryId));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data Not Found");

    }


    @PutMapping(URLMappings.UPDATE_ROOT_CATEGORY)
    public ResponseEntity<?> updateRootCategory(@RequestBody ProductRootCategoryForm productRootCategoryForm)
    {
        if(this.productRootCategoryImple.updateRootCategory(productRootCategoryForm))
        {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).header("something went wrong").build();
        }

    }



}
