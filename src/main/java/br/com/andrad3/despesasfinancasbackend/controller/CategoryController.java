package br.com.andrad3.despesasfinancasbackend.controller;

import br.com.andrad3.despesasfinancasbackend.domain.Category;
import br.com.andrad3.despesasfinancasbackend.dtos.CategoryDTO;
import br.com.andrad3.despesasfinancasbackend.services.CategoryService;
import br.com.andrad3.despesasfinancasbackend.utils.ReutilizarCodigo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/api/category")
@Tag(name = "Category", description = "endpoints for category")
@CrossOrigin(origins = {"https://coincontrol-387d4.web.app","http://coincontrol-387d4.web.app"})
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    @Operation(summary = "Traz todas as categorias pelo id", method = "GET")
    @GetMapping(value = "/{id}")
    public ResponseEntity<List<Category>> getCategoryForId(@PathVariable("id")Long id,@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){
        String token = ReutilizarCodigo.extractTokenFromAuthorizationHeader(authorizationHeader);
        List<Category> categoryList = this.categoryService.getAllCategoryForId(id,token);
        return ResponseEntity.ok().body(categoryList);
    }
    @Operation(summary = "Create Category", method = "POST")
    @PostMapping(value = "/createCategory")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDTO categoryDTO){
        Category category = this.categoryService.criarCategory(categoryDTO);
        return ResponseEntity.ok().body(category);
    }
    @Operation(summary = "Deletar Category", method = "DELETE")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id){
        this.categoryService.deleteCategoryForId(id);
        return ResponseEntity.ok().build();
    }
    @Operation(summary = "AlterarCategory", method = "PUT")
    @PutMapping(value = "/alterarCategory")
    public ResponseEntity<Category>  alterarCategory(@RequestBody CategoryDTO categoryDTO){
        this.categoryService.alterarCategory(categoryDTO);
        return ResponseEntity.ok().build();
    }




}
