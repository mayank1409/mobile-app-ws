package com.mayankmadhav.demo.app.mobileappws.models;

import com.mayankmadhav.demo.app.mobileappws.constants.enums.Roles;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "privileges")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long createDate;

    private long updateDate;

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private Roles name;

    @OneToMany(mappedBy = "role")
    private List<Privileges> privileges;

}