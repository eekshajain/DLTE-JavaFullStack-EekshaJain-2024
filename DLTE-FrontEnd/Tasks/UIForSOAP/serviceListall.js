
const payees=[];
$(document).ready(()=>{
    $("#searchButton").click(()=>{
        const accNumber=$("#accountNumberInput").val()

        var findBasedOnAccNumber=`<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:pay="http://payee.services">
        <soapenv:Header/>
        <soapenv:Body>
           <pay:findAllPayeeBasedOnAccountNumberRequest>
              <pay:senderAccount>${accNumber}</pay:senderAccount>
           </pay:findAllPayeeBasedOnAccountNumberRequest>
        </soapenv:Body>
     </soapenv:Envelope>`


    $.ajax({
        url:"http://localhost:8086/payeerepo",
        type:"POST",
        dataType:"xml",
        beforeSend:function(handler){
            handler.setRequestHeader("Authorization","Basic "+btoa("eeksha06:eeksha123"))
            handler.setRequestHeader("SOAPAction","findAllPayeeBasedOnAccountNumberRequest")
        },
        contentType:"text/xml;charset=utf-8",
        data:findBasedOnAccNumber,
        success: function(response) {
            let element = $("#status")
            element.empty()
        
            const payees = []; // Initialize an array to store payee details
        
            // Extract payee details
            $(response).find("ns2\\:payee").each(function() {
                const payeeId = $(this).find("ns2\\:payeeId").text();
                const payeeName = $(this).find("ns2\\:payeeName").text();
                const payeeAccountNumber = $(this).find("ns2\\:payeeAccountNumber").text();
        
                // Push payee details into the array
                payees.push({
                    payeeId: payeeId,
                    payeeName: payeeName,
                    payeeAccountNumber: payeeAccountNumber
                });
        
                // Log payee details to console
                console.log("Payee ID: " + payeeId);
                console.log("Payee Name: " + payeeName);
                console.log("Payee Account Number: " + payeeAccountNumber);
                console.log("--------------------");
            });
        
            // Log the entire payees array to console
            console.log(payees);
            console.log(response)
            // Display the payee details on the webpage
            element.append(`<h1>Account Number:${accNumber}</h1>`);
            displayPayeeDetails(payees);
        },
        error:function(xhr, status, error){
        
            let element = $("#status")
            element.empty()
            const info = $(response).find(`ns2\\:serviceStatus`).find("ns2\\:status").text()
          element.append(`<h1>${xhr.responseText}</h1>`)
        }
    })
})
})

function displayPayeeDetails(payees) {
    payees.forEach(payee => {
        console.log("Payee ID:", payee.payeeId);
        console.log("Payee Name:", payee.payeeName);
        console.log("Payee Account Number:", payee.payeeAccountNumber);
        console.log("--------------------");
    });
    var payeeDetailsDiv = $('#payeeDetails');
    payeeDetailsDiv.empty(); // Clear previous content
  
    var containerNew = $('<div class="container"></div>');

    var flexContainer =$('<div class="d-flex flex-nowrap justify-content-start"></div>').css({
        'backgroundColor': 'rgb(230, 220, 240)',
        'overflow-x': 'auto', // Enable horizontal scrolling
        'overflow-y': 'hidden', // Disable vertical scrolling
        'max-width': '100%', // Limit maximum width
        'height': '300px', // Set a fixed height for vertical scrolling
    });


payees.forEach(payee => {
    var card = $('<div class="card me-2 col-lg-4 ms-2 mb-2 mt-2"></div>');

    var cardHeader = $('<div class="card-header">Payee Details</div>');

    var cardBody = $('<div class="card-body"></div>');

    var id = $('<p></p>').html(`<strong>ID:</strong> ${payee.payeeId}`);

    var name = $('<p></p>').html(`<strong>Name:</strong> ${payee.payeeName}`);

    var accountNumber = $('<p></p>').html(`<strong>Account Number:</strong> ${payee.payeeAccountNumber}`);

    var addButton = $('<button class="btn btn-primary me-2"></button>').text('Make Transaction').css({
        'backgroundColor': 'rgb(171, 151, 190)',
        'borderColor': 'rgb(171, 151, 190)'
    });

    cardBody.append(id, name, accountNumber, addButton);
    card.append(cardHeader, cardBody); // Append cardHeader and cardBody to card div

    flexContainer.append(card);
});

    containerNew.append(flexContainer);
    payeeDetailsDiv.append(containerNew);
    payeeDetailsDiv.css('display', 'block');

}
