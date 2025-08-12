package me.dev.service;

import me.dev.dto.payload.DTO.GroupDto;
import me.dev.entity.Group;
import me.dev.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {


    @Autowired
    private GroupRepository groupRepository;

    public Group createGroup(GroupDto dto) {
        Group group = Group.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();

        return groupRepository.save(group);
    }
}

