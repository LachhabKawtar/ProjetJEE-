package ma.emsi.activite2ormspringjap.securité.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appuser  {
    @Id
    private String userID;
    @Column(unique = true)
    private String username;
    private String password;
    private String email;
   // @ManyToMany(fetch = FetchType.LAZY)//ne pas charger les roles
   @ManyToMany(fetch = FetchType.EAGER)
    private List<AppRole> roles;
}
