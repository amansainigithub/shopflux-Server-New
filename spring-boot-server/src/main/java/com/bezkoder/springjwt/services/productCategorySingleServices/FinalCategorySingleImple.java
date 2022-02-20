package com.bezkoder.springjwt.services.productCategorySingleServices;

import com.bezkoder.springjwt.entities.categoryProductSingleEntites.CStructureFinalCategory;
import com.bezkoder.springjwt.interfaces.categoryProductSingleInterface.FinalCategorySingle;
import com.bezkoder.springjwt.repositories.productCategorySingleRepo.FinalCategorySingleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinalCategorySingleImple implements FinalCategorySingle {

    @Autowired
    private FinalCategorySingleRepository finalCategorySingleRepository;

    @Override
    public CStructureFinalCategory saveCStructureFinalCategory(CStructureFinalCategory cStructureFinalCategory) {
      CStructureFinalCategory cStructureFinaldata = null;

       try {
           cStructureFinaldata  =   this.finalCategorySingleRepository.save(cStructureFinalCategory);
       }
       catch (Exception e)
       {
        e.printStackTrace();
       }
       return cStructureFinaldata;
    }
}
