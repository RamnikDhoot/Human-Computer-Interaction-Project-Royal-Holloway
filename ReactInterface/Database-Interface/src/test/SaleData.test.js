import React from 'react';
import { render, screen } from '@testing-library/react';
import SaleData from '../pages/Sale data';
import { Doughnut, Bar, Line, Pie } from 'react-chartjs-2';

// Mock the chart components from react-chartjs-2
jest.mock('react-chartjs-2', () => ({
  Doughnut: () => <div data-testid="doughnut-chart"></div>,
  Bar: () => <div data-testid="bar-chart"></div>,
  Line: () => <div data-testid="line-chart"></div>,
  Pie: () => <div data-testid="pie-chart"></div>,
}));

describe('SaleData', () => {
  it('renders the chart components', () => {
    render(<SaleData />);
    
    expect(screen.getByTestId('doughnut-chart')).toBeInTheDocument();
    expect(screen.getByTestId('bar-chart')).toBeInTheDocument();
    expect(screen.getByTestId('line-chart')).toBeInTheDocument();
    expect(screen.getByTestId('pie-chart')).toBeInTheDocument();
  });

  it('renders the sales data table with expected rows', () => {
    render(<SaleData />);
    const tableRows = screen.getAllByRole('row');
    expect(tableRows).toHaveLength(9); 
  });
  
});
