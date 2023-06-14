package br.com.hub.correio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AdressStatus {
    public static final int DEFAULT_ID = 1;

    @Id
    private Integer id;
    private Status status;

}
