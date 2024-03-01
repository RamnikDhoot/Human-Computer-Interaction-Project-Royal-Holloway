import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; 
import { Key, Mic } from 'react-feather';
import 'bootstrap/dist/css/bootstrap.min.css';
import "/src/assets/CSS/Signin.css";

function TwoFactorAuth() {
  const navigate = useNavigate(); 
  const [code, setCode] = useState('');
  const [isListening, setIsListening] = useState(false);
  const [isResending, setIsResending] = useState(false);
  const [countdown, setCountdown] = useState(30);
  const [error, setError] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    if (code === '1111') {
      navigate('/home'); 
    } else {
      setError('Wrong code. Please try again.'); 
      setCode(''); 
    }
  };

  const resendCode = () => {
    setIsResending(true);
    let timer = 30;
    setCountdown(timer);

    const interval = setInterval(() => {
      timer -= 1;
      setCountdown(timer);

      if (timer === 0) {
        clearInterval(interval);
        setIsResending(false);
        setError(''); 
      }
    }, 1000);
  };

  const toggleMicrophone = () => {
    setIsListening(!isListening);
  };

  return (
    <div className="text-center">
      <nav className="navbar navbar-expand-md navbar-dark fixed-top">
        <div className="container-fluid">
          <a className="navbar-brand" href="#">
            Inventory System
          </a>
        </div>
      </nav>

      <div className="two-factor-container">
        <form className="form-2fa" onSubmit={handleSubmit}>
          <h1 className="h3 mb-3 font-weight-normal form-2fa-heading">
            Two-Factor Authentication
          </h1>
          <p className="text-muted">
            For your security, we require two-factor authentication. Please
            enter the code sent to your device.
          </p>

          <div className="input-group mb-3">
            <span className="input-group-text">
              <Key />
            </span>
            <input
              type="text"
              id="inputCode"
              className="form-control"
              placeholder="Authentication Code"
              required
              autoFocus
              value={code}
              onChange={(e) => setCode(e.target.value)}
            />
          </div>

          <div className="form-check mb-3">
            <input type="checkbox" />
            <label className="form-check-label" htmlFor="rememberDevice">
              Remember this device
            </label>
          </div>

          <button className="btn btn-lg btn-primary btn-block" type="submit">
            Verify
          </button>

          <div className="resend-code mt-3">
            {isResending ? (
              <p>Please wait {countdown} seconds.</p>
            ) : (
              <p>
                Didn't receive a code?{" "}
                <button
                  className="btn btn-link"
                  onClick={resendCode}
                  disabled={isResending}
                >
                  Resend code
                </button>
              </p>
            )}
          </div>
        </form>

        {error && <div className="alert alert-danger" role="alert">{error}</div>}


        <div className="voice-auth-option mt-4">
          <p>Or, authenticate using your voice:</p>
          <button
            className={`btn ${
              isListening ? "btn-listening" : "btn-outline-secondary"
            }`}
            id="micButton"
            onClick={toggleMicrophone}
            title="Start Voice Authentication"
          >
            {isListening ? <Mic className="listening" /> : <Mic />}
          </button>
        </div>
      </div>

      <footer className="footer">
        <p>&copy; 2023 Inventory Management System. All rights reserved.</p>
      </footer>
    </div>
  );
}

export default TwoFactorAuth;
