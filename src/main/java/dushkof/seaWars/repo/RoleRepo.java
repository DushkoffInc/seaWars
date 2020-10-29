package dushkof.seaWars.repo;

import dushkof.seaWars.objects.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
}
