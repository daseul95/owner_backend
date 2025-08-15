package me.dev.service;

import jakarta.persistence.EntityNotFoundException;
import me.dev.dto.payload.DTO.GroupDto;
import me.dev.entity.Group;
import me.dev.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {


    @Autowired
    private GroupRepository groupRepository;


    /*
     GroupDto{
      String name;
      String des;
      }
     */
    public Group createGroup(GroupDto dto) {
        Group group = Group.builder()
                .name(dto.getName())
                .des(dto.getDes())
                .build();

        return groupRepository.save(group);
    }

    public List<GroupDto> getAllGroups(){
        return groupRepository.findAll().stream()
                .map(GroupDto::new)
                .collect(Collectors.toList());

    }

    public GroupDto getGroupById(Long id){
       Group group = groupRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Group not found with id:" + id ));
       return new GroupDto(group);
    }


    /*
     GroupDto{
      String name;
      String des;
      }
     */
    @Transactional
    public Group updateGroup(Long id,GroupDto dto) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new IllegalStateException("group id가 존재하지 않습니다."));

        group.setName(dto.getName());
        group.setDes(dto.getDes());

        return group;
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }
}

