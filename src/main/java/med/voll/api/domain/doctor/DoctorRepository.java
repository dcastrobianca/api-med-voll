package med.voll.api.domain.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;


public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findAllByActiveTrue(Pageable page);

    @Query("""
        SELECT d FROM Doctor d 
        WHERE
        d.active = TRUE 
        AND
        d.speciality = :speciality
        AND d.id NOT IN(
                SELECT a.doctor.id FROM Appointment a
                WHERE
                a.dateTime= :dataTime
        )
        ORDER BY rand()
        LIMIT 1
        """)
    Doctor getRandomDoctorFromSpecialityOnDate(Speciality speciality, LocalDateTime dataTime);

    boolean existsByIdAndActiveTrue(Long id);
}
