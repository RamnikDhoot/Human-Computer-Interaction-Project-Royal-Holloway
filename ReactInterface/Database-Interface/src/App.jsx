/**
 * Main Application Entry File.
 * 
 * This file imports necessary React components, styles, and sets up routing for the application using React Router.
 * It defines the main `App` function component that renders the application routes and associated pages/components.
 * 
 * @module App
 */
import "bootstrap/dist/css/bootstrap.min.css";// Importing Bootstrap CSS for global styling.

import React from "react";// React core import for building components.

import { BrowserRouter as Router, Routes, Route } from "react-router-dom";// Importing BrowserRouter, Routes, and Route for managing routing.


import NavBar from "./components/NavBar/NavBar.jsx";
import Footer from "./components/Footer.jsx";
// Importing page components.
import CoverPage from "./pages/CoverPage.jsx";
import Home from "./pages/Home.jsx";
import SideBar from "./components/SideBar.jsx";
import Dashboard from "./pages/Dashboard.jsx";
import SaleData from "./pages/Sale data.jsx";
import SignIn from "./pages/Login pages/SignIn.jsx";
import TwoFactorAuth from "./pages/Login pages/2FA.jsx";
import Signup from "./pages/Login pages/Signup.jsx";
import UserManagement from "./pages/UserManagament.jsx";

/**
 * App is the main function component that uses React Router to define the application's navigation structure.
 * It renders the navigation bar, sidebar, and the current page based on the route.
 * 
 * @returns {React.Component} The Router component wrapping the application's Routes.
 */function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<CoverPage />} />
        <Route path="/signin" element={<SignIn />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/2FA" element={<TwoFactorAuth />} />
        <Route
          path="/dashboard"
          element={
            <>
              <NavBar />
              <div className="container-fluid">
                <SideBar />
                <Dashboard />
              </div>
            </>
          }
        />
        <Route
          path="/home"
          element={
            <>
              <NavBar />
              <div className="container-fluid">
                <SideBar />
                <Home />
              </div>
            </>
          }
        />
        <Route
          path="/saledata"
          element={
            <>
              <NavBar />
              <div className="container-fluid">
                <SideBar />
                <SaleData />
              </div>
            </>
          }
        />
        <Route path="/Footer" element={<Footer />} />
        <Route
          path="/users"
          element={
            <>
              <NavBar />
              <div className="container-fluid">
                <SideBar />
                <UserManagement />
              </div>
            </>
          }
        />
        <Route path="/Footer" element={<Footer />} />
        
      </Routes>
    </Router>
  );
}

export default App;
