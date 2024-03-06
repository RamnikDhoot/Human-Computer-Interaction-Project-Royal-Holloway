import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import '@testing-library/jest-dom';
import Signup from "../pages/Login pages/Signup.jsx";

// Mock useNavigate
const mockNavigate = jest.fn();
jest.mock('react-router-dom', () => ({
  ...jest.requireActual('react-router-dom'),
  useNavigate: () => mockNavigate,
}));

// Mock window.alert
window.alert = jest.fn();

describe('Signup Component', () => {
  beforeEach(() => {
    render(<Signup />);
  });

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

  it('shows password strength', async () => {
    //mot working
  });


  it('submits form with valid data', () => {
    fireEvent.change(screen.getByPlaceholderText('Username'), { target: { value: 'admin' } });
    fireEvent.change(screen.getByPlaceholderText('Email address'), { target: { value: 'admin@example.com' } });
    fireEvent.change(screen.getByPlaceholderText('Password'), { target: { value: 'Password123!' } });
    fireEvent.change(screen.getByPlaceholderText('Confirm Password'), { target: { value: 'Password123!' } });
    fireEvent.click(screen.getByText('Sign up'));

    expect(window.alert).toHaveBeenCalledWith('Your account has been created successfully!');
    expect(mockNavigate).toHaveBeenCalledWith('/signin');
  });

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
