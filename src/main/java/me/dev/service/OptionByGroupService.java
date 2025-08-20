package me.dev.service;

import lombok.RequiredArgsConstructor;
import me.dev.dto.payload.DTO.OptionByGroupDto;
import me.dev.entity.Group;
import me.dev.entity.Option;
import me.dev.entity.OptionByGroup;
import me.dev.repository.GroupRepository;
import me.dev.repository.OptionByGroupRepository;
import me.dev.repository.OptionRepository;
import org.aspectj.weaver.loadtime.Options;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OptionByGroupService {

    private final GroupRepository groupRepository;
    private final OptionRepository optionRepository;
    private final OptionByGroupRepository ogRepository;

    public ResponseEntity<?> createOptionByGroup(OptionByGroupDto ogdto) {
        Group group = groupRepository.findById(ogdto.getGroupId())
                .orElseThrow(() -> new RuntimeException("Group not found"));

        OptionByGroup og = new OptionByGroup();
        og.setGroup(group);

        List<Option> options = optionRepository.findAllByIdIn(ogdto.getOptionId());
        List<OptionByGroup> optionByGroups = options.stream()
                .map(option -> {
                    OptionByGroup obg = new OptionByGroup();
                    obg.setGroup(group);
                    obg.setOption(option);
                    return obg;
                })
                .toList();

        ogRepository.saveAll(optionByGroups);
        
        return ResponseEntity.ok("성공적으로 그룹이 완성되었습니다");
    }

    public OptionByGroupDto getOptionByGroup(Long id){
        OptionByGroup og = ogRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("그룹을 찾을수 없습니다"));

        List<OptionByGroup> liOg= ogRepository.findByGroupId(id);
        List<Long> liLong = liOg.stream()
                .map(OptionByGroup::getOption)
                .map(Option::getId)
                .toList();
        OptionByGroupDto dto = new OptionByGroupDto();
        dto.setId(og.getId());
        dto.setGroupId(id);
        dto.setOptionId(liLong);
        return dto;
    }

    public String patchOptionByGroup(Long id,OptionByGroupDto dto){

        Group oldGroup = groupRepository.findById(id).orElseThrow(()->new IllegalArgumentException("그룹을 찾을수 없습니다."));

        if(dto.getGroupId()==null){
            System.out.println("새로운 그룹 아이디가 null입니다.");
        }
        Group newGroup = groupRepository.findById(dto.getGroupId()).orElseThrow(()->new IllegalArgumentException("업데이트할 그룹을 찾을 수 없습니다"));


        int updated = ogRepository.updateGroup(oldGroup,newGroup);
        String word = "새롭게 그룹이 저장되었습니다 \n,영향받은 row 수:" +updated;
        return word;
    }

    public String deleteOptionByGroup(Long id){
        Group group = groupRepository.findById(id).orElseThrow(()->new IllegalArgumentException("그룹을 찾을 수 없습니다."));

        int delete = ogRepository.deleteByGroup(group);

        String word = "그룹이 삭제 되었습니다 \n,영향받은 row 수:" +delete;

        return word;
    }
}