package com.cognizant.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ProjectManager")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectManager {

   


    @Id
    @Column(name="userid", length=20)
    private String userId;

    @Column(name="upassword", length=20)
    private String password;

    @Column(name="authtoken")
    private String authToken;


    


}
