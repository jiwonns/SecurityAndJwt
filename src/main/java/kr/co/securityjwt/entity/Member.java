package kr.co.securityjwt.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Member")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    @Column(name="member_id")
    private Long id;

    @Column(name="email", length = 50, updatable = false, unique = true)
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="image_path")
    private String imagePath;

    @Enumerated(EnumType.STRING)
    @Column(name="Role", nullable = false)
    private RoleType role;

    public void updateName(String name){
        this.name = name;
    }

    public void updateImagePath(String imagePath){
        this.imagePath = imagePath;
    }

}
