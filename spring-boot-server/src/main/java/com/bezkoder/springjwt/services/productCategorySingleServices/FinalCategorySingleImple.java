package com.bezkoder.springjwt.services.productCategorySingleServices;

import com.bezkoder.springjwt.entities.categoryProductSingleEntites.CStructureFinalCategory;
import com.bezkoder.springjwt.entities.productEntities.ProductFinalCateogoryForm;
import com.bezkoder.springjwt.interfaces.categoryProductSingleInterface.FinalCategorySingle;
import com.bezkoder.springjwt.repositories.productCategoryRepository.ProductFinalCategoryRepository;
import com.bezkoder.springjwt.repositories.productCategorySingleRepo.FinalCategorySingleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinalCategorySingleImple implements FinalCategorySingle {

    @Autowired
    private FinalCategorySingleRepository finalCategorySingleRepository;

    @Autowired
    private ProductFinalCategoryRepository productFinalCategoryRepository;


    @Override
    public CStructureFinalCategory saveCStructureFinalCategory(CStructureFinalCategory cStructureFinalCategory) {
      CStructureFinalCategory cStructureFinaldata = null;

       try {

         ProductFinalCateogoryForm productFinalCateogoryForm  =  this.productFinalCategoryRepository
                                                                  .findById(Long.parseLong(cStructureFinalCategory.getFinalCategoryId())).get();
         cStructureFinalCategory.setFinalCategoryName(productFinalCateogoryForm.getFinalCategoryName());

           cStructureFinaldata  =   this.finalCategorySingleRepository.save(cStructureFinalCategory);
       }
       catch (Exception e)
       {
        e.printStackTrace();
       }
       return cStructureFinaldata;
    }

    public    List<CStructureFinalCategory> getCStructureFinalCategoryList() {
        List<CStructureFinalCategory> cStructureFinaldata = null;

        try {
            cStructureFinaldata  =   this.finalCategorySingleRepository.findAll();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return cStructureFinaldata;
    }

    public boolean deleteCStructureFinalCategory(String id) {
        boolean flag=false;
        try {
                this.finalCategorySingleRepository.deleteById(Long.parseLong(id));
                flag=true;
        }
        catch (Exception e)
        {
                e.printStackTrace();
        }
        return flag;
    }
}
