package com.swapnil.tracker;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<InternshipApplication, Long> {
    List<InternshipApplication> findByStage(Stage stage);
    List<InternshipApplication> findByDeadlineBetweenOrderByDeadline(LocalDate from, LocalDate to);
}
