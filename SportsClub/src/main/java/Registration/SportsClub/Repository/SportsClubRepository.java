package Registration.SportsClub.Repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Registration.SportsClub.Entity.ClubMaster;
import Registration.SportsClub.Entity.SportsMaster;

@Repository
public interface SportsClubRepository extends JpaRepository<ClubMaster, Integer> {
	
	@Query(value="select u from ClubMaster u")
	List<ClubMaster> getClubName();

}
