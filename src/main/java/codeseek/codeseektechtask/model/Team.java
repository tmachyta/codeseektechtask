package codeseek.codeseektechtask.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Data
@SQLDelete(sql = "UPDATE teams SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false, unique = true)
    private int balance;
    @Column(name = "transfer_commission")
    private int transferCommission;
    @Column(name = "created_date")
    private LocalDate createdDate;
    @Column(name = "is_deleted")
    private boolean isDeleted;
}
