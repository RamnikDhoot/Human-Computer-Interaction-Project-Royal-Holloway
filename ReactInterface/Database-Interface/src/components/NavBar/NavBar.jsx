import React, { useState } from "react";
import { useNavigate } from "react-router-dom"; 
import "/src/assets/CSS/navbar.css";

/**
 * A component that renders the NavBar with dropdown and sign-out functionality.
 * 
 * This component utilizes React's useState hook for managing the state of the dropdown menu and loading indicator.
 * It provides a toggle function to show or hide the dropdown menu and a signOut function to handle user sign-out,
 * showing a loading spinner for a brief period before navigating to the home page.
 * 
 * The NavBar includes a welcome message with a dropdown for profile, settings, and sign-out options, a search bar,
 * and a sign-out link. The loading spinner is displayed when the sign-out process is initiated.
 * 
 * @returns {JSX.Element} The NavBar component with a dropdown menu, search bar, and sign-out functionality.
 */
function NavBar() {
    const [dropdownOpen, setDropdownOpen] = useState(false);
    const [isLoading, setIsLoading] = useState(false); 
    const navigate = useNavigate(); 

    /**
     * Toggles the dropdown menu open or closed.
     */
    const toggleDropdown = () => {
        setDropdownOpen(!dropdownOpen);
    };

    /**
     * Handles the sign-out action.
     * Displays a loading spinner for 3 seconds before navigating to the home page.
     */
    const signOut = () => {
        setIsLoading(true); // Show the spinner

        // Simulate a delay for signing out
        setTimeout(() => {
            setIsLoading(false);
            navigate("/"); 
        }, 3000); // 3 second delay
    };

    return (
        <>
            <nav className="navbar navbar-expand-lg sticky-top">
                <div className="container-fluid">
                    {/* Hamburger toggler for mobile screens */}
                    <button
                        className="navbar-toggler"
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#navbarContent"
                        aria-controls="navbarContent"
                        aria-expanded="false"
                        aria-label="Toggle navigation"
                    >
                        <span className="navbar-toggler-icon"></span>
                    </button>

                    {/* Collapsible content */}
                    <div className="collapse navbar-collapse" id="navbarContent">
                        {/* Brand/Dropdown for larger screens or non-collapsed view */}
                        <div className="navbar-brand dropdown">
                            <a
                                href="#"
                                className={`nav-link dropdown-toggle ${dropdownOpen ? "show" : ""}`}
                                id="navbarWelcomeDropdown"
                                role="button"
                                onClick={toggleDropdown}
                                aria-expanded={dropdownOpen}
                            >
                                <img
                                    src="\src\components\NavBar\person-square.svg"
                                    alt="Person Square"
                                />
                                <span className="ms-2">Welcome!</span>
                            </a>
                            <ul
                                className={`dropdown-menu ${dropdownOpen ? "show" : ""}`}
                                aria-labelledby="navbarWelcomeDropdown"
                            >
                                <li><a className="dropdown-item" href="#">Profile</a></li>
                                <li><a className="dropdown-item" href="#">Settings</a></li>
                                <li><hr className="dropdown-divider" /></li>
                                <li><a className="dropdown-item" onClick={signOut}>Sign out</a></li>
                            </ul>
                        </div>

                        {/* Search form */}
                        <form className="form-inline mt-3 mx-auto">
                            <div className="input-group rounded-pill">
                                <input
                                    className="form-control rounded-pill me-2"
                                    type="search"
                                    placeholder="Search"
                                    aria-label="Search"
                                />
                                <button
                                    className="btn btn-outline-primary rounded-pill"
                                    type="submit"
                                >
                                    <img
                                        src="\src\components\NavBar\search.svg"
                                        alt="Search"
                                    />
                                </button>
                            </div>
                        </form>

                        {/* Sign out link */}
                        <div className="d-flex align-items-center">
                            <a
                                className="nav-link text-white btn btn-danger"
                                id="signOutLink"
                                onClick={() => {
                                  if (window.confirm("Are you sure you want to sign out?")) {
                                    signOut();
                                  }
                                }}
                                style={{ fontSize: "1.25rem" }}
                            >
                                Sign out
                            </a>
                        </div>
                    </div>
                </div>
            </nav>

            {/* Loading spinner */}
            {isLoading && (
                <div
                    className="loading-spinner"
                    style={{
                        display: "block",
                        position: "fixed",
                        top: "50%",
                        left: "50%",
                        transform: "translate(-50%, -50%)",
                    }}
                >
                    <div className="spinner-border text-primary" role="status">
                        <span className="visually-hidden">Loading...</span>
                    </div>
                </div>
            )}
        </>
    );
}

export default NavBar;
