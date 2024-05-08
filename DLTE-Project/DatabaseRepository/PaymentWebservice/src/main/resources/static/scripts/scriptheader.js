
function clearSession() {
    sessionStorage.removeItem('selectedAccountNumber');
}
$(document).ready(()=> {
    function getUsername() {
        $.ajax({
            type: "GET",
            url: "/payment/name",
            dataType: "text",
            contentType: "application/json;charset=utf-8",
            success: function (response) {
                $('#Username').text("Hi," + response); // Display the name
            },
            error: function (xhr, status, error) {
                console.error(xhr.responseText);
                $('#Username').text("Error fetching name");
            }
        });
    }
    getUsername();

    var performance = window.performance || window.mozPerformance || window.msPerformance || window.webkitPerformance || {};
    var navigation = performance.navigation || {};
    var isReload = navigation.type === navigation.TYPE_RELOAD;

    // Add event listener to clear session only on page reload
    if (isReload) {
        clearSession();
    }
});

