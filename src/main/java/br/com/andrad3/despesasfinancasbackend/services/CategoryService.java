package br.com.andrad3.despesasfinancasbackend.services;

import br.com.andrad3.despesasfinancasbackend.Exceptions.InvalidEnumException;
import br.com.andrad3.despesasfinancasbackend.domain.Category;
import br.com.andrad3.despesasfinancasbackend.domain.User;
import br.com.andrad3.despesasfinancasbackend.dtos.CategoryDTO;
import br.com.andrad3.despesasfinancasbackend.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserService userService;
    public List<Category> getAllCategoryForId(Long id,String token){
        if(this.userService.userIsPermissonForId(token,id)){
            Optional<List<Category>> categoryList = this.categoryRepository.findByAllforCategoriesForId(id);
            if(categoryList.isEmpty()){
                throw new InvalidEnumException("Nao foi encontrada categoria para esse id");
            }
            return categoryList.get();
        }
        return null;
    }

    public void deleteCategoryForId(Long id){
        Optional<Category> category = this.categoryRepository.findById(id);
        if(category.isEmpty()){
            throw new InvalidEnumException("Nao foi encontrado a categoria pelo id passado");
        }
        category.ifPresent(value -> this.categoryRepository.delete(value));
    }

    public void alterarCategory(CategoryDTO categoryDTO){
        Optional<Category> category = this.categoryRepository.findById(categoryDTO.getId());
        if(category.isEmpty()){
            throw new InvalidEnumException("Nao foi encontrado a categoria pelo id passado");
        }
        Category categoryRecuperada = category.get();
        categoryRecuperada.setNome(categoryDTO.getNome());
        categoryRecuperada.setTipo(categoryDTO.getTipo());
        categoryRecuperada.setIdCon(categoryDTO.getIdCon());

        this.categoryRepository.save(categoryRecuperada);

    }

    public Category criarCategory(CategoryDTO categoryDTO){
        categoryDTO.setId(null);
        User user = this.userService.findById(categoryDTO.getUserId());
        Category category = new Category(categoryDTO,user);
        return this.categoryRepository.save(category);
    }

}
