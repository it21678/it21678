package it21678.application.service;

import it21678.application.entity.Form;
import it21678.application.entity.User;
import it21678.application.repository.FormRepository;
import it21678.application.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormService {
    @Autowired
    private FormRepository formRepository;

    private UserRepository userRepository;

    @Transactional
    public List<Form> getForms(){
        return formRepository.findAll();
    }

    @Transactional
    public void saveForm(Form form){
        formRepository.save(form);
    }

    @Transactional
    public Form getForm(Integer formId){
        return formRepository.findById(formId).get();
    }


    public List<Form> getUserForms(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Error: User is not found."));
        return user.getForms();
    }

    @Transactional
    public Integer updateForm(Form form) {
        form = formRepository.save(form);
        return form.getId();
    }
}
