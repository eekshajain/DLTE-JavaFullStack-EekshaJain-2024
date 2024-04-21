let payeeStore =  {
    "123456789012": [{
        payeeId: "12",
        payeeAccountNumber: "543213456786",
        payeename: "Diya"
    },
    {
        payeeId: "14",
        payeeAccountNumber: "564738291823",
        payeename: "Vandana"
    },{
        payeeId: "14",
        payeeAccountNumber: "564738291823",
        payeename: "Vandana"
    },
    {
        payeeId: "14",
        payeeAccountNumber: "564738291823",
        payeename: "Vandana"
    }
],
    "987654321098": [{
        payeeId: "64",
        payeeAccountNumber: "564738291823",
        payeename: "Vandana"
    }]
};

let currentPage = 1;
const cardsPerPage = 2;
let filteredPayee = [];
let payeeArray = [];

$(document).ready(() => {
    loadStore();
    populatePayeeArray();
    searchAccountNumber();
    searchName();
    // displayPayeeDetails(payeeStore); // Don't display on initial load
    // updatePagination(); // Don't update pagination on initial load
});

// Load stored payee details from local storage
const loadStore = () => {
    const data = localStorage.getItem('payeeStore');
    if (data) {
        payeeStore = JSON.parse(data);
    }
};

// Function to store payee details in local storage
const dumpStore = () => {
    localStorage.setItem('payeeStore', JSON.stringify(payeeStore));
};

// Call loadStore to populate payeeStore from local storage
loadStore();

function populatePayeeArray() {
    for (const accountNumber in payeeStore) {
        payeeStore[accountNumber].forEach(payee => {
            payeeArray.push({
                accountNumber: accountNumber,
                payeeId: payee.payeeId,
                payeeAccountNumber: payee.payeeAccountNumber,
                payeeName: payee.payeename
            });
        });
    }
}

function searchAccountNumber() {
    $("#searchButton").click(() => {
        const accountNumberInput = $("#accountNumberInput").val();
        filteredPayee = payeeArray.filter(payee => payee.accountNumber === accountNumberInput);
        if (filteredPayee.length > 0) {
            currentPage = 1; // Reset currentPage to 1
            displayPayeeDetails(filteredPayee);
            updatePagination();
        } else {
            alert("Payee not found");
        }
    });
}

function searchName() {
    $("#searchName").click(() => {
        const nameInput = $("#nameInput").val();
        const accountNumberInput = $("#accountNumberInput").val();
        filteredPayee = payeeArray.filter(payee => payee.payeeName === nameInput && payee.accountNumber === accountNumberInput);
        if (filteredPayee.length > 0) {
            currentPage = 1; // Reset currentPage to 1
            displayPayeeDetails(filteredPayee);
            updatePagination();
        } else {
            alert("Payee not found");
        }
    });
}

// Function to display payee details
function displayPayeeDetails(payees) {
    const startIndex = (currentPage - 1) * cardsPerPage;
    const endIndex = startIndex + cardsPerPage;
    const paginatedPayees = payees.slice(startIndex, endIndex);
    var payeeDetailsDiv = $('#payeeDetails');
    payeeDetailsDiv.empty(); // Clear previous content

    var containerNew = $('<div class="container"></div>');

    var flexContainer = $('<div class="d-flex flex-nowrap justify-content-start"></div>').css({
        'backgroundColor': 'rgb(230, 220, 240)',
        'overflow-x': 'auto', // Enable horizontal scrolling
        'overflow-y': 'hidden', // Disable vertical scrolling
        'max-width': '100%', // Limit maximum width
        'height': '300px', // Set a fixed height for vertical scrolling
    });

    paginatedPayees.forEach(payee => {
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

function updatePagination() {
    const totalPages = Math.ceil(filteredPayee.length / cardsPerPage);
    const pagination = $('#pagination');
    pagination.empty();

    for (let i = 1; i <= totalPages; i++) {
        const button = $(`<button class="btn btn-outline-primary mr-3">${i}</button>`);
        if (i === currentPage) {
            button.addClass('active');
        }

        button.on('click', () => {
            currentPage = i;
            displayPayeeDetails(filteredPayee);
            updatePagination();
        });

        pagination.append(button);
    }
}
