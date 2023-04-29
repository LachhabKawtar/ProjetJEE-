package ma.emsi.activite2ormspringjap.repositories;

import ma.emsi.activite2ormspringjap.entities.Consultation;
import ma.emsi.activite2ormspringjap.entities.Medecin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {

}
