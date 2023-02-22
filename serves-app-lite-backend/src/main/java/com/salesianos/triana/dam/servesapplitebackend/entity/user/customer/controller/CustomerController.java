package com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.dto.CustomerDTO;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.service.CustomerService;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.view.CustomerViews;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
@Tag(name = "Customer Controller", description = "Controller of the customers operations")
public class CustomerController {

    private final CustomerService customerService;

//    //POST: ADD CUSTOMER path --> "/customer/"
//    @Operation(summary = "Add new customer")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201",
//                    description = "New customer added succesfully",
//                    content = @Content(mediaType = "application/json",
//                            array = @ArraySchema(schema = @Schema(implementation = CustomerDTO.class)),
//                            examples = {@ExampleObject(
//                                    value = """
//                                            {
//                                                 "id": "e672b8ec-831b-4d69-ae07-db4b5c4272b7",
//                                                 "username": "madafaka",
//                                                 "name": "Antonio",
//                                                 "surname": "Jiménez Infante",
//                                                 "email": "jimenes.inant22@triana.salesianos.edu",
//                                                 "subscribedAt": "19/02/2023 18:56:27"
//                                            }
//                                            """
//                            )}
//                    )
//            ),
//            @ApiResponse(responseCode = "400",
//                    description = "Incorrect input format",
//                    content = @Content
//            )
//    })
//    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Input body format",
//            content = @Content(mediaType = "application/json",
//                    schema = @Schema(implementation = CustomerDTO.class),
//                    examples = @ExampleObject(
//                            value = """
//                                    {
//                                         "username" : "madafaka",
//                                         "name" : "Antonio",
//                                         "surname" : "Jiménez Infante",
//                                         "email" : "jimenes.inant22@triana.salesianos.edu"
//                                    }
//                                    """
//                    )
//            )
//    )
//    @JsonView(CustomerViews.CustomerResponse.class)
//    @PostMapping("/")
//    @ResponseStatus(HttpStatus.CREATED)
//    public CustomerDTO registerNewCustomer(
//            @JsonView(CustomerViews.NewCustomer.class)
//            @RequestBody CustomerDTO newCustomer){
//        return customerService.addNewCustomer(newCustomer);
//    }
//
//    //GET: GET ALL CUSTOMERS path --> "/customer/" ROLE[ADMIN]
//    @Operation(summary = "Get all customers")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200",
//                    description = "Customers found",
//                    content = @Content(mediaType = "application/json",
//                            array = @ArraySchema(schema = @Schema(implementation = CustomerDTO.class)),
//                            examples = {@ExampleObject(
//                                    value = """
//                                            [
//                                                {
//                                                    "id": "2df7e225-91ec-4536-8c97-185e9aaab946",
//                                                    "username": "jethro_93",
//                                                    "name": "Jerónimo M.",
//                                                    "surname": "Pérez González",
//                                                    "email": "perez.gojer22@triana.salesianos.edu",
//                                                    "subscribedAt": "19/02/2023 20:29:55"
//                                                },
//                                                {
//                                                    "id": "b8f98b29-5188-45ab-b529-6065ee9f2c34",
//                                                    "username": "madafaka",
//                                                    "name": "Antonio",
//                                                    "surname": "Jiménez Infante",
//                                                    "email": "jimenes.inant22@triana.salesianos.edu",
//                                                    "subscribedAt": "19/02/2023 20:45:03"
//                                                }
//                                            ]
//                                            """
//                            )}
//                    )
//            ),
//            @ApiResponse(responseCode = "404",
//                    description = "Customers not found",
//                    content = @Content
//            )
//    })
//    @JsonView(CustomerViews.CustomerResponse.class)
//    @GetMapping("/")
//    @ResponseStatus(HttpStatus.OK)
//    public List<CustomerDTO> getAllCustomers(){
//        return customerService.getAllCustomers();
//    }
//
//    //GET: GET A CUSTOMER path --> "/customer/{id}" ROLE[ADMIN, COMPANY?]
//    //FINISH THIS DOC
//    @Operation(summary = "Get a customer by id")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200",
//                    description = "Customer found",
//                    content = @Content(mediaType = "application/json",
//                            array = @ArraySchema(schema = @Schema(implementation = CustomerDTO.class)),
//                            examples = {@ExampleObject(
//                                    value = """
//                                            {
//                                            }
//                                            """
//                            )}
//                    )
//            ),
//            @ApiResponse(responseCode = "404",
//                    description = "Customer not found",
//                    content = @Content
//            )
//    })
//    @JsonView(CustomerViews.FullCustomerResponse.class)
//    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public CustomerDTO getCustomerById(@PathVariable UUID id){
//        return customerService.getCustomerById(id);
//    }
//
//    //PUT: EDIT A CUSTOMER path --> "/customer/{id}" ROLE[ADMIN, CUSTOMER]
//    @Operation(summary = "Update a customer")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200",
//                    description = "Customer info updated succesfully",
//                    content = @Content(mediaType = "application/json",
//                            array = @ArraySchema(schema = @Schema(implementation = CustomerDTO.class)),
//                            examples = {@ExampleObject(
//                                    value = """
//                                            {
//                                                "id" : "45c6f247-ce6b-413f-ab84-cd8373990c59",
//                                                "cif" : "A-12345678",
//                                                "companyName" : "Malafama Clandestine Bar",
//                                                "username" : "Malfama2000"
//                                            }
//                                            """
//                            )}
//                    )
//            ),
//            @ApiResponse(responseCode = "400",
//                    description = "Incorrect input format",
//                    content = @Content
//            )
//    })
//    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Input body format",
//            content = @Content(mediaType = "application/json",
//                    schema = @Schema(implementation = CustomerDTO.class),
//                    examples = @ExampleObject(
//                            value = """
//                                    {
//                                        "name" : "Carlitos",
//                                        "surname" : "Pérez"
//                                    }
//                                    """
//                    )
//            )
//    )
//    @JsonView(CustomerViews.CustomerResponse.class)
//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public CustomerDTO updateACustomer(@PathVariable UUID id,
//                                       @JsonView(CustomerViews.CustomerUpdate.class)
//                                       @RequestBody CustomerDTO toUpdate){
//        return customerService.updateACustomer(toUpdate, id);
//    }
//
//    //DELETE: DELETE A CUSTOMER path --> "/customer/{id}" ROLE[ADMIN, CUSTOMER]
//    @Operation(summary = "Delete a customer")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "204",
//                    description = "No content",
//                    content = @Content
//            ),
//            @ApiResponse(responseCode = "404",
//                    description = "Customer not found",
//                    content = @Content
//            )
//    })
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteACustomer(@PathVariable UUID id){
//        customerService.removeACustomer(id);
//    }

}
