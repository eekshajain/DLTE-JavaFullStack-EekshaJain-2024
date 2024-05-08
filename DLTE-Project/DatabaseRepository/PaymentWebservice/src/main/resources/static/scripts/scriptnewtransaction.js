$(document).ready(()=>{
    const transData=JSON.parse(sessionStorage.getItem('dataN'));
    $('#senderAccount').val(transData.sender);
    $('#payeeAccount').val(transData.payee);
    sessionStorage.removeItem('dataN');
    $("#send").click(()=>{
        const senderAcc=$("#senderAccount").val();
        const payeeAcc=$("#payeeAccount").val();
        const transactionType=$('input[name="transactionType"]:checked').val();
        const amount=$("#amount").val();
        let isValid = true;

        // Check if transaction type is selected
        if (!transactionType) {
            $('#transactionTypeValidation').text("Please select a transaction type").show();
            isValid = false;
        } else {
            $('#transactionTypeValidation').hide();
        }

        // Check if amount is provided and is a positive number
        if (!amount || isNaN(amount)) {
            $('#amountValidation').text("Please enter a valid amount").show();
            isValid = false;
        }else if(amount<1){
            $('#amountValidation').text("Minimum transaction amount is Re 1").show();
            isValid = false;
        }
        else {
            $('#amountValidation').hide();
        }

        if (!isValid) {
            return;
        }
        const request={
            "transactionFrom":senderAcc,
            "transactionTo":payeeAcc,
            "transactionType":transactionType,
            "transactionAmount":amount
        };
        console.log(JSON.stringify(request));
        $.ajax({
            url:"/transactions/new",
            type:"POST",
            dataType:"text",
            contentType:"application/json;charset=utf-8",
            data:JSON.stringify(request),
            success: function (response, xhr, status) {
                var exceptionRegex = /EXC00\d\s*:/;
                var validationRegex = /ERR000\d*:/;
                if (exceptionRegex.test(response)) {
                    var errorMessage = response.replace(exceptionRegex, '').trim();
                    $("#modalhead").text(`Error occurred during adding new payee`);
                    $("#message").text(errorMessage);
                    $("#showmodal").modal("show");
                } else if (validationRegex.test(response)) {
                    var errorMessage = response.replace(new RegExp(validationRegex, 'g'), '').trim();
                    $("#validhead").text(`Wrong data entered`);
                    $("#errormessage").text(errorMessage);
                    $("#validationModal").modal("show");
                } else {
                    $("#modalhead").text(`Success`);
                    $("#message").text(response);
                    $("#showmodal").modal("show");
                }
            },
            error:function(xhr, status, error){
                let element=$("#status");
                element.empty();
            }
        });

    });

    $("#clear").click(()=>{
        $('input[name="transactionType"]').prop('checked', false);
        $("#amount").val("");
        $(".validation-message").hide();
    });
});