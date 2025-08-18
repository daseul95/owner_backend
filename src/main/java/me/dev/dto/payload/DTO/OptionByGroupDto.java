package me.dev.dto.payload.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.dev.entity.Group;
import me.dev.entity.Option;
import me.dev.entity.OptionByGroup;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OptionByGroupDto {
    private Long id;
    private Long groupId;

    private List<Long> optionId;
    // 옵션 리스트도 필요하면 DTO로 포함 가능 (생략 가능)

    // 엔티티 -> DTO 변환 생성자
    public OptionByGroupDto(OptionByGroup entity) {
        this.id = entity.getId();
        this.groupId = entity.getGroup().getId();
        this.optionId = List.of(entity.getOption().getId());
        // 필요하면 options도 DTO 리스트로 변환해서 넣을 수 있음
    }
}
