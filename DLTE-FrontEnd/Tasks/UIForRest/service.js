
$(document).ready(()=>{
    $("#transfer").click(()=>{
        alert('clicked')
        const senderAcc=$("#senderAccount").val()
        const payeeAcc=$("#payeeAccount").val()
        const transactionType=$('input[name="transactionType"]:checked').val()
        const amount=$("#amount").val()
    
        const request={
            "transactionFrom":senderAcc,
            "transactionTo":payeeAcc,
            "transactionType":transactionType,
            "transactionAmount":amount
        }
    alert(request)
        console.log(JSON.stringify(request))
        $.ajax({
            url:"http://localhost:8086/transactions/new",
            type:"POST",
            dataType:"text",
            beforeSend:function(handler){
                handler.setRequestHeader("Authorization","Basic "+btoa("eeksha06:eeksha123"))
            },
            contentType:"application/json;charset=utf-8",
            data:JSON.stringify(request),
            success:function(response){
                alert(response)
                let element=$("#status")
                element.append(`<h1>Transaction to ${payeeAcc} is success</h1>`)
                element.empty()
            },
            error:function(xhr, status, error){
                alert("Error: " + xhr.responseText);//exception in alert
                let element=$("#status")
                element.append(`<h1>Transaction to ${payeeAcc} is failed</h1>`)
                element.empty()
            }
        })
    
    })    
})