package Registration.SportsClub.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Registration.SportsClub.Entity.RegistrationMaster;
import Registration.SportsClub.Entity.SportsMaster;

@Repository
public interface SportsMasterRepository extends JpaRepository<RegistrationMaster, Integer>{

	@Query(value="select u from SportsMaster u where club_id=:club_name_id")
	List<SportsMaster> getSportsName(@Param("club_name_id") int club_name_id);

	@Query(value="select fees from SportsMaster  where club_id=:club_name_id and sports_id=:sports_name_id")
	double getTotalFees(@Param("club_name_id") int club_name_id, @Param("sports_name_id") int sports_name_id);

	@Query(value="select r.registration_id,r.applicant_name,r.email_id, r.mobile_no,r.dob,r.image_path,c.club_name,s.sports_name \r\n"
			+ "from ravi_csm.registration_details r inner join ravi_csm.club_master c on r.club_id= c.club_id inner join ravi_csm.sports_master s on r.sports_id=s.sports_id "
			+ "where r.deleted_flag='N' ",nativeQuery=true)
	List<Object[]> getDetails();

	@Query(value="select r.registration_id,r.applicant_name,r.email_id, r.mobile_no,r.dob,r.image_path,c.club_name,s.sports_name \r\n"
			+ "from ravi_csm.registration_details r inner join ravi_csm.club_master c on r.club_id= c.club_id inner join ravi_csm.sports_master s on r.sports_id=s.sports_id"
			+ " where r.club_id=:club_name_id and r.deleted_flag='N' ",nativeQuery=true)
	List<Object[]> getDetailsById(@Param("club_name_id")Integer club_name_id);

	@Query(value="select r.registration_id, r.applicant_name,r.email_id, r.mobile_no,r.dob,r.image_path,c.club_name,s.sports_name \r\n"
			+ "from ravi_csm.registration_details r inner join ravi_csm.club_master c on r.club_id= c.club_id inner join ravi_csm.sports_master s on r.sports_id=s.sports_id"
			+ " where r.club_id=:club_name_id and r.sports_id=:sports_name_id and r.deleted_flag='N' ",nativeQuery=true)
	List<Object[]> getDetailsByClubAndSportsId(@Param("club_name_id")Integer club_name_id, @Param("sports_name_id")Integer sports_name_id);

	@Query(value="select * from ravi_csm.registration_details where registration_id=:registration_id",nativeQuery = true)
	RegistrationMaster getRegistrationDetailsById(@Param("registration_id")Integer registration_id);
}
