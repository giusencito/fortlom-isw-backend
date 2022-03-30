package com.example.fortlomisw.backend.domain.model.entity;


import lombok.*;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 30)
    @Column(unique = true)
    private String username;


    @NotNull
    @NotBlank
    @Size(max = 30)
    private String RealName;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String LastName;

    @NotNull
    @NotBlank
    @Size(max = 100)
    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String password;

    @Lob
    private byte[] content;

    @OneToMany(targetEntity = Forum.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "personid",referencedColumnName = "id")
    private List<Forum> forums;

    @OneToMany(targetEntity = Comment.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "personid",referencedColumnName = "id")
    private List<Comment> comments;


    @OneToMany(targetEntity = Report.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "userMainid",referencedColumnName = "id")
    private List<Report> reportmains;

    @OneToMany(targetEntity = Report.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "userReportedid",referencedColumnName = "id")
    private List<Report> reporttouser;




}
