package ma.emsi.activite2ormspringjap.securité.repository;

import ma.emsi.activite2ormspringjap.securité.entities.Appuser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppuserRepository extends JpaRepository<Appuser,String> {
    Appuser findByUsername(String username);
}
