package org.bozdgn.reportservice.dto;


import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportOutput {
    Long propertyID;

    @JsonAlias("report_body")
    String reportBody;
    LocalDate lastUpdated;
}