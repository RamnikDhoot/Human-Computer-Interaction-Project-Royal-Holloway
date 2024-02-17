//https://stackoverflow.com/questions/7702730/how-to-replace-javascript-alert-which-pops-up-where-the-event-called-it-with-a
function showInventoryDetails(inventoryId) {
    alert("Details for Inventory " + inventoryId);
}
function changeInventoryName(inventoryId) {
    var newName = document.getElementById('newName' + inventoryId).value;
    alert("Changing name for Inventory " + inventoryId + " to: " + newName);
}
function changeInventoryPhoto(inventoryId) {
    alert("Changing photo for Inventory " + inventoryId);
}

document.getElementById('signOutLink').addEventListener('click', function(event) {
    event.preventDefault(); // Prevent the default anchor behavior
    alert('You have been successfully signed out.');
    window.location.href = 'Cover page.html'; // Redirect to cover.html
});

// Adding inventory cards
const inventoryContainer = document.querySelector('.row.row-cols-3.g-4'); 
const addInventoryButton = document.querySelector('.btn.btn-primary.btn-lg'); 
addInventoryButton.addEventListener('click', function() {
    const inventoryTemplate = `
    <div class="col position-relative">
                    <div class="card h-100">
                        <img src="/DBMS Interface/images/cover.jpg"
                            class="card-img-top" alt="Inventory 2">
                        <div class="card-body">
                            <h5 class="card-title">Inventory 2</h5>
                            <p class="card-text">Description of
                                Inventory 2.</p>
                        </div>
                        <!-- Popup menu -->
                        <div class="inventory-popup">
                            <label for="newName1">New Name:</label>
                            <input type="text" id="newName1"
                                placeholder="Enter new name">
                            <button
                                onclick="changeInventoryName(1)">Change
                                Name</button>

                            <label for="newPhoto1">New Photo:</label>
                            <input type="file" id="newPhoto1"
                                accept="image/*">
                            <button
                                onclick="changeInventoryPhoto(1)">Change
                                Photo</button>
                        </div>
                    </div>
                </div>
`;
const newInventoryItem = document.createElement('div');
newInventoryItem.innerHTML = inventoryTemplate;
inventoryContainer.appendChild(newInventoryItem); 
});