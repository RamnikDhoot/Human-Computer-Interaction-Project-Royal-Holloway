// This is to check what settings the user has selected to apply it to other pages
window.onload = function () {
  if (localStorage.getItem("highContrast") === "true") {
    toggleHighContrast();
  }

  if (localStorage.getItem("largerText") === "true") {
    toggleLargerText();
  }

  if (localStorage.getItem("darkMode") === "true") {
    toggleDarkMode();
  }
  toggleFloatingMicrophone();
};

// Toggles high-contrast mode by adding or removing a class to multiple elements.
function toggleHighContrast() {
  document.body.classList.toggle("high-contrast");
  document.querySelector(".sidebar").classList.toggle("high-contrast");
  document.querySelector(".main-content").classList.toggle("high-contrast");
  document.querySelector("h2").classList.toggle("high-contrast");
  document
    .querySelectorAll(".btn-primary")
    .forEach((btn) => btn.classList.toggle("high-contrast"));
}

// Toggles dark mode by adding or removing a class to multiple elements.
function toggleDarkMode() {
  document.body.classList.toggle("dark-mode");
  document.querySelector(".sidebar").classList.toggle("dark-mode");
  document.querySelector(".main-content").classList.toggle("dark-mode");
  document.querySelector("h2").classList.toggle("dark-mode");
  document
    .querySelectorAll(".btn-primary")
    .forEach((btn) => btn.classList.toggle("dark-mode"));
}

// Toggles larger text by adding or removing a class to the body element.
function toggleLargerText() {
  document.body.classList.toggle("larger-text");
}

var isReading = false; // Flag to track if the speech synthesis is currently active.
var speechSynthesis = window.speechSynthesis; // Reference to the speechSynthesis API.
var speechUtterance; // Instance of SpeechSynthesisUtterance to hold the text to be read.

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

function toggleReadScreen() {
  // Toggles the reading of the screen's content.
  if (isReading) {
    stopReading();
  } else {
    readScreenContent();
  }
}

function readScreenContent() {
  // Initiates reading the entire content of the body element.
  var contentToRead = document.body.innerText;

  speechUtterance = new SpeechSynthesisUtterance(contentToRead);

  // Start speaking
  speechSynthesis.speak(speechUtterance);

  isReading = true;
  updateButtonLabel();
}

function stopReading() {
  // Stops reading the content aloud.
  speechSynthesis.cancel();
  isReading = false;
  updateButtonLabel();
}

function updateButtonState() {
  // Updates the label of the button based on the reading state.
  var button = document.getElementById("readScreenButton");
  button.textContent = isReading ? "Stop" : "Read";
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// Adds click event listeners to filter buttons and filters portfolio items based on the selected category.
document.addEventListener("DOMContentLoaded", function () {
  const filters = document.querySelectorAll("#portfolio-flters li"); // Get all filter buttons

  const items = document.querySelectorAll(".portfolio-item"); // Get all items to be filtered

  filters.forEach((filter) => {
    filter.addEventListener("click", function () {
      document
        .querySelector(".filter-active")
        .classList.remove("filter-active"); // Remove 'filter-active' class from all filters

      this.classList.add("filter-active"); // Add 'filter-active' class to clicked filter (change higlighted category)

      const filterValue = this.getAttribute("data-filter");
      showFilteredItems(filterValue);
    });
  });

  function showFilteredItems(filter) {
    let itemsArray = Array.from(items); //Show random photos for example of filtering
    itemsArray.sort(() => Math.random() - 0.5);
    itemsArray.forEach((item) => {
      if (filter === "*" || item.classList.contains(filter.substring(1))) {
        item.style.display = "";
      } else {
        item.style.display = "none";
      }
    });
  }
});

document.addEventListener('DOMContentLoaded', function () {
  var carouselElement = document.querySelector('.carousel');
  if (carouselElement) {
      var carousel = new bootstrap.Carousel(carouselElement, {
          interval: 2000,
      });
  }
});


// Toggles the visibility of a floating microphone for voice control features.
function toggleFloatingMicrophone() {
  const floatingMicrophone = document.getElementById("floatingMicrophone");
  if (localStorage.getItem("voiceControlEnabled") === "true") {
    floatingMicrophone.style.display = "flex";
  } else {
    floatingMicrophone.style.display = "none";
  }
}
