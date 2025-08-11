package me.dev.config;

import lombok.RequiredArgsConstructor;
import me.dev.entity.OptionGroup;
import me.dev.repository.OptionRepository;
import me.dev.repository.OptionGroupRepository;
import me.dev.repository.StoreRepository;
import me.dev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final OptionGroupRepository optionGroupRepository;
    private final OptionRepository optionRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (optionGroupRepository.count() == 0) {
            OptionGroup sizeGroup = new OptionGroup();
            sizeGroup.setName("사이즈");
            sizeGroup.setDescription("음식 크기 옵션");
            optionGroupRepository.save(sizeGroup);

            OptionGroup toppingGroup = new OptionGroup();
            toppingGroup.setName("토핑");
            toppingGroup.setDescription("추가 재료 옵션");
            optionGroupRepository.save(toppingGroup);

            OptionGroup cookingGroup = new OptionGroup();
            cookingGroup.setName("조리 방법");
            cookingGroup.setDescription("조리 방법 옵션");
            optionGroupRepository.save(cookingGroup);

            OptionGroup drinkGroup = new OptionGroup();
            drinkGroup.setName("음료 옵션");
            drinkGroup.setDescription("음료 옵션");
            optionGroupRepository.save(drinkGroup);

            OptionGroup spiceGroup = new OptionGroup();
            spiceGroup.setName("매운맛 단계");
            spiceGroup.setDescription("매운맛 단계 옵션");
            optionGroupRepository.save(spiceGroup);

            OptionGroup breadGroup = new OptionGroup();
            breadGroup.setName("빵 종류");
            breadGroup.setDescription("빵 종류 옵션");
            optionGroupRepository.save(breadGroup);


        }
    }
}