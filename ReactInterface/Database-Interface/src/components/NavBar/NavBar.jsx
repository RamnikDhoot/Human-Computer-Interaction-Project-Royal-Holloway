import React, { useState } from "react";
import {
    FileText,
    Home,
    File,
    ShoppingCart,
    Calendar,
    Package,
    Mic,
    PlusCircle,
} from "react-feather";
import styles from'/src/components/NavBar/navbar.module.css';

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
            setIsLoading(false); // You might not actually need to hide the spinner here since we're redirecting
            window.location.href = "Cover page.html?signedOut=true";
        }, 3000); // 3 second delay
    };

    const [reports, setReports] = useState([
        { name: "Current month" },
        { name: "Last quarter" },
        { name: "Best products" },
    ]); // Initial reports

    const addNewReport = () => {
        const reportName = prompt(
            "Please enter the name for the new report:",
            "New Report Name"
        );
        if (reportName && reportName.trim() !== "") {
            setReports((currentReports) => [...currentReports, { name: reportName }]);
        } else {
            alert("You must enter a name to add a new report.");
        }
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

            <div className="container-fluid">
                <div className="row">
                    {/* Sidebar */}
                    <nav
                        id="sidebarMenu"
                        className="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse"
                    >
                        <div className="position-sticky pt-3 mt-2">
                            <ul className="nav flex-column">
                                <li className="nav-item">
                                    <a className="nav-link" href="Dashboard.html">
                                        <Home className="feather" />
                                        Dashboard
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a className="nav-link" href="#">
                                        <File className="feather" />
                                        Orders
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a className="nav-link" href="#">
                                        <ShoppingCart className="feather" />
                                        Products
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a className="nav-link" href="#">
                                        <Calendar className="feather" />
                                        Calendar
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a className="nav-link" href="#">
                                        <Package className="feather" />
                                        Deliverys
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a
                                        className="nav-link active"
                                        aria-current="page"
                                        href="Home.html"
                                    >
                                        <Home className="feather" />
                                        Home
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a className="nav-link" href="#">
                                        <Mic className="feather" />
                                        Voice control
                                    </a>
                                </li>
                            </ul>
                            <h6 className="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                                <span>Your inventory's sale data</span>
                                <a
                                    className="link-secondary"
                                    href="#"
                                    onClick={addNewReport}
                                    aria-label="Add a new report"
                                >
                                    <PlusCircle className="feather" />
                                </a>
                            </h6>
                            <ul className="nav flex-column mb-2" id="reportList">
                                {reports.map((report, index) => (
                                    <li className="nav-item" key={index}>
                                        <a className="nav-link" href="Sale data.html">
                                            <FileText className="feather" />
                                            {report.name}
                                        </a>
                                    </li>
                                ))}
                            </ul>
                        </div>
                    </nav>
                </div>
            </div>
        </>
    );
};

export default NavBar;
