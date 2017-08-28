package jpaspring.entity;

import javax.persistence.*;
import lombok.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data @EqualsAndHashCode(of={"id"})
abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
}