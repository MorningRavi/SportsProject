package Registration.SportsClub.Service;

import java.util.List;

import Registration.SportsClub.Entity.ClubMaster;
import Registration.SportsClub.Entity.RegistrationMaster;
import Registration.SportsClub.Entity.SportsMaster;

public interface SportsClubService {
	
	List<ClubMaster> getClubName();

	List<SportsMaster> getSportsName(int club_name_id);

	double getTotalFees(int club_name_id, int sports_name_id);

	String submitSportsDetails(RegistrationMaster sportsClub);

	List<RegistrationMaster> getRegistrationDetails();

	List<RegistrationMaster> getRegistrationDetailsById(Integer club_name_id);

	List<RegistrationMaster> getRegistrationDetailsByClubAndSportsId(Integer club_name_id, Integer sports_name_id);

	RegistrationMaster getSportsRegistrationListById(Integer registration_id);

	String updateSportsDetails(RegistrationMaster sportsClub);

	String deleteSportsClub(RegistrationMaster sportsList);

//	String sendSimpleMail(String sender, String receipt, String msgBody, String subject);
}
