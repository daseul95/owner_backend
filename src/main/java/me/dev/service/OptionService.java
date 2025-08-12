package me.dev.service;

import jakarta.persistence.EntityNotFoundException;
import me.dev.dto.payload.request.CreateOptionDto;
import me.dev.dto.payload.request.OptionRequestDto;
import me.dev.dto.payload.response.OptionResponseDto;
import me.dev.entity.Option;
import me.dev.entity.Store;
import me.dev.repository.MenuGroupRepository;
import me.dev.repository.OptionRepository;
import me.dev.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class OptionService {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private MenuGroupRepository menuGroupRepository;
    @Autowired
    private OptionRepository optionRepository;


    // 옵션 추가
    public Option createOption(CreateOptionDto dto) {

        Option option = new Option();
        option.setName(dto.getName());
        option.setCategory(dto.getCategory());
        option.setOptionPrice(dto.getOptionPrice());
        option.setDes(dto.getDes());
        option.setImgUrl(dto.getImgUrl());
        Option savedOption = optionRepository.save(option);

        return savedOption;
    }

    public List<OptionResponseDto> getAllOptions() {
        return optionRepository.findAll().stream()
                .map(OptionResponseDto::new) // 엔티티 → DTO 변환
                .collect(Collectors.toList());
    }

    public OptionResponseDto getOptionById(Long id){

             Option option = optionRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Option not found with id: " + id));;
            return new OptionResponseDto(option);
    }

    @Transactional
    public void updateOption(Long id, OptionRequestDto dto) {
        Option option = optionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("option id가 존재하지 않습니다."));

        if (dto.getName() != null) option.setName(dto.getName());
        if (dto.getCategory() != null) option.setCategory(dto.getCategory());
        if (dto.getDes() != null) option.setDes(dto.getDes());
        if (dto.getOptionPrice() != 0) option.setOptionPrice(dto.getOptionPrice());
        if (dto.getImgUrl() != null) option.setImgUrl(dto.getImgUrl());
    }

    public void deleteOption(Long id) {
        optionRepository.deleteById(id);
    }

}

