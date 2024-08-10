package br.com.systemit.auth.rest.controller;

import br.com.systemit.auth.domain.entity.Module;
import br.com.systemit.auth.domain.repository.ModuleRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/modules")
public class ModuleController {

    private ModuleRepository moduleRepository;

    public ModuleController(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    @GetMapping
    public List<Module> listAll() {
        return moduleRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Module save(@RequestBody @Valid Module module){
        return moduleRepository.save(module);
    }

}
