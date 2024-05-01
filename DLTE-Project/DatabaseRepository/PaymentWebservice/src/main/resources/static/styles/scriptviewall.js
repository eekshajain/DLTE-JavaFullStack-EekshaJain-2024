
$(document).ready(()=>{
    $.ajax({
        type:"GET",
        url:"/payees/fetch-details",
        dataType:"json",
        contentType:"application/json;charset=utf-8",
        success:function (response) {
            alert(response)
            $("#populate").empty().append('<option value="" selected disabled> Select here</option>');
            response.forEach(accountNumber => {
                $("#populate").append(`<option value="${accountNumber}">${accountNumber}</option>`);
            });
            console.log(response);
        },
        error:function (error){
            console.error("Error fetching:"+error);
        }
    })
    $("#populate").change(function () {
        var selectedAccountNumber = $(this).val();
        if (selectedAccountNumber) {
            alert("Selected account number: " + selectedAccountNumber);
            findBasedOnAccNumber(selectedAccountNumber);
        }
    });
    // Function to fetch payees based on account number
    function findBasedOnAccNumber(number) {
        let soapRequest = `<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:pay="http://payee.services">
                                    <soapenv:Header/>
                                    <soapenv:Body>
                                    <pay:findAllPayeeBasedOnAccountNumberRequest>
                                    <pay:senderAccount>${number}</pay:senderAccount>
                                    </pay:findAllPayeeBasedOnAccountNumberRequest>
                                    </soapenv:Body>
                                    </soapenv:Envelope>`;
        $.ajax({
            url: "http://localhost:8082/payeerepo",
            type: "POST",
            dataType: "xml",
            beforeSend: function (handler) {
                handler.setRequestHeader("SOAPAction", "findAllPayeeBasedOnAccountNumberRequest");
            },
            contentType: "text/xml;charset=utf-8",
            data: soapRequest,
            success: function (response) {
                alert("Successfully fetched payee details!");
                const payeeDetails = [];
                $(response).find(`ns2\\:payee`).each(function () {
                    const payee = {
                        payeeId:$(this).find(`ns2\\:payeeId`).text(),
                        senderAccountNumber: $(this).find(`ns2\\:senderAccountNumber`).text(),
                        payeeAccountNumber: $(this).find(`ns2\\:payeeAccountNumber`).text(),
                        payeeName: $(this).find(`ns2\\:payeeName`).text(),
                        // Add other payee details as needed
                    };
                    payeeDetails.push(payee);
                });
                // Display payee details in cards
                displayPayeeDetails(payeeDetails);
            },
            error: function (xhr, status, error) {
                alert("Error fetching payee details: " + error);
                console.error("Error fetching payees: " + error);
            }
        });
    }
    const displayPayeeDetails = (payees) => {
        const payeeCardsContainer = $("#payeeDetails");
        payeeCardsContainer.empty(); // Clear the container before adding new cards

        const itemsPerPage = 6; // Number of cards per page
        const totalPages = Math.ceil(payees.length / itemsPerPage);

        let currentPage = 1;
        showPage(currentPage);

        function showPage(page) {
            payeeCardsContainer.empty(); // Clear the container before adding new cards

            const startIndex = (page - 1) * itemsPerPage;
            const endIndex = Math.min(startIndex + itemsPerPage, payees.length);

            const row = $("<div class='row justify-content-center'></div>"); // Center the cards
            for (let i = startIndex; i < endIndex; i++) {
                const payee = payees[i];
                const card = `
                 <div class="col-md-4 pl-3">
        <div class="card mb-3">
            <div class="card-body">
                <h5 class="card-title">${payee.payeeName}</h5>
                <p class="card-text">Account Number: ${payee.payeeAccountNumber}</p>
                <button class="btn narrow-btn1">Transaction</button>
                <button class="btn btn-danger narrow-btn"
                        data-payee-id="${payee.payeeId}"
                        data-sender-account="${payee.senderAccountNumber}"
                        data-payee-account="${payee.payeeAccountNumber}"
                        data-payee-name="${payee.payeeName}">
                    Delete
                </button>
            </div>
        </div>
    </div>
            `;
                row.append(card);
            }

            payeeCardsContainer.append(row);
        }

        function updatePagination() {
            $(".pagination").remove();

            const pagination = $("<ul class='pagination justify-content-center'></ul>"); // Center the pagination
            for (let i = 1; i <= totalPages; i++) {
                const pageItem = $("<li class='page-item'></li>");
                const pageLink = $(`<a class='page-link' href='#'>${i}</a>`);
                pageLink.click(() => {
                    currentPage = i;
                    showPage(currentPage);
                });
                pageItem.append(pageLink);
                pagination.append(pageItem);
            }
            pagination.find(".page-link").css("margin", "0 5px");
            $("#payeeDetails").after(pagination);
        }

        updatePagination();

    };

})
