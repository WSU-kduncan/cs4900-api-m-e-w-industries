package com.MEW.demo.dto;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder
@Data
@Value
public class ConsoleDto {
    
    private Integer consoleId;
    private String consoleName;
}
