package com.salesianos.triana.dam.servesapplitebackend;

import com.salesianos.triana.dam.servesapplitebackend.entity.line.model.Line;
import com.salesianos.triana.dam.servesapplitebackend.entity.line.model.pk.LinePK;
import com.salesianos.triana.dam.servesapplitebackend.entity.order.model.Order;
import com.salesianos.triana.dam.servesapplitebackend.entity.order.repository.OrderRepository;
import com.salesianos.triana.dam.servesapplitebackend.entity.product.model.Product;
import com.salesianos.triana.dam.servesapplitebackend.entity.product.repository.ProductRepository;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.model.Company;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.repository.CompanyRepository;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.model.Customer;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TestModel {

    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @PostConstruct
    public void run(){

        //PRODUCTS

        Product product1 = Product.builder()
                .productName("Estrella Galicia")
                .category("Cerveza")
                .price(2.00)
                .build();

        Product product2 = Product.builder()
                .productName("Completa")
                .category("Tostada")
                .price(2.50)
                .build();

        Product product3 = Product.builder()
                .productName("Café con leche")
                .category("Café")
                .price(1.40)
                .build();

        productRepository.saveAll(List.of(product1, product2, product3));

        //COMPANIES

        Company company1 = Company.builder()
                .username("chileno69")
                .cif("A-12345678")
                .companyName("Café D&N")
                .menu(List.of(product1, product2, product3))
                .build();

        companyRepository.saveAll(List.of(company1));

        //CUSTOMERS

        Customer customer1 = Customer.builder()
                .username("jethro_93")
                .name("Jerónimo M.")
                .surname("Pérez González")
                .email("perez.gojer22@triana.salesianos.edu")
                .build();

        customerRepository.saveAll(List.of(customer1));

        //ORDERS - SALES LINES

        //1º ORDER

        Order order1 = Order.builder().build();

        orderRepository.saveAll(List.of(order1));

        order1.addToCustomer(customer1);
        order1.addToCompany(company1);

        orderRepository.saveAll(List.of(order1));

        Line line1 = Line.builder()
                .id(
                        LinePK.builder()
                                .order_id(order1.getId())
                                .product_id(product1.getId())
                                .build()
                )
                .order(order1)
                .product(product1)
                .quantity(5)
                .subTotal(product1.getPrice()*5)
                .build();

        line1.addToOrder(order1);

        orderRepository.saveAll(List.of(order1));





















    }
}
