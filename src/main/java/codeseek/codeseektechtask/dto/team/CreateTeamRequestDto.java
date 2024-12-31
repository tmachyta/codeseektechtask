package codeseek.codeseektechtask.dto.team;

import java.time.LocalDate;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateTeamRequestDto {
    private String name;
    private int balance;
    private int transferCommission;
    private LocalDate createdDate;
}
