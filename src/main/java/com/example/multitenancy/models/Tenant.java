package com.example.multitenancy.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tenants",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "subdomain", name = "un_subdomain")
        })
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String subdomain;

    @NotBlank
    private String name;

}
