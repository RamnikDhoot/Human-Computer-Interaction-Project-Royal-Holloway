import React, { useState } from "react";
import { useNavigate } from "react-router-dom"; 
import "/src/assets/CSS/Signin.css";
import { User, Lock, Eye, EyeOff, Facebook } from "react-feather";
import { FaFacebookF, FaGoogle } from "react-icons/fa"; //react-feather doesn't have Facebook and google icons

function SignIn() {
  const navigate = useNavigate(); 

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [passwordVisible, setPasswordVisible] = useState(false);

  const validateCredentials = (e) => {
    e.preventDefault(); // Prevent default form submission
    if (username === "admin" && password === "admin") {
      navigate("/2fa"); 
    } else {
      alert("Invalid username or password");
      setUsername(""); // Clear field username and password
      setPassword(""); 
    }
  };

  const togglePasswordVisibility = () => {
    setPasswordVisible(!passwordVisible);
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

      <div className="sign-in-container">
        <form className="form-signin" onSubmit={validateCredentials}>
          <h1 className="h3 mb-3 font-weight-normal form-signin-heading">
            Sign In
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
              required
              autoFocus
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />
          </div>

          <div className="input-group mb-3">
            <span className="input-group-text">
              <Lock />
            </span>
            <input
              type={passwordVisible ? "text" : "password"}
              id="inputPassword"
              className="form-control"
              placeholder="Password"
              required
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
            <button
              className="btn btn-outline-secondary"
              type="button"
              onClick={togglePasswordVisibility}
            >
              {passwordVisible ? <EyeOff /> : <Eye />}
            </button>
          </div>

          <button className="btn btn-lg btn-primary btn-block" type="submit">
            Sign in
          </button>
          <p className="mt-3">
            Don't have an account? <a href="/signup">Sign Up</a>
          </p>
          <div className="social-signin-buttons">
            <a
              href="https://en-gb.facebook.com/"
              className="btn btn-outline-primary"
            >
              <FaFacebookF /> Facebook
            </a>
            <a
              href="https://accounts.google.com/signup"
              className="btn btn-outline-danger"
            >
              <FaGoogle /> Google
            </a>
          </div>
        </form>
      </div>
      <footer className="footer">
        <p>&copy; 2023 Inventory Management System. All rights reserved.</p>
      </footer>
    </div>
  );
}

export default SignIn;
