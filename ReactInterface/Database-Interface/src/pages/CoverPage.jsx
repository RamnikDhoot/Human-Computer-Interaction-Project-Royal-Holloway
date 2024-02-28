// CoverPage.jsx
import React, { useState } from "react";
import "/src/assets/CSS/main.css";
import Footer from "../components/Footer";

function CoverPage() {
  const [showModal, setShowModal] = useState(false);

  const navigateTo = (path) => {
    window.location.href = path;
  };

  const toggleModal = () => {
    setShowModal(!showModal);
  };

  return (
    <>
      <div className="text-center">
        <nav className="navbar navbar-expand-md navbar-dark fixed-top">
          <div className="container-fluid">
            <a className="navbar-brand" href="#">
              DBMS Inventory System
            </a>
            <button
              className="navbar-toggler"
              type="button"
              data-bs-toggle="collapse"
              data-bs-target="#navbarNav"
              aria-controls="navbarNav"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarNav">
              <ul className="navbar-nav ms-auto">
                <li className="nav-item">
                  <button className="nav-link" onClick={toggleModal}>
                    Help
                  </button>
                </li>
              </ul>
            </div>
          </div>
        </nav>

        <div className="cover-container d-flex flex-column h-100 p-3 mx-auto">
          <main className="my-auto">
            <h1>Welcome to DBMS Inventory Management</h1>
            <p>
              Efficiently manage your warehouse inventory with our user-friendly
              system powered by advanced database management technologies.
            </p>
            <div className="mb-2">
              <button
                className="btn btn-custom btn-primary-custom"
                onClick={() => navigateTo("/signin")}
              >
                Login
              </button>
            </div>
            <div className="mb-2">
              <button
                className="btn btn-custom btn-secondary-custom"
                onClick={() => navigateTo("/signup")}
              >
                Create Account
              </button>
            </div>
          </main>

          <div className="row text-start">
            <div className="col-md-4 feature">
              <h3>Real-Time Data</h3>
              <p>
                Access and manage your inventory in real-time, ensuring data
                accuracy and efficiency.
              </p>
            </div>
            <div className="col-md-4 feature">
              <h3>Secure Access</h3>
              <p>
                Advanced security protocols to protect your data and provide
                secure access from anywhere.
              </p>
            </div>
            <div className="col-md-4 feature">
              <h3>Scalability</h3>
              <p>
                Easily scalable solutions to grow with your business, handling
                increasing data volumes effortlessly.
              </p>
            </div>
          </div>

          <div className="testimonial">
            <blockquote className="blockquote">
              <p className="mb-0">
                "This system has revolutionized how we manage our inventory,
                providing real-time insights and improving overall efficiency."
              </p>
              <footer className="blockquote-footer">
                Jane Doe, <cite title="Source Title">Warehouse Manager</cite>
              </footer>
            </blockquote>
          </div>
          <div className="testimonial">
            <blockquote className="blockquote">
              <p className="mb-0">
                "The security features give us peace of mind, knowing our data
                is protected with the best protocols in the industry."
              </p>
              <footer className="blockquote-footer">
                John Smith, <cite title="Source Title">IT Director</cite>
              </footer>
            </blockquote>
          </div>

          <div className="contact-form">
            <h2>Contact Us</h2>
            <input
              type="text"
              className="form-control"
              placeholder="Your Name"
            ></input>
            <input
              type="email"
              className="form-control"
              placeholder="Your Email"
            ></input>
            <textarea
              className="form-control"
              rows="3"
              placeholder="Your Message"
            ></textarea>
            <button className="btn btn-primary">Send Message</button>
          </div>
        </div>

        {showModal && (
          <div
            className="modal show"
            style={{ display: "block" }}
            id="helpModal"
            tabIndex="-1"
            aria-labelledby="helpModalLabel"
            aria-hidden="true"
          >
            <div className="modal-dialog">
              <div className="modal-content">
                <div className="modal-header">
                  <h5 className="modal-title" id="helpModalLabel">
                    Helpful Information
                  </h5>
                  <button
                    type="button"
                    className="btn-close"
                    onClick={toggleModal}
                  ></button>
                </div>
                <div className="modal-body">
                  <p>
                    Welcome to our DBMS Inventory Management System! Here are
                    some helpful tips:
                  </p>
                  <ul>
                    <li>
                      To add a new item to your inventory, click on the "Create
                      Item" button in the dashboard.
                    </li>
                    <li>
                      You can edit item details, such as name and photo, by
                      clicking on the item and selecting the "Edit" option.
                    </li>
                    <li>
                      If you need assistance, please contact our support team at
                      support@inventorysystem.com.
                    </li>
                  </ul>
                  <p>Thank you for using our system!</p>
                </div>
                <div className="modal-footer">
                  <button
                    type="button"
                    className="btn btn-secondary"
                    onClick={() => setShowModal(false)}
                  >
                    Close
                  </button>
                </div>
              </div>
            </div>
          </div>
        )}

        {/* Backdrop for modal */}
        {showModal && <div className="modal-backdrop show"></div>}
      </div>
      <Footer />
    </>
  );
}

export default CoverPage;
