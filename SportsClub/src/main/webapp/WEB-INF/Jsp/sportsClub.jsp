<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>


<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous" />
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
	integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
	integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
	crossorigin="anonymous"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>

<script src="https://code.jquery.com/jquery-3.5.1.js"></script>

<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
	

<script>
	var $j = jQuery.noConflict(true);
</script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<script>

function checkOnlyDigits(e) {
	var regex = new RegExp("^[0-9]");
	var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
	var code = (e.keyCode ? e.keyCode : e.which);
	if (regex.test(str) || code == 8) {
		return true;
	}
	alert("Please Enter Only Digits.");
	return false;
}

function checkOnlyAlphabet(e) {
	var regex = new RegExp("^[a-zA-Z ]");
	var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
	var code = (e.keyCode ? e.keyCode : e.which);
	if (regex.test(str) || code == 8) {
		return true;
	}
	alert("Please Enter Only Alphabets.");
	return false;
}


function checkValidate(){
	
	if(document.getElementById("club_name_id").value == 0){
		alert("Please select club name");
		$("#club_name_id").focus();
		return false;
	}
	if(document.getElementById("sports_name_id").value == 0){
		alert("Please select sports name");
		$("#sports_name_id").focus();
		return false;
	}
	
	var clubName=document.getElementById("club_name_id").value;
	var sportsName=document.getElementById("sports_name_id").value;
	
	var applicantName=$("#applicant_name_id").val();
	var email=$("#email_id").val();
	var mobileNo=$("#mobile_no_id").val();
	var dob=$("#dob_id").val();
	var male=$("#radio1").val();
	var female=$("#radio2").val();
	var file=document.getElementById("file_id").value;
	
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	
	if(applicantName == ""){
		alert("Please enter Applicant Name");
		$("#applicant_name_id").focus();
		return false;
	}
	if(email==""){
		alert("Please enter email id");
		$("#email_id").focus();
		return false;
	}
	
	if(!email.match(mailformat))
	{
		alert("You have entered an invalid email address!");
		$("#email_id").focus();
		return false;
	}
	if(mobileNo==""){
		alert("Please enter mobile no");
		$("#mobile_no_id").focus();
		return false;
	}
	if(mobileNo.length <10){
		alert("Please enter Valid Mobile no");
		$("#mobile_no_id").focus();
		return false;
	}
	if(dob==""){
		alert("Please select date of birth");
		$("#dob_id").focus();
		return false;
	}
	if(document.getElementById("radio1").checked == false && document.getElementById("radio2").checked == false){
		alert("Please select gender");
		return false;
	}
	if(document.getElementById("file_id").files.length == 0){
		alert("Please upload the image");
		$("#file_id").focus();
		return false;
	}
}

</script>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('#club_name_id')
									.change(
											function() {

												//Spring Ajax call for sports name
												var sportsName = $(this).val();
												//alert(sportsName);
												var dataString = 'club_name_id='
														+ sportsName;

												//alert(dataString);
												$
														.ajax({
															type : "POST",
															url : 'getSportsName',
															data : dataString,
															cache : false,
															success : function(
																	data) {
																var html = '<option value="0">Select</option>';
																var len = data.length;
																// alert("len="+len);
																for (var i = 0; i < len; i++) {

																	html += '<option value="' + data[i].sports_id + '">'
																			+ data[i].sports_name
																			+ '</option>';
																}
																html += '</option>';
																$(
																		'#sports_name_id')
																		.html(
																				html);
															},
															error : function(e) {
																console
																		.log(
																				"ERROR: ",
																				e);
															},
															done : function(e) {
																console
																		.log("DONE");
															}
														});
											});
						});
	</script>

	<script type="text/javascript">
		$(document).ready(function() {
			$("#dob_id").datepicker();
			$('#sports_name_id').change(function() { //Spring Ajax call for Fees

				var sportsId = $('#sports_name_id').val();
				var clubId = $('#club_name_id').val();
				alert(clubId);
				$.ajax({
					type : "POST",
					url : 'getFees',
					dataType : 'json',
					data : {
						"club_name_id" : clubId,
						"sports_name_id" : sportsId
					},
					cache : false,
					success : function(data) {

						$('#feesId').val(data);
					},
					error : function(e) {
						console.log("ERROR: ", e);
					},
					done : function(e) {
						console.log("DONE");
					}
				});
			});
		});
	</script>

	<div class="maincontent-page">
		<div class="card p-4">
			<div class="custom-tab">
				<ul class="nav nav-pills custom-navpills">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="addSportsClub;">Add</a></li>
					<li class="nav-item"><a class="nav-link" href="viewSportsClub">View</a>
					</li>
				</ul>
				<div class="utils">
					<p class="mb-0">
						<span class="text-danger">*</span> marks are mandatory
					</p>
				</div>
			</div>
			
			<c:if test="${not empty errMsg}">

				<center><span class="text-danger" id="emsg"><strong>Error! </strong>${errMsg}</span></center>
			</c:if>

			<c:if test="${not empty msg}">
				
				<center><span class="text-success" id="smsg"><strong>Success! </strong>${msg}</span></center>
			</c:if>
			
			<form:form action="submitSportsClub" modelAttribute="SportsClub"
				autocomplete="off" id="addVillageMasterForm" enctype="multipart/form-data">
				<div class="row">
					<div class="col-sm-6 col-lg-4 col-xl-4 col-xxl-3">
						<div class="mb-3">
							<label for="driver_name" class="form-label">Club Name <span
								class="text-danger">*</span>
							</label>
							<form:select class="form-select"
								aria-label="Default select example" path="club_id"
								id="club_name_id">
								<option value="0">Select</option>
								<form:options items="${clubName}" itemValue="club_id"
									itemLabel="club_name"></form:options>
							</form:select>
						</div>
					</div>
					<div class="col-sm-6 col-lg-4 col-xl-4 col-xxl-3">
						<div class="mb-3">
							<label for="driver_name" class="form-label">Sports Name <span
								class="text-danger">*</span>
							</label>
							<form:select class="form-select"
								aria-label="Default select example" path="sports_id"
								id="sports_name_id">
								<option value="0">Select</option>
								<form:options items="${sportsName}" itemValue="sports_id"
									itemLabel="sports_name"></form:options>
							</form:select>
						</div>
					</div>
					<div class="col-sm-12 col-md-4 col-lg-4 col-xl-4 col-xxl-3">
						<div class="mb-3">
							<label for="subAuaCode" class="form-label">MemberShip Fee
								<span class="text-danger">*</span>
							</label>
							<form:input type="text" class="form-control" path="fees"
								id="feesId" readonly="true"></form:input>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12 col-md-4 col-lg-4 col-xl-4 col-xxl-3">
						<div class="mb-3">
							<label for="subAuaCode" class="form-label">Applicant Name
								<span class="text-danger">*</span>
							</label>
							<form:input type="text" class="form-control"
								path="applicant_name" id="applicant_name_id" maxlength="30"
								onkeypress="return checkOnlyAlphabet(event);"
								placeholder="Please Enter Your Name"></form:input>
						</div>
					</div>
					<div class="col-sm-12 col-md-4 col-lg-4 col-xl-4 col-xxl-3">
						<div class="mb-3">
							<label for="subAuaCode" class="form-label">Email <span
								class="text-danger">*</span></label>
							<form:input type="text" class="form-control" path="email_id"
								id="email_id" placeholder="Please Enter Email Address"></form:input>
						</div>
					</div>
					<div class="col-sm-12 col-md-4 col-lg-4 col-xl-4 col-xxl-3">
						<div class="mb-3">
							<label for="subAuaCode" class="form-label">Mobile No. <span
								class="text-danger">*</span></label>
							<form:input type="text" class="form-control" path="mobile_no"
								id="mobile_no_id" maxlength="10"
								onkeypress="return checkOnlyDigits(event);"
								placeholder="Please Enter Your Mobile No."></form:input>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6 col-lg-4 col-xl-4 col-xxl-3 ">
						<label for="toDate" class="form-label d-block">D.O.B.</label>
						<div class="input-group">
							<input type="text"
								class="form-control datepicker custome-datepicker"
								aria-label="Default"
								aria-describedby="inputGroup-sizing-default" name="dob"
								id="dob_id" placeholder="Please Right Click to Enter Your DOB">
<!-- 														<div class="input-group-prepend"> -->
<!-- 															<span class="input-group-text" id="inputGroup-sizing-default"><i -->
<!-- 																class="fa fa-calendar"></i></span> -->
<!-- 														</div> -->
						</div>
					</div>
					<div class="col-sm-12 col-md-4 col-lg-4 col-xl-4 col-xxl-3">
						<label for="driver_name" class="form-label d-block">Gender</label>
						<div class="form-check form-check-inline">
							<form:radiobutton class="form-check-input" name="r2" id="radio1"
								path="gender" value="M"></form:radiobutton>
							<label class="form-check-label">Male</label>
						</div>
						<div class="form-check form-check-inline">
							<form:radiobutton class="form-check-input" name="r2" id="radio2"
								path="gender" value="F"></form:radiobutton>
							<label class="form-check-label">Female</label>
						</div>
					</div>
					<div class="col-sm-12 col-md-4 col-lg-4 col-xl-4 col-xxl-3">
						<label for="driver_name" class="form-label d-block">Upload
							Photo</label>
							 <input type="file" name="image" id="file_id"/>
					</div>
				</div>
				<div class="row">
					<div class="fs-6">
					<div><br></div>
						<button type="submit" class="btn btn-success px-3 py-1 rounded" onclick="return checkValidate();">Submit</button>
						<button type="reset" class="btn btn-danger  px-3 py-1 rounded">Reset</button>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>