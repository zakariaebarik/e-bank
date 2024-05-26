package ma.enset.ebankbackend;

import ma.enset.ebankbackend.dtos.BankAccountDTO;
import ma.enset.ebankbackend.dtos.CurrentBankAccountDTO;
import ma.enset.ebankbackend.dtos.CustomerDTO;
import ma.enset.ebankbackend.dtos.SavingsBankAccountDTO;
import ma.enset.ebankbackend.entities.*;
import ma.enset.ebankbackend.enums.AccountStatus;
import ma.enset.ebankbackend.enums.OperationType;
import ma.enset.ebankbackend.exceptions.BalanceNotSufficientException;
import ma.enset.ebankbackend.exceptions.BankAccountNotFoundException;
import ma.enset.ebankbackend.exceptions.CustomerNotFoundException;
import ma.enset.ebankbackend.repositories.BankAccountRepository;
import ma.enset.ebankbackend.repositories.CustomerRepository;
import ma.enset.ebankbackend.repositories.OperationRepository;
import ma.enset.ebankbackend.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankBackendApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService){
        return args -> {
            Stream.of("Hassan","Imane","Mohammed").forEach(name->{
                CustomerDTO customer=new CustomerDTO();
                customer.setName(name);
                customer.setEmail(name+"@gmail.com");
                bankAccountService.saveCostumer(customer);
            });

            bankAccountService.listCostumers().forEach(customer -> {
                try {
                    bankAccountService.saveCurrentBankAccount(Math.random()*90000,customer.getId(),9000);
                    bankAccountService.saveSavingsBankAccount(Math.random()*90000,customer.getId(),1.2);


                } catch (CustomerNotFoundException  e) {
                    e.printStackTrace();
                }
            });
            List<BankAccountDTO> bankAccounts = bankAccountService.bankAccountList();
            for(BankAccountDTO bankAccount:bankAccounts){
                for(int i=0; i<10; i++){
                    String accountId;
                    if(bankAccount instanceof SavingsBankAccountDTO){
                        accountId = ((SavingsBankAccountDTO) bankAccount).getId();
                    } else {
                        accountId = ((CurrentBankAccountDTO) bankAccount).getId();
                    }
                    bankAccountService.credit(accountId,10000+Math.random()*12000,"Credit");
                    bankAccountService.debit(accountId,1000+Math.random()*9000,"Debit");
                }
            }
        };
    }
    //@Bean
    CommandLineRunner start(CustomerRepository customerRepository,
                            BankAccountRepository bankAccountRepository,
                            OperationRepository operationRepository){
        return args -> {
            /*Stream.of("Hassan", "Yassine", "Aicha").forEach(name->{
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name+"gmail.com");
                customerRepository.save(customer);
            });

            customerRepository.findAll().forEach(customer -> {
                // Current account
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random()*10000);
                currentAccount.setCreatedAt(new Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(customer);
                currentAccount.setOverDraft(9000);
                bankAccountRepository.save(currentAccount);
                // Savings account
                SavingsAccount savingsAccount = new SavingsAccount();
                savingsAccount.setId(UUID.randomUUID().toString());
                savingsAccount.setBalance(Math.random()*10000);
                savingsAccount.setCreatedAt(new Date());
                savingsAccount.setStatus(AccountStatus.CREATED);
                savingsAccount.setCustomer(customer);
                savingsAccount.setInterestRate(1.2);
                bankAccountRepository.save(savingsAccount);
            });

            bankAccountRepository.findAll().forEach(account->{
                for(int i = 0; i<10; i++){
                    Operation operation = new Operation();
                    operation.setDate(new Date());
                    operation.setAmount(Math.random()*10000);
                    operation.setBankAccount(account);
                    operation.setType(Math.random()>0.5? OperationType.DEBIT : OperationType.CREDIT);
                    operationRepository.save(operation);
                }
            });*/
        };
    }

}
