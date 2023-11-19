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
            categories.add(new Category(null,"Alimentação",2, TypeTransaction.DESPESA,user));
            categories.add(new Category(null,"Assinaturas e serviços",2, TypeTransaction.DESPESA,user));
            categories.add(new Category(null,"Bares e restaurantes",2, TypeTransaction.DESPESA,user));
            categories.add(new Category(null,"Casa",2, TypeTransaction.DESPESA,user));
            categories.add(new Category(null,"Investimentos",2, TypeTransaction.DESPESA,user));
            categories.add(new Category(null,"Saude",2, TypeTransaction.DESPESA,user));
            categories.add(new Category(null,"Mercado",2, TypeTransaction.DESPESA,user));
            categories.add(new Category(null,"Lazer e hobbies",2, TypeTransaction.DESPESA,user));
            categories.add(new Category(null,"Outros",2, TypeTransaction.DESPESA,user));
            //DESPESAS
            categories.add(new Category(null,"Empréstimos",2, TypeTransaction.RECEITA,user));
            categories.add(new Category(null,"Investimentos",2, TypeTransaction.RECEITA,user));
            categories.add(new Category(null,"Outras receitas",2, TypeTransaction.RECEITA,user));
            categories.add(new Category(null,"Salário",2, TypeTransaction.RECEITA,user));
        });
       categoryRepository.saveAll(categories);
    }

    public void setDefaultAccount(Long id){
        Account account;
        Optional<User> userRecuperado = userRepository.findById(id);
        if(userRecuperado.isPresent()){
            account = new Account(null,"Conta Principal",BigDecimal.ZERO,userRecuperado.get(),null);
            accountRepository.save(account);
        }
    }
}
