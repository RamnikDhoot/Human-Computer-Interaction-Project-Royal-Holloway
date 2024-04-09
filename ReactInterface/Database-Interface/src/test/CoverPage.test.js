import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import { MemoryRouter, Route, Routes } from 'react-router-dom';
import CoverPage from '../pages/CoverPage';
import '@testing-library/jest-dom';

/**
 * Helper function to render a component with a specified route.
 * 
 * @param {JSX.Element} ui The JSX element to render.
 * @param {object} options Options for rendering.
 * @param {string} options.route The route to render the component at.
 * @returns {RenderResult} The rendered component.
 */
function renderWithRouter(ui, { route = '/' } = {}) {
  window.history.pushState({}, 'Test page', route);
  return render(ui, { wrapper: MemoryRouter });
}

/**
 * Test suite for the CoverPage component.
 * 
 * @group CoverPage
 */
describe('CoverPage', () => {
  /**
   * Test case to verify rendering of the CoverPage component.
   * 
   * @test {CoverPage} Renders the CoverPage component correctly
   */
  it('renders the CoverPage component correctly', () => {
    render( <MemoryRouter>
        <CoverPage />
      </MemoryRouter>);
    expect(screen.getByText('Welcome to DBMS Inventory Management')).toBeInTheDocument();
    expect(screen.getByText('Login')).toBeInTheDocument();
    expect(screen.getByText('Create Account')).toBeInTheDocument();
  });  

  /**
   * Test case to verify navigation to signin page on login button click.
   * 
   * @test {CoverPage} Navigates to signin page on login button click
   */
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

  /**
   * Test case to verify navigation to signup page on create account button click.
   * 
   * @test {CoverPage} Navigates to signup page on create account button click
   */
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
