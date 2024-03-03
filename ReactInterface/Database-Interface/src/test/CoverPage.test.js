import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import { MemoryRouter, Route, Routes } from 'react-router-dom';
import CoverPage from '../pages/CoverPage';
import '@testing-library/jest-dom';

// A helper function to wrap CoverPage with MemoryRouter for testing navigation
function renderWithRouter(ui, { route = '/' } = {}) {
  window.history.pushState({}, 'Test page', route);
  return render(ui, { wrapper: MemoryRouter });
}

describe('CoverPage', () => {
  it('renders the CoverPage component correctly', () => {
    render( <MemoryRouter>
        <CoverPage />
      </MemoryRouter>);
    expect(screen.getByText('Welcome to DBMS Inventory Management')).toBeInTheDocument();
    expect(screen.getByText('Login')).toBeInTheDocument();
    expect(screen.getByText('Create Account')).toBeInTheDocument();
  });  

  it('navigates to signin page on login button click', () => {
    const { getByText } = renderWithRouter(
      <Routes>
        <Route path="/" element={<CoverPage />} />
        <Route path="/signin" element={<div>Signin Page</div>} />
      </Routes>
    );
    fireEvent.click(getByText('Login'));
    expect(screen.getByText('Signin Page')).toBeInTheDocument();
  });

  it('navigates to signup page on create account button click', () => {
    const { getByText } = renderWithRouter(
      <Routes>
        <Route path="/" element={<CoverPage />} />
        <Route path="/signup" element={<div>Signup Page</div>} />
      </Routes>
    );
    fireEvent.click(getByText('Create Account'));
    expect(screen.getByText('Signup Page')).toBeInTheDocument();
  });
});
