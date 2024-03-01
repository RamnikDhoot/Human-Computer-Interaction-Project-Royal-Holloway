import "bootstrap/dist/css/bootstrap.min.css";
import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import NavBar from "./components/NavBar/NavBar.jsx";
import Footer from "./components/Footer.jsx";
import CoverPage from "./pages/CoverPage.jsx";
import Home from "./pages/Home.jsx";
import SideBar from "./components/SideBar.jsx";
import Dashboard from "./pages/Dashboard.jsx";
import SaleData from "./pages/Sale data.jsx";
import SignIn from "./pages/Login pages/SignIn.jsx";
import TwoFactorAuth from "./pages/Login pages/2FA.jsx";
import Signup from "./pages/Login pages/Signup.jsx";

function App() {
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
      </Routes>
    </Router>
  );
}

export default App;
