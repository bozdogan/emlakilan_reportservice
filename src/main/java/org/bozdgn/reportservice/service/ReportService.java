package org.bozdgn.reportservice.service;

import org.bozdgn.reportservice.dto.ReportOutput;
import org.bozdgn.reportservice.dto.UpdateOutput;
import org.bozdgn.reportservice.dto.messaging.PropertyMessage;
import org.bozdgn.reportservice.dto.messaging.UpdateRequestMessage;
import org.bozdgn.reportservice.messaging.MessageSender;
import org.bozdgn.reportservice.model.Report;
import org.bozdgn.reportservice.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class ReportService {

    private static final String REPORT_URL_TEMPLATE = "/api/report/%s";
    private static final String REPORT_BODY_TEMPLATE = "The advertisement #%s was created by %s %s days ago. It has been viewed %s times.";
    private final ReportRepository repository;
    private final MessageSender messageSender;

    public ReportService(ReportRepository repository, MessageSender messageSender) {
        this.repository = repository;
        this.messageSender = messageSender;
    }


    public List<ReportOutput> getAll() {
        return repository.findAll().stream()
                .map(it -> new ReportOutput(it.getPropertyId(), it.getReportBody(), it.getLastUpdated()))
                .collect(Collectors.toList());
    }

    public ReportOutput get(Long propertyID) {
        Optional<Report> optReport = repository.findById(propertyID);
        if(optReport.isPresent()) {
            Report report = optReport.get();
            return new ReportOutput(
                    report.getPropertyId(),
                    report.getReportBody(),
                    report.getLastUpdated());
        } else {
            return new ReportOutput();
        }
    }

    public UpdateOutput triggerReportUpdate(Long propertyID) {
        messageSender.sendPropertyUpdateRequest(new UpdateRequestMessage(propertyID));
        return new UpdateOutput(
                "Report update request saved.",
                String.format(REPORT_URL_TEMPLATE, propertyID));
    }

    public ReportOutput save(PropertyMessage propertyInfo) {
        Report report = repository.findById(propertyInfo.getPropertyID()).orElse(new Report());
        long elapsedDays = DAYS.between(propertyInfo.getDateCreated().atStartOfDay(), LocalDateTime.now());

        report.setPropertyId(propertyInfo.getPropertyID());
        report.setReportBody(String.format(REPORT_BODY_TEMPLATE,
                propertyInfo.getPropertyID(),
                propertyInfo.getAuthor(),
                elapsedDays,
                propertyInfo.getViewCount()));
        report.setLastUpdated(LocalDate.now());

        Report savedReport = repository.save(report);
        return new ReportOutput(
                savedReport.getPropertyId(),
                savedReport.getReportBody(),
                savedReport.getLastUpdated());
    }

}
