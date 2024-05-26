package ma.enset.ebankbackend.mappers;

import ma.enset.ebankbackend.dtos.CurrentBankAccountDTO;
import ma.enset.ebankbackend.dtos.CustomerDTO;
import ma.enset.ebankbackend.dtos.OperationDTO;
import ma.enset.ebankbackend.dtos.SavingsBankAccountDTO;
import ma.enset.ebankbackend.entities.CurrentAccount;
import ma.enset.ebankbackend.entities.Customer;
import ma.enset.ebankbackend.entities.Operation;
import ma.enset.ebankbackend.entities.SavingsAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapperImpl {
    public CustomerDTO fromCustomer(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer,customerDTO);
        return customerDTO;
    }

    public Customer fromCustomerDTO(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;

    }

    public SavingsBankAccountDTO fromSavingsBankAccount(SavingsAccount savingsAccount){
        SavingsBankAccountDTO savingsBankAccountDTO = new SavingsBankAccountDTO();
        BeanUtils.copyProperties(savingsAccount, savingsBankAccountDTO);
        savingsBankAccountDTO.setCustomerDTO(fromCustomer(savingsAccount.getCustomer()));
        savingsBankAccountDTO.setType(savingsAccount.getClass().getSimpleName());
        return savingsBankAccountDTO;
    }

    public SavingsAccount fromSavingsBankAccountDTO(SavingsBankAccountDTO savingsBankAccountDTO){
        SavingsAccount savingsAccount = new SavingsAccount();
        BeanUtils.copyProperties(savingsBankAccountDTO, savingsAccount);
        savingsAccount.setCustomer(fromCustomerDTO(savingsBankAccountDTO.getCustomerDTO()));
        return savingsAccount;
    }

    public CurrentBankAccountDTO fromCurrentBankAccount(CurrentAccount currentAccount){
        CurrentBankAccountDTO currentBankAccountDTO=new CurrentBankAccountDTO();
        BeanUtils.copyProperties(currentAccount,currentBankAccountDTO);
        currentBankAccountDTO.setCustomerDTO(fromCustomer(currentAccount.getCustomer()));
        currentBankAccountDTO.setType(currentAccount.getClass().getSimpleName());
        return currentBankAccountDTO;
    }
    public CurrentAccount fromCurrentBankAccountDTO(CurrentBankAccountDTO currentBankAccountDTO){
        CurrentAccount currentAccount=new CurrentAccount();
        BeanUtils.copyProperties(currentBankAccountDTO,currentAccount);
        currentAccount.setCustomer(fromCustomerDTO(currentBankAccountDTO.getCustomerDTO()));
        return currentAccount;
    }

    public OperationDTO fromAccountOperation(Operation accountOperation){
        OperationDTO accountOperationDTO=new OperationDTO();
        BeanUtils.copyProperties(accountOperation,accountOperationDTO);
        return accountOperationDTO;
    }



}
