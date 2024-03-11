// This is to check what settings the user has selected to apply it to other pages
  window.onload = function () {
      if (localStorage.getItem('highContrast') === 'true') {
          toggleHighContrast();
      }

      if (localStorage.getItem('largerText') === 'true') {
          toggleLargerText();
      }

      if (localStorage.getItem('darkMode') === 'true') {
        toggleDarkMode();
    }
    toggleFloatingMicrophone();
  };

  function toggleHighContrast() {
      document.body.classList.toggle('high-contrast');
      document.querySelector('.sidebar').classList.toggle('high-contrast');
      document.querySelector('.main-content').classList.toggle('high-contrast');
      document.querySelector('h2').classList.toggle('high-contrast');
      document.querySelectorAll('.btn-primary').forEach(btn => btn.classList.toggle('high-contrast'));

  }

  function toggleDarkMode() {
    document.body.classList.toggle('dark-mode');
    document.querySelector('.sidebar').classList.toggle('dark-mode');
    document.querySelector('.main-content').classList.toggle('dark-mode');
    document.querySelector('h2').classList.toggle('dark-mode');
    document.querySelectorAll('.btn-primary').forEach(btn => btn.classList.toggle('dark-mode'));
  }

  function toggleLargerText() {
      document.body.classList.toggle('larger-text');

  }

  var isReading = false;
  var speechSynthesis = window.speechSynthesis;
  var speechUtterance;

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  function toggleReadScreen() {
    if (isReading) {
      stopReading();
    } else {
      readScreenContent();
    }
  }

  function readScreenContent() {
    var contentToRead = document.body.innerText;

    speechUtterance = new SpeechSynthesisUtterance(contentToRead);

    // Start speaking
    speechSynthesis.speak(speechUtterance);

    isReading = true;
    updateButtonLabel();
  }

  function stopReading() {
    speechSynthesis.cancel();
    isReading = false;
    updateButtonLabel();
  }

  function updateButtonState() {
    var button = document.getElementById('readScreenButton');
    button.textContent = isReading ? 'Stop' : 'Read';
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//Portfolio section
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
    let itemsArray = Array.from(items); //Show random photos
    itemsArray.sort(() => Math.random() - 0.5);
    itemsArray.forEach((item) => {
      if (
        filter === "*" ||
        item.classList.contains(filter.substring(1))
      ) {
        item.style.display = "";
      } else {
        item.style.display = "none";
      }
    });
  }
});

$(".carousel").carousel({
  interval: 2000, // Adjust time interval for auto-scroll in milliseconds
});

// Function to toggle the floating microphone
function toggleFloatingMicrophone() {
  const floatingMicrophone = document.getElementById("floatingMicrophone");
  if (localStorage.getItem("voiceControlEnabled") === "true") {
    floatingMicrophone.style.display = "flex";
  } else {
    floatingMicrophone.style.display = "none";
  }
}
