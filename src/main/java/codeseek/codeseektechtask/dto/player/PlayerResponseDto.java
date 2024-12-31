package codeseek.codeseektechtask.dto.player;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PlayerResponseDto {
    private Long id;
    private String name;
    private String surname;
    private int age;
    private int experience;
    private int transferValue;
    private Long teamId;
}
