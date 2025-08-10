package me.dev.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import me.dev.dto.payload.DTO.OptionGroupDto;
import me.dev.dto.payload.request.CreateMenuOptionDto;
import me.dev.dto.payload.response.MenuOptionResponseDto;
import me.dev.entity.MenuOption;
import me.dev.entity.OptionGroup;
import me.dev.repository.MenuOptionRepository;
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

