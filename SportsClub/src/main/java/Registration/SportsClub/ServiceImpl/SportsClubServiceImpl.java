package Registration.SportsClub.ServiceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Registration.SportsClub.Entity.ClubMaster;
import Registration.SportsClub.Entity.RegistrationMaster;
import Registration.SportsClub.Entity.SportsMaster;
import Registration.SportsClub.Repository.SportsClubRepository;
import Registration.SportsClub.Repository.SportsMasterRepository;
import Registration.SportsClub.Service.SportsClubService;

@Service
public class SportsClubServiceImpl implements SportsClubService{

//	@Autowired private JavaMailSender javaMailSender;
	
	@Autowired
	SportsClubRepository sportsClubRepository;
	
	@Autowired
	SportsMasterRepository sportsMasterRepository;
	
	@Override
	public List<ClubMaster> getClubName() {
		List<ClubMaster> clubName=new ArrayList<ClubMaster>();
		try {
		    clubName=sportsClubRepository.getClubName();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return clubName;
	}

	@Override
	public List<SportsMaster> getSportsName(int club_name_id) {
		List<SportsMaster> sportsName=new ArrayList<SportsMaster>();
		try {
			sportsName=sportsMasterRepository.getSportsName(club_name_id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return sportsName;
	}

	@Override
	public double getTotalFees(int club_name_id, int sports_name_id) {
		double totalFees=0;
		try {
			totalFees=sportsMasterRepository.getTotalFees(club_name_id,sports_name_id);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return totalFees;
	}

	@Override
	public String submitSportsDetails(RegistrationMaster sportsClub) {
		String ret="failure";
		try {
			sportsClub.setDeleted_flag("N");
			sportsMasterRepository.save(sportsClub);
			ret="success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public List<RegistrationMaster> getRegistrationDetails() {
		List<RegistrationMaster> sportsList=new ArrayList<RegistrationMaster>();
		RegistrationMaster data=null;
		try {
			List<Object[]> sportsVo=sportsMasterRepository.getDetails();
			for(Object[] obj:sportsVo) {
				data=new RegistrationMaster();
				data.setRegistration_id((Integer) obj[0]);
				data.setApplicant_name((String)obj[1]);
				data.setEmail_id((String)obj[2]);
				data.setMobile_no((String)obj[3]);
				
				Calendar dbDate=Calendar.getInstance();
				dbDate.setTime((Date) obj[4]);
				int year=dbDate.get(Calendar.YEAR);
				
				Calendar currDate=Calendar.getInstance();
				currDate.setTime(new Date());
				int currYear=currDate.get(Calendar.YEAR);
				
				int age=currYear-year;
				
				data.setAge(age);
				data.setImage_path((String) obj[5]);
				data.setClub_name((String) obj[6]);
				data.setSports_name((String) obj[7]);
				sportsList.add(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sportsList;
	}

	@Override
	public List<RegistrationMaster> getRegistrationDetailsById(Integer club_name_id) {
		List<RegistrationMaster> sportsList=new ArrayList<RegistrationMaster>();
		RegistrationMaster data=null;
		try {
			List<Object[]> sportsVo=sportsMasterRepository.getDetailsById(club_name_id);
			for(Object[] obj:sportsVo) {
				data=new RegistrationMaster();
				data.setRegistration_id((int) obj[0]);
				data.setApplicant_name((String)obj[1]);
				data.setEmail_id((String)obj[2]);
				data.setMobile_no((String)obj[3]);
				
				Calendar dbDate=Calendar.getInstance();
				dbDate.setTime((Date) obj[4]);
				int year=dbDate.get(Calendar.YEAR);
				
				Calendar currDate=Calendar.getInstance();
				currDate.setTime(new Date());
				int currYear=currDate.get(Calendar.YEAR);
				
				int age=currYear-year;
				
				data.setAge(age);
				data.setImage_path((String) obj[5]);
				data.setClub_name((String) obj[6]);
				data.setSports_name((String) obj[7]);
				sportsList.add(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sportsList;
	}

	@Override
	public List<RegistrationMaster> getRegistrationDetailsByClubAndSportsId(Integer club_name_id,
			Integer sports_name_id) {
		List<RegistrationMaster> sportsList=new ArrayList<RegistrationMaster>();
		RegistrationMaster data=null;
		try {
			List<Object[]> sportsVo=sportsMasterRepository.getDetailsByClubAndSportsId(club_name_id,sports_name_id);
			for(Object[] obj:sportsVo) {
				data=new RegistrationMaster();
				data.setRegistration_id((int) obj[0]);
				data.setApplicant_name((String)obj[1]);
				data.setEmail_id((String)obj[2]);
				data.setMobile_no((String)obj[3]);
				
				Calendar dbDate=Calendar.getInstance();
				dbDate.setTime((Date) obj[4]);
				int year=dbDate.get(Calendar.YEAR);
				
				Calendar currDate=Calendar.getInstance();
				currDate.setTime(new Date());
				int currYear=currDate.get(Calendar.YEAR);
				
				int age=currYear-year;
				
				data.setAge(age);
				data.setImage_path((String) obj[5]);
				data.setClub_name((String) obj[6]);
				data.setSports_name((String) obj[7]);
				sportsList.add(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sportsList;
	}

	@Override
	public RegistrationMaster getSportsRegistrationListById(Integer registration_id) {
		RegistrationMaster sportsList=new RegistrationMaster();
		try {
			sportsList=sportsMasterRepository.getRegistrationDetailsById(registration_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sportsList;
	}

	@Override
	public String updateSportsDetails(RegistrationMaster sportsClub) {
		String ret="";
		try {
			sportsClub.setDeleted_flag("N");
			sportsMasterRepository.save(sportsClub);
			ret="success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public String deleteSportsClub(RegistrationMaster sportsList) {
		String ret="";
		try {
			sportsMasterRepository.save(sportsList);
			ret="success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

//	@Override
//	public String sendSimpleMail(String sender, String receipt, String msgBody, String subject) {
//		try {
//			SimpleMailMessage mailMessage=new SimpleMailMessage();
//			mailMessage.setFrom(sender);
//			mailMessage.setTo(receipt);
//			mailMessage.setText(msgBody);
//			mailMessage.setSubject(subject);
//			
//			javaMailSender.send(mailMessage);
//			
//			return "Mail Sent Successfully...";
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "Error while Sending Mail";
//		}
//		
//	}

}
