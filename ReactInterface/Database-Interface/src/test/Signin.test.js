import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import '@testing-library/jest-dom';
import SignIn from "../pages/Login pages/SignIn.jsx";

// Mock useNavigate
const mockNavigate = jest.fn();
jest.mock('react-router-dom', () => ({
  ...jest.requireActual('react-router-dom'),
  useNavigate: () => mockNavigate,
}));

// Mock window.alert
window.alert = jest.fn();

describe('SignIn Component', () => {
  beforeEach(() => {
    render(<SignIn />);
  });

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
