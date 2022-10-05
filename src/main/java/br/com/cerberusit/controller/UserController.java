package br.com.cerberusit.controller;

import br.com.cerberusit.controller.request.UserRequestDto;
import br.com.cerberusit.controller.response.UserResponseDto;
import br.com.cerberusit.event.CreatedResourceEvent;
import br.com.cerberusit.service.interfaces.IUserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    final IUserService userService;
    final ApplicationEventPublisher publisher;

    public UserController(IUserService userService, ApplicationEventPublisher publisher) {
        this.userService = userService;
        this.publisher = publisher;
    }

    @GetMapping
    public ResponseEntity<Page<UserResponseDto>> findAll(@PageableDefault Pageable pageable) {
        log.info("Request: page {} - size {}", pageable.getPageNumber(), pageable.getPageSize());
        return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        UserResponseDto userResponseDto = this.userService.findById(id);
        return null != userResponseDto
                ? new ResponseEntity<>(userResponseDto, HttpStatus.OK)
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto
            , HttpServletResponse response) {
        UserResponseDto userResponseDto = userService.createOrUpdateUser(userRequestDto);
        publisher.publishEvent(new CreatedResourceEvent(this, response, userResponseDto.getId()));
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto)
    {
        UserResponseDto userResponseDto = userService.createOrUpdateUser(userRequestDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @SneakyThrows
    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<UserResponseDto> switchUserStatus(@PathVariable Long id) {
        UserResponseDto userResponseDto = userService.switchUserState(id);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
