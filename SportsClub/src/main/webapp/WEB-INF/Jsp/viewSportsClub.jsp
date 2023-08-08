<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">

<!-- bootstrap5 dataTables  -->

<link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/dataTables.bootstrap5.min.css"/>  
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.13.4/js/dataTables.bootstrap5.min.js"></script>

<!-- end dataTables -->

<div class="card p-4">
	<ul class="nav nav-pills custom-navpills">
		<li class="nav-item"><a class="nav-link " aria-current="page"
			href="addSportsClub">Add</a></li>
		<li class="nav-item"><a class="nav-link active"
			href="viewSportsClub">View</a></li>
	</ul>
	
	<script>
	$(document).ready(function () {
	    $('#mTable').DataTable();
	});
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

																/* $('#sports_name_id').val(scheme); */
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
		if ('${club}' != null && '${club}' != undefined && '${club}' != '') {
			// 		$("#club_name_id").val('${club}');
			var sports = '${sports}';
			var dataString = 'club_name_id=' + '${club}';

			// alert(dataString);
			$.ajax({
				type : "POST",
				url : 'getSportsName',
				data : dataString,
				cache : false,
				success : function(data) {

					var html = '<option value="0">Select</option>';
					var len = data.length;

					for (var i = 0; i < len; i++) {
						html += '<option value="' + data[i].sports_id + '">'
								+ data[i].sports_name + '</option>';
					}
					html += '</option>';
					$('#sports_name_id').html(html);
					$('#sports_name_id').val(sports);

				},

				error : function(e) {
					console.log("ERROR: ", e);
				},
				done : function(e) {
					console.log("DONE");
				}
			});

		}
	</script>
	
	<script>
		function editSportsClubById(registration_id){
			$("#hdnRegId").val(registration_id);
			$("form[name='editform']").submit();
		}
	</script>
	
	<script>
		function deleteSportsClubById(registration_id){
			$("#hiddenId").val(registration_id);
			$("form[name='deleteform']").submit();
		}
	</script>
	
	<form:form name="editform" action="editSportsClub" method="post">
		<input type="hidden" id="hdnRegId" name="registration_id">
	</form:form>
	
	<form:form name="deleteform" action="deleteSportsClub" method="post" >
        <input type="hidden" id="hiddenId" name="registration_id"/>     
    </form:form>
	
	<form:form action="viewSportsClub" modelAttribute="SearchSportsClub">
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
					<div class="col-sm-12 col-md-4 col-lg-4 col-xl-4 col-xxl-3"><br>
						<button type="submit" class="btn btn-outline-primary  cutome-mt-2  rounded"><i class="bi bi-search me-2"></i>Search</button>
					</div>
		</div>
	</form:form>
	<div class="table-responsive mt-3">
		<table class="table" id="mTable">
			<thead>
				<tr>
					<th width="50">SL No.</th>
					<th>Name</th>
					<th>Email</th>
					<th>Mobile No.</th>
					<th>Age</th>
					<th>Image</th>
					<th>Club Name</th>
					<th>Sports Name</th>
					<th class="text-center" width="133">Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${sportsList}" var="so" varStatus="counter">
					<tr>
						<td class="text-center"><c:out value="${counter.count}"/>
    					<c:set var="count" value="${count + 1}" scope="page"/></td>
    					<td>${so.applicant_name}</td>
    					<td>${so.email_id}</td>
    					<td>${so.mobile_no}</td>
    					<td>${so.age}</td>
    					<td><a href="">${so.image_path}</a></td>
    					<td>${so.club_name}</td>
    					<td>${so.sports_name}</td>
    					<td class="text-center">
                            <a class="btn text-primary cutome-btn  me-1 p-1" data-bs-toggle="tooltip" data-bs-placement="top" title="Edit"  onclick="editSportsClubById('${so.registration_id}');"> <i class="bi bi-pencil"></i> </a>
                            <a class="btn text-danger cutome-btn me-0 p-1"  data-bs-toggle="tooltip" data-bs-placement="top" title="Delete" onclick="deleteSportsClubById('${so.registration_id}');"><i class="bi bi-trash"></i></a>
                        </td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>