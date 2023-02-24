package com.salesianos.triana.dam.servesapplitebackend.entity.line.model.pk;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LinePK implements Serializable {

    private Long order_id;
    private Long item_id;

}
