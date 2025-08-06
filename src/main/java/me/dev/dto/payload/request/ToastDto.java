package me.dev.dto.payload.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ToastDto {
    private Long id;
    private String toastName;
    private String description;
    private String imgUrl;

    // 생성자, Getter, Setter 등
}
