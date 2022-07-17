package org.bozdgn.reportservice.service;

import org.bozdgn.reportservice.dto.ReportOutput;
import org.bozdgn.reportservice.dto.UpdateOutput;
import org.bozdgn.reportservice.dto.messaging.UpdateRequestMessage;
import org.bozdgn.reportservice.messaging.MessageSender;
import org.bozdgn.reportservice.model.Report;
import org.bozdgn.reportservice.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private static final String REPORT_URL_TEMPLATE = "/api/report/%s";
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

    public UpdateOutput updateReport(Long propertyID) {
        messageSender.sendPropertyUpdateRequest(new UpdateRequestMessage(propertyID));
        return new UpdateOutput(
                "Report update request saved.",
                String.format(REPORT_URL_TEMPLATE, propertyID));
    }

}
