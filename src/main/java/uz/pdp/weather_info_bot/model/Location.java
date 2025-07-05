package uz.pdp.weather_info_bot.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false)
    private Double lat;

    @Column(nullable = false)
    private Double lon;

    @ToString.Exclude
    @OneToMany(mappedBy = "location")
    private List<User> users;


}