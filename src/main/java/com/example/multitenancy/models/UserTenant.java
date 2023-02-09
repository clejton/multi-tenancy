package com.example.multitenancy.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users_tenants",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "user_id", name = "un_user_id"),
                @UniqueConstraint(columnNames = "tenant_id", name = "un_tenant_id")
        })
public class UserTenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_users_tenants_user1"))
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_users_tenants_tenant1"))
    @JsonBackReference
    private Tenant tenant;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_tenants_roles",
            joinColumns = {@JoinColumn(name = "user_tenant_id",
                    foreignKey = @ForeignKey(name = "fk_users_tenants_user_tenant1"))},
            inverseJoinColumns = {@JoinColumn(name = "role_id",
                    foreignKey = @ForeignKey(name = "fk_users_tenants_roles1"))})
    private Set<Role> roles = new HashSet<>();

}
