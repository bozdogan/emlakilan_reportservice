package org.bozdgn.reportservice.dto.messaging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyMessage {
    Long propertyID;
    String author;
    LocalDate dateCreated;
    Integer viewCount;
}
