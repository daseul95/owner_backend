package me.dev.dto.payload.DTO;

import lombok.Getter;
import lombok.Setter;
import me.dev.entity.Group;
import me.dev.entity.Option;

@Getter
@Setter
public class OptionGroupDto {
    private Long groups;
    private Long option;
}
