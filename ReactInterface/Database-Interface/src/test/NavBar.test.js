import React from 'react';
import { MemoryRouter, Route, Routes } from 'react-router-dom';
import { render, screen, fireEvent, act } from '@testing-library/react';
import '@testing-library/jest-dom';
import NavBar from '../components/NavBar/NavBar';
import { waitFor } from '@testing-library/react';


// Mock useNavigate
const mockNavigate = jest.fn();

jest.mock('react-router-dom', () => ({
  ...jest.requireActual('react-router-dom'),
  useNavigate: () => mockNavigate,
}));

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

  it('renders the navbar', () => {
    expect(screen.getByRole('navigation')).toBeInTheDocument();
  });

  it('toggles the dropdown', async () => {
    const dropdownToggle = screen.getByText('Welcome!').closest('a');
    fireEvent.click(dropdownToggle);
    expect(screen.getByText('Profile')).toBeVisible();
    
    fireEvent.click(dropdownToggle);
    
    await waitFor(() => {
      expect(screen.queryByText('Profile')).toBeInTheDocument();
    });
  });
  

  it('renders the sign out link', () => {
    const signOutLinks = screen.getAllByText('Sign out');
    expect(signOutLinks.length).toBe(2);
  });
  

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

// Use fake timers for setTimeout
beforeAll(() => {
  jest.useFakeTimers();
});

afterAll(() => {
  jest.useRealTimers();
});
