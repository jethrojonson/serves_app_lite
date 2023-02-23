package com.salesianos.triana.dam.servesapplitebackend.entity.order.service;

import com.salesianos.triana.dam.servesapplitebackend.entity.line.model.Line;
import com.salesianos.triana.dam.servesapplitebackend.entity.line.model.pk.LinePK;
import com.salesianos.triana.dam.servesapplitebackend.entity.order.dto.OrderDTO;
import com.salesianos.triana.dam.servesapplitebackend.entity.order.model.Order;
import com.salesianos.triana.dam.servesapplitebackend.entity.order.repository.OrderRepository;
import com.salesianos.triana.dam.servesapplitebackend.entity.product.exception.ProductExceptions;
import com.salesianos.triana.dam.servesapplitebackend.entity.product.service.ProductService;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.base.model.User;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.exception.CompanyExceptions;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.model.Company;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.exception.CustomerExceptions;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order createNewOrder(User user, String companyName, List<Line> salesLines){

        companyName = companyName.replace(" ", "_");

        Order created = orderRepository.save(Order.builder().build());

        created.addToCustomer(orderRepository.findCustomerById(user.getId())
                .orElseThrow(CustomerExceptions.CustomerNotFoundException::new));

        created.addToCompany(orderRepository.findCompanyByCompanyName(companyName)
                .orElseThrow(CompanyExceptions.CompanyNotFoundException::new));


        salesLines.forEach(line -> {
            line.getId().setOrder_id(created.getId());
            line.setProduct(orderRepository.findProductById(line.getId().getProduct_id())
                    .orElseThrow(ProductExceptions.ProductNotFoundException::new));
            line.addToOrder(created);
            line.setSubTotal(line.getProduct().getPrice()*line.getQuantity());
        });

        created.setTotal(created.getSalesLines().stream().mapToDouble(Line::getSubTotal).sum());

        return orderRepository.save(created);
    }

    public Order getOrder(Long id){
        return orderRepository.findById(id).get();
    }

}
