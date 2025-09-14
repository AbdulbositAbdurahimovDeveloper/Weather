package uz.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.weather.model.UserProfile;

@Repository
public interface userProfileRepository extends JpaRepository<UserProfile, Long> {
}