let currentItemIndex = 0; // Tracks the current item being highlighted

// Tracks if the screen reading is active
let isReading = false;

function setupKeyPressEvent() {
  document.addEventListener("keydown", function (event) {
    // Check if Enter key is pressed
    if (event.key === "Enter") {
      highlightPartsOfPage();
    }
  });
}

// Function runs when the window is loaded, applies stored settings
window.onload = function () {
  checkAndApplySetting("highContrast", toggleHighContrast);
  checkAndApplySetting("largerText", toggleLargerText);
  checkAndApplySetting("darkMode", toggleDarkMode);
  // Voice control settings are handled in the DOMContentLoaded listener
};

// Function to check and apply a setting based on local storage
function checkAndApplySetting(settingKey, toggleFunction) {
  if (localStorage.getItem(settingKey) === "true") {
    toggleFunction();
  }
}

// Toggles high contrast mode
function toggleHighContrast() {
  toggleClassAndSaveSetting(document.body, "high-contrast", "highContrast");
}

// Toggles larger text
function toggleLargerText() {
  toggleClassAndSaveSetting(document.body, "larger-text", "largerText");
}

// Toggles dark mode
function toggleDarkMode() {
  toggleClassAndSaveSetting(document.body, "dark-mode", "darkMode");
}

// Helper function to toggle a class and save the setting
function toggleClassAndSaveSetting(element, className, settingKey) {
  element.classList.toggle(className);
  localStorage.setItem(settingKey, element.classList.contains(className));
}

// Toggles the screen reader
function toggleReadScreen() {
  if (isReading) {
    stopReading();
  } else {
    startReading();
  }
}

// Starts reading the screen content
function startReading() {
  const contentToRead = document.body.innerText;
  const speechUtterance = new SpeechSynthesisUtterance(contentToRead);
  speechSynthesis.speak(speechUtterance);
  isReading = true;
  updateReadButton();
}

// Stops reading the screen content
function stopReading() {
  speechSynthesis.cancel();
  isReading = false;
  updateReadButton();
}

// Updates the read screen button text
function updateReadButton() {
  const readScreenButton = document.getElementById("readScreenButton");
  readScreenButton.textContent = isReading ? "Stop Reading" : "Read Aloud";
}

// Sets up additional accessibility features and event listeners after the DOM is fully loaded.
document.addEventListener("DOMContentLoaded", function () {
  setupVoiceControl();
  setupDraggableMicrophone();
  setupMicrophoneClick();
});

// Sets up voice control functionality and stores the user's preference
function setupVoiceControl() {
  const voiceControlCheckbox = document.getElementById("voiceControlToggle");
  const floatingMicrophone = document.getElementById("floatingMicrophone");

  // Define a function to update the microphone's visibility based on the checkbox
  function updateMicrophoneVisibility() {
    floatingMicrophone.style.display = voiceControlCheckbox.checked
      ? "flex"
      : "none";
    // Save the state to localStorage
    localStorage.setItem("voiceControlEnabled", voiceControlCheckbox.checked);
  }

  // Attach the change event listener to the checkbox
  voiceControlCheckbox.addEventListener("change", updateMicrophoneVisibility);

  // Call the function to set the initial state based on localStorage or default
  // This ensures the microphone's visibility matches the stored preference or shows by default
  voiceControlCheckbox.checked =
    localStorage.getItem("voiceControlEnabled") === "true";
  updateMicrophoneVisibility();
}

// Sets up the functionality for the draggable microphone
function setupDraggableMicrophone() {
  const floatingMicrophone = document.getElementById("floatingMicrophone");
  let isDragging = false;

  floatingMicrophone.addEventListener("mousedown", function (event) {
    event.preventDefault();
    isDragging = true;

    // Calculate initial offset on mouse down
    let shiftX =
      event.clientX - floatingMicrophone.getBoundingClientRect().left;
    let shiftY = event.clientY - floatingMicrophone.getBoundingClientRect().top;

    function onMouseMove(event) {
      if (isDragging) {
        // Set new position based on mouse location minus initial offset
        floatingMicrophone.style.left = event.clientX - shiftX + "px";
        floatingMicrophone.style.top = event.clientY - shiftY + "px";
      }
    }

    // Attach move and up handlers to document to allow for a smoother drag experience
    document.addEventListener("mousemove", onMouseMove);
    document.addEventListener(
      "mouseup",
      function () {
        isDragging = false;
        document.removeEventListener("mousemove", onMouseMove);
      },
      { once: true }
    );
  });

  floatingMicrophone.ondragstart = function () {
    return false;
  };
}

// Handles microphone button clicks, toggles active state and icon
function setupMicrophoneClick() {
  const floatingMicrophone = document.getElementById("floatingMicrophone");
  floatingMicrophone.addEventListener("click", function () {
    // Toggle microphone active state
    const isActive = floatingMicrophone.classList.toggle("active");

    // Update the SVG icon based on the active state
    floatingMicrophone.innerHTML = isActive
      ? `<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-mic-fill" viewBox="0 0 16 16">
          <path d="M5 3a3 3 0 0 1 6 0v5a3 3 0 0 1-6 0z"/>
          <path d="M3.5 6.5A.5.5 0 0 1 4 7v1a4 4 0 0 0 8 0V7a.5.5 0 0 1 1 0v1a5 5 0 0 1-4.5 4.975V15h3a.5.5 0 0 1 0 1h-7a.5.5 0 0 1 0-1h3v-2.025A5 5 0 0 1 3 8V7a.5.5 0 0 1 .5-.5"/>
        </svg>`
      : `<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-mic-mute-fill" viewBox="0 0 16 16">
          <path d="M13 8c0 .564-.094 1.107-.266 1.613l-.814-.814A4 4 0 0 0 12 8V7a.5.5 0 0 1 1 0zm-5 4c.818 0 1.578-.245 2.212-.667l.718.719a5 5 0 0 1-2.43.923V15h3a.5.5 0 0 1 0 1h-7a.5.5 0 0 1 0-1h3v-2.025A5 5 0 0 1 3 8V7a.5.5 0 0 1 1 0v1a4 4 0 0 0 4 4m3-9v4.879L5.158 2.037A3.001 3.001 0 0 1 11 3"/>
          <path d="M9.486 10.607 5 6.12V8a3 3 0 0 0 4.486 2.607m-7.84-9.253 12 12 .708-.708-12-12z"/>
        </svg>`;

    //Apply red color and animation if active
    if (isActive) {
      floatingMicrophone.style.backgroundColor = "red";
      alert(
        "This is a demonstration of how voice control could work on this website. Please press Enter to move the highlighting."
      );
    } else {
      //Revert to the original styles if not active
      floatingMicrophone.style.backgroundColor = "#007bff"; //original color
      // Remove any highlights when the microphone is turned off
      removeHighlights();
    }
  });
}

// Function to remove all highlights from the page
function removeHighlights() {
  const highlightedElements = document.querySelectorAll(".highlight");
  highlightedElements.forEach((element) => {
    element.classList.remove("highlight");
    element.style.color = ""; //Reset all style changes
  });
  // Reset the index for highlighting parts of the page
  currentItemIndex = 0;
}

//Toggles the reading of the screen based on the current state.
function toggleReadScreen() {
  if (isReading) {
    stopReading();
  } else {
    readScreenContent();
  }
}

// Starts reading the screen content using the Web Speech API's speech synthesis.
function readScreenContent() {
  var contentToRead = document.body.innerText;

  speechUtterance = new SpeechSynthesisUtterance(contentToRead);

  // Start speaking
  speechSynthesis.speak(speechUtterance);

  isReading = true;
  // Updates the button's label to reflect the current action.
  updateButtonLabel();
}

function stopReading() {
  speechSynthesis.cancel();
  isReading = false;
  updateButtonLabel();
}

// Updates the text displayed on the "Read Screen" button based on whether text is being read aloud.
function updateButtonState() {
  var button = document.getElementById("readScreenButton");
  button.textContent = isReading ? "Stop" : "Read";
}

// Sets up event listeners after the DOM content has fully loaded.
document.addEventListener("DOMContentLoaded", (event) => {
  const textToSpeechCheckbox = document.getElementById("textToSpeechCheckbox");
  const readScreenButton = document.getElementById("readScreenButton");

  // Function to toggle the visibility of the Read Screen button
  function toggleReadScreenButton() {
    if (textToSpeechCheckbox.checked) {
      readScreenButton.style.display = "none"; // Hide button
    } else {
      readScreenButton.style.display = "block"; // Show button
    }
  }

  // Event listener for changes in the Text-to-Speech checkbox
  textToSpeechCheckbox.addEventListener("change", toggleReadScreenButton);
  toggleReadScreenButton();
});

// Function to highlight one part of the page at a time
function highlightPartsOfPage() {
  // Define the elements you want to highlight
  const elementsToHighlight = document.querySelectorAll(
    "h2, h4, .btn-primary, input, select"
  );

  // Remove highlight from all elements
  elementsToHighlight.forEach((element) => {
    element.classList.remove("highlight");
    element.style.color = ""; // Reset font color
  });

  //Check if the current index is beyond the number of elements
  if (currentItemIndex >= elementsToHighlight.length) {
    currentItemIndex = 0;
  }

  //Highlight the current element
  if (elementsToHighlight.length > 0) {
    elementsToHighlight[currentItemIndex].classList.add("highlight");
    elementsToHighlight[currentItemIndex].style.color = "black";
  }

  //Move to the next element for the next function call
  currentItemIndex++;
}

//Add the keydown event listener to the document to listen for the Enter key press
document.addEventListener("keydown", function (event) {
  if (event.key === "Enter") {
    highlightPartsOfPage();
  }
});

// JavaScript Function to Show Notifications
function showNotification(message) {
  const container = document.getElementById("notificationContainer");
  const notification = document.createElement("div");
  notification.innerHTML = message;
  notification.style.background = "rgba(0, 123, 255, 0.8)";
  notification.style.color = "#fff";
  notification.style.padding = "10px";
  notification.style.marginBottom = "10px";
  notification.style.borderRadius = "5px";
  notification.style.boxShadow = "0 2px 4px rgba(0,0,0,.2)";
  notification.style.maxWidth = "300px";
  notification.style.fontSize = "14px";

  container.appendChild(notification);

  setTimeout(() => {
    container.removeChild(notification);
  }, 4000);
}

// Functionality for Each Accessibility Checkbox
document.querySelectorAll(".form-check-input").forEach((item) => {
  item.addEventListener("change", function () {
    let settingName = this.nextElementSibling.innerText;
    let state = this.checked ? "enabled" : "disabled";
    let message = `${settingName} has been ${state}.`;
    showNotification(message);
  });
});
