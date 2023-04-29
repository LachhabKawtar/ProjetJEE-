package ma.emsi.activite2ormspringjap;

import ma.emsi.activite2ormspringjap.entities.*;
import ma.emsi.activite2ormspringjap.repositories.ConsultationRepository;
import ma.emsi.activite2ormspringjap.repositories.MedicineRepository;
import ma.emsi.activite2ormspringjap.repositories.PatientRepository;
import ma.emsi.activite2ormspringjap.repositories.RendezVousRepository;
import ma.emsi.activite2ormspringjap.securité.services.AccountServices;
import ma.emsi.activite2ormspringjap.service.HopitalServicesImpl;
import ma.emsi.activite2ormspringjap.web.PatientController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class Activite2OrmsPringJapApplication {
    @Autowired
     private PatientRepository patientRepository;
    private MedicineRepository medecinRepository;
    private RendezVousRepository rendezVousRepository;
    private ConsultationRepository consultationRepository;
    public static void main(String[] args) {
        SpringApplication.run(Activite2OrmsPringJapApplication.class, args);
    }
    //  @Override
    //public void run(String... args) throws Exception {
   // @Bean
    CommandLineRunner start(HopitalServicesImpl hopitalServices, RendezVousRepository rendezVousRepository,
                         ConsultationRepository  consultationRepository) {
      return  args -> {
          /*
          -----Patient-----
           */

      /*    hopitalServices.savePatient(
                      new Patient(null, "Kawtar Lachhab", new Date(), false, (int)  100));
          hopitalServices.savePatient(
                  new Patient(null, "karim", new Date(), true, (int)    100));
          hopitalServices.savePatient(
                  new Patient(null, "Othmane", new Date(), false, (int)  200));
          hopitalServices.savePatient(
                  new Patient(null, "Zakaria", new Date(), false, (int)  210));
          hopitalServices.savePatient(
                  new Patient(null, "Ihssane", new Date(), true, (int)  300));
          hopitalServices.savePatient(
                  new Patient(null, "Ikram", new Date(), false, (int)    400));
          hopitalServices.savePatient(
                  new Patient(null, "Samir", new Date(), true, (int)     470));
          hopitalServices.savePatient(
                  new Patient(null, "Ahmed", new Date(), true, (int)  132));
          hopitalServices.savePatient(
                  new Patient(null, "", new Date(), false, (int)  500));
          hopitalServices.savePatient(
                  new Patient(null, "Fatima", new Date(), true, (int)  340));
          hopitalServices.savePatient(
                  new Patient(null, "Chaîmaa", new Date(), false, (int)  600));

          //  List<Patient> patients = patientRepository.findAll();
          //problème si nombre d'enregistrement est important
          //solution => Pagination
          /*Page<Patient> patients = hopitalServices.findAll(PageRequest.of(1, 10));
          System.out.printf("total pages: " + patients.getTotalPages());
          System.out.printf("total element:" + patients.getTotalElements());
          System.out.printf("num page:" + patients.getNumber());
          List<Patient> content = patients.getContent();
          content.forEach(p -> {
          System.out.println("-------------------------Patient--------------------------");
          System.out.println(String.valueOf(p.getId()));
          System.out.println(String.valueOf(p.getNom()));
          System.out.println(String.valueOf(p.getMalade()));
          System.out.println(String.valueOf(p.getDateNaiss()));
          System.out.println(String.valueOf(p.getScore()));
          });
          System.out.println("-----------------------chercher patient par id-------");*/
         Patient patient = hopitalServices.findPatientById(1L);
         /* System.out.println("-------------Patient(id=1)---------");
          Patient patient = patientRepository.findById(1L).orElse(null);
          if (patient != null) {
              System.out.println(String.valueOf(patient.getId()));
              System.out.println(String.valueOf(patient.getNom()));
              System.out.println(String.valueOf(patient.getMalade()));
              System.out.println(String.valueOf(patient.getDateNaiss()));
              System.out.println(String.valueOf(patient.getScore()));
          }
          /*
           -------Medecins------

          */
         Stream.of("kawtar", "samir", "zakaria").forEach(name -> {
              Medecin medecin = new Medecin();
              medecin.setNom(name);
              medecin.setEmail(name + "@gmail.com");
              medecin.setSpecialite(Math.random() > 0.5 ? "cardio" : "Dentiste");
              hopitalServices.saveMedecin(medecin);
          });
          Medecin medecin =  hopitalServices.findMedecinById(1L);
          /*-------Rendez-vous-----*

           */
          RendezVous rendezVous = new RendezVous();
          rendezVous.setDate(new Date());
          rendezVous.setStatuRDV(StatuRDV.PENDING);
          rendezVous.setMedecin(medecin);
          rendezVous.setPatient(patient);
          hopitalServices.saveRendezVous(rendezVous);
          /*
          ------Consultation-----
          */
          RendezVous rendezVous1 =  rendezVousRepository.findById(1L).orElseThrow(() -> new RuntimeException("patient not found"));
          Consultation consultation = new Consultation();
          consultation.setDateConsult(new Date());
          consultation.setRendezVous(rendezVous1);
          consultation.setRapport("rapport de la consultation1--------------");
          //PatientController consultationRepository;
          consultationRepository.save(consultation);
          // patient.setScore(180);
          // patientRepository.save(patient);//update id !=null
          // patientRepository.deleteById(1L);
          //patientRepository.deleteAll();
          //List<Patient> byMalade = patientRepository.findByMalade(true);
          //byMalade.forEach(System.out::println);
          //List<Patient> nameLike = patientRepository.charcherPatient("%h%");
          // nameLike.forEach(System.out::println);*/
     };
   }
        // @Bean
          CommandLineRunner commandLineRunnerUserDetail(AccountServices accountServices){
              return  args -> {
                     //accountServices.addRole("ADMIN");
                    //accountServices.addRole("USER");
                   // accountServices.addNewUser("user21","12345","kawtar@gmail.com","12345");
                   //accountServices.addNewUser("kawtar lachhab","@?/123","kawtar@gmail.com","@?/123");
                  //accountServices.addNewUser("admin","@?/123@/","kawtar@gmail.com","@?/123@/");
                  //accountServices.addRoleToUser("kawtar lachhab","USER");
                 // accountServices.addRoleToUser("admin","ADMIN");
                 // accountServices.addRoleToUser("admin","USER");
              };
          }
    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
