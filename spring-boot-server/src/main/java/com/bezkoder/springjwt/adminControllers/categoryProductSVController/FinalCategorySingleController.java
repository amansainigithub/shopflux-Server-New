package com.bezkoder.springjwt.adminControllers.categoryProductSVController;

import com.bezkoder.springjwt.entities.categoryProductSingleEntites.CStructureFinalCategory;
import com.bezkoder.springjwt.entities.productEntities.ProductForm;
import com.bezkoder.springjwt.services.productCategorySingleServices.FinalCategorySingleImple;
import com.bezkoder.springjwt.urlMappings.CPSingleUrlMappings;
import com.bezkoder.springjwt.urlMappings.URLMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(URLMappings.AUTH_ACCESS_URL_ADMIN)
public class FinalCategorySingleController {

    @Autowired
    private FinalCategorySingleImple finalCategorySingleImple;


    @PostMapping(CPSingleUrlMappings.SAVE_FINAL_CATEGORY_SINGLE)
    public ResponseEntity<?> saveFinalCategorySingle(@RequestBody CStructureFinalCategory cStructureFinalCategory, HttpServletResponse response)
    {
        CStructureFinalCategory cStructureFinalCategoryResult= this.finalCategorySingleImple.saveCStructureFinalCategory(cStructureFinalCategory);

        if(cStructureFinalCategoryResult != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(cStructureFinalCategoryResult);
        }
        else
        {
            response.setHeader("message","Something went wrong ! ERROR.");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }

    }




}
