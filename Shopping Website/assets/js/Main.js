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
