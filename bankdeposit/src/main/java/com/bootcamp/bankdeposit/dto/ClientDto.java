package com.bootcamp.bankdeposit.dto;

import com.bootcamp.bankdeposit.bean.ClientType;
import lombok.*;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDto {

    private String id;
    private String name;

    private String clientNumber;

    private String email;
    private String phone;
    private String address;

    private ClientType clientType;
}
