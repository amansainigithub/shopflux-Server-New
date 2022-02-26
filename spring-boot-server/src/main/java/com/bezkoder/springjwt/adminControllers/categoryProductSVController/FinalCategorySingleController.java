package com.bezkoder.springjwt.adminControllers.categoryProductSVController;

import com.bezkoder.springjwt.entities.categoryProductSingleEntites.CStructureFinalCategory;
import com.bezkoder.springjwt.entities.productEntities.ProductForm;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.services.productCategorySingleServices.FinalCategorySingleImple;
import com.bezkoder.springjwt.urlMappings.CPSingleUrlMappings;
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


    @GetMapping(CPSingleUrlMappings.GET_CSTRUCTURE_FINAL_CATEGORY_LIST)
    public ResponseEntity<?> getCStructureFinalCategoryList()
    {
        List<CStructureFinalCategory> list = this.finalCategorySingleImple.getCStructureFinalCategoryList();
        if(list != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }

    }

    @DeleteMapping(CPSingleUrlMappings.DELETE_C_STRUCTURE_FINAL_CATEGORY)
    public ResponseEntity<?> deleteCStructureFinalCategory(@PathVariable String id)
    {
        boolean result = this.finalCategorySingleImple.deleteCStructureFinalCategory(id);
        if(result)
        {
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("success"));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }

    }



}
