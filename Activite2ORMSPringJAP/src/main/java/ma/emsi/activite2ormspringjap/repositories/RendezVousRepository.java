package ma.emsi.activite2ormspringjap.repositories;

import ma.emsi.activite2ormspringjap.entities.Patient;
import ma.emsi.activite2ormspringjap.entities.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous,Long> {

}
