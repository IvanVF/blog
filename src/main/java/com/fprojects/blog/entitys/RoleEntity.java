package com.fprojects.blog.entitys;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class RoleEntity implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Size(min = 2, message = "Минимум 2 символа")
    private String name;

    /*@Transient
    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> users;*/

    @Override
    public String getAuthority() {
        return getName();
    }

    public RoleEntity(UUID id) {
        this.id = id;
    }

    public RoleEntity(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
