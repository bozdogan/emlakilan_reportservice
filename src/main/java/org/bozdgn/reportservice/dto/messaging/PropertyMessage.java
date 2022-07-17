package org.bozdgn.reportservice.dto.messaging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyMessage implements Serializable {
    Long propertyId;
    String author;
    LocalDate dateCreated;
    Integer viewCount;
}
