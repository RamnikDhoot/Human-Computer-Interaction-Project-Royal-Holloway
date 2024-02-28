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
import "/src/assets/CSS/navbar.css";

function SideBar() {
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
        </>
    );
};

export default SideBar;
