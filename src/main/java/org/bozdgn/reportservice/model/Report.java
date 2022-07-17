package org.bozdgn.reportservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "property")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "report_seq")
    @SequenceGenerator(name = "report_seq", sequenceName = "report_seq", allocationSize = 1)
    @Column(name = "property_id")
    Long propertyId;

    @Column(name="report_body", columnDefinition = "TEXT")
    String reportBody;

    @Column(name="last_updated")
    LocalDate lastUpdated;
}