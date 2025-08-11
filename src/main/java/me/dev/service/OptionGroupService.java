package me.dev.service;

import me.dev.dto.payload.DTO.OptionGroupDto;
import me.dev.entity.OptionGroup;
import me.dev.repository.OptionGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptionGroupService {


    @Autowired
    private  OptionGroupRepository optionGroupRepository;

    public OptionGroup createOptionGroup(OptionGroupDto dto) {
        OptionGroup group = OptionGroup.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();

        return optionGroupRepository.save(group);
    }
}

