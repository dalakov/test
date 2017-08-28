package jpaspring.entity;

import javax.persistence.*;

import lombok.*;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "APPROLE", schema = "PUBLIC", catalog = "PUBLIC")
@Getter @Setter @ToString
public class AppRole extends AbstractEntity { //NOSONAR (ID is enough for equals in this case)
    private String rolename;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "appRoles")
    private Set<AppUser> appUsers;
}
