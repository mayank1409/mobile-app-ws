package com.mayankmadhav.demo.app.mobileappws.models;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "encPassword")
@Builder
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date updatedDate;

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
