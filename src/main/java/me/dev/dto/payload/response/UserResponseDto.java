package me.dev.dto.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private String userId;
    private String email;
    private String name;
    private String nickname;
}
