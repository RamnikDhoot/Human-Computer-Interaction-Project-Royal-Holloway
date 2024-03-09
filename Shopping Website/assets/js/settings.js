
// Tracks if the screen reading is active
let isReading = false;
    
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





// Event listener to handle DOMContentLoaded
document.addEventListener("DOMContentLoaded", function () {
setupVoiceControl();
setupDraggableMicrophone();
setupMicrophoneClick();
});

// Sets up voice control functionality and stores the user's preference
function setupVoiceControl() {
  const voiceControlCheckbox = document.getElementById('voiceControlToggle');
  const floatingMicrophone = document.getElementById('floatingMicrophone');

  // Toggle the microphone based on the voice control checkbox
  voiceControlCheckbox.addEventListener('change', function () {
    floatingMicrophone.style.display = voiceControlCheckbox.checked ? 'flex' : 'none';
    localStorage.setItem("voiceControlEnabled", voiceControlCheckbox.checked);
  });

  // Initial state of the microphone based on stored settings
  floatingMicrophone.style.display = localStorage.getItem("voiceControlEnabled") === "true" ? 'flex' : 'none';
}

// Sets up the functionality for the draggable microphone
function setupDraggableMicrophone() {
const floatingMicrophone = document.getElementById('floatingMicrophone');
let isDragging = false;

floatingMicrophone.addEventListener('mousedown', function (event) {
  // Prevent default action to avoid text selection, etc.
  event.preventDefault();
  isDragging = true;
  const shiftX = event.clientX - floatingMicrophone.getBoundingClientRect().left;
  const shiftY = event.clientY - floatingMicrophone.getBoundingClientRect().top;

  // Disable text selection on the whole document to prevent highlighting
  document.body.style.userSelect = 'none';

  function onMouseMove(event) {
      if (isDragging) {
          floatingMicrophone.style.left = event.pageX - shiftX + 'px';
          floatingMicrophone.style.top = event.pageY - shiftY + 'px';
      }
  }

  document.addEventListener('mousemove', onMouseMove);

  document.addEventListener('mouseup', function () {
      isDragging = false;
      document.removeEventListener('mousemove', onMouseMove);
      // Re-enable text selection after dragging ends
      document.body.style.userSelect = '';
  }, { once: true });

  floatingMicrophone.ondragstart = function() {
      return false;
  };
});
}

// Handles microphone button clicks, toggles active state and icon
function setupMicrophoneClick() {
const floatingMicrophone = document.getElementById('floatingMicrophone');
floatingMicrophone.addEventListener('click', function () {
// Toggle microphone active state
const isActive = floatingMicrophone.classList.toggle('active');

// Update the SVG icon based on the active state
floatingMicrophone.innerHTML = isActive ? 
`<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-mic-fill" viewBox="0 0 16 16">
  <path d="M5 3a3 3 0 0 1 6 0v5a3 3 0 0 1-6 0z"/>
  <path d="M3.5 6.5A.5.5 0 0 1 4 7v1a4 4 0 0 0 8 0V7a.5.5 0 0 1 1 0v1a5 5 0 0 1-4.5 4.975V15h3a.5.5 0 0 1 0 1h-7a.5.5 0 0 1 0-1h3v-2.025A5 5 0 0 1 3 8V7a.5.5 0 0 1 .5-.5"/>
</svg>` :
`<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-mic-mute-fill" viewBox="0 0 16 16">
  <path d="M13 8c0 .564-.094 1.107-.266 1.613l-.814-.814A4 4 0 0 0 12 8V7a.5.5 0 0 1 1 0zm-5 4c.818 0 1.578-.245 2.212-.667l.718.719a5 5 0 0 1-2.43.923V15h3a.5.5 0 0 1 0 1h-7a.5.5 0 0 1 0-1h3v-2.025A5 5 0 0 1 3 8V7a.5.5 0 0 1 1 0v1a4 4 0 0 0 4 4m3-9v4.879L5.158 2.037A3.001 3.001 0 0 1 11 3"/>
  <path d="M9.486 10.607 5 6.12V8a3 3 0 0 0 4.486 2.607m-7.84-9.253 12 12 .708-.708-12-12z"/>
</svg>`;

// Apply red color and animation if active
if (isActive) {
  floatingMicrophone.style.backgroundColor = 'red';
} else {
  // Revert to the original styles if not active
  floatingMicrophone.style.backgroundColor = '#007bff'; //original color
  // Remove animation class 
}
});
}




// Toggles the reading of the screen based on the current state.
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
const textToSpeechCheckbox = document.getElementById(
"textToSpeechCheckbox"
);
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









































































// // This is to check what settings the user has selected to apply it to other pages
// window.onload = function () {
//     if (localStorage.getItem("highContrast") === "true") {
//       toggleHighContrast();
//     }

//     if (localStorage.getItem("largerText") === "true") {
//       toggleLargerText();
//     }
//   };

//   function toggleHighContrast() {
//     document.body.classList.toggle("high-contrast");
//     document.querySelector(".sidebar").classList.toggle("high-contrast");
//     document
//       .querySelector(".main-content")
//       .classList.toggle("high-contrast");
//     document.querySelector("h2").classList.toggle("high-contrast");
//     document
//       .querySelectorAll(".btn-primary")
//       .forEach((btn) => btn.classList.toggle("high-contrast"));

//     // When the user selects high-contrast it is saved in the local storage and applied to other pages
//     localStorage.setItem(
//       "highContrast",
//       document.body.classList.contains("high-contrast")
//     );
//   }

//   function toggleLargerText() {
//     document.body.classList.toggle("larger-text");

//     // When the user selects larger text it is saved in the local storage and applied to other pages
//     localStorage.setItem(
//       "largerText",
//       document.body.classList.contains("larger-text")
//     );
//   }

//   function toggleDarkMode() {
//     document.body.classList.toggle("dark-mode");
//     document.querySelector(".sidebar").classList.toggle("dark-mode");
//     document.querySelector(".main-content").classList.toggle("dark-mode");
//     document.querySelector("h2").classList.toggle("dark-mode");
//     document
//       .querySelectorAll(".btn-primary")
//       .forEach((btn) => btn.classList.toggle("dark-mode"));

//     // Save dark mode preference to local storage
//     localStorage.setItem(
//       "darkMode",
//       document.body.classList.contains("dark-mode")
//     );
//   }

//   function toggleReadScreen() {
//     if (isReading) {
//       stopReading();
//     } else {
//       readScreenContent();
//     }
//   }

//   function readScreenContent() {
//     var contentToRead = document.body.innerText;

//     speechUtterance = new SpeechSynthesisUtterance(contentToRead);

//     // Start speaking
//     speechSynthesis.speak(speechUtterance);

//     isReading = true;
//     updateButtonLabel();
//   }

//   function stopReading() {
//     speechSynthesis.cancel();
//     isReading = false;
//     updateButtonLabel();
//   }

//   function updateButtonState() {
//     var button = document.getElementById("readScreenButton");
//     button.textContent = isReading ? "Stop" : "Read";
//   }

//   document.addEventListener("DOMContentLoaded", (event) => {
//     const textToSpeechCheckbox = document.getElementById(
//       "textToSpeechCheckbox"
//     );
//     const readScreenButton = document.getElementById("readScreenButton");

//     // Function to toggle the visibility of the Read Screen button
//     function toggleReadScreenButton() {
//       if (textToSpeechCheckbox.checked) {
//         readScreenButton.style.display = "none"; // Hide button
//       } else {
//         readScreenButton.style.display = "block"; // Show button
//       }
//     }

//     // Event listener for changes in the Text-to-Speech checkbox
//     textToSpeechCheckbox.addEventListener("change", toggleReadScreenButton);
//     toggleReadScreenButton();
//   });




//   // Voice control
//   document.addEventListener("DOMContentLoaded", function() {
// // Get the elements
// const voiceControlCheckbox = document.getElementById('voiceControlToggle');
// const floatingMicrophone = document.getElementById('floatingMicrophone');

// // Function to toggle the microphone visibility
// function toggleMicrophoneVisibility() {
// if(voiceControlCheckbox.checked) {
//   floatingMicrophone.style.display = 'flex'; // Show the microphone
// } else {
//   floatingMicrophone.style.display = 'none'; // Hide the microphone
// }
// }

// // Event listener for checkbox changes
// voiceControlCheckbox.addEventListener('change', toggleMicrophoneVisibility);

// // Set the initial state of the microphone
// toggleMicrophoneVisibility();
// });

//   document.addEventListener('DOMContentLoaded', function () {
// const floatingMicrophone = document.getElementById('floatingMicrophone');
// let isDragging = false;

// floatingMicrophone.addEventListener('mousedown', function (event) {
// // Prevent any text selection on the page.
// document.body.style.userSelect = 'none';
// isDragging = true;
// let shiftX = event.clientX - floatingMicrophone.getBoundingClientRect().left;
// let shiftY = event.clientY - floatingMicrophone.getBoundingClientRect().top;

// function moveAt(pageX, pageY) {
//   floatingMicrophone.style.left = pageX - shiftX + 'px';
//   floatingMicrophone.style.top = pageY - shiftY + 'px';
// }

// function onMouseMove(event) {
//   if (isDragging) {
//     moveAt(event.pageX, event.pageY);
//   }
// }

// document.addEventListener('mousemove', onMouseMove);

// floatingMicrophone.addEventListener('mouseup', function () {
//   document.removeEventListener('mousemove', onMouseMove);
//   floatingMicrophone.onmouseup = null;
//   isDragging = false;
//   // Restore text selection on the page.
//   document.body.style.userSelect = '';
// }, {once: true});
// });

// floatingMicrophone.ondragstart = function() {
// return false;
// };

// // This is to prevent the microphone from moving slightly below the cursor.
// floatingMicrophone.addEventListener('mousemove', function (e) {
// e.preventDefault();
// });

// // Optional: If you want the dragging to cancel when the mouse leaves the button
// floatingMicrophone.addEventListener('mouseleave', function() {
// if(isDragging){
//   document.dispatchEvent(new MouseEvent('mouseup'));
// }
// });

// });
