package ma.emsi.activite2ormspringjap.securité.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.emsi.activite2ormspringjap.securité.entities.AppRole;
import ma.emsi.activite2ormspringjap.securité.entities.Appuser;
import ma.emsi.activite2ormspringjap.securité.repository.AppRoleRepository;
import ma.emsi.activite2ormspringjap.securité.repository.AppuserRepository;
import ma.emsi.activite2ormspringjap.securité.services.AccountServices;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional  //Toutes les méthodes sont transactional
@AllArgsConstructor //lombock
//@NoArgsConstructor
public class AccountServicesImpl implements AccountServices {

    private AppuserRepository appuserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public Appuser addNewUser(String username, String password, String email, String confirmationPassword) {
        Appuser appuser =appuserRepository.findByUsername(username);
        /*
        ----- Règles métiers ---
         */
        if(appuser!=null) throw  new RuntimeException("this user already exist");
        if(!password.equals(confirmationPassword)) throw  new RuntimeException("password not match");
        appuser=Appuser.builder()
                .userID(UUID.randomUUID().toString()) //password aléatoire
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();
          Appuser saveUser =appuserRepository.save(appuser);
        return saveUser;
    }
    @Override
    public AppRole addRole(String role) {
        AppRole appRole =appRoleRepository.findById(role).orElse(null);
        if(appRole!=null)throw  new RuntimeException("This role already exist");
        appRole =AppRole.builder().role(role).build();
        return appRoleRepository.save(appRole);
    }
    @Override
    public void addRoleToUser(String username, String role) {
         Appuser appuser =appuserRepository.findByUsername(username);
         AppRole appRole =appRoleRepository.findById(role).get();
         appuser.getRoles().add(appRole);
       //  appuserRepository.save(appuser);
    }
    @Override
    public void removeRoleFromUser(String username, String role) {
        Appuser appuser =appuserRepository.findByUsername(username);
        AppRole appRole =appRoleRepository.findById(role).get();
        appuser.getRoles().remove(appRole);
    }
    @Override
    public Appuser loadUserByUsername(String username) {
        return appuserRepository.findByUsername(username);
    }
}
