package th.ac.ku.atm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.atm.model.BankAccount;
import th.ac.ku.atm.service.BankAccountService;

@Controller
@RequestMapping("/bankaccount")
public class BankAccountController {
    private BankAccountService bankAccountService;


    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public String getBankAccountPage(Model model){
        model.addAttribute("allBankAccounts",bankAccountService.getBankAccounts());
        return "bankaccount";
    }

    @PostMapping
    public String openAccount(@ModelAttribute BankAccount bankAccount, Model model) {
        bankAccountService.openAccount(bankAccount);
        model.addAttribute("allBankAccounts",bankAccountService.getBankAccounts());
        return "redirect:bankaccount";
    }

//    @GetMapping("/edit/{id}")
//    public String getEditBankAccountPage(@PathVariable int id,Model model){
//        BankAccount bankAccount=bankAccountService.getBankAccount(id);
//        model.addAttribute("bankAccount",bankAccount);
//        return "bankaccount-edit";
//    }

//    @PostMapping("/edit/{id}")
//    public String editAccount(@PathVariable int id,
//                              @ModelAttribute BankAccount bankAccount,
//                              Model model){
//        bankAccountService.editBankAccount(bankAccount);
//        model.addAttribute("allBankAccounts",bankAccountService.getBankAccounts());
//        return "redirect:/bankaccount";
//    }

    @GetMapping("/delete/{id}")
    public String deleteBankAccount(@PathVariable int id,Model model){
        bankAccountService.deleteBankAccount(id);
        model.addAttribute("allBankAccounts",bankAccountService.getBankAccounts());
        return "redirect:/bankaccount";
    }

    @GetMapping("/deposit/{id}")
    public String getDepositBalancePage(@PathVariable int id
                                 ,Model model){
        BankAccount bankAccount=bankAccountService.getBankAccount(id);
        model.addAttribute("bankAccount",bankAccount);
        return "bankaccount-deposit";
    }

    @PostMapping("/deposit/{id}")
    public String depositBalance(@PathVariable int id,
                                 Model model,@RequestParam double amount){
        bankAccountService.depositBalance(id,amount);
        model.addAttribute("allBankAccounts",bankAccountService.getBankAccounts());
        return "redirect:/bankaccount";
    }

    @GetMapping("/withdraw/{id}")
    public String getWithdrawBalancePage(@PathVariable int id,
                                         Model model){
        BankAccount bankAccount=bankAccountService.getBankAccount(id);
        model.addAttribute("bankAccount",bankAccount);
        return "bankaccount-withdraw";
    }

    @PostMapping("/withdraw/{id}")
    public String withdrawBalance(@PathVariable int id,
                                 Model model,@RequestParam double amount){
        bankAccountService.withdrawBalance(id,amount);
        model.addAttribute("allBankAccounts",bankAccountService.getBankAccounts());
        return "redirect:/bankaccount";
    }

}
