package me.dev.dto.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
	@NotBlank
  private String email;

	@NotBlank
	private String password;

	public String getEmail() {
		return email;
	}

	public void setUsername(String email) {
		this.email = email;
	}
}
