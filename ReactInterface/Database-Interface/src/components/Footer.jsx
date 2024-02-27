import React from 'react';
import '/src/assets/CSS/Footer.css';

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
