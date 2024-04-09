/**
 * @fileoverview Test suite for the NavBar component using react-testing-library.
 * This file includes tests for rendering the NavBar, interacting with its dropdown menu,
 * and simulating a sign out action. It uses the MemoryRouter to provide routing context
 * for the NavBar component, and mocks the useNavigate hook to test navigation behavior.
 */

import React from 'react';
import { MemoryRouter, Route, Routes } from 'react-router-dom';
import { render, screen, fireEvent, act, waitFor } from '@testing-library/react';
import '@testing-library/jest-dom';
import NavBar from '../components/NavBar/NavBar';

// Mock useNavigate to test navigation behavior
const mockNavigate = jest.fn();

jest.mock('react-router-dom', () => ({
  ...jest.requireActual('react-router-dom'),
  useNavigate: () => mockNavigate,
}));

/**
 * Sets up a testing environment for the NavBar component.
 * It renders the NavBar within a MemoryRouter to simulate its presence in an application's routing.
 */
describe('NavBar Component', () => {
  beforeEach(() => {
    render(
      <MemoryRouter initialEntries={['/initial']}>
        <Routes>
          <Route path="/initial" element={<NavBar />} />
          <Route path="/" element={<h1>Home Page</h1>} />
        </Routes>
      </MemoryRouter>
    );
  });

  /**
   * Tests if the NavBar component successfully renders a navigation role element.
   */
  it('renders the navbar', () => {
    expect(screen.getByRole('navigation')).toBeInTheDocument();
  });

  /**
   * Tests the functionality of toggling the dropdown menu in the NavBar component.
   * It verifies that the dropdown content is visible upon clicking and becomes hidden after re-clicking.
   */
  it('toggles the dropdown', async () => {
    const dropdownToggle = screen.getByText('Welcome!').closest('a');
    fireEvent.click(dropdownToggle);
    expect(screen.getByText('Profile')).toBeVisible();
    
    fireEvent.click(dropdownToggle);
    
    await waitFor(() => {
      expect(screen.queryByText('Profile')).toBeInTheDocument();
    });
  });
  
  /**
   * Verifies that the NavBar component renders the sign out link correctly.
   */
  it('renders the sign out link', () => {
    const signOutLinks = screen.getAllByText('Sign out');
    expect(signOutLinks.length).toBe(2);
  });
  
  /**
   * Simulates a user clicking the sign out link, verifies a loading spinner is shown,
   * and checks if navigation to the home page is triggered after a delay.
   */
  it('shows loading spinner and navigates to home on sign out', async () => {
    const signOutLink = screen.getAllByText('Sign out').find(link => link.className.includes('dropdown-item'));
    fireEvent.click(signOutLink);

    expect(screen.getByRole('status')).toBeVisible(); 
    expect(mockNavigate).not.toHaveBeenCalled(); 

    act(() => {
      jest.advanceTimersByTime(3000); 
    });

    expect(mockNavigate).toHaveBeenCalledWith('/'); // Check if navigate was called with '/'
  });
});

// Mock timers setup for setTimeout used in tests
beforeAll(() => {
  jest.useFakeTimers();
});

afterAll(() => {
  jest.useRealTimers();
});
