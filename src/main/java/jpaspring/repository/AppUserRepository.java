package jpaspring.repository;

import jpaspring.entity.AppUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface AppUserRepository extends JpaRepository <AppUser, Long> {
    @Query("select b from AppUser b where b.username = :username")
    AppUser findByName(@Param("username") String username);
}
