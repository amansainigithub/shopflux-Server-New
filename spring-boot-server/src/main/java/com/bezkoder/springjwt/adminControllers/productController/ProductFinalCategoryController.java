package com.bezkoder.springjwt.adminControllers.productController;

import com.bezkoder.springjwt.entities.productEntities.ProductFinalCateogoryForm;
import com.bezkoder.springjwt.entities.productEntities.ProductRootCategoryForm;
import com.bezkoder.springjwt.entities.productEntities.ProductSubCategoryForm;
import com.bezkoder.springjwt.helper.ResponseData;
import com.bezkoder.springjwt.repositories.productCategoryRepository.ProductFinalCategoryRepository;
import com.bezkoder.springjwt.services.productCategoryServices.ProductFinalCategoryImple;
import com.bezkoder.springjwt.services.productCategoryServices.ProductRootCategoryImple;
import com.bezkoder.springjwt.urlMappings.URLMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(URLMappings.AUTH_ACCESS_URL_ADMIN)
public class ProductFinalCategoryController {

    @Autowired
    private ProductFinalCategoryImple productFinalCategoryImple;

    @PostMapping(URLMappings.SAVE_FINAL_PRODUCT_CATEGORY)
    public ResponseEntity<Map<String,Object>> saveFinalProductCatgory(@Valid @RequestBody ProductFinalCateogoryForm productFinalCateogoryForm)
    {
        HashMap<String,Object> data=new HashMap<String, Object>();
        try {
            ProductFinalCateogoryForm finalCategoryData=this.productFinalCategoryImple.saveFinalProductCategory(productFinalCateogoryForm);
            if(finalCategoryData != null)
            {
                data.put("productRootCategoryForm",finalCategoryData);
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


    @GetMapping(URLMappings.GET_FINAL_CATEGORY_LIST)
    public ResponseEntity<?> getFinalCategoryList()
    {
        if(this.productFinalCategoryImple.getFinalCategoryList() != null)
            return  ResponseEntity.status(HttpStatus.OK).body(this.productFinalCategoryImple.getFinalCategoryList());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping(URLMappings.UPDATE_FINAL_CATEGORY)
    public ResponseEntity<Map<String,Object>> updateFinalCategory(@RequestBody ProductFinalCateogoryForm productFinalCateogoryForm)
    {
        HashMap<String,Object> data=new HashMap<String, Object>();
        try {
            ProductFinalCateogoryForm productFinalCateogoryForm1=this.productFinalCategoryImple.updateFinalCategory(productFinalCateogoryForm);
            if(productFinalCateogoryForm1 != null)
            {
                data.put("productFinalCateogoryForm1",productFinalCateogoryForm1);
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

    @GetMapping(URLMappings.GET_FINAL_CATEGORY_BY_ID)
    public ResponseEntity<?> getFinalCategoryById(@PathVariable("finalCategoryId") long finalCategoryId)
    {
        ProductFinalCateogoryForm productSubCategoryForm= this.productFinalCategoryImple.getFinalCategoryById(finalCategoryId);
        if(productSubCategoryForm != null)
            return  ResponseEntity.status(HttpStatus.OK).body(productSubCategoryForm);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data Not Found");

    }


}
