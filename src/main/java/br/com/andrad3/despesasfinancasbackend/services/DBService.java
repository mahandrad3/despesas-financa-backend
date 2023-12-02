package br.com.andrad3.despesasfinancasbackend.services;

import br.com.andrad3.despesasfinancasbackend.domain.Account;
import br.com.andrad3.despesasfinancasbackend.domain.Category;
import br.com.andrad3.despesasfinancasbackend.domain.User;
import br.com.andrad3.despesasfinancasbackend.domain.enums.TypeTransaction;
import br.com.andrad3.despesasfinancasbackend.repositories.AccountRepository;
import br.com.andrad3.despesasfinancasbackend.repositories.CategoryRepository;
import br.com.andrad3.despesasfinancasbackend.repositories.UserRepository;
import org.hibernate.annotations.AttributeAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DBService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    public void setDefaultCategories(Long id){
        List<Category> categories = new ArrayList<>();
        Optional<User> userRecuperado = userRepository.findById(id);
        userRecuperado.ifPresent(user ->{
            //DESPESAS
            categories.add(new Category(null,"Alimentação",8, TypeTransaction.DESPESA,user));
            categories.add(new Category(null,"Assinaturas e serviços",5, TypeTransaction.DESPESA,user));
            categories.add(new Category(null,"Bares e restaurantes",2, TypeTransaction.DESPESA,user));
            categories.add(new Category(null,"Casa",6, TypeTransaction.DESPESA,user));
            categories.add(new Category(null,"Saude",7, TypeTransaction.DESPESA,user));
            categories.add(new Category(null,"Mercado",15, TypeTransaction.DESPESA,user));
            categories.add(new Category(null,"Lazer e hobbies",9, TypeTransaction.DESPESA,user));
            categories.add(new Category(null,"Outros",19, TypeTransaction.DESPESA,user));
            categories.add(new Category(null,"Pets",4, TypeTransaction.DESPESA,user));
            categories.add(new Category(null,"Roupas",11, TypeTransaction.DESPESA,user));
            categories.add(new Category(null,"Transporte",13, TypeTransaction.DESPESA,user));

            //DESPESAS
            categories.add(new Category(null,"Empréstimos",18, TypeTransaction.RECEITA,user));
            categories.add(new Category(null,"Investimentos",20, TypeTransaction.RECEITA,user));
            categories.add(new Category(null,"Outras receitas",19, TypeTransaction.RECEITA,user));
            categories.add(new Category(null,"Salário",21, TypeTransaction.RECEITA,user));
        });
       categoryRepository.saveAll(categories);
    }

    public void setDefaultAccount(Long id){
        Account account;
        Optional<User> userRecuperado = userRepository.findById(id);
        if(userRecuperado.isPresent()){
            account = new Account(null,"Conta Principal",userRecuperado.get(),null);
            accountRepository.save(account);
        }
    }
}
