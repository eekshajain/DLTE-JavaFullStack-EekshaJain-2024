let payeeStore = {
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

// Event listener for the search button
document.getElementById('button-addon2').addEventListener('click', function() {
    var accountNumber = document.getElementById('accountNumberInput').value;
    if(accountNumber===''){
        alert("Please enter account number");
    }else{
    var payee = payeeStore[accountNumber];
    if (payee) {
        displayPayeeDetails(payee);
    } else {
        alert("Payee not found for the entered account number.");
    }
}
});

// Function to display payee details
function displayPayeeDetails(payees) {
    var payeeDetailsDiv = document.getElementById('payeeDetails');
    payeeDetailsDiv.innerHTML = ''; // Clear previous content
  
    var containerNew = document.createElement('div');
    containerNew.classList.add('container');

    var flexContainer = document.createElement('div');
    flexContainer.classList.add('d-flex', 'flex-nowrap', 'flex-row');
    flexContainer.style.overflowX = 'auto'; // Enable horizontal scrolling
    flexContainer.style.maxWidth = '100%'; // Adjust width as needed
    flexContainer.style.backgroundColor='rgb(230, 220, 240)';
    // Iterate over each payee in the array
    payees.forEach(payee => {
        var card = document.createElement('div');
        card.classList.add('card', 'me-2','col-lg-3','ms-2','mb-2','mt-2');

        var cardHeader = document.createElement('div');
        cardHeader.classList.add('card-header');
        cardHeader.textContent = 'Payee Details';

        var cardBody = document.createElement('div');
        cardBody.classList.add('card-body');

        var id = document.createElement('p');
        id.innerHTML = `<strong>ID:</strong> ${payee.payeeId}`;

        var name = document.createElement('p');
        name.innerHTML = `<strong>Name:</strong> ${payee.payeename}`;

        var accountNumber = document.createElement('p');
        accountNumber.innerHTML = `<strong>Account Number:</strong> ${payee.payeeAccountNumber}`;

        var addButton = document.createElement('button');
        addButton.textContent = 'Make Transaction';
        addButton.classList.add('btn', 'btn-primary', 'me-2');
        addButton.style.backgroundColor='rgb(171, 151, 190)';
        addButton.style.borderColor='rgb(171, 151, 190)';

        addButton.addEventListener('click', function() {
            window.location.href = 'newtransaction.html'; // Redirect to newtransaction.html
        });

        cardBody.appendChild(id);
        cardBody.appendChild(name);
        cardBody.appendChild(accountNumber);
        cardBody.appendChild(addButton);

        card.appendChild(cardHeader);
        card.appendChild(cardBody);

        flexContainer.appendChild(card);
    });
    payeeDetailsDiv.appendChild(flexContainer);
    payeeDetailsDiv.appendChild(containerNew);
    payeeDetailsDiv.style.display = 'block';
}

document.addEventListener('DOMContentLoaded', registerEventListeners);