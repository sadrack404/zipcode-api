package br.com.hub.correio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Adress {

    @Id
    private String zipcode;
    private String street;
    private String district;
    private String city;
    private String state;

}
