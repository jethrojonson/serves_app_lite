package com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.service;

import com.salesianos.triana.dam.servesapplitebackend.entity.line.model.Line;
import com.salesianos.triana.dam.servesapplitebackend.entity.order.model.Order;
import com.salesianos.triana.dam.servesapplitebackend.entity.order.repository.OrderRepository;
import com.salesianos.triana.dam.servesapplitebackend.entity.order.service.OrderService;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.base.model.User;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.exception.CompanyExceptions;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.model.Company;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.service.CompanyService;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.exception.CustomerExceptions;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.model.Customer;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;


    public Customer addCustomer(Customer customer){
        return customerRepository.save(customer);
    }

//    public Order createNewOrder(Order newOrder, User loggedUser, String companyName){
//        Customer orderMaker = customerRepository.findById(loggedUser.getId())
//                .orElseThrow(CustomerExceptions.CustomerNotFoundException::new);
//        Company orderReceiver = companyService.findByCompanyName(companyName)
//                .orElseThrow(CompanyExceptions.CompanyNotFoundException::new);
//        newOrder.addToCustomer(orderMaker);
//        newOrder.addToCompany(orderReceiver);
//        Order added = orderService.addOrder(newOrder);
//        newOrder.getSalesLines().forEach(l-> l.setSubTotal(l.getQuantity()*l.getProduct().getPrice()));
//        newOrder.setTotal(newOrder.getSalesLines().stream().mapToDouble(Line::getSubTotal).sum());
//        return orderService.addOrder(newOrder);
//    }


//
//    public CustomerDTO addNewCustomer(CustomerDTO newCustomer){
//        return CustomerDTO.of(customerRepository.save(CustomerDTO.of(newCustomer)));
//    }
//
//    public List<CustomerDTO> getAllCustomers(){
//        return customerRepository.findAll().stream().map(CustomerDTO::of).toList();
//    }
//
//    public CustomerDTO getCustomerById(UUID id){
//        Customer result = customerRepository.findById(id).get();
//        return CustomerDTO.of(result);
//    }
//
//    @Transactional
//    public CustomerDTO updateACustomer(CustomerDTO toUpdate, UUID id){
//        Customer result = customerRepository.findById(id).get();
//        result.setName(toUpdate.getName());
//        result.setSurname(toUpdate.getSurname());
//        return CustomerDTO.of(customerRepository.save(result));
//    }
//
//    public void removeACustomer(UUID id){
//        customerRepository.delete(customerRepository.findById(id).get());
//    }
}
