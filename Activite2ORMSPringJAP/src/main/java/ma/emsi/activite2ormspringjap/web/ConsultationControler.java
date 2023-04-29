package ma.emsi.activite2ormspringjap.web;

import lombok.AllArgsConstructor;
import ma.emsi.activite2ormspringjap.entities.Consultation;
import ma.emsi.activite2ormspringjap.entities.Medecin;
import ma.emsi.activite2ormspringjap.repositories.ConsultationRepository;
import ma.emsi.activite2ormspringjap.repositories.MedicineRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@AllArgsConstructor
public class ConsultationControler {
    private ConsultationRepository consultationRepository;
    @GetMapping(path = "/user/consultation")
    public String medecins(Model model,
                           @RequestParam(name = "page", defaultValue = "0")int page,
                           @RequestParam(name="size",defaultValue = "5")int size,
                           @RequestParam ( name= "keyword",defaultValue = "")String keyword) {
        //Page<Patient> pagePatients = patientRepository.findAll(PageRequest.of(page,size));
        Page<Consultation> pageConsultation = consultationRepository.findAll(PageRequest.of(page,size));
        model.addAttribute("listConsultation",pageConsultation.getContent());
        model.addAttribute("pages",new int[pageConsultation.getTotalPages()]);
        model.addAttribute("currentePage",page);
        model.addAttribute("keyword",keyword);
        return "/Consultation/consultation";
    }
}
