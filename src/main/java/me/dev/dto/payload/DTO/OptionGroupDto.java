package me.dev.dto.payload.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.dev.entity.OptionGroup;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OptionGroupDto {
    private Long id;
    private String name;
    private String description;
    // 옵션 리스트도 필요하면 DTO로 포함 가능 (생략 가능)

    // 엔티티 -> DTO 변환 생성자
    public OptionGroupDto(OptionGroup entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        // 필요하면 options도 DTO 리스트로 변환해서 넣을 수 있음
    }

    // getter, setter 생략
}