package org.bozdgn.reportservice.repository;

import org.bozdgn.reportservice.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
