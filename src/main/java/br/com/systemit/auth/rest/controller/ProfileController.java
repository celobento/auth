package br.com.systemit.auth.rest.controller;

import br.com.systemit.auth.domain.entity.Profile;
import br.com.systemit.auth.domain.repository.ProfileRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private ProfileRepository profileRepository;

    public ProfileController(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @GetMapping
    public List<Profile> listAll() {
        return profileRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Profile save(@RequestBody @Valid Profile profile) {
        return profileRepository.save(profile);
    }
}
