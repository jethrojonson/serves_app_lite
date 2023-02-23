package com.salesianos.triana.dam.servesapplitebackend.entity.item.model.pk;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ItemPK implements Serializable {

    private UUID company_id;
    private Long product_id;

}
