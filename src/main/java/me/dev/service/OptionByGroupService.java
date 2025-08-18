package me.dev.service;

import lombok.RequiredArgsConstructor;
import me.dev.dto.payload.DTO.OptionByGroupDto;
import me.dev.entity.Group;
import me.dev.entity.Option;
import me.dev.entity.OptionByGroup;
import me.dev.repository.GroupRepository;
import me.dev.repository.OptionByGroupRepository;
import me.dev.repository.OptionRepository;
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
}