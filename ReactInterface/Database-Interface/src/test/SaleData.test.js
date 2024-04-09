/**
 * @fileoverview Test suite for the SaleData component using react-testing-library.
 * This file includes tests for verifying the rendering of various chart components
 * (Doughnut, Bar, Line, Pie) using mocked chart components from react-chartjs-2 library,
 * and tests for the presence and correctness of data in the sales data table.
 */

import React from 'react';
import { render, screen } from '@testing-library/react';
import SaleData from '../pages/Sale data';
import { Doughnut, Bar, Line, Pie } from 'react-chartjs-2';

/**
 * Mocks the chart components from the react-chartjs-2 library to test their rendering within the SaleData component.
 * Each chart component is replaced with a simple div that has a unique data-testid attribute for easy identification.
 */
jest.mock('react-chartjs-2', () => ({
  Doughnut: () => <div data-testid="doughnut-chart"></div>,
  Bar: () => <div data-testid="bar-chart"></div>,
  Line: () => <div data-testid="line-chart"></div>,
  Pie: () => <div data-testid="pie-chart"></div>,
}));

/**
 * Defines a test suite for the SaleData component, focusing on its ability to render chart components
 * and display a sales data table with the expected number of rows.
 */
describe('SaleData', () => {
  /**
   * Tests if the SaleData component correctly renders the four chart components (Doughnut, Bar, Line, Pie)
   * by checking for the presence of each chart's mock representation.
   */
  it('renders the chart components', () => {
    render(<SaleData />);
    
    expect(screen.getByTestId('doughnut-chart')).toBeInTheDocument();
    expect(screen.getByTestId('bar-chart')).toBeInTheDocument();
    expect(screen.getByTestId('line-chart')).toBeInTheDocument();
    expect(screen.getByTestId('pie-chart')).toBeInTheDocument();
  });

  /**
   * Tests if the SaleData component renders a sales data table with the expected number of rows,
   * verifying the component's ability to display sales data correctly.
   */
  it('renders the sales data table with expected rows', () => {
    render(<SaleData />);
    const tableRows = screen.getAllByRole('row');
    expect(tableRows).toHaveLength(9); // Expected number of rows in the sales data table
  });
  
});
