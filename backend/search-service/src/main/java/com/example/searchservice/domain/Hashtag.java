package com.example.searchservice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hashtags",
        indexes = @Index(unique = true, name = "hashtag_name_idx", columnList = "name")
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false)
    private Long count = 0L;

    public Hashtag(String name) {
        this.name = name;
    }

    public void increaseCount() {
        this.count++;
    }

    public void decreaseCount() {
        this.count--;
    }
}
