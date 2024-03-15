package dev.jihogrammer.spring.basic.thymeleaf.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonDeserialize(builder = UserData.UserDataBuilder.class)
public class UserData {

    private final String name;

    private final Integer age;

}
