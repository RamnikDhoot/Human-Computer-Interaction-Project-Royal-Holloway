import React from 'react';
import { render, screen, fireEvent, act } from '@testing-library/react';
import '@testing-library/jest-dom';
import userEvent from '@testing-library/user-event';
import TwoFactorAuth from '../pages/Login pages/2FA';

// Mock useNavigate
const mockNavigate = jest.fn();

jest.mock('react-router-dom', () => ({
  ...jest.requireActual('react-router-dom'),
  useNavigate: () => mockNavigate,
}));

describe('TwoFactorAuth Component', () => {
  beforeEach(() => {
    render(<TwoFactorAuth />);
  });

  it('navigates to home on correct code', () => {
    fireEvent.change(screen.getByPlaceholderText('Authentication Code'), {
      target: { value: '1111' },
    });
    fireEvent.click(screen.getByText('Verify'));
    expect(mockNavigate).toHaveBeenCalledWith('/home');
  });

  it('shows error on incorrect code', () => {
    fireEvent.change(screen.getByPlaceholderText('Authentication Code'), {
      target: { value: 'wrong-code' },
    });
    fireEvent.click(screen.getByText('Verify'));
    expect(screen.getByRole('alert')).toHaveTextContent('Wrong code. Please try again.');
    expect(screen.getByPlaceholderText('Authentication Code')).toHaveValue('');
  });

  it('resends the code and starts countdown', async () => {
    fireEvent.click(screen.getByText('Resend code'));
    expect(await screen.findByText(/Please wait 30 seconds./)).toBeInTheDocument();
    
    // Simulating countdown
    act(() => {
      jest.advanceTimersByTime(3000); 
    });

    expect(await screen.findByText(/Please wait 27 seconds./)).toBeInTheDocument();
  });

  it('toggles microphone on button click', () => {
    const micButton = screen.getByTitle('Start Voice Authentication');
    fireEvent.click(micButton);
    expect(micButton).toHaveClass('btn-listening');
    fireEvent.click(micButton);
    expect(micButton).not.toHaveClass('btn-listening');
  });
});

// Use fake timers for setInterval in resendCode
beforeAll(() => {
  jest.useFakeTimers();
});

afterAll(() => {
  jest.useRealTimers();
});
