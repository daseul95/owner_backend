package me.dev.service;

import lombok.RequiredArgsConstructor;
import me.dev.dto.payload.DTO.OptionGroupDto;
import me.dev.entity.Group;
import me.dev.entity.OptionGroup;
import me.dev.repository.GroupRepository;
import me.dev.repository.OptionGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OptionGroupService {

    @Autowired
    private OptionGroupRepository optionGroupRepository;

    @Autowired
    private GroupRepository groupRepository;

    public OptionGroupDto createOptionCroup(OptionGroupDto dto){
        OptionGroup optionGroup = new OptionGroup();
        Group group = groupRepository.findById(dto.getGroups()).orElseThrow(()->new IllegalArgumentException("group이 없습니다."));
        optionGroup.setGroups(group);
        optionGroup.setId(dto.getGroups());

        optionGroupRepository.save(optionGroup);
        return dto;
    }


}
