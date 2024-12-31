package codeseek.codeseektechtask.dto.player;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreatePlayerRequestDto {
    private String name;
    private String surname;
    private int age;
    private int experience;
    private Long teamId;
}
