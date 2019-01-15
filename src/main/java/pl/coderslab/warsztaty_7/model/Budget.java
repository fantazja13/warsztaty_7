package pl.coderslab.warsztaty_7.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "budgets")
public class Budget extends Auditable{

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL)
    private List<BankAccount> bankAccounts;

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL)
    private List<User> users;

    //TODO: transfery

    //TODO: targety


    public Budget() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public void addBankAccount(BankAccount bankAccount){
        bankAccounts.add(bankAccount);
        bankAccount.setBudget(this);
    }

    public void deleteBankAccount(BankAccount bankAccount){
        bankAccounts.remove(bankAccount);
        bankAccount.setBudget(null); //ToDO: zmienić usuwanie na zmianę flagi
    }
}