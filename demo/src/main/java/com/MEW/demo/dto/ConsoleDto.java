package com.MEW.demo.dto;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import com.MEW.demo.model.Console;

@Builder
@Data
@Value
public class ConsoleDto {
    
    private Integer consoleId;
    private String consoleName;

    public static ConsoleDto fromEntity(Console console) {
        
        if(console == null) return null;
        
        return ConsoleDto.builder()
            .consoleId(console.getConsoleId())
            .consoleName(console.getConsoleName())
            .build();
    }

    public Console toEntity() {
        
        return Console.builder()
            .consoleId(this.consoleId)
            .consoleName(this.consoleName)
            .build();
    }
}
