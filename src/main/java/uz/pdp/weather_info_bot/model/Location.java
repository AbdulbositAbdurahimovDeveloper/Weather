package uz.pdp.weather_info_bot.model;

import jakarta.persistence.*;
import lombok.*;

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

  private Double lat;

  private Double lon;

  @ToString.Exclude
  @ManyToMany(mappedBy = "locations")
  private Set<User> users;



}