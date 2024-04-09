/**
 * @fileoverview Test suite for the Signup component using react-testing-library.
 * Tests ensure proper handling of user input in form fields, form submission with valid data,
 * and appropriate feedback and actions on submission with invalid data. Mocks are employed
 * for react-router-dom's useNavigate to simulate navigation and for window.alert to capture alert messages.
 */

import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import '@testing-library/jest-dom';
import Signup from "../pages/Login pages/Signup.jsx";

/**
 * Mocks the useNavigate function from react-router-dom to test navigation without affecting the browser's URL.
 * This mock facilitates verification of navigation upon successful signup.
 */
const mockNavigate = jest.fn();
jest.mock('react-router-dom', () => ({
  ...jest.requireActual('react-router-dom'),
  useNavigate: () => mockNavigate,
}));

/**
 * Mocks the window.alert function to prevent displaying alerts during tests,
 * allowing for verification of alert messages without disrupting the test runner.
 */
window.alert = jest.fn();

/**
 * Defines a test suite for the Signup component, focusing on interaction with form fields,
 * validation of form submissions, and user feedback mechanisms.
 */
describe('Signup Component', () => {
  /**
   * Renders the Signup component before each test to ensure a clean state.
   */
  beforeEach(() => {
    render(<Signup />);
  });

  /**
   * Verifies that user input is correctly reflected in the username, email, password,
   * and confirm password fields.
   */
  it('updates input fields', () => {
    const usernameInput = screen.getByPlaceholderText('Username');
    const emailInput = screen.getByPlaceholderText('Email address');
    const passwordInput = screen.getByPlaceholderText('Password');
    const confirmPasswordInput = screen.getByPlaceholderText('Confirm Password');

    fireEvent.change(usernameInput, { target: { value: 'testuser' } });
    fireEvent.change(emailInput, { target: { value: 'testuser@example.com' } });
    fireEvent.change(passwordInput, { target: { value: 'Password123!' } });
    fireEvent.change(confirmPasswordInput, { target: { value: 'Password123!' } });

    expect(usernameInput).toHaveValue('testuser');
    expect(emailInput).toHaveValue('testuser@example.com');
    expect(passwordInput).toHaveValue('Password123!');
    expect(confirmPasswordInput).toHaveValue('Password123!');
  });

  // Placeholder for the unimplemented "shows password strength" test

  /**
   * Tests successful form submission with valid data, verifying that the appropriate success message is shown
   * and the user is navigated to the sign-in page.
   */
  it('submits form with valid data', () => {
    fireEvent.change(screen.getByPlaceholderText('Username'), { target: { value: 'admin' } });
    fireEvent.change(screen.getByPlaceholderText('Email address'), { target: { value: 'admin@example.com' } });
    fireEvent.change(screen.getByPlaceholderText('Password'), { target: { value: 'Password123!' } });
    fireEvent.change(screen.getByPlaceholderText('Confirm Password'), { target: { value: 'Password123!' } });
    fireEvent.click(screen.getByText('Sign up'));

    expect(window.alert).toHaveBeenCalledWith('Your account has been created successfully!');
    expect(mockNavigate).toHaveBeenCalledWith('/signin');
  });

  /**
   * Tests form submission with invalid data, ensuring that an appropriate error message is displayed
   * and the password fields are cleared to allow for correction by the user.
   */
  it('alerts and clears fields with invalid data', () => {
    fireEvent.change(screen.getByPlaceholderText('Username'), { target: { value: 'admin' } });
    fireEvent.change(screen.getByPlaceholderText('Email address'), { target: { value: 'admin@example.com' } });
    fireEvent.change(screen.getByPlaceholderText('Password'), { target: { value: 'Pass' } }); // Weak password
    fireEvent.change(screen.getByPlaceholderText('Confirm Password'), { target: { value: 'Pass' } });
    fireEvent.click(screen.getByText('Sign up'));

    expect(window.alert).toHaveBeenCalledWith('Please ensure the passwords match and meet the strength requirements.');
    expect(screen.getByPlaceholderText('Password')).toHaveValue('');
    expect(screen.getByPlaceholderText('Confirm Password')).toHaveValue('');
  });
});
