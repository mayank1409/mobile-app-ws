package com.mayankmadhav.demo.app.mobileappws.models;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "encPassword")
@Builder
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long createDate;

    private long updateDate;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    private String encPassword;

    private String mobile;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Embedded
    private UserProfile userProfile;


}
