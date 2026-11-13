package com.swapnil.tracker;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
    private final ApplicationRepository repo;

    public ApplicationController(ApplicationRepository repo) { this.repo = repo; }

    @GetMapping
    public List<InternshipApplication> list(@RequestParam(required = false) Stage stage) {
        return stage == null ? repo.findAll() : repo.findByStage(stage);
    }

    @GetMapping("/upcoming")
    public List<InternshipApplication> upcoming(@RequestParam(defaultValue = "14") int days) {
        LocalDate today = LocalDate.now();
        return repo.findByDeadlineBetweenOrderByDeadline(today, today.plusDays(days));
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public InternshipApplication create(@Valid @RequestBody InternshipApplication a) { return repo.save(a); }

    @PutMapping("/{id}")
    public ResponseEntity<InternshipApplication> update(@PathVariable Long id, @Valid @RequestBody InternshipApplication in) {
        return repo.findById(id).map(a -> {
            a.setCompany(in.getCompany()); a.setRole(in.getRole()); a.setStage(in.getStage());
            a.setDeadline(in.getDeadline()); a.setNotes(in.getNotes());
            return ResponseEntity.ok(repo.save(a));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
