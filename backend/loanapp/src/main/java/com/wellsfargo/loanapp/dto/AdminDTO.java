package com.wellsfargo.loanapp.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AdminDTO {
	
	private String username;
	private String password;
}
