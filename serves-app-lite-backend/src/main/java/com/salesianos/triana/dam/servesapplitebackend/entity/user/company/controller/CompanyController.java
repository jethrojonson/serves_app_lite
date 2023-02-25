package com.salesianos.triana.dam.servesapplitebackend.entity.user.company.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianos.triana.dam.servesapplitebackend.entity.item.dto.ItemDTO;
import com.salesianos.triana.dam.servesapplitebackend.entity.item.model.Item;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.dto.CompanyDTO;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.model.Company;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.service.CompanyService;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.view.CompanyViews;
import com.salesianos.triana.dam.servesapplitebackend.search.util.PageDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
@Tag(name = "Company Controller", description = "Controller of the companies operations")
public class CompanyController {

    private final CompanyService companyService;


    @PostMapping("/menu/item/{product_id}")
    public ResponseEntity<ItemDTO> addProductToMenu(
            @AuthenticationPrincipal Company company,
            @PathVariable Long product_id,
            @RequestBody ItemDTO toAdd){
        Item added =companyService.addProductToMenu(company, product_id, ItemDTO.of(toAdd));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(added.getId())
                .toUri();
        return ResponseEntity.created(uri).body( ItemDTO.of(added));
    }

    @JsonView(CompanyViews.CompanySimpleResponse.class)
    @GetMapping("/menu/{company_id}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyDTO getMenu(@PathVariable String company_id){
        return CompanyDTO.of(companyService.getCompany(company_id));
    }

    @JsonView(CompanyViews.FullCompanyResponse.class)
    @GetMapping("/{company_id}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyDTO getCompany (@PathVariable String company_id){
        return CompanyDTO.of(companyService.getCompany(company_id));
    }

    @JsonView(CompanyViews.CompanyListItem.class)
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public PageDTO<CompanyDTO> getAllCompanies(
            @RequestParam(value = "s", defaultValue = "") String s,
            @PageableDefault(size = 25, page = 0) Pageable pageable
            ){
        return PageDTO.of(companyService.searchAll(s,pageable).map(CompanyDTO::of));
    }


    @DeleteMapping("/menu/item/{item_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItemFromMenu(@PathVariable Long item_id,
                                   @AuthenticationPrincipal Company loggedCompany){
        companyService.deleteItemFromMenu(loggedCompany,item_id);
    }



//    //POST: NEW COMPANY path --> "/company/"
////    @Operation(summary = "Add new company")
////    @ApiResponses(value = {
////            @ApiResponse(responseCode = "201",
////                    description = "New company added succesfully",
////                    content = @Content(mediaType = "application/json",
////                            array = @ArraySchema(schema = @Schema(implementation = CompanyDTO.class)),
////                            examples = {@ExampleObject(
////                                    value = """
////                                            {
////                                                "id" : "45c6f247-ce6b-413f-ab84-cd8373990c59",
////                                                "cif" : "A-12345678",
////                                                "companyName" : "Malafama Clandestine Bar",
////                                                "username" : "Malfama2000"
////                                            }
////                                            """
////                            )}
////                    )
////            ),
////            @ApiResponse(responseCode = "400",
////                    description = "Incorrect input format",
////                    content = @Content
////            )
////    })
////   @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Input body format",
////            content = @Content(mediaType = "application/json",
////                    schema = @Schema(implementation = CompanyDTO.class),
////                    examples = @ExampleObject(
////                            value = """
////                                    {
////                                        "cif" : "A-12345678",
////                                        "companyName" : "Malafama Clandestine Bar",
////                                        "username" : "Malfama2000"
////                                    }
////                                    """
////                    )
////            )
////    )
////    @JsonView(CompanyViews.CompanyResponse.class)
////    @PostMapping("/")
////    @ResponseStatus(HttpStatus.CREATED)
////    public CompanyDTO registerNewCompany (
////            @JsonView(CompanyViews.NewCompany.class)
////            @RequestBody CompanyDTO newCompany){
////        return companyService.addNewCompany(newCompany);
////    }
//
//    @JsonView(CompanyViews.CompanyResponse.class)
//    @PostMapping("/register/")
//    @ResponseStatus(HttpStatus.CREATED)
//    public CompanyDTO registerNewCompany(@RequestBody CompanyDTO newCompany){
//        return CompanyDTO.of(companyService.createNewCompany(CompanyDTO.of(newCompany)));
//    }
//
//    //GET: GET ALL COMPANIES path --> "/company/"
//    @Operation(summary = "Get all companies")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200",
//                    description = "Companies found",
//                    content = @Content(mediaType = "application/json",
//                            array = @ArraySchema(schema = @Schema(implementation = CompanyDTO.class)),
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
//            @ApiResponse(responseCode = "404",
//                    description = "Companies not found",
//                    content = @Content
//            )
//    })
//    @JsonView(CompanyViews.CompanyResponse.class)
//    @GetMapping("/")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public List<CompanyDTO> getAllCompanies(){
//        return companyService.getAllCompanies();
//    }
//
//    //GET: GET A COMPANY path --> "/company/{id}"
//    @Operation(summary = "Get a company by id")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200",
//                    description = "Company found",
//                    content = @Content(mediaType = "application/json",
//                            array = @ArraySchema(schema = @Schema(implementation = CompanyDTO.class)),
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
//            @ApiResponse(responseCode = "404",
//                    description = "Company not found",
//                    content = @Content
//            )
//    })
//    @JsonView(CompanyViews.FullCompanyResponse.class)
//    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public CompanyDTO getCompanyById(@PathVariable UUID id){
//        return companyService.getCompanyById(id);
//    }
//
//    //PUT: EDIT A COMPANY path --> "/company/{id}"  ROLE[ADMIN, COMPANY]
//    @Operation(summary = "Update a company")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200",
//                    description = "Company info updated succesfully",
//                    content = @Content(mediaType = "application/json",
//                            array = @ArraySchema(schema = @Schema(implementation = CompanyDTO.class)),
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
//                    schema = @Schema(implementation = CompanyDTO.class),
//                    examples = @ExampleObject(
//                            value = """
//                                    {
//                                        "companyName" : "Malafama Clandestine Bar"
//                                    }
//                                    """
//                    )
//            )
//    )
//    @JsonView(CompanyViews.CompanyResponse.class)
//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public CompanyDTO updateACompany(@PathVariable UUID id,
//                                     @JsonView(CompanyViews.CompanyUpdate.class)
//                                     @RequestBody CompanyDTO toUpdate){
//        return companyService.updateACompany(toUpdate, id);
//    }
//
//    //DELETE: DELETE A COMPANY path --> "/company/{id}" ROLE[ADMIN, COMPANY]
//    @Operation(summary = "Delete a company")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "204",
//                    description = "No content",
//                    content = @Content
//            ),
//            @ApiResponse(responseCode = "404",
//                    description = "Company not found",
//                    content = @Content
//            )
//    })
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteACompany(@PathVariable UUID id){
//        companyService.removeACompany(id);
//    }
}
