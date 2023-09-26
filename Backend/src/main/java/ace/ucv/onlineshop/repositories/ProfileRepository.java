package ace.ucv.onlineshop.repositories;

import ace.ucv.onlineshop.model.Profile;
import ace.ucv.onlineshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Profile findProfileByUser(User user);
}