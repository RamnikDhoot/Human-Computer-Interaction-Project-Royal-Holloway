function signOut() {
    document.querySelector('.loading-spinner').style.display = 'block';

    // Simulate a delay for signing out
    setTimeout(function() {
        window.location.href = 'Cover page.html?signedOut=true';
    }, 3000); // 3 second
}

//////////////////////////////////////////////////////////////////////////////
function addNewReport() {
// Ask the user for the report name
var reportName = prompt("Please enter the name for the new report:", "New Report Name");

// Check if the user provided a name
if (reportName === null || reportName === "") {
    alert("You must enter a name to add a new report.");
    return; 
}

// Create a new list item
var newItem = document.createElement("li");
newItem.classList.add("nav-item");

// Add content to the new item
newItem.innerHTML = '<a class="nav-link" href="Sale data.html">' +
                    '<span data-feather="file-text"></span> ' + reportName +
                    '</a>';

// Find the report list and append the new item
document.getElementById("reportList").appendChild(newItem);

// Reinitialize feather icons to ensure the new icon is rendered correctly
feather.replace();
}