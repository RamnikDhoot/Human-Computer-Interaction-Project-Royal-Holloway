import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom"; 
import { User, Mail, Lock } from "react-feather";
import "/src/assets/CSS/Signin.css";

function Signup() {
  const [formData, setFormData] = useState({
    username: "",
    email: "",
    password: "",
    confirmPassword: "",
  });
  const [passwordStrength, setPasswordStrength] = useState(0);
  const [passwordDescription, setPasswordDescription] = useState(
    "Enter a password to see its strength."
  );
  const [progressBarColor, setProgressBarColor] = useState("");
  const navigate = useNavigate(); 

  useEffect(() => {
    calculateStrength();
  }, [formData.password]);

  const calculateStrength = () => {
    let strength = 0;
    const { password } = formData;
    if (password.length >= 6) strength += 20;
    if (/[A-Z]/.test(password)) strength += 20;
    if (/[a-z]/.test(password)) strength += 20;
    if (/[0-9]/.test(password)) strength += 20;
    if (/[^A-Za-z0-9]/.test(password)) strength += 20;

    setPasswordStrength(strength);

    let description = "";
    let color = "";
    if (strength < 40) {
      description = "Weak - Try adding numbers or special characters.";
      color = "bg-danger";
    } else if (strength < 60) {
      description = "Moderate - Improve strength with mixed case letters.";
      color = "bg-warning";
    } else if (strength <= 80) {
      description = "Good - Make it stronger with more unique characters.";
      color = "bg-info";
    } else {
      description = "Strong - Great job!";
      color = "bg-success";
    }

    setPasswordDescription(description);
    setProgressBarColor(color);
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (
      formData.password === formData.confirmPassword &&
      passwordStrength >= 60
    ) {
      alert("Your account has been created successfully!");
      navigate("/signin");
    } else {
      alert(
        "Please ensure the passwords match and meet the strength requirements."
      );
      setFormData({ ...formData, password: "", confirmPassword: "" });
    }
  };

  return (
    <div className="text-center">
      <nav className="navbar navbar-expand-md navbar-dark fixed-top">
        <div className="container-fluid">
          <a className="navbar-brand">
            Inventory System
          </a>
        </div>
      </nav>

      <div className="sign-up-container">
        <form className="form-signup" onSubmit={handleSubmit}>
          <h1 className="h3 mb-3 font-weight-normal form-signup-heading">
            Sign Up
          </h1>

          <div className="input-group mb-3">
            <span className="input-group-text">
              <User />
            </span>
            <input
              type="text"
              id="inputUsername"
              className="form-control"
              placeholder="Username"
              name="username"
              required
              autoFocus
              value={formData.username}
              onChange={handleInputChange}
            />
          </div>

          <div className="input-group mb-3">
            <span className="input-group-text">
              <Mail />
            </span>
            <input
              type="email"
              id="inputEmail"
              className="form-control"
              placeholder="Email address"
              name="email"
              required
              value={formData.email}
              onChange={handleInputChange}
            />
          </div>

          <div className="input-group mb-3">
            <span className="input-group-text">
              <Lock />
            </span>
            <input
              type="password"
              className="form-control"
              placeholder="Password"
              name="password"
              required
              value={formData.password}
              onChange={handleInputChange}
            />
          </div>

          <div className="input-group mb-3">
            <span className="input-group-text">
              <Lock />
            </span>
            <input
              type="password"
              id="inputConfirmPassword"
              className="form-control"
              placeholder="Confirm Password"
              name="confirmPassword"
              required
              value={formData.confirmPassword}
              onChange={handleInputChange}
            />
          </div>

          <button className="btn btn-lg btn-success btn-block" type="submit">
            Sign up
          </button>

          <p className="mt-3" onClick={() => navigate("/signin")}>
            Already have an account? <a href="">Sign In</a>
          </p>
        </form>
      </div>

      <div className="password-container">
        <div
          id="passwordStrength"
          className="progress "
          style={{ height: "8px", marginTop: "5px" }}
        >
          <div
            className={`progress-bar ${progressBarColor}`}
            role="progressbar"
            style={{ width: `${passwordStrength}%` }}
            aria-valuenow={passwordStrength}
            aria-valuemin="0"
            aria-valuemax="100"
          ></div>
        </div>

        <div className="password-explanation faq-link">
          <h3 className="password-explanation-heading">Password Strength</h3>
          <p id="passwordDescription">{passwordDescription}</p>
        </div>
      </div>
      <footer className="footer">
        <p>&copy; 2023 Inventory Management System. All rights reserved.</p>
      </footer>
    </div>
  );
}

export default Signup;
