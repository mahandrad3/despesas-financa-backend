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
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title ="Despesas e financas",version = "1",description = "API desenvolvida para TCC"))
public class Startup {
	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);

	}
}
