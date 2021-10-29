package com.mayankmadhav.demo.app.mobileappws.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "privileges")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Privileges {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long createDate;

    private long updateDate;

    @Column
    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;
}
