package br.com.andrad3.despesasfinancasbackend;

import br.com.andrad3.despesasfinancasbackend.domain.Account;
import br.com.andrad3.despesasfinancasbackend.domain.Category;
import br.com.andrad3.despesasfinancasbackend.domain.User;
import br.com.andrad3.despesasfinancasbackend.domain.enums.TypeTransaction;
import br.com.andrad3.despesasfinancasbackend.repositories.AccountRepository;
import br.com.andrad3.despesasfinancasbackend.repositories.CategoryRepository;
import br.com.andrad3.despesasfinancasbackend.repositories.UserRepository;
import br.com.andrad3.despesasfinancasbackend.services.AccountService;
import br.com.andrad3.despesasfinancasbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Startup implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private AccountRepository accountRepository;

	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		List<Category> categoryList = new ArrayList<>();
		categoryList.add(new Category(null,"ALIMENTACAO",2, TypeTransaction.RECEITA,null));
		categoryList.add(new Category(null,"MEDICAMENTO",3, TypeTransaction.RECEITA,null));
		categoryList.add(new Category(null,"COMPUTADOR",1, TypeTransaction.DESPESA,null));

		categoryList.add(new Category(null,"ALIMENTACAO",2, TypeTransaction.DESPESA,null));
		categoryList.add(new Category(null,"MEDICAMENTO",3, TypeTransaction.RECEITA,null));
		categoryList.add(new Category(null,"COMPUTADOR",1, TypeTransaction.DESPESA,null));

		categoryList.add(new Category(null,"ALIMENTACAO",2, TypeTransaction.DESPESA,null));
		categoryList.add(new Category(null,"MEDICAMENTO",3, TypeTransaction.RECEITA,null));
		categoryList.add(new Category(null,"COMPUTADOR",1, TypeTransaction.DESPESA,null));

		categoryList.add(new Category(null,"PC GAMER",2, TypeTransaction.DESPESA,null));
		categoryList.add(new Category(null,"FORUM Z",3, TypeTransaction.RECEITA,null));
		categoryList.add(new Category(null,"SAMP",1, TypeTransaction.DESPESA,null));

		List<Account> accountList = new ArrayList<>();
		accountList.add(new Account(null,"CONTA CAIXA",BigDecimal.ZERO,null,null));
		accountList.add(new Account(null,"CONTA CAIXA",BigDecimal.ZERO,null,null));
		accountList.add(new Account(null,"CONTA CAIXA",BigDecimal.ZERO,null,null));

		User usuario1 = new User(null,"Matheus Andrade","123123123","mahhard@hotmail.com",null,null);
		User usuario2 = new User(null,"Matheus Andrade","123123123","mahhard1@hotmail.com",null,null);
		User usuario3 = new User(null,"Matheus Andrade","123123123","mahhardaa@hotmail.com",null,null);

		User savedUser = userRepository.save(usuario1);
		User savedUser2 = userRepository.save(usuario2);
		User savedUser3 = userRepository.save(usuario3);

		int i = 0;
		for(Category category : categoryList){
			category.setUser(usuario1);
			i++;
			if(i >3){
				category.setUser(usuario2);
			}
			if(i > 6){
				category.setUser(usuario3);
			}
		}
		int d = 0;
		for(Account account : accountList){
			account.setUser(savedUser);
			d++;
			if(d >1){
				account.setUser(savedUser2);
			}
			if(d >2){
				account.setUser(savedUser3);
			}
		}
		accountRepository.saveAll(accountList);
		categoryRepository.saveAll(categoryList);
	}
}
