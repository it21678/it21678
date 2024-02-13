package it21678.application.controller;


import it21678.application.entity.Form;
import it21678.application.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/form")
public class FormController {

    @Autowired
    private FormService formService;

    @GetMapping("")
    public String showForms(Model model){
        model.addAttribute("forms", formService.getForms());
        return "forms";
    }

    @GetMapping("/new")
    public String addForm(Model model){
        Form form = new Form();
        model.addAttribute("form", form);
        return "add_form";
    }

    @PostMapping("/new")
    public String saveForm(Form form,Model model){
        formService.saveForm(form);
        model.addAttribute("forms", formService.getForms());
        return "forms";
    }

    @GetMapping("/accept/{form_id}")
    public String acceptForm(@PathVariable Integer form_id, Model model){
        Form form = formService.getForm(form_id);
        model.addAttribute("form", form);
        return "accept";
    }

    @PostMapping("/accept/{form_id}")
    public String acceptFormConfirm(Form form, Model model){
        formService.saveForm(form);
        model.addAttribute("forms", formService.getForms());
        return "forms";
    }


    @GetMapping("/decline/{form_id}")
    private String declineForm(@PathVariable Integer form_id, Model model){
        Form form = formService.getForm(form_id);
        model.addAttribute("form", form);
        return "decline";
    }

    @PostMapping("/decline/{form_id}")
    public String declineFormConfirm(@PathVariable Integer form_id, Model model){
        Form form = formService.getForm(form_id);
        form.setResult("DECLINE");
        formService.saveForm(form);
        model.addAttribute("forms", formService.getForms());
        return "forms";
    }


    @GetMapping("/user/forms/{user_id}")
    public String showUserForms(@PathVariable Long user_id, Model model){
        model.addAttribute("forms", formService.getUserForms(user_id));
        return "user_forms";
    }



}
