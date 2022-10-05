package br.com.cerberusit.controller;

import br.com.cerberusit.controller.request.ProfileRequestDto;
import br.com.cerberusit.controller.response.ProfileResponseDto;
import br.com.cerberusit.event.CreatedResourceEvent;
import br.com.cerberusit.service.interfaces.IProfileService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/profiles")
public class ProfileController {

    final IProfileService profileService;
    final ApplicationEventPublisher publisher;

    public ProfileController(IProfileService profileService, ApplicationEventPublisher publisher){
        this.profileService = profileService;
        this.publisher = publisher;
    }

    @GetMapping
    @Operation(summary = "Find all profiles", description = "Endpoint to find all created profiles")
    public ResponseEntity<Page<ProfileResponseDto>> findAll(@PageableDefault(size = 10, page = 0,
            sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        log.info("Request: page {} - size {}", pageable.getPageNumber(), pageable.getPageSize());
        return new ResponseEntity<>(profileService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Profile details", description = "Endpoint to find a profile by its ID and see its details")
    public ResponseEntity<ProfileResponseDto> findById(@PathVariable Long id) {
        ProfileResponseDto profileResponseDto = profileService.findById(id);
        return null != profileResponseDto
                ? new ResponseEntity<>(profileResponseDto, HttpStatus.OK)
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Create profile", description = "Endpoint to create a new profile")
    public ResponseEntity<ProfileResponseDto> createProfile(@RequestBody ProfileRequestDto profileRequesDto
            , HttpServletResponse response) {
        ProfileResponseDto profileResponseDto = profileService.createOrUpdateProfile(profileRequesDto);
        publisher.publishEvent(new CreatedResourceEvent(this, response, profileResponseDto.getId()));
        return new ResponseEntity<>(profileResponseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edit profile", description = "Endpoint to edit a existing profile")
    public ResponseEntity<ProfileResponseDto> updateProfile(@PathVariable Long id
            , @RequestBody ProfileRequestDto profileRequestDto ){

        ProfileResponseDto profileResponseDto = profileService.createOrUpdateProfile(profileRequestDto);
        return new ResponseEntity<>(profileResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Deactivate profile", description = "Endpoint to deactivate a existing profile")
    public ResponseEntity<Void> deactivateProfile(@PathVariable Long id){
        return null; //TODO: Finalizar implementação do metodo
    }
}
