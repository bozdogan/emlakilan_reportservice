package org.bozdgn.reportservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "property")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @Column(name = "property_id")
    Long propertyId;

    @Column(name="report_body", columnDefinition = "TEXT")
    String reportBody;

    @Column(name="last_updated")
    LocalDate lastUpdated;
}