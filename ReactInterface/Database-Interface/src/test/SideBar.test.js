/**
 * @fileoverview Test suite for the SideBar component using React Testing Library.
 * This suite checks for correct rendering of navigation links, navigation functionality,
 * active link highlighting, and the addition of new reports through user interaction.
 * The SideBar component is wrapped inside a BrowserRouter to accurately test navigation links.
 */

import React from 'react';
import { render, screen, waitFor } from '@testing-library/react';
import '@testing-library/jest-dom';
import userEvent from '@testing-library/user-event';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import SideBar from '../components/SideBar';

/**
 * Renders the SideBar component within a BrowserRouter.
 * This setup is necessary to test components that utilize react-router's NavLink or useRouteMatch.
 * @returns {void} Nothing.
 */
const renderSideBar = () => render(
  <BrowserRouter>
    <Routes>
      <Route path="*" element={<SideBar />} />
    </Routes>
  </BrowserRouter>
);

describe('SideBar Component', () => {
  /**
   * Verifies if the SideBar component renders correctly with expected navigation links.
   * Checks for the presence of "Dashboard" and "Orders" links in the document.
   */
  it('renders correctly', () => {
    renderSideBar();
    expect(screen.getByText('Dashboard')).toBeInTheDocument();
    expect(screen.getByText('Orders')).toBeInTheDocument();
  });

  /**
   * Ensures that the navigation links within the SideBar component have correct href attributes.
   * This test verifies if clicking on the "Dashboard" link will navigate to the "/dashboard" path.
   */
  it('navigation links work correctly', () => {
    renderSideBar();
    expect(screen.getByText('Dashboard')).toHaveAttribute('href', '/dashboard');
  });

  /**
   * Tests if the active link is highlighted by checking the applied class.
   * This simulates clicking on the "Dashboard" link and then verifies if it receives an 'active' class.
   */
  it('active link is highlighted', async () => {
    renderSideBar();
    userEvent.click(screen.getByText('Dashboard'));
    await waitFor(() => {
      expect(screen.getByText('Dashboard').closest('a')).toHaveClass('active');
    });
  });

  /**
   * Tests the functionality for adding a new report through the SideBar.
   * This simulates user interaction that triggers a prompt for the report name,
   * then verifies that a new report link labeled "Test Report" is added to the document.
   */
  it('adds a new report correctly', async () => {
    window.prompt = jest.fn().mockImplementation(() => "Test Report");
    renderSideBar();
  
    userEvent.click(screen.getByLabelText("Add a new report"));
  
    await waitFor(() => {
      expect(window.prompt).toHaveBeenCalledWith("Please enter the name for the new report:", "New Report Name");
    });
  
    await waitFor(() => {
      expect(screen.getByText("Test Report")).toBeInTheDocument();
    });
  });
});
