package jpaspring.entity;

import javax.persistence.*;

import lombok.*;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "APPUSER", schema = "PUBLIC", catalog = "PUBLIC")
@Getter @Setter @ToString
public class AppUser extends AbstractEntity { //NOSONAR (ID is enough for equals in this case)

    private String username;
    private String password;
    private Boolean enabled;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "userrole",
            joinColumns = @JoinColumn(name = "appuser_id"),
            inverseJoinColumns = @JoinColumn(name = "approle_id"))
    private Set<AppRole> appRoles;
}