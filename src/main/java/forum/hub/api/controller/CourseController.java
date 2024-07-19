package forum.hub.api.controller;

import forum.hub.api.domain.course.CourseService;
import forum.hub.api.domain.course.dto.CourseDetailsDTO;
import forum.hub.api.domain.course.dto.CourseRegistrationDTO;
import forum.hub.api.domain.course.dto.CourseUpdateDTO;
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
@RequestMapping("/cursos")
public class CourseController {

    @Autowired
    private CourseService service;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid CourseRegistrationDTO data, UriComponentsBuilder uriBuilder){
        var courseDetails = service.register(data);
        var uri = uriBuilder.path("/cursos/{id}").buildAndExpand(courseDetails.id()).toUri();
        return ResponseEntity.created(uri).body(courseDetails);
    }

    @GetMapping
    public ResponseEntity<Page<CourseDetailsDTO>> list(@PageableDefault(size = 10, sort = {"category"}) Pageable pagination){
        var page = service.list(pagination);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id){
        var courseDetails = service.detail(id);
        return ResponseEntity.ok(courseDetails);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity update(@RequestBody @Valid CourseUpdateDTO data, @PathVariable Long id){
        var courseDetails = service.update(data, id);
        return ResponseEntity.ok(courseDetails);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
