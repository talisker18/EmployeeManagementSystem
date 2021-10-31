/**
 * not used anymore, validation is done in backend now (see EmployeeController)
 */

function empFormValidation(){
	var allInputsValid = false;
	const regexNames = new RegExp("^[a-zA-Z]*$");
	const regexForEmail = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	var text;
	
	//check if firstname consists of letters only AND is not empty string
	var firstNameInput = document.getElementById("firstName").value;
	
	if(regexNames.test(firstNameInput) && firstNameInput.length > 0){
		text = "Input OK";
		document.getElementById("input-firstname").innerHTML = text;
		document.getElementById("input-firstname").style.color = "#7deb34";
	}else{
		allInputsValid = false;
		text = "First Name must contain only letters";
		document.getElementById("input-firstname").innerHTML = text;
		document.getElementById("input-firstname").style.color = "#eb3434";
	}
	
	//check if lastname consists of letters only
	var lastNameInput = document.getElementById("lastName").value;
	
	if(regexNames.test(lastNameInput) && lastNameInput.length > 0){
		text = "Input OK";
		document.getElementById("input-lastname").innerHTML = text;
		document.getElementById("input-lastname").style.color = "#7deb34";
	}else{
		allInputsValid = false;
		text = "Last Name must contain only letters";
		document.getElementById("input-lastname").innerHTML = text;
		document.getElementById("input-lastname").style.color = "#eb3434";
	}
	
	//check if username has input
	var userNameInput= document.getElementById("userName").value;
	
	if(userNameInput.length > 0){
		text = "Input OK";
		document.getElementById("input-username").innerHTML = text;
		document.getElementById("input-username").style.color = "#7deb34";
	}else{
		allInputsValid = false;
		text = "Username not filled in";
		document.getElementById("input-username").innerHTML = text;
		document.getElementById("input-username").style.color = "#eb3434";
	}
	
	//check if date of birth is correctly filled in
	var dobInput= document.getElementById("dateOfBirth").value;
	var dobAsDate = new Date(dobInput);
	var today = Date.now();
	var todayMinus24Years = today;
	
	todayMinus24Years.setDate(today.getFullYear - 24);
	
	var differenceInMs = today - dobAsDate;
	
	if(!(!dobInput)){ //check if dobInput is NOT NULL and age is older than 24 years
		text = "Input OK";
		document.getElementById("input-dob").innerHTML = today;
		document.getElementById("input-dob").style.color = "#7deb34";
	}else{
		allInputsValid = false;
		text = "Date of Birth not filled in";
		document.getElementById("input-dob").innerHTML = text;
		document.getElementById("input-dob").style.color = "#eb3434";
	}
	
	//check email address
	var emailInput = document.getElementById("email").value;
	
	if(regexForEmail.test(emailInput)){
		text = "Input OK";
		document.getElementById("input-email").innerHTML = text;
		document.getElementById("input-email").style.color = "#7deb34";
	}else{
		allInputsValid = false;
		text = "Invalid format for email (e.g. example@gmail.com)";
		document.getElementById("input-email").innerHTML = text;
		document.getElementById("input-email").style.color = "#eb3434";
	}
	
	return false;
}