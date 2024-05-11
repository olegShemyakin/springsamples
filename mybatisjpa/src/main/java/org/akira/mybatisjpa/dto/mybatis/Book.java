package org.akira.mybatisjpa.dto.mybatis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class Book {

    private UUID id;
    private String name;
    private Integer year;
}
