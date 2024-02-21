// Progress Bar
document.addEventListener('DOMContentLoaded', function () {
    var currentStep = 3; // Starting at Step 3 bacue you would already be in the basket

    function updateProgressBar(step) {
        var steps = [
            "Step 1: Add item to basket",
            "Step 2: Go to basket",
            "Step 3: Check items are correct",
            "Step 4: Put billing information",
            "Step 5: Choose payment method",
            "Step 6: Put payment info",
            "Step 7: Done"
        ];
        var percentage = (step / steps.length) * 100;
        var progressBar = document.querySelector('.progress-bar');
        progressBar.style.width = percentage + '%';
        progressBar.setAttribute('aria-valuenow', percentage);
        progressBar.textContent = steps[step - 1];
    }

    document.getElementById('itemsCorrectBtn').addEventListener('click', function() {
        if (currentStep === 3) {
            currentStep = 4;
            updateProgressBar(currentStep);
        }
    });

    document.getElementById('billingInfoForm').addEventListener('input', function() {
        // Check if all required fields in billingInfoForm are filled
        var allFilled = Array.from(this.querySelectorAll('[required]')).every(input => input.value.trim() !== '');
        if (currentStep === 4 && allFilled) {
            currentStep = 5;
            updateProgressBar(currentStep);
        }
    });

    document.getElementById('paymentTypeForm').addEventListener('change', function() {
        // Check if a payment method is selected
        if (currentStep === 5 && this.querySelector('[name="paymentMethod"]:checked')) {
            currentStep = 6;
            updateProgressBar(currentStep);
        }
    });

    document.getElementById('paymentInfoForm').addEventListener('input', function() {
        // Check if all required fields in paymentInfoForm are filled
        var allFilled = Array.from(this.querySelectorAll('[required]')).every(input => input.value.trim() !== '');
        if (currentStep === 6 && allFilled) {
            currentStep = 7;
            updateProgressBar(currentStep);
        }
    });

    // Initialize the progress bar at the current step
    updateProgressBar(currentStep);
});


// Progress bar colour
document.addEventListener('DOMContentLoaded', function () {
    var currentStep = 3; 

    function updateProgressBar(step) {
        var steps = [
            "Step 1: Add item to basket",
            "Step 2: Go to basket",
            "Step 3: Check items are correct",
            "Step 4: Put billing information",
            "Step 5: Choose payment method",
            "Step 6: Put payment info",
            "Step 7: Done"
        ];
        var percentage = (step / steps.length) * 100;
        var progressBar = document.querySelector('.progress-bar');
        if (!progressBar) {
            console.error('Progress bar element not found!');
            return;
        }

        // Clear existing classes and apply the current step's color
        progressBar.className = 'progress-bar';
        progressBar.classList.add('step-' + step + '-color'); 

        progressBar.style.width = percentage + '%';
        progressBar.setAttribute('aria-valuenow', percentage);
        progressBar.textContent = steps[step - 1];

        console.log('Progress bar updated to step:', step); 
    }

    document.getElementById('itemsCorrectBtn').addEventListener('click', function() {
        if (currentStep < 7) { 
            currentStep++;
            updateProgressBar(currentStep);
        }
    });

    updateProgressBar(currentStep);
});

document.addEventListener('DOMContentLoaded', function() {
    const progressSteps = [
      "Step 1: Add item to basket",
      "Step 2: Go to basket",
      "Step 3: Check items are correct",
      "Step 4: Put billing information",
      "Step 5: Choose payment method",
      "Step 6: Put payment info",
      "Step 7: Done"
    ];
  
    const progressBar = document.querySelector('.progress-bar');
    const stepDescription = document.getElementById('stepDescription');
  
    // Update progress bar when adding an item to the basket
    document.querySelector('.add-to-cart').addEventListener('click', function() {
      progressBar.style.width = '28.56%'; // Move to Step 2
      progressBar.setAttribute('aria-valuenow', '28.56');
      progressBar.textContent = progressSteps[1]; // "Step 2: Go to basket"
      stepDescription.textContent = progressSteps[1];
    });
  
    // Function to update progress on page load if coming from the single item page
    function updateProgressForBasketPage() {
      const currentStep = sessionStorage.getItem('shoppingProgress');
      if (currentStep === "Step 2: Go to basket") {
        progressBar.style.width = '28.56%';
        progressBar.setAttribute('aria-valuenow', '28.56');
        progressBar.textContent = currentStep;
        stepDescription.textContent = "Next, check items are correct";
      }
    }
  
    // Check if we need to update progress on load (for basket page scenario)
    updateProgressForBasketPage();
  
    // Optional: Save progress in session storage when adding item to basket
    document.querySelector('.add-to-cart').addEventListener('click', function() {
      sessionStorage.setItem('shoppingProgress', "Step 2: Go to basket");
    });
  });
  

  
  // Function to update the progress bar and step description
    document.addEventListener('DOMContentLoaded', function() {
      let currentStep = 1; 
    
      function updateProgressBar(step) {
        const progressSteps = [
          "Step 1: Add item to basket",
          "Step 2: Go to basket",
          "Step 3: Check items are correct",
          "Step 4: Put billing information",
          "Step 5: Choose payment method",
          "Step 6: Put payment info",
          "Step 7: Done"
        ];
    
        const progressBar = document.querySelector('.progress-bar');
        const stepDescription = document.getElementById('stepDescription');
    
        const percentage = (step / progressSteps.length) * 100;
    
        // Update progress bar
        progressBar.style.width = `${percentage}%`;
        progressBar.setAttribute('aria-valuenow', percentage);
        progressBar.textContent = progressSteps[step - 1];
        stepDescription.textContent = progressSteps[step - 1];
    
        // Clear existing classes and apply the current step's color
        progressBar.className = 'progress-bar'; // Reset classes
        progressBar.classList.add('step-' + step + '-color'); // Apply new step color
      }
    
      // Attach click event listener to the "Add to cart" button
      document.querySelector('.add-to-cart').addEventListener('click', function() {
        currentStep = 2; // Move to step 2
        updateProgressBar(currentStep);
      });
    
      // Initialize with current step
      updateProgressBar(currentStep);
    });