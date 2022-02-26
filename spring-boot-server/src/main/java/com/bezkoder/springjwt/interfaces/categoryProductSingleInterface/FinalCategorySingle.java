package com.bezkoder.springjwt.interfaces.categoryProductSingleInterface;

import com.bezkoder.springjwt.entities.categoryProductSingleEntites.CStructureFinalCategory;

import java.util.List;

public interface FinalCategorySingle {

    public CStructureFinalCategory saveCStructureFinalCategory(CStructureFinalCategory cStructureFinalCategory);
    public List<CStructureFinalCategory> getCStructureFinalCategoryList();
    public boolean deleteCStructureFinalCategory(String cStructureId);
}
