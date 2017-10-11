package hu.elte.alkfej.entity;

import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @OneToMany(targetEntity = Issue.class, mappedBy = "user")
    private List<Issue> issues;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(nullable=false, unique=true)
    private String username;
    
    @Column(nullable=false)
    private String password;
    
    @Column(nullable=false, unique=true)
    private String email;
    
    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private Role role;
    
    public enum Role {
        QUEST, USER, ADMIN
    }
    
}
