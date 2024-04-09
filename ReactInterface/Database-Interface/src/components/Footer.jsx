import React from "react";
import "/src/assets/CSS/Footer.css";
import Tooltip from "../pages/Tooltip";
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
          <Tooltip text="Learn more about our company">
            <li>
              <a href="/about">About Us</a>
            </li>
          </Tooltip>
          <Tooltip text="Get in touch with us">
            <li>
              <a href="/contact">Contact</a>
            </li>
          </Tooltip>
          <Tooltip text="Read our privacy policy">
            <li>
              <a href="/privacy">Privacy Policy</a>
            </li>
          </Tooltip>
        </ul>
      </div>
    </footer>
  );
}

export default Footer;
