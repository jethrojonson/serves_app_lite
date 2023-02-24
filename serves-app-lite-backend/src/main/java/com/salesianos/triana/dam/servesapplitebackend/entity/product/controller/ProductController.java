package com.salesianos.triana.dam.servesapplitebackend.entity.product.controller;

import com.salesianos.triana.dam.servesapplitebackend.entity.product.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Tag(name = "Product Controller", description = "Controller of the products operations")
@Validated
public class ProductController {

    private final ProductService productService;

//    //POST: NEW PRODUCT path --> "/product/" ROLE[ADMIN, COMPANY]
//    @Operation(summary = "Add new product")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201",
//                    description = "New product added succesfully",
//                    content = @Content(mediaType = "application/json",
//                            array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class)),
//                            examples = {@ExampleObject(
//                                    value = """
//                                            {
//                                                 "id": 5,
//                                                 "productName": "Red Bull",
//                                                 "category": "Refresco",
//                                                 "price": 1.75
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
//                    schema = @Schema(implementation = ProductDTO.class),
//                    examples = @ExampleObject(
//                            value = """
//                                    {
//                                         "productName" : "Red Bull",
//                                         "category" : "Refresco",
//                                         "price" : 1.75
//                                    }
//                                    """
//                    )
//            )
//    )
//    @JsonView(ProductViews.ProductResponse.class)
//    @PostMapping("/")
//    public ResponseEntity<ProductDTO> addNewProduct(
//
//            @Valid @RequestBody @JsonView(ProductViews.NewProduct.class) ProductDTO newProduct) {
//        Product added = productService.addNewProduct(ProductDTO.of(newProduct));
//        URI uri = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("{id}")
//                .buildAndExpand(added.getId()).toUri();
//        return ResponseEntity.created(uri).body(ProductDTO.of(added));
//    }
//
//    //GET: GET ALL ACTIVE PRODUCTS path --> "/product/? ROLE[CUSTOMER, COMPANY]
//    @Operation(summary = "Get all active products")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200",
//                    description = "Products found",
//                    content = @Content(mediaType = "application/json",
//                            array = @ArraySchema(schema = @Schema(implementation = PageDTO.class)),
//                            examples = {@ExampleObject(
//                                    value = """
//                                            {
//                                                 "resultPerPage": 25,
//                                                 "results": [
//                                                     {
//                                                         "id": 1,
//                                                         "productName": "Red Bull",
//                                                         "category": "Refresco",
//                                                         "price": 1.75
//                                                     },
//                                                     {
//                                                         "id": 2,
//                                                         "productName": "Caña",
//                                                         "category": "Cerveza",
//                                                         "price": 1.4
//                                                     }
//                                                 ],
//                                                 "totalResults": 2,
//                                                 "page": 0,
//                                                 "totalPages": 1
//                                            }
//                                            """
//                            )}
//                    )
//            ),
//            @ApiResponse(responseCode = "404",
//                    description = "Products not found",
//                    content = @Content
//            )
//    })
//    @JsonView(ProductViews.ProductResponse.class)
//    @GetMapping("/")
//    @ResponseStatus(HttpStatus.OK)
//    public PageDTO<ProductDTO> getAllActiveProducts(
//            @RequestParam(value = "s", defaultValue = "") String s,
//            @PageableDefault(size = 25, page = 0) Pageable pageable
//            ) {
//        return PageDTO.of(productService.searchActive(s,pageable).map(ProductDTO::of));
//    }
//
//    //GET: GET A PRODUCT path --> "/product/{id}" ROLE[ADMIN, COMPANY]
//    @Operation(summary = "Get a product by id")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200",
//                    description = "Product found",
//                    content = @Content(mediaType = "application/json",
//                            array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class)),
//                            examples = {@ExampleObject(
//                                    value = """
//                                            {
//                                                 "id": 1,
//                                                 "productName": "Estrella Galicia",
//                                                 "category": "Cerveza",
//                                                 "price": 2.0,
//                                                 "active": true
//                                            }
//                                            """
//                            )}
//                    )
//            ),
//            @ApiResponse(responseCode = "404",
//                    description = "Product not found",
//                    content = @Content
//            )
//    })
//    @JsonView({ProductViews.FullProductResponse.class})
//    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public ProductDTO getProductById( @PathVariable @Min(1) @LongID String id) {
//        return ProductDTO.of(productService.getProductById(Long.parseLong(id)));
//    }
//
//    //PUT: EDIT A PRODUCT path --> "/product/{id}" ROLE[ADMIN, COMPANY]
//    @Operation(summary = "Update a product")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200",
//                    description = "Product info updated succesfully",
//                    content = @Content(mediaType = "application/json",
//                            array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class)),
//                            examples = {@ExampleObject(
//                                    value = """
//                                            {
//                                                "id": 3,
//                                                "productName": "Café con leche",
//                                                "category": "Café",
//                                                "price": 1.4
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
//                    schema = @Schema(implementation = EditProductDTO.class),
//                    examples = @ExampleObject(
//                            value = """
//                                    {
//                                        "category" : "Comida",
//                                        "price" : 2.50
//                                    }
//                                    """
//                    )
//            )
//    )
//    @JsonView(ProductViews.ProductResponse.class)
//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public ProductDTO updateAProduct(
//            @PathVariable @Min(1) @LongID String id,
//            @Valid @RequestBody EditProductDTO toUpdate) {
//        return ProductDTO.of(productService.updateAProduct(EditProductDTO.of(toUpdate), Long.parseLong(id)));
//    }
//
//
//    //DELETE: RETIRE A PRODUCT --> path "/product/{id}" ROLE[ADMIN]
//    @Operation(summary = "Retire a product")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200",
//                    description = "Product retired",
//                    content = @Content(mediaType = "application/json",
//                            array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class)),
//                            examples = {@ExampleObject(
//                                    value = """
//                                            {
//                                                 "id": 1,
//                                                 "productName": "Estrella Galicia",
//                                                 "category": "Cerveza",
//                                                 "price": 2.0,
//                                                 "active": true
//                                            }
//                                            """
//                            )}
//                    )
//            ),
//            @ApiResponse(responseCode = "404",
//                    description = "Product not found",
//                    content = @Content
//            )
//    })
//    @JsonView(ProductViews.FullProductResponse.class)
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void retireAProduct(@PathVariable @Min(1) @LongID String id) {
//        productService.retireAProduct(Long.parseLong(id));
//    }
}
