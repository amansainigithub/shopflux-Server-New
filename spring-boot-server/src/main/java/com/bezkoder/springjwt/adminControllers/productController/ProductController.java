package com.bezkoder.springjwt.adminControllers.productController;

import com.bezkoder.springjwt.bucket.bucketRepository.BucketRepository;
import com.bezkoder.springjwt.entities.productEntities.ProductFileUrls;
import com.bezkoder.springjwt.entities.productEntities.ProductForm;
import com.bezkoder.springjwt.helper.ResponseData;
import com.bezkoder.springjwt.services.productCategoryServices.ProductServiceImple;
import com.bezkoder.springjwt.urlMappings.URLMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(URLMappings.AUTH_ACCESS_URL_ADMIN)
public class ProductController {

    @Autowired
    private ProductServiceImple productServiceImple;

    @Autowired
    private BucketRepository bucketRepository;

    @PostMapping(URLMappings.SAVE_PRODUCT)
    public ResponseEntity<?> saveProduct(@RequestBody ProductForm productForm,HttpServletResponse response)
    {
        System.out.println("****************************************************************");
//        System.out.println(productForm.getUk10());
//        System.out.println(productForm.getUk4());

        System.out.println("****************************************************************");
        ProductForm product= this.productServiceImple.saveProduct(productForm);

        if(product != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }
        else
        {
            response.setHeader("message","Something went wrong ! ERROR.");
           return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }

    }


    @DeleteMapping(URLMappings.DELETE_PRODUCT_BY_ID)
    public ResponseEntity<?> deleteProductById(@PathVariable("productId") long productId)
    {
        if(this.productServiceImple.deleteProduct(productId))
        {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else
        {
         return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }



    @GetMapping(URLMappings.GET_PRODUCT_LIST)
    public ResponseEntity<?> getProductList(HttpServletResponse response,Principal principal)
    {
            System.out.println("*********************************"+principal.getName());
        List<ProductForm> list = this.productServiceImple.getProductList();
        if(list != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }



    @GetMapping(URLMappings.GET_PRODUCT_BY_ID)
    public ResponseEntity<?> getProductById(@PathVariable("productId") String productId)
    {
        ProductForm productForm = this.productServiceImple.getProductById(Long.parseLong(productId));
        if(productForm != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(productForm);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @GetMapping(URLMappings.GET_PRODUCT_FILES_BY_PRODUCT_ID)
    public ResponseEntity<?> getProductFilesByProductId(@PathVariable("productId") String productId)
    {
        List<ProductFileUrls> productFileUrls = this.productServiceImple.getProductFilesByProductId(productId);
        if(productFileUrls.size() >0)
        {
            return ResponseEntity.status(HttpStatus.OK).body(productFileUrls);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @PostMapping(URLMappings.SET_PRODUCT_THUMBNAIL)
    public ResponseEntity<?> setProductThumbnail(@PathVariable("productId")String productId,
                                                 @PathVariable("bucketId") String bucketId,
                                                 HttpServletResponse response)
    {
        if(this.productServiceImple.setProductThumbnail(productId,bucketId))
        {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else
        {
            response.setHeader("message","Something went wrong ! ERROR.");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }

    }


    @PostMapping(URLMappings.SET_THUMBNAIL_MAIN_PAGE)
    public ResponseEntity<?> setThumbnailMainPage(@PathVariable("productId")String productId,
                                                 @PathVariable("bucketId") String bucketId,
                                                 HttpServletResponse response)
    {
        if(this.productServiceImple.setThumbnailMainPage(productId,bucketId))
        {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else
        {
            response.setHeader("message","Something went wrong ! ERROR.");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }

    }




    @PostMapping("/Nano_cooking")
    public ResponseEntity<?> DemoTester()
    {
       ProductForm productForm1=new ProductForm();

        Map<String,Object> map=new HashMap<>();
        map.put("data",this.bucketRepository.findAll());
        map.put("success","Message successfully nodes to node");
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }




}
