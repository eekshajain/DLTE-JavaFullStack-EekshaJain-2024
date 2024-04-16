var payeeStore = {
    "123456789012": {
        name: "John Doe",
        email: "john@example.com",
        phone: "123-456-7890"
    },
    "987654321098": {
        name: "Jane Smith",
        email: "jane@example.com",
        phone: "987-654-3210"
    }
};

// Event listener for the search button
document.getElementById('button-addon2').addEventListener('click', function() {
    var accountNumber = document.getElementById('accountNumberInput').value;
    var payee = payeeStore[accountNumber];
    if (payee) {
        displayPayeeDetails(payee);
    } else {
        alert("Payee not found for the entered account number.");
    }
});

// Function to display payee details
function displayPayeeDetails(payee) {
    var payeeDetailsDiv = document.getElementById('payeeDetails');
    payeeDetailsDiv.innerHTML = `
        <div class="card">
            <div class="card-header">Payee Details</div>
            <div class="card-body">
                <p><strong>Name:</strong> ${payee.name}</p>
                <p><strong>Email:</strong> ${payee.email}</p>
                <p><strong>Phone:</strong> ${payee.phone}</p>
            </div>
        </div>
    `;
    payeeDetailsDiv.style.display = 'block';
}