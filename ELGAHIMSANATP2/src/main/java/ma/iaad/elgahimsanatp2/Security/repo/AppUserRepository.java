package ma.iaad.elgahimsanatp2.Security.repo;

import ma.iaad.elgahimsanatp2.Security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,String> {
    AppUser findByUsername(String username);
}

