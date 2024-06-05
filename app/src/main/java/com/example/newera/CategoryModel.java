package com.example.newera;

public class CategoryModel {
    private String CategoryIconLink;
    private String categoryName;

    public CategoryModel(String categoryIconLink, String categoryName){
        CategoryIconLink = categoryIconLink;
        this.categoryName = categoryName;
    }
    public String getCategoryIconLink() {
        return CategoryIconLink;
    }

    public void setCategoryIconLink(String categoryItemLink) {
        this.CategoryIconLink = categoryItemLink;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
