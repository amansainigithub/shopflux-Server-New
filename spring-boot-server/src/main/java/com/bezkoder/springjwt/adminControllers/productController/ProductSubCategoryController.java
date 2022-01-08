package com.bezkoder.springjwt.adminControllers.productController;

import com.bezkoder.springjwt.entities.productEntities.ProductRootCategoryForm;
import com.bezkoder.springjwt.helper.ResponseData;
import com.bezkoder.springjwt.services.productCategoryServices.ProductSubCategoryImple;
import com.bezkoder.springjwt.entities.productEntities.ProductSubCategoryForm;
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
public class ProductSubCategoryController {

    @Autowired
    private ProductSubCategoryImple productSubCategoryImple;

    @PostMapping(URLMappings.ADD_PRODUCT_SUB_CATEGORY)
    public ResponseEntity<Map<String,Object>> addProductSubCategory(@RequestBody ProductSubCategoryForm productSubCategoryForm)
    {
        HashMap<String,Object> data=new HashMap<String, Object>();
        try {
            ProductSubCategoryForm productSubCategory=this.productSubCategoryImple.addProductSubCategory(productSubCategoryForm);
            if(productSubCategory != null)
            {
                data.put("productRootCategoryForm",productSubCategory);
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
    @GetMapping(URLMappings.GET_SUB_CATEGORY_LIST)
    public ResponseEntity<?> getSubCategoryList()
    {
        if(this.productSubCategoryImple.getProductSubCategoryList() != null)
            return  ResponseEntity.status(HttpStatus.OK).body(this.productSubCategoryImple.getProductSubCategoryList());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping(URLMappings.REMOVE_SUB_CATEGORY)
    public ResponseEntity<?> removeSubCategory(@PathVariable("subCategoryId") long subCategoryId)
    {
        if(this.productSubCategoryImple.removeSubCategory(subCategoryId))
        {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).header("something went wrong").build();
        }
    }


    @PostMapping(URLMappings.UPDATE_SUB_CATEGORY)
    public ResponseEntity<Map<String,Object>> updateSubCategory(@RequestBody ProductSubCategoryForm productSubCategoryForm)
    {
        HashMap<String,Object> data=new HashMap<String, Object>();
        try {
            ProductSubCategoryForm productSubCategory=this.productSubCategoryImple.updateSubCategory(productSubCategoryForm);
            if(productSubCategory != null)
            {
                data.put("productRootCategoryForm",productSubCategory);
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

    @GetMapping(URLMappings.GET_SUB_CATEGORY_BY_ID)
    public ResponseEntity<?> getSubCategoryById(@PathVariable("subCategoryId") long subCategoryId)
    {


        ProductSubCategoryForm productSubCategoryForm= this.productSubCategoryImple.getSubCategoryById(subCategoryId);
        if(productSubCategoryForm != null)
            return  ResponseEntity.status(HttpStatus.OK).body(this.productSubCategoryImple.getSubCategoryById(subCategoryId));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data Not Found");

    }




}
