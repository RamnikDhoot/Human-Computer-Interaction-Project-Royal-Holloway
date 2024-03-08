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

/**
 * Test suite for the TwoFactorAuth component.
 * 
 * @group TwoFactorAuth
 */
describe('TwoFactorAuth Component', () => {
  beforeEach(() => {
    render(<TwoFactorAuth />);
  });

  /**
   * Test case to verify navigation to home on correct code input.
   * 
   * @test {TwoFactorAuth} Navigates to home on correct code
   */
  it('navigates to home on correct code', () => {
    fireEvent.change(screen.getByPlaceholderText('Authentication Code'), {
      target: { value: '1111' },
    });
    fireEvent.click(screen.getByText('Verify'));
    expect(mockNavigate).toHaveBeenCalledWith('/home');
  });

  /**
   * Test case to verify error display on incorrect code input.
   * 
   * @test {TwoFactorAuth} Shows error on incorrect code
   */
  it('shows error on incorrect code', () => {
    fireEvent.change(screen.getByPlaceholderText('Authentication Code'), {
      target: { value: 'wrong-code' },
    });
    fireEvent.click(screen.getByText('Verify'));
    expect(screen.getByRole('alert')).toHaveTextContent('Wrong code. Please try again.');
    expect(screen.getByPlaceholderText('Authentication Code')).toHaveValue('');
  });

  /**
   * Test case to verify code resend functionality and countdown.
   * 
   * @test {TwoFactorAuth} Resends the code and starts countdown
   */
  it('resends the code and starts countdown', async () => {
    fireEvent.click(screen.getByText('Resend code'));
    expect(await screen.findByText(/Please wait 30 seconds./)).toBeInTheDocument();
    
    // Simulating countdown
    act(() => {
      jest.advanceTimersByTime(3000); 
    });

    expect(await screen.findByText(/Please wait 27 seconds./)).toBeInTheDocument();
  });

  /**
   * Test case to verify microphone toggle functionality.
   * 
   * @test {TwoFactorAuth} Toggles microphone on button click
   */
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
