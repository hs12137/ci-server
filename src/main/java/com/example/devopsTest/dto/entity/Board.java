package com.example.devopsTest.dto.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "BOARD_ID")
    Long id;
    @Column(name = "BOARD_USER_NAME", nullable = false)
    String userName;
    @Column(name = "BOARD_CONTENT", length = 1000, nullable = false) @Setter
    String content;
}
