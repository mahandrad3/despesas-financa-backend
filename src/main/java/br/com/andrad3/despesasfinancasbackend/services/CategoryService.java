package br.com.andrad3.despesasfinancasbackend.services;

import br.com.andrad3.despesasfinancasbackend.domain.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(Category category);
    Category updateCaterogy(Long id);
    Category getCategoryById(Long id);
    List<Category> getAllCategory();
    void deleteCategory(Long id);
}
