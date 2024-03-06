import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import '@testing-library/jest-dom';
import userEvent from '@testing-library/user-event';
import { BrowserRouter } from 'react-router-dom';
import SideBar from '../components/SideBar';
import { waitFor } from '@testing-library/react';


// Helper function to render the component within a Router since it uses NavLink
const renderSideBar = () => render(
  <BrowserRouter>
    <SideBar />
  </BrowserRouter>
);

describe('SideBar Component', () => {
  it('renders correctly', () => {
    renderSideBar();
    expect(screen.getByText('Dashboard')).toBeInTheDocument();
    expect(screen.getByText('Orders')).toBeInTheDocument();
  });

  it('navigation links work correctly', () => {
    renderSideBar();
    expect(screen.getByText('Dashboard')).toHaveAttribute('href', '/dashboard');
  });

  it('active link is highlighted', async () => {
    renderSideBar();
  });

  it('adds a new report correctly', async () => {
    const promptSpy = jest.spyOn(window, 'prompt');
    promptSpy.mockImplementation(() => "Test Report");
  
    renderSideBar();
  
    userEvent.click(screen.getByLabelText("Add a new report"));
  
    await waitFor(() => {
      expect(promptSpy).toHaveBeenCalledWith("Please enter the name for the new report:", "New Report Name");
    });
  
    //wait for the state update and re-render of the component
    await waitFor(() => {
      expect(screen.getByText("Test Report")).toBeInTheDocument();
    });
  
    // Clean up the mock to ensure it doesn't affect other tests
    promptSpy.mockRestore();
  });
  
});
