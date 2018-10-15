package org.apache.payment.gateway.dto.beyonic.request;

import lombok.*;
import org.apache.payment.gateway.dto.beyonic.MetaData;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WebHook {

    private String phoneNumber;

    private String payment_type;

    private double amount;

    private String currency;

    private long account;

    private String description;

    private String callback_url;

    private MetaData metadata;

    private String firstName;

    private String lastName;

}
