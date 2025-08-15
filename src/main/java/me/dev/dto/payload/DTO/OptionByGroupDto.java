package me.dev.dto.payload.DTO;

import lombok.Getter;
import lombok.Setter;
import me.dev.entity.Option;

import java.util.List;

@Getter
@Setter
public class OptionByGroupDto {
    private Long id;

    private String name;
    private String description;
    private List<Option> options;
    // 옵션 리스트도 필요하면 DTO로 포함 가능 (생략 가능)

    // 엔티티 -> DTO 변환 생성자
    public OptionByGroupDto(Option entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDes();
        this.options = entity.getOptionByGroup().getOptions();
        // 필요하면 options도 DTO 리스트로 변환해서 넣을 수 있음
    }
}
