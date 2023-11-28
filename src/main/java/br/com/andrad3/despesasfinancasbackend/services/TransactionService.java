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

import java.math.BigDecimal;
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
                if(objDTO.getRecorencia()){
                    if(objDTO.getParcelas() >= 2){
                        BigDecimal valorParcela = objDTO.getValor().divide(BigDecimal.valueOf(objDTO.getParcelas()));
                        //fix 2
                        Transaction transaction = new Transaction(null,valorParcela,category.getId(),
                                objDTO.getDescricao()+" 1/"+objDTO.getParcelas(), objDTO.getDataTransacao(),objDTO.getTipoTransacao(),
                                objDTO.getAccount(),objDTO.getIdAccount(),objDTO.getRecorencia(),objDTO.getParcelas());
                        Transaction transactionPai = this.transactionRepository.save(transaction);

                        for(int i= 2; i <= objDTO.getParcelas();i++){
                            Transaction transactionfilhas = new Transaction(null,valorParcela,category.getId(),
                                    objDTO.getDescricao()+" "+i+"/"+transactionPai.getParcelas(), objDTO.getDataTransacao().plusMonths(i-1),objDTO.getTipoTransacao(),
                                    objDTO.getAccount(),objDTO.getIdAccount(),objDTO.getRecorencia(),transactionPai.getId());
                            this.transactionRepository.save(transactionfilhas);
                        }

                        return  transactionPai;

                    }

                }
                Transaction transaction = new Transaction(null,objDTO.getValor(),category.getId(),
                        objDTO.getDescricao(), objDTO.getDataTransacao(),objDTO.getTipoTransacao(),
                        objDTO.getAccount(),objDTO.getIdAccount());
                Transaction transactionSalvada = this.transactionRepository.save(transaction);
                return transactionSalvada;
            }catch (Exception e){
                throw new InvalidEnumException("Erro ao tentar criar e salvar a transacao");
            }
        }
        return null;
    }
    public void removeTransaction(Long id){
        Optional<Transaction> transaction = this.transactionRepository.findById(id);
        transaction.ifPresent(value -> this.transactionRepository.delete(value));
    }

    public void alterarTransacao(TransactionDTO transactionDTO) {
        Category category;
        if (transactionDTO.getIdCategory() != null) {
            category = this.categoryRepository.findById(transactionDTO.getIdCategory()).get();
        }else{
            if(TypeTransaction.RECEITA == transactionDTO.getTipoTransacao()){
                category = this.categoryRepository.findByTypeEnumForIdAndName(
                        transactionDTO.getIdUser(),
                        transactionDTO.getTipoTransacao(),
                        "Outras receitas"
                );
            }else{
                category = this.categoryRepository.findByTypeEnumForIdAndName(
                        transactionDTO.getIdUser(),
                        transactionDTO.getTipoTransacao(),
                        "Outros"
                );
            }
        }
        transactionDTO.setIdCategory(category.getId());
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


