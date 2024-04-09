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
  Users,
} from "react-feather";
import { useNavigate } from "react-router-dom";
import { NavLink } from "react-router-dom";

import "/src/assets/CSS/navbar.css";
import Tooltip from "../pages/Tooltip";

/**
 * SideBar Component
 * Renders a sidebar navigation menu for a dashboard application, including dynamic report links.
 * The sidebar includes navigation links to different sections such as Dashboard, Orders, Products,
 * Calendar, Delivery, Home, and Voice Control. Additionally, it has a dynamic list of reports that
 * can be added to by the user.
 *
 * @component
 * @requires react
 * @requires react-router-dom
 * @requires react-feather
 * @see {@link https://reactrouter.com/} for React Router documentation.
 * @see {@link https://feathericons.com/} for React Feather icons.
 *
 * @returns {JSX.Element} The Sidebar component.
 */
function SideBar() {
  const navigate = useNavigate();

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
  const [userRole, setUserRole] = useState("guest"); // Possible roles: admin, user, guest


  return (
    <>
      <div className="row">
        {/* Sidebar */}
        <nav
          id="sidebarMenu"
          className="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse"
        >
          <div className="position-sticky pt-3 mt-4">
            <ul className="nav flex-column">
              <Tooltip text="View your dashboard with an overview of performance">
                <li className="nav-item">
                  <NavLink
                    to="/dashboard"
                    className="nav-link"
                    activeClassName="active"
                  >
                    <Home className="feather" /> Dashboard
                  </NavLink>
                </li>
              </Tooltip>
              <Tooltip text="Manage and track orders">
                <li className="nav-item">
                  <NavLink
                    to="/orders"
                    className="nav-link"
                    activeClassName="active"
                  >
                    <File className="feather" /> Orders
                  </NavLink>
                </li>
              </Tooltip>
              <Tooltip text="Access your product inventory">
                <li className="nav-item">
                  <NavLink
                    to="/products"
                    className="nav-link"
                    activeClassName="active"
                  >
                    <ShoppingCart className="feather" /> Products
                  </NavLink>
                </li>
              </Tooltip>
              <Tooltip text="Access your calendar">
                <li className="nav-item">
                  <NavLink
                    to="/products"
                    className="nav-link"
                    activeClassName="active"
                  >
                    <Calendar className="feather" />
                    Calendar
                  </NavLink>
                </li>
              </Tooltip>
              <Tooltip text="Access your Deliverys">
                <li className="nav-item">
                  <NavLink
                    to="/products"
                    className="nav-link"
                    activeClassName="active"
                  >
                    <Package className="feather" />
                    Deliverys
                  </NavLink>
                </li>
              </Tooltip>
              <Tooltip text="Homepage where you can see all of your inventorys">
                <li className="nav-item">
                  <NavLink
                    to="/home"
                    className="nav-link"
                    activeClassName="active"
                  >
                    <Home className="feather" />
                    Home
                  </NavLink>
                </li>
              </Tooltip>
              
              <Tooltip text="User Management">
  <li className="nav-item">
    <NavLink to="/users" className={({ isActive }) => isActive ? "nav-link active" : "nav-link"}>
      <Users className="feather" /> User Management
    </NavLink>
  </li>
</Tooltip>

              
              <Tooltip text="Access Voice controls">
                <li className="nav-item">
                  <NavLink
                    to="/products"
                    className="nav-link"
                    activeClassName="active"
                  >
                    <Mic className="feather" />
                    Voice control
                  </NavLink>
                </li>
              </Tooltip>
            </ul>
            <Tooltip text="Add a new report to your list">
              <h6 className="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                <span>Your inventory's sale data</span>
                <a
                  className="link-secondary"
                  onClick={addNewReport}
                  aria-label="Add a new report"
                >
                  <PlusCircle className="feather" />
                </a>
              </h6>
            </Tooltip>
            <ul className="nav flex-column mb-2" id="reportList">
              {reports.map((report, index) => (
                <Tooltip key={index} text={`View report: ${report.name}`}>
                  <li className="nav-item">
                    <NavLink
                      to="/saledata"
                      className="nav-link"
                      activeClassName="active"
                    >
                      <FileText className="feather" />
                      {report.name}
                    </NavLink>
                  </li>
                </Tooltip>
              ))}
            </ul>
          </div>
        </nav>
      </div>
    </>
  );
}

export default SideBar;
