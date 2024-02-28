import React, { useState } from "react";
import "/src/assets/CSS/navbar.css";

function NavBar() {
    const [dropdownOpen, setDropdownOpen] = useState(false);
    const [isLoading, setIsLoading] = useState(false); // State for loading spinner visibility

    const toggleDropdown = () => {
        setDropdownOpen(!dropdownOpen);
    };

    const signOut = () => {
        setIsLoading(true); // Show the spinner

        // Simulate a delay for signing out
        setTimeout(() => {
            setIsLoading(false);
            window.location.href = "Cover page.html?signedOut=true";
        }, 3000); // 3 second delay
    };

    return (
        <>
            {/* Navigation bar */}
            <nav className="navbar navbar-expand-lg navbar-dark bg-dark sticky-top shadow">
                <div className="container-fluid">
                    <div className="navbar-brand dropdown">
                        <a
                            href="#"
                            className={`nav-link dropdown-toggle ${dropdownOpen ? "show" : ""
                                }`}
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
                            <li>
                                <a className="dropdown-item" href="#">
                                    Profile
                                </a>
                            </li>
                            <li>
                                <a className="dropdown-item" href="#">
                                    Settings
                                </a>
                            </li>
                            <li>
                                <hr className="dropdown-divider" />
                            </li>
                            <li>
                                <a className="dropdown-item" href="#" onClick={signOut}>
                                    Sign out
                                </a>
                            </li>
                        </ul>
                    </div>

                    {/* Toggler for mobile screens */}
                    <button
                        className="navbar-toggler"
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#navbarNavDropdown"
                        aria-controls="navbarNavDropdown"
                        aria-expanded="false"
                        aria-label="Toggle navigation"
                    >
                        <span className="navbar-toggler-icon"></span>
                    </button>

                    <div
                        className="collapse navbar-collapse justify-content-center"
                        id="navbarNavDropdown"
                    >
                        <form className="form-inline">
                            <div className="input-group rounded-pill">
                                <input
                                    className="form-control rounded-pill me-2"
                                    type="search"
                                    placeholder="Search"
                                    aria-label="Search"
                                    style={{ width: "400px" }}
                                />
                                <button
                                    className="btn btn-outline-primary rounded-pill"
                                    type="submit"
                                >
                                    <img
                                        src="\src\components\NavBar\search.svg"
                                        alt="Person Square"
                                    />
                                </button>
                            </div>
                        </form>
                    </div>

                    {/* Sign out on the right */}
                    <div className="d-flex align-items-center">
                        <a
                            className="nav-link text-white"
                            href="#"
                            id="signOutLink"
                            onClick={signOut}
                            style={{
                                fontSize: "1.25rem",
                                paddingTop: "0.3125rem",
                                paddingBottom: "0.3125rem",
                            }}
                        >
                            Sign out
                        </a>
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
};

export default NavBar;
