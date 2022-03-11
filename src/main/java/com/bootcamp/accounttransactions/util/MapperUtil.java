package com.bootcamp.accounttransactions.util;

import com.bootcamp.accounttransactions.dto.MovementDto;
import com.bootcamp.accounttransactions.entity.MovementRecord;
import org.springframework.beans.BeanUtils;

public abstract class MapperUtil {

    public MovementDto convertToDto(MovementRecord movementRecord) {
        MovementDto movementDto = new MovementDto();
        BeanUtils.copyProperties(movementRecord, movementDto);

        return movementDto;
    }

    public MovementRecord convertToEntity(MovementDto movementDto) {
        MovementRecord movementRecord = new MovementRecord();
        BeanUtils.copyProperties(movementDto, movementRecord);

        return movementRecord;
    }


}
