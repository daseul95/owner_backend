package me.dev.dto.payload.DTO;

import lombok.Getter;
import lombok.Setter;
import me.dev.entity.Option;

import java.util.List;

@Getter
@Setter
public class OptionByGroupDto {
    private Long id;
    private Long groupId;

    private Long optionId;
    // 옵션 리스트도 필요하면 DTO로 포함 가능 (생략 가능)
}
