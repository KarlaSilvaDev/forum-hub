package forum.hub.api.controller;

import forum.hub.api.domain.user.UserService;
import forum.hub.api.domain.user.dto.UserDetailsDTO;
import forum.hub.api.domain.user.dto.UserRegistrationDTO;
import forum.hub.api.domain.user.dto.UserUpdateDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid UserRegistrationDTO data, UriComponentsBuilder uriBuilder){
        var userDetails = service.register(data);
        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(userDetails.id()).toUri();
        return ResponseEntity.created(uri).body(userDetails);
    }

    @GetMapping
    public ResponseEntity<Page<UserDetailsDTO>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination){
        var page = service.list(pagination);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id){
        var userDetails = service.detail(id);
        return ResponseEntity.ok(userDetails);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UserUpdateDTO data, @PathVariable Long id){
        var userDetails = service.update(data, id);
        return ResponseEntity.ok(userDetails);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
