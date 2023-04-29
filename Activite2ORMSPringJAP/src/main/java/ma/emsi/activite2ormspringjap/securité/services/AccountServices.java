package ma.emsi.activite2ormspringjap.securité.services;

import ma.emsi.activite2ormspringjap.securité.entities.AppRole;
import ma.emsi.activite2ormspringjap.securité.entities.Appuser;

public interface AccountServices {
    Appuser addNewUser(String username, String password, String email, String  confirmation);
    AppRole addRole(String role);
    void addRoleToUser(String username, String role);
    void removeRoleFromUser(String username,String role);
    Appuser loadUserByUsername(String username);
}
