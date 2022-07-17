package org.bozdgn.reportservice.controller;

import org.bozdgn.reportservice.dto.ReportOutput;
import org.bozdgn.reportservice.dto.UpdateOutput;
import org.bozdgn.reportservice.service.ReportService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/report")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }


    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @GetMapping("/{id}")
    public ReportOutput get(@PathVariable("id") Long propertyID) {
        return reportService.get(propertyID);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @PostMapping("/{id}")
    public UpdateOutput updateReport(@PathVariable("id") Long propertyID) {
        return reportService.updateReport(propertyID);
    }

}
