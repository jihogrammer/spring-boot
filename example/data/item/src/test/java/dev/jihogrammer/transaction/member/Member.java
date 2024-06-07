package dev.jihogrammer.transaction.member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

   Member(String name) {
       this.name = name;
   }

}
