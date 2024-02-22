import React, { useState } from 'react';

const NavBar = () => {
  const [loading, setLoading] = useState(false);
  const [reports, setReports] = useState(["Current month", "Last quarter", "Best products"]); // Example report names

  const signOut = () => {
    setLoading(true);
    setTimeout(() => {
      setLoading(false);
      window.location.href = 'Cover page.html?signedOut=true';
    }, 3000); 
  };

  const addNewReport = () => {
    const reportName = prompt("Please enter the name for the new report:", "New Report Name");
    if (reportName && reportName !== "") {
      setReports(prevReports => [...prevReports, reportName]);
    } else {
      alert("You must enter a name to add a new report.");
    }
  };

  return (
    <>
    <nav
            className="navbar navbar-expand-lg navbar-dark bg-dark sticky-top shadow">
            <div className="container-fluid">
                <div className="navbar-brand dropdown">
                    <a href="#" className="nav-link dropdown-toggle"
                        id="navbarWelcomeDropdown" role="button"
                        data-bs-toggle="dropdown" aria-expanded="false">
                        <svg xmlns="http://www.w3.org/2000/svg" width="30"
                            height="30" fill="currentColor"
                            className="bi bi-person-square" viewBox="0 0 16 16">
                            <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z" />
                            <path
                                d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm12 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1v-1c0-1-1-4-6-4s-6 3-6 4v1a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12z" />
                        </svg>
                        <span className="ms-2">Welcome!</span>
                    </a>
                    <ul className="dropdown-menu"
                        aria-labelledby="navbarWelcomeDropdown">
                        <li><a className="dropdown-item" href="#">Profile</a></li>
                        <li><a className="dropdown-item" href="#">Settings</a></li>
                        <li><hr className="dropdown-divider"></li>
                        <li><a className="dropdown-item" href="#">Sign out</a></li>
                    </ul>
                </div>

                <button className="navbar-toggler" type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navbarNavDropdown"
                    aria-controls="navbarNavDropdown" aria-expanded="false"
                    aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>

                <div className="collapse navbar-collapse justify-content-center"
                    id="navbarNavDropdown">
                    <form className="form-inline">
                        <div className="input-group rounded-pill">
                            <input className="form-control rounded-pill me-2"
                                type="search" placeholder="Search"
                                aria-label="Search" style="width: 400px;" />
                            <button className="btn btn-outline-primary rounded-pill"
                                type="submit">
                                <svg xmlns="http://www.w3.org/2000/svg"
                                    width="16" height="16" fill="currentColor"
                                    className="bi bi-search" viewBox="0 0 16 16">
                                    <path
                                        d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
                                </svg>
                            </button>
                        </div>
                    </form>

                </div>

                <div className="d-flex align-items-center">
                    <a className="nav-link text-white" href="#" id="signOutLink"
                        onclick="signOut()"
                        style="font-size: 1.25rem; padding-top: 0.3125rem; padding-bottom: 0.3125rem;">Sign
                        out</a>
                </div>
            </div>
        </nav>

        <div className="container-fluid">
            <div className="row">
                <nav id="sidebarMenu"
                    className="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
                    <div className="position-sticky pt-3">
                        <ul className="nav flex-column">
                            <li className="nav-item">
                                <a className="nav-link" href="Dashboard.html">
                                    <span data-feather="home"></span>
                                    Dashboard
                                </a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="#">
                                    <span data-feather="file"></span>
                                    Orders
                                </a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="#">
                                    <span data-feather="shopping-cart"></span>
                                    Products
                                </a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="#">
                                    <span data-feather="calendar"></span>
                                    Calendar
                                </a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="#">
                                    <span data-feather="package"></span>
                                    Deliverys
                                </a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link active" aria-current="page"
                                    href="Home.html">
                                    <span data-feather="home"></span>
                                    Home
                                </a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="#">
                                    <span data-feather="mic"></span>
                                    Voice control
                                </a>
                            </li>
                        </ul>

                        <h6
                            className="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                            <span>Your inventorys sale data</span>
                            <a className="link-secondary" onclick="addNewReport()"
                                aria-label="Add a new report">
                                <span data-feather="plus-circle"></span>
                            </a>
                        </h6>
                        <ul className="nav flex-column mb-2" id="reportList">

                            <li className="nav-item">
                                <a className="nav-link" href="Sale data.html">
                                    <span data-feather="file-text"></span>
                                    Current month
                                </a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="Sale data.html">
                                    <span data-feather="file-text"></span>
                                    Last quarter
                                </a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="Sale data.html">
                                    <span data-feather="file-text"></span>
                                    Best products
                                </a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
            

    </>
  );
};

export default NavBar;
