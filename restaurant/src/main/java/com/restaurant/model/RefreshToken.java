package com.restaurant.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "refresh_tokens")
@Schema(description = "RefreshToken entity representing a refresh token for authentication")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "token")
    private String token;

    @Column(name = "issued_at")
    private Date issuedAt;

    @Column(name = "expires_at")
    private Date expiresAt;

    @ManyToOne
    @JoinColumn(name = "username" , referencedColumnName = "username")
    private Customer customer;

}
