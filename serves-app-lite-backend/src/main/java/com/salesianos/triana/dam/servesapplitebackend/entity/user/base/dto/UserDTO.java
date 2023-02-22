package com.salesianos.triana.dam.servesapplitebackend.entity.user.base.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.base.model.User;
import com.salesianos.triana.dam.servesapplitebackend.entity.user.base.view.UserViews;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {

    @JsonView({UserViews.UserResponse.class})
    private UUID id;

    @JsonView({UserViews.UserResponse.class})
    private String username;

    private String password;

    private String verifyPassword;

    @JsonView({UserViews.UserResponse.class})
    private String avatar;

    @JsonView({UserViews.UserResponse.class})
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonView({UserViews.UserWithTokenResponse.class})
    private String token;

    @JsonView({UserViews.UserWithTokenResponse.class})
    private String refreshToken;

    public static User of (UserDTO u){
        return User.builder()
                .username(u.getUsername())
                .password(u.getPassword())
                .avatar(u.getAvatar())
                .build();
    }

    public static UserDTO of (User u){
        return UserDTO.builder()
                .id(u.getId())
                .username(u.getUsername())
                .password(u.getPassword())
                .avatar(u.getAvatar())
                .createdAt(u.getCreatedAt())
                .build();
    }

    public static UserDTO of (User u, String token, String refreshToken){
        UserDTO result = of(u);
        result.setToken(token);
        result.setRefreshToken(refreshToken);
        return result;
    }
}
