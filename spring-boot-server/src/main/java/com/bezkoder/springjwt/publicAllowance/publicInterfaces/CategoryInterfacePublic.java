package com.bezkoder.springjwt.publicAllowance.publicInterfaces;

import com.bezkoder.springjwt.entities.productEntities.ProductFinalCateogoryForm;
import com.bezkoder.springjwt.entities.productEntities.ProductRootCategoryForm;

import java.util.List;

public interface CategoryInterfacePublic {

    public List<ProductFinalCateogoryForm> getFinalCategoryListPublic(String subCategoryName);
    public List<ProductFinalCateogoryForm> getFinalCategoryList();
}
