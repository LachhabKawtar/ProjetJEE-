package ma.emsi.activite2ormspringjap.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.emsi.activite2ormspringjap.entities.Medecin;
import ma.emsi.activite2ormspringjap.entities.Patient;
import ma.emsi.activite2ormspringjap.repositories.MedicineRepository;
import ma.emsi.activite2ormspringjap.repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@AllArgsConstructor
public class MedecinController {
    private MedicineRepository medicineRepository;
    @GetMapping(path = "/user/medecins")
    public String medecins(Model model,
                           @RequestParam(name = "page", defaultValue = "0")int page,
                           @RequestParam(name="size",defaultValue = "5")int size,
                           @RequestParam ( name= "keyword",defaultValue = "")String keyword) {
        //Page<Patient> pagePatients = patientRepository.findAll(PageRequest.of(page,size));
        Page<Medecin> pageMedecins = medicineRepository.findByNomContains(keyword,PageRequest.of(page,size));
        model.addAttribute("listMedecins",pageMedecins.getContent());
        model.addAttribute("pages",new int[pageMedecins.getTotalPages()]);
        model.addAttribute("currentePage",page);
        model.addAttribute("keyword",keyword);
        return "/Medecin/medecins";
    }
    @GetMapping(path = "/admin/formMedecin")
    public String formMdecins(Model model)
    {
        model.addAttribute("medecin",new Medecin());
        return "/Medecin/formMedecin";
    }
    @PostMapping(path="/admin/saveMedecin")
    //@PreAuthorize ("hasRole('ROLE_ADMIN')")
    public String saveMedecin (Model model, @Valid Medecin medecin, BindingResult bindingResult,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "") String keyword) {
        if (bindingResult.hasErrors()) {
            return "/Medecin/formMedecin";
        }
        medicineRepository.save(medecin);
        return "redirect:/user/medecins?page="+page+"&keyword="+keyword;
    }
    @GetMapping(path = "/admin/deleteMedecins")
    public String deleteMedecins(Long id,String keyword,int page)
    {
        medicineRepository.deleteById(id);
        return "redirect:/user/medecins?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/admin/editMedecin")
    //  @PreAuthorize ("hasRole('ROLE_ADMIN')")
    public String editMedecins(Model model, Long id,String keyword,int page)
    {
        Medecin medecin = medicineRepository.findById(id).orElse(null);
        if(medecin==null) throw  new RuntimeException("Medecin introuvable");
        model.addAttribute("medecin",medecin);
        model.addAttribute("keyword",keyword);
        model.addAttribute("page",page);
        return "/Medecin/editMedecin";
    }
}
