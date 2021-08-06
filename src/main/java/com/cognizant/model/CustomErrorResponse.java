package com.cognizant.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomErrorResponse {

	private LocalDateTime timestamp;

    private HttpStatus status;

    private String reason;

    private String message;

}
