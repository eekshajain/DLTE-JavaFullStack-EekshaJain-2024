

$(document).ready(() => {
    const storedAccountNumber = sessionStorage.getItem('selectedAccountNumber');
    if (storedAccountNumber) {
        // Set the selected option in the dropdown to the stored value
        $("#populate").val(storedAccountNumber);

        // Trigger the change event manually to fetch balance and payee details
        $("#populate").change();
        findBasedOnAccNumber(storedAccountNumber);
        fetchBalance(storedAccountNumber)
    }
    // Fetch account numbers and populate dropdown
    $.ajax({
        type: "GET",
        url: "/transactions/fetch-details",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success: function (response) {
            $("#populate").empty().append('<option value="" selected disabled>Select Account Number</option>');
            response.forEach(accountNumber => {
                $("#populate").append(`<option value="${accountNumber}">${accountNumber}</option>`);
            });
        },
        error: function (error) {

        }
    });
    // Event handler for dropdown change
    $("#populate").change(function () {
        var selectedAccountNumber = $(this).val();
        sessionStorage.clear();
        sessionStorage.setItem('selectedAccountNumber', selectedAccountNumber);
        fetchBalance(selectedAccountNumber);
        if (selectedAccountNumber) {
            findBasedOnAccNumber(selectedAccountNumber);
        }
    });

    function fetchBalance(accountNumber) {
        $("#error").text("");
        $.ajax({
            type: "GET",
            url: "/transactions/getBalance",
            data: { accountNumber: accountNumber },
            success: function(response) {
                // Update the balance display with the fetched balance
                $("#balanceDisplay").text("Your Account Balance: " + response);
            },
            error: function(xhr, status, error) {

            }
        });
    }
    // Function to fetch payees based on account number
    function findBasedOnAccNumber(number) {
        $("#error").text("");
        let soapRequest = `<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:pay="http://payee.services">
<soapenv:Header/>
<soapenv:Body>
<pay:findAllPayeeBasedOnAccountNumberRequest>
<pay:senderAccount>${number}</pay:senderAccount>
</pay:findAllPayeeBasedOnAccountNumberRequest>
</soapenv:Body>
</soapenv:Envelope>`;
        $.ajax({
            url: "http://localhost:8085/payeerepo",
            type: "POST",
            dataType: "xml",
            beforeSend: function (handler) {
                handler.setRequestHeader("SOAPAction", "findAllPayeeBasedOnAccountNumberRequest");
            },
            contentType: "text/xml;charset=utf-8",
            data: soapRequest,
            success: function (response) {
                console.log(response)
                const payeeDetails = [];
                var exceptionRegex = /EXC00\d\s*:/;
                $(response).find(`ns2\\:serviceStatus`).each(function () {
                    if(exceptionRegex.test($(this).find('ns2\\:message').text())){
                        var errorMessage = $(this).find('ns2\\:message').text().replace(exceptionRegex, '').trim();
                        $("#modalhead").text(`Error occurred during viewing payee`);
                        $("#message").text(errorMessage);
                        $("#showmodal").modal("show");
                        sessionStorage.clear()
                    }else {
                        $(response).find(`ns2\\:payee`).each(function () {
                            const payee = {
                                payeeId: $(this).find(`ns2\\:payeeId`).text(),
                                senderAccountNumber: $(this).find(`ns2\\:senderAccountNumber`).text(),
                                payeeAccountNumber: $(this).find(`ns2\\:payeeAccountNumber`).text(),
                                payeeName: $(this).find(`ns2\\:payeeName`).text(),
                                // Add other payee details as needed
                            };
                            payeeDetails.push(payee);

                        });
                    }
                });
                // Display payee details in cards
                displayPayeeDetails(payeeDetails);
            },
            error: function (xhr, status, error) {

            }
        });
    }
    const displayPayeeDetails = (payees) => {
        const payeeCardsContainer = $("#payeeDetails");
        payeeCardsContainer.empty(); // Clear the container before adding new cards
        const itemsPerPage = 3; // Number of cards per page
        const totalPages = Math.ceil(payees.length / itemsPerPage);
        let currentPage = 1;
        showPage(currentPage);

        function showPage(page) {
            payeeCardsContainer.empty(); // Clear the container before adding new cards
            const startIndex = (page - 1) * itemsPerPage;
            const endIndex = Math.min(startIndex + itemsPerPage, payees.length);
            const row = $("<div class='row'></div>");
            for (let i = startIndex; i < endIndex; i++) {
                const payee = payees[i];
                const card = `
                <div class="col-md-4 mb-3">
                    <div class="card h-100">
                        <div class="card-body">
                            <h5 class="card-title">${payee.payeeName}</h5>
                            <p class="card-text">Account Number: ${payee.payeeAccountNumber}</p>
                           <button class="btn narrow-btn1" onclick="viewtransaction(sessionStorage.getItem('selectedAccountNumber'), '${payee.payeeAccountNumber}')">Transaction</button>
                            <a class="btn btn-danger" href="/payment/error?code=404&msg=Page Under Construction">Delete</a>
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
            const pagination = $("<ul class='pagination justify-content-center'></ul>");
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
function viewtransaction(dropdownAccountNumber, cardAccountNumber) {
    const data={
        sender:dropdownAccountNumber,
        payee:cardAccountNumber
    };
    sessionStorage.setItem('dataN',JSON.stringify(data));
    window.location.href = "../payment/new-trans";
}