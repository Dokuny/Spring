package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")  // 판별자 값, 기본으로 두면 클래스 명으로 들어간다.
@Getter @Setter
public class Album extends Item{
    private String artist;

    private String etc;
}
