package th.ac.ku.atm.controller;

import org.springframework.stereotype.Service;
import th.ac.ku.atm.data.CustomerRepository;
import th.ac.ku.atm.model.Customer;
import org.mindrot.jbcrypt.BCrypt;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public void createCustomer(Customer customer){
        String hashPin = hash(customer.getPin());
        customer.setPin(hashPin);
       repository.save(customer);
    }

    public List<Customer> getCustomers(){

        return repository.findAll();
    }

    public Customer findCustomer(int id){
        return repository.findById(id);
    }

    public Customer checkPin(Customer inputCustomer){
        Customer storedCustomer=findCustomer(inputCustomer.getId());
        if(storedCustomer != null){
            String hashPin=storedCustomer.getPin();
            if(BCrypt.checkpw(inputCustomer.getPin(),hashPin))return storedCustomer;
        }
        return null;
    }

    private String hash(String pin){
        String salt=BCrypt.gensalt(12);
        return BCrypt.hashpw(pin,salt);
    }
}
