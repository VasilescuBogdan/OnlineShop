package ace.ucv.onlineshop.Repositories;

import ace.ucv.onlineshop.Model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}