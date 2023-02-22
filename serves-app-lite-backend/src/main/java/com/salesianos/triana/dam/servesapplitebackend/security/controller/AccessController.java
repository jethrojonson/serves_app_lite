package com.salesianos.triana.dam.servesapplitebackend.security.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.base.dto.UserDTO;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.base.model.User;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.base.service.UserService;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.base.view.UserViews;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.dto.CompanyDTO;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.company.view.CompanyViews;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.dto.CustomerDTO;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.customer.view.CustomerViews;
import com.salesianos.triana.dam.servesapplitebackend.security.dto.LoginRequest;
import com.salesianos.triana.dam.servesapplitebackend.security.jwt.access.JwtProvider;
import com.salesianos.triana.dam.servesapplitebackend.security.jwt.refresh.RefreshToken;
import com.salesianos.triana.dam.servesapplitebackend.security.jwt.refresh.RefreshTokenService;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Access Controller", description = "Controller of the access operations")
public class AccessController {

    private final UserService userService;
    private final AuthenticationManager authManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;

    @Operation(summary = "Register a new admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "New admin added successfully",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": "759b5e20-0e29-4494-9b2d-31614bbfcb7a",
                                                "username": "admin0000000",
                                                "avatar": "https://robohash.org/admin002",
                                                "createdAt": "22/02/2023 19:43:44"
                                            }
                                            """
                            )}
                    )
            ),
            @ApiResponse(responseCode = "400",
                    description = "Incorret input format",
                    content = @Content
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Input body format",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserDTO.class),
                    examples = @ExampleObject(
                            value = """
                                    {
                                        "username" : "admin0000000",
                                        "password" : "admin",
                                        "avatar" : "https://robohash.org/admin002"
                                    }
                                    """
                    )
            )
    )
    @JsonView(UserViews.UserResponse.class)
    @PostMapping("/register/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO registerNewAdmin (@RequestBody UserDTO newAdmin){
        return UserDTO.of(userService.registerNewAdmin(UserDTO.of(newAdmin)));
    }

    @Operation(summary = "Register a new customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "New customer added successfully",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CustomerDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": "a59ba620-2403-48fa-8f1d-b08852195007",
                                                "username": "jethro_93",
                                                "avatar": "https://robohash.org/user001",
                                                "name": "Jerónimo M.",
                                                "surname": "Pérez González",
                                                "email": "perez.gojer22@triana.salesianos.edu",
                                                "subscribedAt": "22/02/2023 19:12:01"
                                            }
                                            """
                            )}
                    )
            ),
            @ApiResponse(responseCode = "400",
                    description = "Incorret input format",
                    content = @Content
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Input body format",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomerDTO.class),
                    examples = @ExampleObject(
                            value = """
                                    {
                                         "username" : "jethro_93",
                                         "password" : "user",
                                         "verifyPassword" : "user",
                                         "avatar" : "https://robohash.org/user001",
                                         "name" : "Jerónimo M.",
                                         "surname" : "Pérez González",
                                         "email" : "perez.gojer22@triana.salesianos.edu"
                                    }
                                    """
                    )
            )
    )
    @JsonView(CustomerViews.CustomerResponse.class)
    @PostMapping("/register/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO registerNewCustomer(@RequestBody CustomerDTO newCustomer){
        return CustomerDTO.of(userService.registerNewCustomer(CustomerDTO.of(newCustomer)));
    }

    @Operation(summary = "Register a new company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "New company added successfully",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CompanyDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": "8c7c141a-c3ef-4868-824b-0a93422f29ac",
                                                "cif": "A-12345678",
                                                "companyName": "Café D&N",
                                                "username": "chileno_69",
                                                "avatar": "http://robohash.org/chileno_69",
                                                "subscribedAt": "22/02/2023 07:38:48"
                                            }
                                            """
                            )}
                    )
            ),
            @ApiResponse(responseCode = "400",
                    description = "Incorret input format",
                    content = @Content
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Input body format",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CompanyDTO.class),
                    examples = @ExampleObject(
                            value = """
                                    {
                                         "username" : "chileno_69",
                                         "password" : "user",
                                         "verifyPassword" : "user",
                                         "companyName" : "Café D&N",
                                         "avatar" : "http://robohash.org/chileno_69",
                                         "cif" : "A-12345678"
                                    }
                                    """
                    )
            )
    )
    @JsonView(CompanyViews.CompanyResponse.class)
    @PostMapping("/register/company")
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyDTO registerNewCompany(@RequestBody CompanyDTO newCompany){
        return CompanyDTO.of(userService.registerNewCompany(CompanyDTO.of(newCompany)));
    }

    @Operation(summary = "Log in an user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User logged successfully",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserDTO.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "id": "596c8ae6-ae26-11ed-afa1-0242ac120002",
                                                 "username": "admin000",
                                                 "avatar": "https://robohash.org/admin000",
                                                 "createdAt": "22/02/2023 19:38:40",
                                                 "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI1OTZjOGFlNi1hZTI2LTExZWQtYWZhMS0wMjQyYWMxMjAwMDIiLCJpYXQiOjE2NzcwOTE0MjAsImV4cCI6MTY3NzA5MTQ4MH0.R61Kg1aBxhCGhQN-TMqMQUMGXA_jYmL_dMiVai7M16I5CQR5DppLCZgyD5ISV6a7zFzDG0fk798c1r1BRmF8AQ",
                                                 "refreshToken": "9caf1206-02c6-4b06-99c3-fb1d39d8bfba"
                                             }
                                            """
                            )}
                    )
            ),
            @ApiResponse(responseCode = "401",
            description = "Log in process failed, cannot authenticate",
                    content = @Content
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Input body format",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = LoginRequest.class),
                    examples = @ExampleObject(
                            value = """
                                    {
                                        "username" : "admin000",
                                        "password" : "admin"
                                    }
                                    """
                    )
            )
    )
    @Transactional
    @JsonView(UserViews.UserWithTokenResponse.class)
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication =
                authManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(),
                                loginRequest.getPassword()
                        )
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        User user = (User) authentication.getPrincipal();
        refreshTokenService.deleteByUser(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);
        return UserDTO.of(user, token, refreshToken.getToken());
    }

}
