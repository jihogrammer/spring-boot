package dev.jihogrammer.transaction.member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
class Log {

    @Id
    @GeneratedValue
    private Long id;

    private String message;

    Log(String message) {
        this.message = message;
    }

}
