package Registration.SportsClub.Controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Registration.SportsClub.Entity.ClubMaster;
import Registration.SportsClub.Entity.RegistrationMaster;
import Registration.SportsClub.Entity.SportsMaster;
import Registration.SportsClub.Service.SportsClubService;

@Controller
public class SportsClubController {
	
	private static String  UPLOADED_FOLDER = "static/img";
	 
//    @Value("${spring.mail.username}") 
//    private String sender;
    
	
	@Autowired
	SportsClubService sportsClubService;
	
	@RequestMapping(value="/addSportsClub")
	public String showSportsClub(Model model) {
		List<ClubMaster> clubName=new ArrayList<ClubMaster>();
		try {
			clubName=sportsClubService.getClubName();
			model.addAttribute("clubName", clubName);	
			model.addAttribute("SportsClub", new RegistrationMaster());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return "sportsClub";
	}
	
	@PostMapping(value="/getSportsName")
	@ResponseBody
	public List<SportsMaster> getSportsName(@RequestParam(value="club_name_id",required=true)Integer club_name_id,Model model) {
		List<SportsMaster> sportsName=new ArrayList<SportsMaster>();
		try {
			sportsName=sportsClubService.getSportsName(club_name_id);
			model.addAttribute("sportsName",sportsName);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return sportsName;
		
	}
	
	@PostMapping(value="/getFees")
	@ResponseBody
	public Double getFees(@RequestParam(value = "club_name_id")int club_name_id,@RequestParam(value="sports_name_id") int sports_name_id,Model model) {
		double totalFees=0;
		try {
			totalFees=sportsClubService.getTotalFees(club_name_id,sports_name_id);
			model.addAttribute("totalFees", totalFees);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return totalFees;
		
	}
	@RequestMapping(value = "/submitSportsClub",method = RequestMethod.POST)
	public String addSportsDetails(@ModelAttribute("SportsClub")RegistrationMaster sportsClub,@RequestParam("image") MultipartFile file,Model model,RedirectAttributes redirectAttributes) {
		String ret="";
//		if (file.isEmpty()) {
//            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
//            return "redirect:uploadStatus";
//        }

        try {
        	sportsClub.setImage_path(file.getOriginalFilename());
        	File saveFile=new ClassPathResource(UPLOADED_FOLDER).getFile();
        	Path path=Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
        	Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        	System.out.println(" Image is Uploaded ");
        	
            model.addAttribute("SportsClub", sportsClub);
            ret=sportsClubService.submitSportsDetails(sportsClub);
            
            if ("success".equals(ret)) {
				model.addAttribute("SportsClub", new RegistrationMaster());
				redirectAttributes.addFlashAttribute("msg",
	                    "Registration successfull" );
				
//				String host ="smtp.gmail.com";
//				Properties props=new Properties();
//				
//				props.put("mail.smtp.auth", "true");
//				props.put("mail.smtp.starttls.enable", "true");
//				props.put("mail.smtp.host",host);
//				props.put("mail.smtp.port", "587");
//				props.put("mail.smtp.ssl.protocols", "TLSv1.2");
//				props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
				
//				String receipt=sportsClub.getEmail_id();
//			    
//			    String msgBody="Hey! \n\n You have Successfully Registered to SportsClub \n\n Thanks";
//			    
//			    String subject="Regarding SportsClub Registration";
//				
//				String status=sportsClubService.sendSimpleMail(sender,receipt,msgBody,subject);
				
				
				// Recipient's email ID needs to be mentioned.
				String to = sportsClub.getEmail_id();
				  
				  // Sender's email ID needs to be mentioned
				String from ="fromemail@gmail.com";
				  
				final String username = "vs79014@gmail.com";//change accordingly
				final String password = "hbpziojxwdmnujhd";//change accordingly
				  
				  // Assuming you are sending email through relay.jangosmtp.net
				String host ="smtp.gmail.com";
				Properties props = new Properties();
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host",host);
				props.put("mail.smtp.port", "587");
				props.put("mail.smtp.ssl.protocols", "TLSv1.2");
				props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
				  
				  // Get the Session object.
				Session session = Session.getInstance(props, new
				  javax.mail.Authenticator() { protected PasswordAuthentication
				  getPasswordAuthentication() { return new PasswordAuthentication(username,
				  password); } });
				// Create a default MimeMessage object.
				Message message =new MimeMessage(session);
				  
				  // Set From: header field of the header.
				  message.setFrom(new InternetAddress(from));
				  
				  // Set To: header field of the header.
				  message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
				  
				  // Set Subject: header field
				  message.setSubject("Testing Subject");
				  
				  // Create the message part
				  BodyPart messageBodyPart = new MimeBodyPart();
				  
				  // Now set the actual message
				  messageBodyPart.setText("Dear "+sportsClub.getApplicant_name()
				  +", Your appointment has been booked for Date : ");
				  
				  messageBodyPart.setContent("<h1>Dear "+sportsClub.getApplicant_name()
				  +" Your appointment has been booked for Date: and Time: </h1>","text/html");
				  // Create a multipar message
				  Multipart multipart = new MimeMultipart();
				  
				  // Set text message part
				  multipart.addBodyPart(messageBodyPart);
				  
				  
				  // Send the complete message parts
				  message.setContent(multipart);
				  
				  // Send message
				  Transport.send(message);
			}

			else {
				model.addAttribute("SportsClub", new RegistrationMaster());
				redirectAttributes.addFlashAttribute("errMsg",
	                    "Registration failed");
			}

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:viewSportsClub";
		
	}
	
	@GetMapping(value = "/viewSportsClub")
	public String viewSportsClub(Model model) {
		List<RegistrationMaster> sportsList=new ArrayList<RegistrationMaster>();
		List<ClubMaster> clubName=new ArrayList<ClubMaster>();
		try {
			clubName=sportsClubService.getClubName();
			model.addAttribute("clubName", clubName);
			sportsList=sportsClubService.getRegistrationDetails();
			model.addAttribute("sportsList", sportsList);
			model.addAttribute("SearchSportsClub", new RegistrationMaster());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewSportsClub";
		
	}
	@PostMapping(value="/viewSportsClub")
	public String viewSearchSportsClub(@ModelAttribute("SearchSportsClub") RegistrationMaster searchSportsClub,@RequestParam(value="club_id" ,required = true)Integer club_name_id,
			@RequestParam(value="sports_id",required = true)Integer sports_name_id,Model model) {
		List<RegistrationMaster> sportsList=new ArrayList<RegistrationMaster>();
		List<ClubMaster> clubName=new ArrayList<ClubMaster>();
		try {
			clubName=sportsClubService.getClubName();
			model.addAttribute("clubName", clubName);
			if(club_name_id!=0) {
				sportsList=sportsClubService.getRegistrationDetailsById(club_name_id);
				model.addAttribute("sportsList", sportsList);
			}
			if(club_name_id!=0 && sports_name_id!=0) {
				sportsList=sportsClubService.getRegistrationDetailsByClubAndSportsId(club_name_id,sports_name_id);
				model.addAttribute("sportsList", sportsList);
			}
			model.addAttribute("sports", sports_name_id);
			model.addAttribute("club", club_name_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
				return "viewSportsClub";
		
	}
	
	@RequestMapping(value="/editSportsClub")
	public String viewEditSportsClub(@RequestParam(value="registration_id",required = true)Integer registration_id, Model model,HttpSession session) {
		List<ClubMaster> clubName=new ArrayList<ClubMaster>();
		List<SportsMaster> sportsName=new ArrayList<SportsMaster>();
		RegistrationMaster sportsList=new RegistrationMaster();
		SimpleDateFormat formater=new SimpleDateFormat("dd-MMM-yyyy");
		try {
			sportsList=sportsClubService.getSportsRegistrationListById(registration_id);
			clubName=sportsClubService.getClubName();
			model.addAttribute("clubName", clubName);
			sportsName=sportsClubService.getSportsName(sportsList.getClub_id());
			model.addAttribute("sportsName",sportsName);
			model.addAttribute("SportsClub",sportsList);
			model.addAttribute("date", formater.format(sportsList.getDob()));
			session.setAttribute("SessionSportsClub", sportsList);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return "editSportsClub";
		
	}
	@PostMapping(value="/updateSportsClub")
	public String updateSportsClub(@ModelAttribute("SportsClub")RegistrationMaster sportsClub,@RequestParam("image") MultipartFile file,Model model,HttpSession session,RedirectAttributes redirectAttributes) {
		String ret="failure";
		try {
			sportsClub.setImage_path(file.getOriginalFilename());
        	File saveFile=new ClassPathResource(UPLOADED_FOLDER).getFile();
        	Path path=Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
        	Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        	System.out.println(" Image is Uploaded ");
        	
			RegistrationMaster sportsList2=(RegistrationMaster) session.getAttribute("SessionSportsClub");
			sportsClub.setRegistration_id(sportsList2.getRegistration_id());
			if(sportsClub.getRegistration_id()==sportsList2.getRegistration_id()) {
				ret=sportsClubService.updateSportsDetails(sportsClub);
				if("success".equals(ret)) {
					redirectAttributes.addFlashAttribute("msg", "Registration Details Modified Successfully.");
				} else {
					redirectAttributes.addFlashAttribute("errMsg", "Registration Details not Modified Successfully.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:viewSportsClub";
	}
	@RequestMapping(value="/deleteSportsClub")
	public String deleteSportsClub(@RequestParam(value="registration_id",required=true)Integer registration_id,RedirectAttributes redirectAttributes) {
		RegistrationMaster sportsList=new RegistrationMaster();
		String ret="";
		try {
			sportsList=sportsClubService.getSportsRegistrationListById(registration_id);
			sportsList.setDeleted_flag("Y");
			ret=sportsClubService.deleteSportsClub(sportsList);
			if("success".equals(ret)) {
				redirectAttributes.addFlashAttribute("msg", "Registration Details Deleted Successfully.");
			} else {
				redirectAttributes.addFlashAttribute("errMsg", "Registration Details not Deleted.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:viewSportsClub";
	}
}
