import React from 'react';
import '/src/assets/CSS/Footer.css';

/**
 * Renders a simple footer.
 * 
 * The Footer component displays copyright information, and links to pages like About Us, Contact, and Privacy Policy.
 * 
 * @returns {JSX.Element} A Footer component displaying copyright information and useful links.
 */
function Footer() {
  return (
    <footer className="simple-footer">
      <div className="container">
          <p>&copy; 2024 Company Name. All rights reserved.</p>
          <ul>
              <li><a href="/about">About Us</a></li>
              <li><a href="/contact">Contact</a></li>
              <li><a href="/privacy">Privacy Policy</a></li>
          </ul>
      </div>
    </footer>
  );
}

export default Footer;
