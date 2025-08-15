//package me.dev.config;
//
//import lombok.RequiredArgsConstructor;
//import me.dev.entity.OptionByGroup;
//import me.dev.repository.OptionRepository;
//import me.dev.repository.GroupRepository;
//import me.dev.repository.StoreRepository;
//import me.dev.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class DataInitializer implements CommandLineRunner {
//
//    private final GroupRepository groupRepository;
//    private final OptionRepository optionRepository;
//    private final UserRepository userRepository;
//    private final StoreRepository storeRepository;
//
//
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
//
//    @Override
//    public void run(String... args) {
//        if (groupRepository.count() == 0) {
//        }
//    }
//}