package me.dev.config;

import lombok.RequiredArgsConstructor;
import me.dev.entity.MenuOption;
import me.dev.entity.OptionGroup;
import me.dev.entity.Store;
import me.dev.entity.User;
import me.dev.repository.MenuOptionRepository;
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
    private final MenuOptionRepository menuOptionRepository;
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

            // 사이즈 옵션
            menuOptionRepository.save(new MenuOption("Small", "SIZE", 0, "소 사이즈", null, sizeGroup));
            menuOptionRepository.save(new MenuOption("Medium", "SIZE", 1000, "중간 사이즈", null, sizeGroup));
            menuOptionRepository.save(new MenuOption("Large", "SIZE", 2000, "대 사이즈", null, sizeGroup));

            // 토핑 옵션
            menuOptionRepository.save(new MenuOption("치즈 추가", "TOPPING", 1000, "치즈 토핑", null, toppingGroup));
            menuOptionRepository.save(new MenuOption("베이컨 추가", "TOPPING", 1500, "베이컨 토핑", null, toppingGroup));
            menuOptionRepository.save(new MenuOption("버섯 추가", "TOPPING", 1200, "버섯 토핑", null, toppingGroup));

            // 조리 방법 옵션
            menuOptionRepository.save(new MenuOption("바삭하게 굽기", "COOKING", 0, "바삭한 식감", null, cookingGroup));
            menuOptionRepository.save(new MenuOption("부드럽게 굽기", "COOKING", 0, "부드러운 식감", null, cookingGroup));

            // 음료 옵션
            menuOptionRepository.save(new MenuOption("콜라", "DRINK", 1500, "탄산음료 콜라", null, drinkGroup));
            menuOptionRepository.save(new MenuOption("사이다", "DRINK", 1500, "탄산음료 사이다", null, drinkGroup));

            // 매운맛 단계 옵션
            menuOptionRepository.save(new MenuOption("순한맛", "SPICE", 0, "순한맛", null, spiceGroup));
            menuOptionRepository.save(new MenuOption("중간맛", "SPICE", 500, "적당히 매운맛", null, spiceGroup));
            menuOptionRepository.save(new MenuOption("아주 매운맛", "SPICE", 1000, "아주 매운맛", null, spiceGroup));

            // 빵 종류 옵션
            menuOptionRepository.save(new MenuOption("흰 빵", "BREAD", 0, "기본 흰 빵", null, breadGroup));
            menuOptionRepository.save(new MenuOption("통밀 빵", "BREAD", 500, "건강한 통밀 빵", null, breadGroup));
            menuOptionRepository.save(new MenuOption("치아바타 빵", "BREAD", 800, "이탈리아 치아바타 빵", null, breadGroup));
    }
    }
}