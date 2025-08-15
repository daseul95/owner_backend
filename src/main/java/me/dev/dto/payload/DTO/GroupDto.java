package me.dev.dto.payload.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.dev.entity.Group;
import me.dev.entity.Option;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {
    private Long id;

    private String name;
    private String des;
    // 옵션 리스트도 필요하면 DTO로 포함 가능 (생략 가능)

    private List<OptionByGroupDto> optionByGroupDto;

    // 엔티티 -> DTO 변환 생성자
    public GroupDto(Group entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.des = entity.getDes();
        // 필요하면 options도 DTO 리스트로 변환해서 넣을 수 있음
    }

}