package com.bootcamp.bankdeposit.util;

import com.bootcamp.bankdeposit.bean.Deposit;
import com.bootcamp.bankdeposit.dto.DepositDTO;
import org.springframework.beans.BeanUtils;

public class AppUtils {

    public static DepositDTO entityToDto(Deposit deposit){
        DepositDTO accDto=new DepositDTO();
        BeanUtils.copyProperties(deposit,accDto);
        return accDto;
    }

    public static Deposit dtoToEntity(DepositDTO accDto){
        Deposit deposit=new Deposit();
        BeanUtils.copyProperties(accDto,deposit);
        return deposit;
    }
}
