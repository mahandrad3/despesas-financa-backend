package br.com.andrad3.despesasfinancasbackend.services;

import br.com.andrad3.despesasfinancasbackend.Exceptions.InvalidEnumException;
import br.com.andrad3.despesasfinancasbackend.domain.Account;
import br.com.andrad3.despesasfinancasbackend.domain.Category;
import br.com.andrad3.despesasfinancasbackend.domain.Transaction;
import br.com.andrad3.despesasfinancasbackend.domain.User;
import br.com.andrad3.despesasfinancasbackend.domain.enums.TypeTransaction;
import br.com.andrad3.despesasfinancasbackend.dtos.TransactionDTO;
import br.com.andrad3.despesasfinancasbackend.repositories.CategoryRepository;
import br.com.andrad3.despesasfinancasbackend.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserService userService;

    public Transaction addTransaction(TransactionDTO objDTO, String token) {
        User user = this.userService.getUserForToken(token).get();
        if (this.userService.userIsPermissonForId(token, objDTO.getIdUser())) {
            Category category;
            if (objDTO.getIdCategory() != null) {
                category = this.categoryRepository.findById(objDTO.getIdCategory()).get();
            }else{
                if(TypeTransaction.RECEITA == objDTO.getTipoTransacao()){
                    category = this.categoryRepository.findByTypeEnumForIdAndName(
                            user.getId(),
                            objDTO.getTipoTransacao(),
                            "Outras receitas"
                    );
                }else{
                    category = this.categoryRepository.findByTypeEnumForIdAndName(
                            user.getId(),
                            objDTO.getTipoTransacao(),
                            "Outros"
                    );
                }
            }
            try{
                Transaction transaction = new Transaction(null,objDTO.getValor(),category.getId(),
                        objDTO.getDescricao(), objDTO.getDataTransacao(),objDTO.getTipoTransacao(),
                        objDTO.getAccount());
                Transaction transactionSalvada = this.transactionRepository.save(transaction);
                return transactionSalvada;
            }catch (Exception e){
                throw new InvalidEnumException("Erro ao tentar criar e salvar a transacao");
            }
        }
        return null;
    }
    public void removeTransaction(TransactionDTO  transactionDTO){
        Optional<Transaction> transaction = this.transactionRepository.findById(transactionDTO.getIdTransaction());
        transaction.ifPresent(value -> this.transactionRepository.delete(value));
    }

    public void alterarTransacao(TransactionDTO transactionDTO) {
        this.transactionRepository.findById(transactionDTO.getIdTransaction())
                .map(transactionRecuperada -> {
                    Transaction novaTransaction = new Transaction(transactionDTO);
                    this.transactionRepository.save(novaTransaction);
                    return novaTransaction;
                })
                .orElseThrow(() -> new InvalidEnumException("Transação não encontrada com o ID: " + transactionDTO.getIdTransaction()));
    }


    public List<Transaction> getAllTransactionForId(Long id,String token) {
        User user = this.userService.getUserForToken(token).get();
        if(this.userService.userIsPermissonForId(token, id)){
            List<Transaction> transactions = new ArrayList<>();
            List<Account> accounts = user.getContas();
            for(Account account : accounts){
                if(account.getTransactions() !=null){
                    transactions.addAll(account.getTransactions());
                }
            }
            if(transactions.isEmpty()){
                throw new InvalidEnumException("Nenhuma transacao encontrada nesse usuario");
            }
            return  transactions;
        }
        return null;
    }


}


