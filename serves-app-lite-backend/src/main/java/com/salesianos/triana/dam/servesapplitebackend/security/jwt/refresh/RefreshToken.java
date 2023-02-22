package com.salesianos.triana.dam.servesapplitebackend.security.jwt.refresh;

import com.salesianos.triana.dam.servesapplitebackend.entity.user.base.model.User;
import lombok.*;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RefreshToken {

    @Id
    private UUID id;

    @MapsId
    @OneToOne
    @JoinColumn(
            name = "user_id",
            columnDefinition = "uuid",
            foreignKey = @ForeignKey(name = "FK_REFRESHTOKEN_USER")
    )
    private User user;

    @NaturalId
    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;

    @CreatedDate
    private Instant createdAt;
}
