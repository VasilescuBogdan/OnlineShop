package ace.ucv.onlineshop.Repositories;

import ace.ucv.onlineshop.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}