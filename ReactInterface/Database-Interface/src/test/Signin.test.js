/**
 * @fileoverview Test suite for the SignIn component using react-testing-library.
 * Tests include interaction with username and password fields, navigating to a 2FA page upon
 * correct credentials, showing an alert on incorrect credentials, and toggling the visibility of the password.
 * Mocks are used for react-router-dom's useNavigate to test navigation, and window.alert to test alert behavior.
 */

import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import '@testing-library/jest-dom';
import SignIn from "../pages/Login pages/SignIn.jsx";

/**
 * Mocks the useNavigate function from react-router-dom to simulate navigation in tests.
 * This allows us to verify that the correct navigation action is taken without affecting the browser's URL.
 */
const mockNavigate = jest.fn();
jest.mock('react-router-dom', () => ({
  ...jest.requireActual('react-router-dom'),
  useNavigate: () => mockNavigate,
}));

/**
 * Mocks the window.alert function to prevent it from showing alerts during tests.
 * This allows us to verify that an alert is called without disrupting the test runner.
 */
window.alert = jest.fn();

/**
 * Defines a suite of tests for the SignIn component to ensure it functions correctly.
 * Tests focus on user interactions with form inputs and validation of credentials.
 */
describe('SignIn Component', () => {
  /**
   * Re-renders the SignIn component before each test to ensure test isolation.
   */
  beforeEach(() => {
    render(<SignIn />);
  });

  /**
   * Tests if user input is correctly updated in the username and password fields.
   */
  it('updates username and password fields', () => {
    fireEvent.change(screen.getByPlaceholderText('Username'), {
      target: { value: 'testuser' },
    });
    fireEvent.change(screen.getByPlaceholderText('Password'), {
      target: { value: 'password' },
    });
    
    expect(screen.getByPlaceholderText('Username')).toHaveValue('testuser');
    expect(screen.getByPlaceholderText('Password')).toHaveValue('password');
  });

  /**
   * Tests if the application navigates to the 2FA page upon entering correct credentials.
   */
  it('navigates to 2fa on correct credentials', () => {
    fireEvent.change(screen.getByPlaceholderText('Username'), {
      target: { value: 'admin' },
    });
    fireEvent.change(screen.getByPlaceholderText('Password'), {
      target: { value: 'admin' },
    });
    fireEvent.click(screen.getByText('Sign in'));

    expect(mockNavigate).toHaveBeenCalledWith('/2fa');
  });

  /**
   * Tests if an alert is shown and fields are cleared upon entering incorrect credentials.
   */
  it('shows alert and clears fields on incorrect credentials', () => {
    fireEvent.change(screen.getByPlaceholderText('Username'), {
      target: { value: 'wrong' },
    });
    fireEvent.change(screen.getByPlaceholderText('Password'), {
      target: { value: 'credentials' },
    });
    fireEvent.click(screen.getByText('Sign in'));

    expect(window.alert).toHaveBeenCalledWith('Invalid username or password');
    expect(screen.getByPlaceholderText('Username')).toHaveValue('');
    expect(screen.getByPlaceholderText('Password')).toHaveValue('');
  });

  /**
   * Tests if the password visibility toggle function works correctly, switching between hiding and showing the password.
   */
  it('toggles password visibility', () => {
    const passwordField = screen.getByPlaceholderText('Password');
    const toggleButton = screen.getByRole('button', { name: '' });

    expect(passwordField).toHaveAttribute('type', 'password');
    
    fireEvent.click(toggleButton);
    expect(passwordField).toHaveAttribute('type', 'text');
    
    fireEvent.click(toggleButton);
    expect(passwordField).toHaveAttribute('type', 'password');
  });
});
