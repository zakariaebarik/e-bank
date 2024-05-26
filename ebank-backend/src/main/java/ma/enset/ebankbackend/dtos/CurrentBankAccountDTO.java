package ma.enset.ebankbackend.dtos;


import lombok.Data;
import ma.enset.ebankbackend.enums.AccountStatus;
import java.util.Date;


@Data
public class CurrentBankAccountDTO extends BankAccountDTO{
    private String id;
    private Date createdAt;
    private double balance;
    private AccountStatus status;
    private String currency;
    private double overDrat;
    private CustomerDTO customerDTO;

}
