package com.salesianos.triana.dam.servesapplitebackend.entity.product.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianos.triana.dam.servesapplitebackend.entity.product.dto.EditProductDTO;
import com.salesianos.triana.dam.servesapplitebackend.entity.product.dto.ProductDTO;
import com.salesianos.triana.dam.servesapplitebackend.entity.product.model.Product;
import com.salesianos.triana.dam.servesapplitebackend.entity.product.service.ProductService;
import com.salesianos.triana.dam.servesapplitebackend.entity.product.view.ProductViews;
import com.salesianos.triana.dam.servesapplitebackend.validation.annotation.LongID;
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
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Tag(name = "Product Controller", description = "Controller of the products operations")
@Validated
public class ProductController {

    private final ProductService productService;

    //POST: NEW PRODUCT path --> "/product/" ROLE[ADMIN, COMPANY]
    @Operation(summary = "Add new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "New product added succesfully",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "id": 5,
                                                 "productName": "Red Bull",
                                                 "category": "Refresco",
                                                 "price": 1.75
                                            }
                                            """
                            )}
                    )
            ),
            @ApiResponse(responseCode = "400",
                    description = "Incorrect input format",
                    content = @Content
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Input body format",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductDTO.class),
                    examples = @ExampleObject(
                            value = """
                                    {
                                         "productName" : "Red Bull",
                                         "category" : "Refresco",
                                         "price" : 1.75
                                    }
                                    """
                    )
            )
    )
    @JsonView(ProductViews.ProductResponse.class)
    @PostMapping("/")
    public ResponseEntity<ProductDTO> addNewProduct(
            @JsonView(ProductViews.NewProduct.class)
            @Valid @RequestBody ProductDTO newProduct) {
        Product added = productService.addNewProduct(ProductDTO.of(newProduct));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(added.getId()).toUri();
        return ResponseEntity.created(uri).body(ProductDTO.of(added));
    }

    //GET: GET ALL PRODUCTS path --> "/product/ ROLE[ADMIN, COMPANY]
    @Operation(summary = "Get all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Products found",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                    "id": 1,
                                                    "productName": "Estrella Galicia",
                                                    "category": "Cerveza",
                                                    "price": 2.0
                                                },
                                                {
                                                    "id": 2,
                                                    "productName": "Completa",
                                                    "category": "Tostada",
                                                    "price": 2.5
                                                },
                                                {
                                                    "id": 3,
                                                    "productName": "Café con leche",
                                                    "category": "Café",
                                                    "price": 1.4
                                                }
                                            ]
                                            """
                            )}
                    )
            ),
            @ApiResponse(responseCode = "404",
                    description = "Products not found",
                    content = @Content
            )
    })
    @JsonView(ProductViews.ProductResponse.class)
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts() {
        return productService.getAllProducts().stream().map(ProductDTO::of).toList();
    }

//    //GET: GET ACTIVE PRODUCTS path --> "/product/active" ROLE[ADMIN, COMPANY]
//    @GetMapping("/active")
//    public List<ProductDTO> getAllActiveProducts() {
//        return productService.getAllActiveProducts();
//    }

    //GET: GET A PRODUCT path --> "/product/{id}" ROLE[ADMIN, COMPANY]
    @Operation(summary = "Get a product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Product found",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "id": 1,
                                                 "productName": "Estrella Galicia",
                                                 "category": "Cerveza",
                                                 "price": 2.0,
                                                 "active": true
                                            }
                                            """
                            )}
                    )
            ),
            @ApiResponse(responseCode = "404",
                    description = "Product not found",
                    content = @Content
            )
    })
    @JsonView({ProductViews.FullProductResponse.class})
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO getProductById( @PathVariable @Min(1) @LongID String id) {
        return ProductDTO.of(productService.getProductById(Long.parseLong(id)));
    }

    //PUT: EDIT A PRODUCT path --> "/product/{id}" ROLE[ADMIN, COMPANY]
    @Operation(summary = "Update a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Product info updated succesfully",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 3,
                                                "productName": "Café con leche",
                                                "category": "Café",
                                                "price": 1.4
                                            }
                                            """
                            )}
                    )
            ),
            @ApiResponse(responseCode = "400",
                    description = "Incorrect input format",
                    content = @Content
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Input body format",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EditProductDTO.class),
                    examples = @ExampleObject(
                            value = """
                                    {
                                        "category" : "Comida",
                                        "price" : 2.50
                                    }
                                    """
                    )
            )
    )
    @JsonView(ProductViews.ProductResponse.class)
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO updateAProduct(
            @PathVariable @Min(1) @LongID String id,
            @Valid @RequestBody EditProductDTO toUpdate) {
        return ProductDTO.of(productService.updateAProduct(EditProductDTO.of(toUpdate), Long.parseLong(id)));
    }


    //DELETE: RETIRE A PRODUCT --> path "/product/{id}" ROLE[ADMIN]
    @Operation(summary = "Retire a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Product retired",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "id": 1,
                                                 "productName": "Estrella Galicia",
                                                 "category": "Cerveza",
                                                 "price": 2.0,
                                                 "active": true
                                            }
                                            """
                            )}
                    )
            ),
            @ApiResponse(responseCode = "404",
                    description = "Product not found",
                    content = @Content
            )
    })
    @JsonView(ProductViews.FullProductResponse.class)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void retireAProduct(@PathVariable @Min(1) @LongID String id) {
        productService.retireAProduct(Long.parseLong(id));
    }
}
