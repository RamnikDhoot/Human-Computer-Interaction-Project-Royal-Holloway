/**
 * @fileoverview Test suite for the SideBar component using react-testing-library.
 * Tests the SideBar component for proper rendering of links, navigation functionality,
 * active link highlighting, and the addition of new reports through user interaction.
 * The component is rendered within a BrowserRouter to accurately test navigation links.
 */

import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import '@testing-library/jest-dom';
import userEvent from '@testing-library/user-event';
import { BrowserRouter } from 'react-router-dom';
import SideBar from '../components/SideBar';

/**
 * Helper function to render the SideBar component within a Router.
 * Necessary since the SideBar uses NavLink which requires a Router context.
 */
const renderSideBar = () => render(
  <BrowserRouter>
    <SideBar />
  </BrowserRouter>
);

describe('SideBar Component', () => {
  /**
   * Tests if the SideBar component renders correctly with the expected navigation links.
   */
  it('renders correctly', () => {
    renderSideBar();
    expect(screen.getByText('Dashboard')).toBeInTheDocument();
    expect(screen.getByText('Orders')).toBeInTheDocument();
  });

  /**
   * Verifies that the navigation links within the SideBar have correct href attributes,
   * ensuring the routing works as expected.
   */
  it('navigation links work correctly', () => {
    renderSideBar();
    expect(screen.getByText('Dashboard')).toHaveAttribute('href', '/dashboard');
  });

  /**
   * Placeholder for testing active link highlighting.
   * This test would typically check if the active link receives a specific styling
   * or class to indicate it is the current page.
   */
  it('active link is highlighted', async () => {
    // Test implementation would go here
  });

  /**
   * Tests the functionality of adding a new report through the SideBar.
   * Simulates a user interaction that triggers a prompt for the report name,
   * and verifies that a new report is added based on the user input.
   */
  it('adds a new report correctly', async () => {
    const promptSpy = jest.spyOn(window, 'prompt');
    promptSpy.mockImplementation(() => "Test Report");
  
    renderSideBar();
  
    userEvent.click(screen.getByLabelText("Add a new report"));
  
    await waitFor(() => {
      expect(promptSpy).toHaveBeenCalledWith("Please enter the name for the new report:", "New Report Name");
    });
  
    // Wait for the state update and re-render of the component
    await waitFor(() => {
      expect(screen.getByText("Test Report")).toBeInTheDocument();
    });
  
    // Clean up the mock to ensure it doesn't affect other tests
    promptSpy.mockRestore();
  });
  
});
