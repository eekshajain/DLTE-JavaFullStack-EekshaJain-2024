const formValidation=()=>{
  var isValid=true

  var myForm=document.forms['application']
  const accNumber=myForm.accountNumber.value
  const name=myForm.accountHolder.value
  const accType=myForm.accountType.value
  const chequeType=myForm.chequeType.value
  const date=myForm.applyDate.value
  const address=myForm.holderAddress.value
  const phoneNumber=myForm.contactNumber.value
  const mailId=myForm.email.value
  const digiSign=myForm.signature.value


  var accNumberError=document.getElementById("accNumberErr")
  var nameError=document.getElementById("accHolderError")
  var accountTypeError=document.getElementById("accTypeError")
  var chequeTypeError=document.getElementById("chequeTypeErr")
  var dateError =document.getElementById("dateErr")
  var addressError=document.getElementById("addressErr")
  var phoneNumberError=document.getElementById("contactNumberErr")
  var mailIdError=document.getElementById("emailErr")
  var digiSignError=document.getElementById("signatureErr")
try{

    if(!(/[0-9]{5,}/).test(accNumber))
    {
       throw "Account number must be 12 digits"
    }
}catch(message){
    isValid=false
    accNumberError.innerHTML=message
}

try{
 if(!(/^[a-zA-Z ]+$/).test(name))
 {
    throw "Holder name must contaion only letters"
 }
}catch(message){
    isValid=false
    nameError.innerHTML=message
}


try{
    if(!accType){
        throw "Account type must be selected"
    }
}catch(message){
    isValid=false
    accountTypeError.innerHTML=message
}

try{
    if(!chequeType){
        throw "Cheque type must be selected"
    }
}catch(message){
    isValid=false
    chequeTypeError.innerHTML=message
}


try{
if(!date){
    throw "Date of apply must be selected"
}
}catch(message){
    isValid=false
    dateError.innerHTML=message
}

try{
    if(!address){
        throw "Address must be entered"
    }
}catch(message){
    isValid=false
    addressError.innerHTML=message
}

try{
    if(!(/[6-9]{1}[0-9]{0}/).test(phoneNumber))
    {
       throw "Phone number must be 10 digits"
    }
}catch(message){
    isValid=false
    phoneNumberError.innerHTML=message
}

try{
    if(!(/^[^\s@]+@[^\s@]+\.[^\s@]+$/).test(mailId)){
      throw "Enter a valid Email ID"  
    }
}catch(message){
    isValid=false
    mailIdError.innerHTML=message
}


try {
    // Check if the file path is empty
    if (!digiSign) {
        throw "No file selected";
    }
    const allowedExtensions = ["jpg", "jpeg", "png"];
    // Get the file extension
    const fileExtension = digiSign.split(".").pop().toLowerCase();

    // Check if the file extension is allowed
    if (!allowedExtensions.includes(fileExtension)) {
        throw "Invalid file format";
    }
} catch (message) {
 isValid=false
 digiSignError.innerHTML=message
}

return isValid
}