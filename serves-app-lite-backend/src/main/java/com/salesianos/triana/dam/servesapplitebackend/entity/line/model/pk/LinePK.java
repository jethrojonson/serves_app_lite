package com.salesianos.triana.dam.servesapplitebackend.entity.line.model.pk;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianos.triana.dam.servesapplitebackend.entity.order.view.OrderViews;
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
    private Long product_id;

}
