package ace.ucv.onlineshop.Repositories;

import ace.ucv.onlineshop.Model.Profile;
import ace.ucv.onlineshop.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Profile findProfileByUser(User user);
}